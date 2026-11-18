"""Ponto de entrada e interface gráfica (Tkinter) do extrator de documentos de auditoria."""

import json
from pathlib import Path
import threading
import tkinter as tk
from tkinter import filedialog, messagebox, scrolledtext, ttk

from cliente_ollama import consultar_ollama, detectar_servidor_ativo, salvar_resultado_json
from config import MODELOS_DISPONIVEIS
from leitor_documentos import extrair_textos_multiplos_documentos
from prompts import TipoDocumento, listar_tipos_disponiveis, montar_prompt_extracao
from config import obter_pasta_downloads

class AplicacaoAuditoria(tk.Tk):
    """Janela principal para seleção de parâmetros, execução da análise e visualização do JSON."""

    # Construtor
    def __init__(self) -> None:
        super().__init__()
        self.title("Extrator de Documentos de Auditoria (Ollama)")
        self.geometry("820x640") # Tamanho inicial
        self.minsize(720, 540)  # Tamanho mínimo

        # Mapeia o texto amigável exibido no Combobox apenas para tipos com prompt .txt disponível
        self._mapa_rotulo_para_tipo: dict[str, TipoDocumento] = {
            tipo.value: tipo for tipo in listar_tipos_disponiveis()
        }

        self._var_tipo_doc = tk.StringVar()
        self._var_modelo = tk.StringVar()
        self._caminhos_arquivos: list[Path] = []
        self._var_status = tk.StringVar(value="Pronto para iniciar.")

        self._construir_interface()
        self._preencher_valores_iniciais()

    def _construir_interface(self) -> None:
        """Monta os componentes visuais da janela."""
        quadro_principal = ttk.Frame(self, padding=16)
        quadro_principal.pack(fill=tk.BOTH, expand=True)

        # --- Bloco 1: Configurações de Extração ---
        quadro_config = ttk.LabelFrame(quadro_principal, text="Parâmetros da Extração", padding=12)
        quadro_config.pack(fill=tk.X, pady=(0, 12))
        quadro_config.columnconfigure(1, weight=1)

        # Tipo de Documento (Enum TipoDocumento)
        ttk.Label(quadro_config, text="Tipo de Documento:").grid(
            row=0, column=0, sticky=tk.W, padx=(0, 8), pady=6
        )
        self._combo_tipo_doc = ttk.Combobox(
            quadro_config,
            textvariable=self._var_tipo_doc,
            values=list(self._mapa_rotulo_para_tipo.keys()),
            state="readonly",
        )
        self._combo_tipo_doc.grid(row=0, column=1, columnspan=2, sticky=tk.EW, pady=6)

        # Modelo Ollama
        ttk.Label(quadro_config, text="Modelo Ollama:").grid(
            row=1, column=0, sticky=tk.W, padx=(0, 8), pady=6
        )
        self._combo_modelo = ttk.Combobox(
            quadro_config,
            textvariable=self._var_modelo,
            values=MODELOS_DISPONIVEIS,
            state="readonly",
        )
        self._combo_modelo.grid(row=1, column=1, columnspan=2, sticky=tk.EW, pady=6)

        # Arquivos de Referência (.docx / .pdf)
        ttk.Label(quadro_config, text="Documentos (.docx/.pdf):").grid(
            row=2, column=0, sticky=tk.NW, padx=(0, 8), pady=6
        )
        self._lista_arquivos = tk.Listbox(quadro_config, height=4)
        self._lista_arquivos.grid(row=2, column=1, sticky=tk.EW, padx=(0, 8), pady=6)

        quadro_botoes_arquivo = ttk.Frame(quadro_config)
        quadro_botoes_arquivo.grid(row=2, column=2, sticky=tk.NE, pady=6)

        self._btn_adicionar = ttk.Button(
            quadro_botoes_arquivo,
            text="Adicionar Arquivos...",
            command=self._adicionar_arquivos,
        )
        self._btn_adicionar.pack(fill=tk.X, pady=(0, 4))

        self._btn_limpar = ttk.Button(
            quadro_botoes_arquivo,
            text="Limpar Lista",
            command=self._limpar_arquivos,
        )
        self._btn_limpar.pack(fill=tk.X)

        # --- Bloco 2: Ação e Progresso ---
        quadro_acoes = ttk.Frame(quadro_principal)
        quadro_acoes.pack(fill=tk.X, pady=(0, 12))

        self._btn_executar = ttk.Button(
            quadro_acoes,
            text="Processar e Salvar JSON em Downloads",
            command=self._iniciar_processamento,
        )
        self._btn_executar.pack(side=tk.LEFT)

        self._barra_progresso = ttk.Progressbar(quadro_acoes, mode="indeterminate", length=180)
        self._barra_progresso.pack(side=tk.RIGHT)

        # --- Bloco 3: Visualização do Resultado ---
        quadro_resultado = ttk.LabelFrame(
            quadro_principal, text="JSON Extraído (Pré-visualização)", padding=10
        )
        quadro_resultado.pack(fill=tk.BOTH, expand=True)

        self._area_json = scrolledtext.ScrolledText(
            quadro_resultado,
            wrap=tk.WORD,
            font=("Consolas", 10),
            state=tk.DISABLED,
        )
        self._area_json.pack(fill=tk.BOTH, expand=True)

        # --- Barra de Status Inferior ---
        barra_status = ttk.Label(
            quadro_principal,
            textvariable=self._var_status,
            relief=tk.SUNKEN,
            anchor=tk.W,
            padding=(6, 4),
        )
        barra_status.pack(fill=tk.X, pady=(10, 0))

    def _preencher_valores_iniciais(self) -> None:
        """Define as seleções padrão nos menus suspensos."""
        rotulos = list(self._mapa_rotulo_para_tipo.keys())
        if rotulos:
            self._var_tipo_doc.set(rotulos[0])
        if MODELOS_DISPONIVEIS:
            self._var_modelo.set(MODELOS_DISPONIVEIS[0])

    def _adicionar_arquivos(self) -> None:
        """Abre o seletor de múltiplos arquivos filtrado para .docx e .pdf."""
        caminhos = filedialog.askopenfilenames(
            title="Selecione os documentos de referência",
            filetypes=[
                ("Documentos Suportados", "*.docx *.pdf"),
                ("Documentos Word (*.docx)", "*.docx"),
                ("Documentos PDF (*.pdf)", "*.pdf"),
            ],
            initialdir=str(obter_pasta_downloads())
        )
        if not caminhos:
            return

        adicionados = 0
        for caminho_str in caminhos:
            caminho = Path(caminho_str)
            if caminho not in self._caminhos_arquivos:
                self._caminhos_arquivos.append(caminho)
                self._lista_arquivos.insert(tk.END, caminho.name)
                adicionados += 1

        total = len(self._caminhos_arquivos)
        self._var_status.set(f"{adicionados} arquivo(s) adicionado(s). Total na lista: {total}.")

    def _limpar_arquivos(self) -> None:
        """Remove todos os arquivos selecionados da lista."""
        self._caminhos_arquivos.clear()
        self._lista_arquivos.delete(0, tk.END)
        self._var_status.set("Lista de documentos limpa.")

    def _alternar_estado_controles(self, processando: bool) -> None:
        """Habilita ou desabilita botões durante a inferência para evitar cliques duplicados."""
        estado_botao = tk.DISABLED if processando else tk.NORMAL
        estado_combo = "disabled" if processando else "readonly"

        self._btn_executar.config(state=estado_botao)
        self._btn_adicionar.config(state=estado_botao)
        self._btn_limpar.config(state=estado_botao)
        self._combo_tipo_doc.config(state=estado_combo)
        self._combo_modelo.config(state=estado_combo)

        if processando:
            self._barra_progresso.start(12)
        else:
            self._barra_progresso.stop()

    def _exibir_json_na_tela(self, texto_json: str) -> None:
        """Atualiza a caixa de texto com o conteúdo JSON formatado."""
        self._area_json.config(state=tk.NORMAL)
        self._area_json.delete("1.0", tk.END)
        self._area_json.insert(tk.END, texto_json)
        self._area_json.config(state=tk.DISABLED)

    def _iniciar_processamento(self) -> None:
        """Valida as entradas e dispara a execução em uma thread separada."""
        rotulo_doc = self._var_tipo_doc.get()
        modelo = self._var_modelo.get()

        if not self._caminhos_arquivos:
            messagebox.showwarning(
                "Atenção",
                "Adicione ao menos um arquivo (.docx ou .pdf) antes de continuar.",
            )
            return

        tipo_documento = self._mapa_rotulo_para_tipo.get(rotulo_doc)
        if not tipo_documento:
            messagebox.showerror("Erro", "Tipo de documento inválido.")
            return

        self._alternar_estado_controles(processando=True)
        self._exibir_json_na_tela("")
        self._var_status.set(
            f"Iniciando extração de texto de {len(self._caminhos_arquivos)} documento(s)..."
        )

        thread = threading.Thread(
            target=self._executar_pipeline,
            args=(tipo_documento, modelo, list(self._caminhos_arquivos)),
            daemon=True,
        )
        thread.start()

    def _executar_pipeline(
        self,
        tipo_documento: TipoDocumento,
        modelo: str,
        caminhos_arquivos: list[Path],
    ) -> None:
        """Executa o fluxo completo em segundo plano: leitura -> prompt -> Ollama -> salvamento."""
        try:
            # 1. Extração e consolidação de texto (nativo + OCR se necessário)
            self.after(
                0,
                self._var_status.set,
                f"Extraindo texto de {len(caminhos_arquivos)} documento(s)...",
            )
            texto_documento = extrair_textos_multiplos_documentos(caminhos_arquivos)

            # 2. Montagem do prompt específico a partir do arquivo .txt correspondente
            self.after(0, self._var_status.set, "Localizando servidor Ollama ativo...")
            servidor = detectar_servidor_ativo()
            prompt_final = montar_prompt_extracao(texto_documento, tipo_documento)

            # 3. Consulta ao modelo no Ollama
            self.after(
                0,
                self._var_status.set,
                f"Processando com '{modelo}' em {servidor} (aguarde)...",
            )
            dados_extraidos = consultar_ollama(
                prompt_completo=prompt_final,
                modelo=modelo,
                url_servidor=servidor,
            )

            # 4. Salvamento automático na pasta Downloads
            caminho_json_salvo = salvar_resultado_json(
                dados_extraidos,
                tipo_documento.name.lower(),
            )
            json_formatado = json.dumps(dados_extraidos, indent=2, ensure_ascii=False)

            self.after(0, self._finalizar_com_sucesso, json_formatado, caminho_json_salvo)

        except Exception as erro:
            self.after(0, self._finalizar_com_erro, str(erro))

    def _finalizar_com_sucesso(self, json_formatado: str, caminho_salvo: Path) -> None:
        """Atualiza a interface após concluir o fluxo sem erros."""
        self._alternar_estado_controles(processando=False)
        self._exibir_json_na_tela(json_formatado)
        self._var_status.set(f"Concluído! Arquivo salvo em: {caminho_salvo}")
        messagebox.showinfo(
            "Extração Concluída",
            f"O JSON foi gerado e salvo com sucesso em:\n\n{caminho_salvo}",
        )

    def _finalizar_com_erro(self, mensagem_erro: str) -> None:
        """Restaura a interface e exibe o erro ocorrido."""
        self._alternar_estado_controles(processando=False)
        self._var_status.set("Falha durante o processamento.")
        messagebox.showerror("Erro na Extração", mensagem_erro)


def main() -> None:
    """Inicializa a aplicação gráfica."""
    app = AplicacaoAuditoria()
    app.mainloop()

if __name__ == "__main__":
    main()