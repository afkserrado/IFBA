# Extrator de Documentos de Auditoria

Ferramenta desktop em Python com interface gráfica (Tkinter) desenvolvida para analisar documentos institucionais e normativos de auditoria do Poder Judiciário, extrair informações relevantes e subsidiar a elaboração de seções técnicas (como a seção **Metodologia** do documento **Entendimento do Objeto**) utilizando Modelos de Linguagem de Grande Escala (LLMs) via Ollama.

---

## Funcionalidades

- **Interface gráfica intuitiva (Tkinter):** seleção do tipo de documento institucional, fonte do servidor Ollama, modelo de IA e arquivos de entrada.
- **Leitura de múltiplos documentos (`.docx` e `.pdf`):** extração consolidada de textos nativos (parágrafos e tabelas) com delimitação automática de fronteiras entre arquivos para rastreabilidade.
- **OCR local integrado:** reconhecimento óptico de caracteres automático para páginas de PDF escaneadas e imagens embutidas em arquivos Word (`.docx`).
- **Integração com servidores Ollama (Local e Remoto):** descoberta dinâmica dos modelos instalados no servidor local ou no servidor institucional do IFBA.
- **Ajuste de parâmetros de geração da IA:** controle de **Temperatura**, **Top-P** e **Top-K** diretamente na interface, com opção de restauração para os valores padrão.
- **Validação de fontes e geração estruturada:** verificação de conformidade documental e retorno estrito em formato JSON.
- **Pré-visualização e exportação automática:** exibição do JSON formatado na tela e salvamento automático na pasta `Downloads` do usuário com identificação do tipo documental e carimbo de data/hora (`timestamp`).

---

## Tecnologias utilizadas

- **Linguagem e Interface:**
  - Python 3.12+
  - Tkinter (`ttk`, `scrolledtext`, `filedialog`, `messagebox`)
- **Processamento de Documentos e OCR:**
  - `PyMuPDF` (`fitz`) — leitura e renderização de páginas PDF
  - `python-docx` — leitura de parágrafos e tabelas em arquivos `.docx`
  - `rapidocr-onnxruntime` — motor de OCR local baseado em ONNX Runtime
- **Inteligência Artificial e Comunicação HTTP:**
  - Ollama API (`/api/tags` e `/api/chat`)
  - `requests` e `urllib.request`
- **Infraestrutura e Containerização:**
  - Docker e Docker Compose (com suporte a redirecionamento gráfico X11 no Linux e WSLg no Windows)

---

## Pré-requisitos

### Para Execução Nativa
- **Python 3.12** ou superior
- Biblioteca gráfica **Tkinter** instalada no sistema operacional (ex.: pacote `python3-tk` em distribuições Linux baseadas em Debian/Ubuntu)
- Dependências listadas em `requirements.txt` instaladas no ambiente virtual (`.venv` no Linux ou `.venv-win` no Windows)
- Acesso a um servidor **Ollama** ativo:
  - **Local:** Ollama instalado e em execução em `http://localhost:11434`, **ou**
  - **Remoto (IFBA):** conexão de rede com acesso a `https://ollama-api.ifba.edu.br`

### Para Execução via Docker
- **Docker** e **Docker Compose** instalados
- **No Linux:** servidor gráfico X11 ativo e utilitário `xhost`
- **No Windows:** Docker Desktop com suporte a **WSLg** (Windows 11 / WSL 2)

---

## Instalação e execução

### 1. Execução Nativa (Ambiente Virtual Python)

#### Ativar o ambiente virtual
* **Linux:** `source .venv/bin/activate`
* **Windows:** `.\.venv-win\Scripts\Activate.ps1`

#### Executar o programa
* **Linux:** `python main.py`
* **Windows:** `python main.py`

---

### 2. Execução via Docker

#### Linux
```bash
xhost +local:docker                     # 1. Liberar acesso ao servidor gráfico X11 (uma vez por sessão)
docker compose build                    # 2. Construir a imagem (a cada edição de código ou prompts)
docker compose run --rm auditoria-app   # 3. Executar o programa
```

#### Windows
```bash
docker compose -f docker-compose.yml -f docker-compose.windows.yml build                  # 1. Construir a imagem (a cada edição de código ou prompts)
docker compose -f docker-compose.yml -f docker-compose.windows.yml run --rm auditoria-app  # 2. Executar o programa
```

---

## Configurações

As configurações centrais do sistema estão concentradas no módulo `config.py` e nos arquivos do Docker Compose.

### Fontes e Servidores Ollama

| Fonte | Endereço Padrão | Descrição |
|---|---|---|
| **Local** | `http://localhost:11434` (ou valor de `OLLAMA_HOST_URL`) | Instância local do Ollama na máquina do usuário ou no host do Docker |
| **IFBA (Remoto)** | `https://ollama-api.ifba.edu.br` | Servidor institucional remoto do IFBA |

### Parâmetros Padrão de IA (`config.py`)

| Parâmetro | Valor Padrão | Descrição |
|---|---|---|
| `TEMPERATURA_PADRAO` | `0.1` | Controla o grau de aleatoriedade das respostas (`0.0` a `1.0`) |
| `TOP_P_PADRAO` | `0.90` | Limita a seleção de tokens com base na probabilidade acumulada (`0.1` a `1.0`) |
| `TOP_K_PADRAO` | `40` | Limita a quantidade de tokens candidatos considerados a cada passo (`1` a `100`) |
| `CONTEXTO_PADRAO` (`num_ctx`) | `65536` | Tamanho da janela de contexto em tokens (entrada + saída) |
| `TIMEOUT_REQUISICAO` | `300` | Tempo limite (em segundos) aguardando a resposta da inferência (5 minutos) |

### Variáveis de Ambiente (Docker)

| Variável | Descrição |
|---|---|
| `OLLAMA_HOST_URL` | URL do servidor Ollama no host (configurada no Docker como `http://host.docker.internal:11434`) |
| `DISPLAY` | Identificador do servidor gráfico X11 repassado ao contêiner para renderizar o Tkinter |
| `WAYLAND_DISPLAY` / `XDG_RUNTIME_DIR` | Variáveis gráficas utilizadas no ambiente Windows (WSLg) via `docker-compose.windows.yml` |

---

## Estrutura do projeto

```text
projeto_auditoria/
├── docs/                           # Documentos normativos de referência em PDF
│   ├── Manual de auditoria Poder Judiciario.pdf
│   └── Resolucao CNJ 309-2020.pdf
├── prompts/                        # Mapeamento de tipos documentais e templates de prompt
│   ├── __init__.py                 # Enum TipoDocumento e funções de carregamento de prompts
│   └── entendimento_objeto.txt     # Prompt específico para o documento Entendimento do Objeto
├── cliente_ollama.py               # Comunicação HTTP em streaming com a API do Ollama e exportação JSON
├── config.py                       # Constantes, parâmetros padrão de IA e descoberta de modelos/servidores
├── leitor_documentos.py            # Extração de texto nativo e OCR local para arquivos .docx e .pdf
├── main.py                         # Ponto de entrada e interface gráfica (Tkinter)
├── requirements.txt                # Dependências Python do projeto
├── Dockerfile                      # Definição da imagem Docker (Python 3.12 + Tkinter + libs gráficas)
├── docker-compose.yml              # Configuração comum (build, nome, Ollama)
├── docker-compose.linux.yml        # Rede do host, X11 e volumes para Linux
├── docker-compose.windows.yml      # Sobrescrita de volumes e variáveis para ambiente Windows (WSLg)
└── README.md                       # Documentação do projeto
```

---

## Documentação e utilização

### Fluxo principal de utilização na interface

1. **Tipo de Documento:** selecione o tipo documental desejado no menu suspenso (inicialmente, **Entendimento do Objeto**). O sistema habilita automaticamente os tipos que possuem arquivo `.txt` correspondente no diretório `prompts/`.
2. **Fonte dos Modelos:** escolha entre **Local** ou **IFBA (Remoto)**. Ao selecionar a fonte, o sistema consulta o endpoint `/api/tags` do servidor e carrega a lista de modelos disponíveis.
3. **Modelo Ollama:** escolha o modelo de LLM que realizará a análise.
4. **Documentos (`.docx`/`.pdf`):** clique em **Adicionar Arquivos...** para selecionar um ou mais documentos (como os normativos na pasta `docs/`). Use **Limpar Lista** caso deseje reiniciar a seleção.
5. **Parâmetros de Geração:** ajuste, se necessário, os controles de **Temperatura**, **Top-P** e **Top-K**, ou clique em **Padrão** para restaurar os valores originais.
6. **Execução e Exportação:** clique em **Processar e Salvar JSON em Downloads**.
   - O sistema extrairá o texto dos arquivos (acionando o OCR automaticamente caso detecte páginas digitalizadas ou imagens embutidas).
   - O prompt estruturado será enviado ao Ollama e a resposta JSON será validada.
   - O resultado aparecerá na área **JSON Extraído (Pré-visualização)** e será salvo automaticamente em `~/Downloads` com o padrão de nome `<tipo_documento>_<AAAAMMDD_HHMMSS>.json` (ex.: `entendimento_objeto_20261121_003000.json`).

### Adição de novos tipos de documentos

Os tipos documentais são declarados no `Enum` `TipoDocumento` em `prompts/__init__.py`. Para habilitar um novo tipo na interface gráfica, basta criar o arquivo `<nome_do_enum_em_minusculas>.txt` dentro da pasta `prompts/` contendo as instruções de extração ou elaboração.