# Relatório Técnico Comparativo: Avaliação de Modelos de Linguagem (LLMs) e Perfis de Configuração na Elaboração da Metodologia de Auditoria Interna do Poder Judiciário

## 1. Introdução

Este relatório apresenta uma avaliação técnica, normativa, estatística descritiva e comparativa de cinco modelos de linguagem de grande porte (LLMs) submetidos a cinco perfis de configuração de geração, totalizando **25 combinações experimentais**. A tarefa avaliada consiste na elaboração estruturada, em formato JSON, da seção **"Metodologia"** do documento **"Entendimento do Objeto"** de auditoria interna, incluindo a etapa prévia de validação documental das fontes de referência.

Os modelos avaliados, conforme registrados exatamente nos metadados das execuções, são: `gemma4:e4b`, `gemma4:12b`, `gemma4:26b`, `qwen3.5:4b` e `qwen3.5:9b`. Cada modelo foi submetido aos perfis de configuração **Determinístico**, **Recomendado**, **Padrão atual**, **Moderado** e **Estresse**.

A finalidade desta comparação é distinguir, de maneira objetiva e rastreável: (i) o desempenho associado às características de cada modelo; (ii) os efeitos associados aos conjuntos de parâmetros utilizados; (iii) a qualidade técnica e normativa do conteúdo; (iv) o cumprimento estrito das instruções do prompt original; (v) a quantidade e a abrangência útil dos tópicos; e (vi) a velocidade de geração. Ao final, apresenta-se um sistema de pontuação de 0 a 4 pontos por aspecto (convertido para escala de 0 a 100) e os rankings das 25 combinações, dos cinco modelos e dos cinco perfis de configuração, visando subsidiar a escolha da configuração mais equilibrada especificamente para esta tarefa de auditoria governamental. 

## 2. Documentos e Metodologia

### 2.1. Inventário documental e limitação de acervo

A avaliação foi conduzida sobre os seguintes documentos efetivamente disponibilizados:  

- **Referência técnica:** _Manual de Auditoria do Poder Judiciário_ (CNJ, Brasília, 2023, 114 páginas no arquivo PDF).    
- **Referência normativa:** _Resolução CNJ nº 309, de 11 de março de 2020_ (texto compilado com alterações das Resoluções CNJ nº 422/2021 e nº 633/2025, 25 páginas no arquivo PDF).    
- **Resultados dos experimentos:** `Resultados dos testes.md`, contendo os 25 arquivos JSON completos produzidos pelas execuções dos modelos.    
- **Documento ausente:** o arquivo `Parâmetros de teste.md`, mencionado no escopo inicial da avaliação, **não foi disponibilizado** nos anexos nem nas fontes vinculadas. Em conformidade com o princípio de integridade da análise, não foram presumidas condições de hardware, infraestrutura de servidor ou metodologia de coleta que não constem nos metadados dos arquivos JSON efetivamente examinados.    

### 2.2. Prompt original submetido aos modelos

O prompt original utilizado nos experimentos atua como referência obrigatória e independente para a avaliação da aderência. Suas exigências estruturais e semânticas compreendem:  

1. **Papel e tarefa:** atuar como auditor interno sênior do Poder Judiciário para elaborar a lista de técnicas e procedimentos da seção _"Metodologia"_ do documento _"Entendimento do Objeto"_ com base no _Manual de Auditoria do Poder Judiciário_ e na _Resolução CNJ nº 309/2020_.    
2. **Validação de fontes (`Validacao_fontes`):** registrar `conformidade_fontes`, `fontes_esperadas_identificadas`, `fontes_ausentes` e `arquivos_inesperados_ou_incompativeis`, obedecendo à **regra de fidelidade** (produzir `Metodologia` apenas se ambas as fontes estiverem presentes; caso contrário, retornar `Metodologia: []`).    
3. **Preservação da lista-base:** manter integralmente os 6 tópicos institucionais fornecidos (_Levantamento e análise documental_; _Entrevistas com gestores e áreas envolvidas_; _Aplicação de questionários de maturidade_; _Testes de controles e validação de processos_; _Análise de riscos e identificação de não conformidades_; _Confronto das práticas institucionais com os critérios estabelecidos_).    
4. **Complementação normativa:** acrescentar técnicas específicas explicitamente previstas nos normativos fornecidos, evitando termos vagos como `"técnicas selecionadas"`.    
5. **Restrições de conteúdo:** não incluir etapas administrativas genéricas (cronograma, designação de equipe, estimativa de custos ou guarda de papéis de trabalho).    
6. **Redação:** tópicos curtos e nominais (entre 3 e 10 palavras), diretos, sem explicações longas após vírgulas.    
7. **Formato estrito:** retornar exclusivamente um JSON válido contendo apenas as chaves `"Validacao_fontes"` e `"Metodologia"`.    

### 2.3. Critérios de avaliação

A avaliação desdobra-se em quatro dimensões independentes: **Qualidade técnica e normativa (35%)**, **Aderência ao prompt original (35%)**, **Velocidade de geração (15%)** e **Quantidade e abrangência dos tópicos (15%)**. Os critérios complementares (precisão terminológica, aproveitamento em auditoria, consistência conceitual e organização) subsidiam qualitativamente as quatro dimensões sem gerar pontuação adicional duplicada.  

## 3. Caracterização dos Experimentos

### 3.1. Estrutura e inventário das execuções

A verificação integral de `Resultados dos testes.md` confirma a presença completa das **25 combinações previstas** (5 modelos × 5 perfis), sem arquivos ausentes ou duplicados, com exatamente **1 execução registrada por combinação**. Todas as execuções ocorreram em 24 de novembro de 2026, entre `20:35:17+00:00` e `21:11:24+00:00`, na seguinte ordem cronológica de blocos: **Padrão atual** (20:35–20:42), **Determinístico** (20:44–20:50), **Recomendado** (20:51–20:57), **Moderado** (20:58–21:04) e **Estresse** (21:05–21:11).
  

|**Perfil de Configuração**|**Temperatura**|**Top-P**|**Top-K**|**Execuções Registradas**|**Modelos Avaliados**|
|---|---|---|---|---|---|
|**Determinístico**|0,00|0,10|1|5|`gemma4:e4b`, `gemma4:12b`, `gemma4:26b`, `qwen3.5:4b`, `qwen3.5:9b`|
|**Recomendado**|0,10|0,75|20|5|`gemma4:e4b`, `gemma4:12b`, `gemma4:26b`, `qwen3.5:4b`, `qwen3.5:9b`|
|**Padrão atual**|0,10|0,90|40|5|`gemma4:e4b`, `gemma4:12b`, `gemma4:26b`, `qwen3.5:4b`, `qwen3.5:9b`|
|**Moderado**|0,35|0,85|40|5|`gemma4:e4b`, `gemma4:12b`, `gemma4:26b`, `qwen3.5:4b`, `qwen3.5:9b`|
|**Estresse**|0,80|0,95|80|5|`gemma4:e4b`, `gemma4:12b`, `gemma4:26b`, `qwen3.5:4b`, `qwen3.5:9b`|

### 3.2. Limitações metodológicas e inconsistências registradas

1. **Amostra unitária ($n = 1$):** como há apenas uma execução por combinação, os resultados constituem observações individuais. Não são formuladas inferências estatísticas sobre repetibilidade, variância intra-perfil ou estabilidade estocástica.    
2. **Variação conjunta de parâmetros:** temperatura, Top-P e Top-K variam simultaneamente entre os perfis, impossibilitando isolar o efeito causal de cada hiperparâmetro individualmente.    
3. **Divergência sistemática entre horários e `tempo_total_segundos`:** em **todas as 25 execuções**, o intervalo bruto entre `fim_requisicao` e `inicio_requisicao` supera o valor registrado em `tempo_total_segundos` (diferença entre 5,82 s no `gemma4:e4b`/Padrão atual e 26,10 s no `gemma4:26b`/Moderado). **Impacto:** isso indica que os timestamps abrangem etapas externas à métrica registrada em `tempo_total_segundos` (como pré-processamento, rede ou carregamento do modelo). Em respeito ao protocolo de avaliação, utilizou-se exclusivamente `tempo_total_segundos` como métrica principal, sem substituições.    
4. **Campo `Metadados_requisicao`:** presente nos 25 arquivos JSON como registro de controle do experimento, não sendo contabilizado como erro de formato produzido pelos modelos.    

## 4. Análise Comparativa da Velocidade

### 4.1. Tempos registrados (`tempo_total_segundos`) e médias

A Tabela 1 consolida os tempos observados em segundos para todas as 25 combinações, acompanhados das médias por modelo (linhas) e por perfil (colunas). 

|**Modelo**|**Determinístico**|**Recomendado**|**Padrão atual**|**Moderado**|**Estresse**|**Média do Modelo (s)**|**Amplitude (s)**|
|---|---|---|---|---|---|---|---|
|**`gemma4:e4b`**|9,36|9,61|**9,18**|9,54|9,49|**9,44**|0,43|
|**`qwen3.5:4b`**|14,92|16,15|15,75|14,42|15,52|**15,35**|1,73|
|**`qwen3.5:9b`**|33,67|32,95|31,86|32,82|31,29|**32,52**|2,38|
|**`gemma4:12b`**|54,68|52,15|49,14|51,49|54,03|**52,30**|5,54|
|**`gemma4:26b`**|73,85|79,13|**91,95**|74,90|74,07|**78,78**|18,10|
|**Média do Perfil (s)**|**37,30**|**38,00**|**39,58**|**36,63**|**36,88**|**37,68** (Geral)|—|

### 4.2. Comparação entre modelos sob a mesma configuração e entre perfis para o mesmo modelo

- **Entre modelos (mesma configuração):** a ordenação temporal manteve-se **rigorosamente idêntica nos cinco perfis**: `gemma4:e4b` < `qwen3.5:4b` < `qwen3.5:9b` < `gemma4:12b` < `gemma4:26b`. Não houve sobreposição entre as faixas de tempo de modelos distintos. O `gemma4:e4b` foi, em média, **1,63×** mais rápido que o `qwen3.5:4b`, **3,45×** mais rápido que o `qwen3.5:9b`, **5,54×** mais rápido que o `gemma4:12b` e **8,35×** mais rápido que o `gemma4:26b`.    
- **Entre perfis (mesmo modelo):** a variação temporal entre os cinco perfis foi baixa para `gemma4:e4b` (amplitude de 0,43 s), `qwen3.5:4b` (1,73 s) e `qwen3.5:9b` (2,38 s). A maior discrepância ocorreu no `gemma4:26b` no perfil **Padrão atual** (91,95 s contra 73,85–79,13 s nos demais perfis). Como o bloco _Padrão atual_ foi o primeiro executado cronologicamente, essa diferença pode refletir condições iniciais de execução ou infraestrutura, não sendo atribuível isoladamente aos hiperparâmetros.    
- **Extremos:** a combinação mais rápida foi **`gemma4:e4b` (Padrão atual)** com **9,18 s**; a mais lenta foi **`gemma4:26b` (Padrão atual)** com **91,95 s** (10,02× maior).    
- **Relação tempo × quantidade de tópicos:** não houve proporcionalidade direta entre modelos — o `qwen3.5:4b` (Recomendado) gerou o maior volume de itens do experimento (15 tópicos) em apenas 16,15 s, enquanto o `qwen3.5:9b` levou mais do dobro do tempo médio (32,52 s) para gerar apenas 6 tópicos. Dentro do mesmo modelo (`qwen3.5:4b`), observou-se leve associação positiva: 9 tópicos em 14,42 s (Moderado) até 15 tópicos em 16,15 s (Recomendado).    

## 5. Análise Comparativa da Validação das Fontes

Em **100% das 25 execuções**, todos os modelos preencheram o objeto `Validacao_fontes` de maneira idêntica e compatível com os documentos efetivamente fornecidos:  

- `conformidade_fontes`: `true`    
- `fontes_esperadas_identificadas`: `["Manual de Auditoria do Poder Judiciário", "Resolução CNJ nº 309/2020"]`    
- `fontes_ausentes`: `[]`    
- `arquivos_inesperados_ou_incompativeis`: `[]`    

**Verificação independente:** ambos os documentos obrigatórios constavam efetivamente no acervo dos testes, sem arquivos incompatíveis. Contudo, a declaração de conformidade em `Validacao_fontes` **não garante análise efetiva do conteúdo**: o modelo `qwen3.5:9b` declarou `conformidade_fontes: true` nas cinco execuções, mas não extraiu um único procedimento adicional de nenhuma das duas fontes, limitando-se a reproduzir a lista-base do prompt. Como todas as 25 execuções obtiveram resultado idêntico neste campo, não houve diferenciação entre modelos ou perfis no aspecto específico de validação formal das fontes.  

## 6. Análise Comparativa da Qualidade Técnica das Respostas

### 6.1. Referencial normativo e técnico verificado

O exame integral das fontes de referência identificou os principais procedimentos e técnicas pertinentes à seção _Metodologia_:  

- **Manual de Auditoria do Poder Judiciário (CNJ, 2023):**
    
    - _Técnicas de levantamento de informações_ (Seção 2.1.2, pág. 14): pesquisas on-line, indagação escrita, entrevistas, brainstorming.        
    - _Análise preliminar e mapeamento_ (Seção 3.1.1, págs. 51–55): mapeamento do processo, avaliação de riscos inerentes e avaliação preliminar dos controles internos.        
    - _Natureza dos procedimentos_ (Seção 3.1.2.3.2.3, págs. 62–63; Glossário, págs. 104 e 106): testes de controle (desenho e efetividade operacional; observação, indagação, análise documental, reexecução) e procedimentos substantivos (testes de detalhes de classes de transações, saldos de contas e divulgações; procedimentos analíticos substantivos).        
    - _Técnicas de auditoria_ (Seção 3.2.1, págs. 67–68): análise documental, **circularização** (confirmação com terceiros), conferência de cálculos, correlação entre informações, entrevista, exame dos registros, inspeção/verificação física, questionário, revisão analítica e **observação**.
    - _Amostragem_ (Seção 3.2.2, págs. 68–69): amostragem probabilística (estatística) e não probabilística.        
    - _Comunicação/requisição formal_ (Seção 3.2.3.1.4, pág. 72): Solicitação de Auditoria (SA) para requisição formal de documentos e informações complementares.        
    - _Evidências e causa raiz_ (Seção 3.2.3.3.2, págs. 76–77; Seção 3.3.1.7.1, pág. 87): evidências físicas, testemunhais, documentais e analíticas; atuação na causa raiz.        
    - _Glossário_ (págs. 100 e 102): Auditoria Baseada em Riscos (ABR) e **Benchmarking** (identificação de boas práticas).

- **Resolução CNJ nº 309/2020:**
    
    - Avaliação da adequação e eficácia da governança, gestão, gerenciamento de riscos e controles internos (art. 2º, I–II; art. 23, parágrafo único);
    - Classificação das auditorias em Conformidade, Operacional, Financeira e Especial (art. 25, com inciso IV revogado pela Res. 633/2025);        
    - Requisição formal de documentos, informações ou manifestações (art. 18, § 3º; art. 46, § 3º);        
    - Execução mediante testes previstos e _"técnicas de auditoria selecionadas"_ (art. 45); revisão e supervisão (arts. 27 e 49).        

### 6.2. Avaliação qualitativa por modelo e perfil

- **`qwen3.5:4b`:** apresentou o **mais alto nível de profundidade técnica do experimento** no perfil **Recomendado**, extraindo técnicas específicas do Manual ignoradas por todos os demais modelos: _"Observação direta de processos e atividades"_ (Manual 3.2.1, alínea _j_, pág. 68), _"Confirmação com terceiros e fornecedores"_ (circularização, Manual 3.2.1, alínea _b_, pág. 67), _"Análise de amostragem estatística ou não probabilística"_ (Manual 3.2.2, págs. 68–69), _"Exame substantivo de transações e saldos"_ (Manual 3.1.2.3.2.3, pág. 63; Glossário, pág. 104) e _"Requisição formal de documentos e informações"_ (Manual 3.2.3.1.4, pág. 72; Res. 309/2020, art. 18, § 3º). Nos perfis **Determinístico** e **Moderado**, manteve excelência técnica com menor extensão. Em **Estresse**, trouxe o achado pertinente _"Identificação de causas-raiz das falhas"_ (Manual 3.3.1.7.1, pág. 87), mas incorreu em pleonasmo (_"Análise analítica dos dados"_) e alta redundância com a lista-base. No **Padrão atual**, sofreu queda técnica acentuada ao incluir _"Revisão e supervisão dos papéis de trabalho"_ (atividade de controle de qualidade/gestão interna — Manual 3.3.2.5, pág. 93; Res. 309/2020, arts. 27 e 49 — inadequada como técnica metodológica de campo), além do pleonasmo _"Análise analítica de dados"_ e erro de concordância (_"Obtenção de evidências testemunhal"_).
    
- **`gemma4:26b`:** demonstrou sólido domínio conceitual e boa articulação normativa. No perfil **Estresse**, produziu seu melhor conjunto técnico, incluindo o tópico exclusivo e fundamentado _"Realização de benchmarking para identificação de boas práticas"_ (Manual, Glossário, págs. 100 e 102). Em **Determinístico**, incorporou corretamente _"Execução de procedimentos de auditoria baseados em riscos"_ (Manual, Glossário, pág. 100; Res. 309/2020, art. 23). Contudo, em **Recomendado** e **Moderado**, confundiu parcialmente _classificações de auditoria_ (auditoria operacional, conformidade e financeira — Manual 1.2, págs. 11–12; Res. 309/2020, art. 25) com técnicas de execução, além de apresentar leve desvio de concordância (_"exames de conformidade e financeira"_) e incorporar expressões das Referências Bibliográficas do TCU (_"técnicas de pesquisa e análise de problemas"_, pág. 110).
    
- **`gemma4:12b`:** apresentou desempenho técnico consistente em **Padrão atual** (destacando _"Análise de indicadores e metas estratégicas"_, fundamentada no Manual 3.1.1, pág. 52, e 3.2.1, alínea _i_, pág. 68) e **Moderado**. Em **Determinístico** e **Recomendado**, misturou tipos de auditoria (_"Análise de conformidade e auditoria operacional"_) e sobrepôs itens à lista-base. Em **Estresse**, degradou-se tecnicamente ao fragmentar a definição de _Teste substantivo_ do Glossário (pág. 104) em três itens isolados de _"Testes de detalhes"_ e copiar literalmente dois títulos das Referências Bibliográficas da pág. 110 (_"Técnicas de pesquisa para auditorias"_ e _"Técnicas de análise de problemas para auditorias"_).
    
- **`gemma4:e4b`:** em quatro dos cinco perfis (**Determinístico**, **Recomendado**, **Padrão atual** e **Moderado**), limitou-se a acrescentar apenas dois itens, sendo um deles a fórmula genérica _"Aplicação de técnicas de auditoria selecionadas"_ (copiada do art. 45 da Res. 309/2020, sem valor técnico operativo) e apenas um procedimento real (_testes substantivos_). Surpreendentemente, no perfil **Estresse**, corrigiu essa falha e entregou dois complementares limpos, específicos e fundamentados (_"Técnicas de amostragem para auditorias"_ e _"Testes de detalhes e procedimentos analíticos"_).
    
- **`qwen3.5:9b`:** em todos os cinco perfis, devolveu apenas os 6 tópicos da lista-base. Embora os 6 itens sejam corretos, há omissão integral de técnicas normativas essenciais (testes substantivos, amostragem, circularização, inspeção física, observação, revisão analítica, mapeamento de processos e Solicitação de Auditoria).
    

### 6.3. Omissões comuns a todas as combinações

Mesmo as melhores combinações omitiram técnicas expressamente previstas na Seção 3.2.1 do Manual (págs. 67–68) e na Seção 3.1.1.1 (pág. 53), como **inspeção física/verificação in loco**, **conferência de cálculos**, **exame dos registros**, **correlação entre informações obtidas**, **teste de reexecução de controle** e **mapeamento do processo objeto de auditoria**.

### 6.4. Tabela de avaliação da qualidade técnica por modelo e perfil (notas de 0 a 4)

|**Modelo**|**Determinístico**|**Recomendado**|**Padrão atual**|**Moderado**|**Estresse**|**Média Técnica**|
|---|---|---|---|---|---|---|
|**`qwen3.5:4b`**|3,80|**4,00**|1,80|3,80|3,20|**3,32**|
|**`gemma4:26b`**|3,60|2,80|3,20|2,80|3,60|**3,20**|
|**`gemma4:12b`**|3,00|3,00|3,60|3,60|2,60|**3,16**|
|**`qwen3.5:9b`**|2,20|2,20|2,20|2,20|2,20|**2,20**|
|**`gemma4:e4b`**|1,60|1,60|1,60|1,60|3,60|**2,00**|

## 7. Análise Comparativa da Aderência ao Prompt Original

### 7.1. Requisitos integralmente atendidos por todas as combinações

Em todas as 25 execuções, verificou-se cumprimento integral (nota 4) em quatro aspectos:

1. **Formato de saída:** JSON sintaticamente válido, com chaves exatas (`Validacao_fontes` e `Metodologia`) e sem texto externo.    
2. **Validação das fontes:** preenchimento exato dos quatro subcampos exigidos.
3. **Regra de fidelidade:** comportamento coerente (`conformidade_fontes: true` com lista preenchida).
4. **Preservação da lista-base:** todos os 6 tópicos obrigatórios foram mantidos literalmente, sem supressões, paráfrases ou reordenações.

### 7.2. Desvios de aderência identificados com exemplos concretos

- **Descumprimento da complementação obrigatória (Critério 2):** `qwen3.5:9b` em todos os 5 perfis não acrescentou nenhum tópico complementar.

- **Uso de expressão vaga expressamente vedada (Critérios 2 e 4):** `gemma4:e4b` (Determinístico, Recomendado, Padrão atual e Moderado) gerou `"Aplicação de técnicas de auditoria selecionadas"`, violando frontalmente a instrução _"evitando termos vagos como 'técnicas selecionadas'"_.

- **Violação da restrição de conteúdo (Critério 3):** `qwen3.5:4b` (Padrão atual) incluiu `"Revisão e supervisão dos papéis de trabalho"`, descumprindo a vedação de etapas administrativas/gestão de papéis de trabalho.

- **Contagem de palavras e qualidade redacional (Critério 4):** **100% dos tópicos gerados nas 25 execuções respeitaram o intervalo de 3 a 10 palavras** (mínimo observado: 3 palavras, ex.: _"Procedimentos analíticos substantivos"_; máximo observado: 9 palavras, ex.: _"Aplicação de técnicas de auditoria para obtenção de evidências"_). Entretanto, houve falhas redacionais pontuais em `qwen3.5:4b` (Padrão atual: _"Análise analítica de dados"_ e _"Obtenção de evidências testemunhal"_; Estresse: _"Análise analítica dos dados"_) e `gemma4:26b` (Recomendado e Moderado: _"Realização de exames de conformidade e financeira"_).

- **Fidelidade documental (títulos bibliográficos como técnicas):** `gemma4:12b` (Estresse) extraiu dois títulos da lista de Referências Bibliográficas do TCU (pág. 110) como técnicas normatizadas; `gemma4:26b` (Recomendado, Padrão atual e Moderado) apresentou um item nessa mesma condição.

### 7.3. Classificação qualitativa e notas de aderência ao prompt por modelo e perfil

|**Modelo**|**Determinístico**|**Recomendado**|**Padrão atual**|**Moderado**|**Estresse**|**Média Aderência**|**Classificação Predominante**|
|---|---|---|---|---|---|---|---|
|**`qwen3.5:4b`**|**4,00** (Int.)|**4,00** (Int.)|3,13 (Parc.)|3,88 (Int.)|3,75 (Parc.)|**3,75**|Integralmente aderente (Det/Rec/Mod)|
|**`gemma4:12b`**|3,88 (Parc.)|3,88 (Parc.)|3,88 (Parc.)|3,88 (Parc.)|3,25 (Parc.)|**3,75**|Parcialmente aderente|
|**`gemma4:26b`**|3,88 (Parc.)|3,50 (Parc.)|3,63 (Parc.)|3,50 (Parc.)|**4,00** (Int.)|**3,70**|Integralmente aderente (Est) / Parcial|
|**`qwen3.5:9b`**|3,50 (Parc.)|3,50 (Parc.)|3,50 (Parc.)|3,50 (Parc.)|3,50 (Parc.)|**3,50**|Parcialmente aderente (falha Critério 2)|
|**`gemma4:e4b`**|3,13 (Parc.)|3,13 (Parc.)|3,13 (Parc.)|3,13 (Parc.)|3,88 (Int.)|**3,28**|Parcialmente aderente / Integral (Est)|

_(Legenda: Int. = Integralmente aderente; Parc. = Parcialmente aderente. Nenhuma combinação foi classificada como "Pouco aderente" ou "Não aderente", pois todas mantiveram JSON válido, validação correta e preservação dos 6 tópicos-base.)_

## 8. Análise Comparativa da Quantidade e Abrangência dos Tópicos

Para distinguir volume bruto de valor informacional efetivo, adotou-se critério uniforme: (a) **Quantidade bruta** = total de strings em `Metodologia`; (b) **Tópicos consolidados/relevantes** = itens distintos e tecnicamente pertinentes após descontar redundâncias diretas com a lista-base, fragmentações excessivas, expressões vagas vedadas e etapas administrativas indevidas.

### 8.1. Tabela de quantidade bruta de tópicos (total = 6 da base + complementares)

|**Modelo**|**Determinístico**|**Recomendado**|**Padrão atual**|**Moderado**|**Estresse**|**Média Bruta**|
|---|---|---|---|---|---|---|
|**`qwen3.5:4b`**|11|**15**|13|9|12|**12,00**|
|**`gemma4:12b`**|12|12|12|10|12|**11,60**|
|**`gemma4:26b`**|10|11|11|11|12|**11,00**|
|**`gemma4:e4b`**|8|8|8|8|8|**8,00**|
|**`qwen3.5:9b`**|6|6|6|6|6|**6,00**|
|**Média do Perfil**|**9,40**|**10,40**|**10,00**|**8,80**|**10,00**|**9,72**|

### 8.2. Tabela de tópicos tecnicamente relevantes e consolidados (descontando redundâncias e itens inadequados)

|**Modelo**|**Determinístico**|**Recomendado**|**Padrão atual**|**Moderado**|**Estresse**|**Média Relevantes**|**Redundâncias/Inadequações Típicas**|
|---|---|---|---|---|---|---|---|
|**`qwen3.5:4b`**|10|**12**|9|9|9|**9,80**|Rec: 3 sobrep.; Padrão: 1 proibido + 2 red.; Est: 3 red.|
|**`gemma4:26b`**|9|10|10|10|**11**|**10,00**|1 genérico/bibliográfico ou 1 redundância (Est)|
|**`gemma4:12b`**|9|9|10|8|8|**8,80**|2–3 sobreposições à base ou fragmentação (Est)|
|**`gemma4:e4b`**|7|7|7|7|8|**7,20**|1 termo vago vedado em 4 perfis; 0 no Estresse|
|**`qwen3.5:9b`**|6|6|6|6|6|**6,00**|0 redundâncias, porém 0 complementação|

**Análise de abrangência:** o `qwen3.5:4b` (Recomendado) alcançou a maior cobertura de procedimentos distintos (12 relevantes em 15 brutos), seguido por `gemma4:26b` (Estresse, 11 relevantes em 12 brutos). Por outro lado, `qwen3.5:4b` (Moderado, 9/9) e `gemma4:e4b` (Estresse, 8/8) atingiram **100% de aproveitamento sem redundância**, demonstrando que listas mais concisas podem entregar maior eficiência informacional do que listas infladas com repetições.

## 9. Análise da Influência dos Perfis de Configuração

Observando a evolução de cada modelo ao longo dos cinco perfis (sem atribuir causalidade isolada a Temperatura, Top-P ou Top-K, que variam conjuntamente), identificam-se comportamentos heterogêneos:

|**Modelo**|**Evolução entre os Cinco Perfis (Determinístico →\rightarrow→ Recomendado →\rightarrow→ Padrão atual →\rightarrow→ Moderado →\rightarrow→ Estresse)**|
|---|---|
|**`gemma4:e4b`**|Comportamento rígido nos 4 perfis menos livres (repetiu o termo vago `"técnicas selecionadas"` em todos); no perfil **Estresse**, a maior liberdade rompeu esse padrão repetitivo e elevou drasticamente a qualidade técnica (1,60 $\rightarrow$ 3,60) e a pontuação (62,91 $\rightarrow$ 91,66).|
|**`gemma4:12b`**|Saída idêntica em Determinístico e Recomendado; atingiu pico em **Padrão atual** e **Moderado** (79,47); no perfil **Estresse**, perdeu foco normativo, fragmentando testes de detalhes e puxando títulos da bibliografia (queda para 62,44).|
|**`gemma4:26b`**|Saída idêntica em Recomendado e Moderado (66,38); bom desempenho em **Determinístico** (76,66) e pico absoluto no perfil **Estresse** (77,75), onde introduziu _benchmarking_ e eliminou referências a tipos de auditoria.|
|**`qwen3.5:4b`**|Altamente sensível ao perfil: atingiu o **melhor resultado de todo o experimento em Recomendado** (93,44), mantendo excelência em **Moderado** (91,53) e **Determinístico** (90,75). Caiu em **Estresse** (77,69, por excesso de redundâncias) e teve seu pior resultado em **Padrão atual** (60,91, por incluir revisão de papéis de trabalho e defeitos redacionais).|
|**`qwen3.5:9b`**|**Totalmente insensível aos perfis** quanto ao conteúdo: gerou exatamente os mesmos 6 itens da lista-base de Determinístico até Estresse, variando apenas marginalmente no tempo (31,29 s a 33,67 s).|

**Síntese dos padrões observados:**

1. Maior liberdade de geração (**Estresse**) **não** implicou degradação universal: prejudicou `gemma4:12b` e `qwen3.5:4b`, mas beneficiou expressivamente `gemma4:e4b` e `gemma4:26b`.
2. O perfil **Padrão atual** (Temp=0,10; Top-P=0,90; Top-K=40) registrou o pior desempenho médio geral entre os cinco perfis (67,83) e o maior tempo médio (39,58 s).
3. O impacto dos perfis sobre a velocidade foi residual em comparação ao impacto do modelo escolhido.

## 10. Sistema de Pontuação

### 10.1. Critérios, pesos, fórmula e regras

Todas as 25 combinações foram avaliadas de forma uniforme na escala de **0 a 4 pontos** por aspecto, aplicando-se a regra comparativa de que o melhor desempenho observado em cada aspecto com diferenciação recebe **4** e o pior recebe **0** (mantendo nota igual quando há empate real ou ausência de diferenciação).

- **Pesos:** Qualidade técnica e normativa (**35%**), Aderência ao prompt original (**35%**), Velocidade de geração (**15%**) e Quantidade e abrangência (**15%**).
- **Fórmula:**
$$N_{\text{final}} = (0{,}35 \times N_{\text{técnica}}) + (0{,}35 \times N_{\text{aderência}}) + (0{,}15 \times N_{\text{velocidade}}) + (0{,}15 \times N_{\text{abrangência}})$$ 
    $$P_{\text{final}} = N_{\text{final}} \times 25$$
    
- **Critérios não avaliáveis:** não houve critérios não aplicáveis ou dados ausentes nos 25 JSONs; todas as 25 combinações possuem comparabilidade plena.
- **Desempate:** (1) Qualidade técnica; (2) Aderência ao prompt; (3) Abrangência; (4) Velocidade; (5) Menor `tempo_total_segundos`.
- **Velocidade (5 faixas de 5 combinações):** Faixa 4 (`gemma4:e4b`: 9,18–9,61 s), Faixa 3 (`qwen3.5:4b`: 14,42–16,15 s), Faixa 2 (`qwen3.5:9b`: 31,29–33,67 s), Faixa 1 (`gemma4:12b`: 49,14–54,68 s) e Faixa 0 (`gemma4:26b`: 73,85–91,95 s).

### 10.2. Detalhamento das notas por aspecto (rastreabilidade completa)

_(Os aspectos de aderência sem diferenciação — Formato de saída, Validação das fontes, Regra de fidelidade e Preservação da lista-base — receberam nota **4** em todas as 25 combinações e constam no cálculo da média dos 8 aspectos de aderência.)_

|**Modelo**|**Perfil**|**T1 Cor**|**T2 Fun**|**T3 Cmp**|**T4 Pre**|**T5 Inc**|**NteˊcN_{\text{téc}}Nteˊc​**|**A5 Cmp**|**A6 Res**|**A7 Red**|**A8 Fid**|**NadeN_{\text{ade}}Nade​**|**NvelN_{\text{vel}}Nvel​**|**B1 Cob**|**B2 Div**|**B3 Apr**|**B4 Red**|**NabrN_{\text{abr}}Nabr​**|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|`qwen3.5:4b`|Recomendado|4|4|4|4|4|**4,00**|4|4|4|4|**4,00**|**3,00**|4|4|3|2|**3,25**|
|`gemma4:e4b`|Estresse|4|4|2|4|4|**3,60**|3|4|4|4|**3,88**|**4,00**|2|2|4|4|**3,00**|
|`qwen3.5:4b`|Moderado|4|4|3|4|4|**3,80**|3|4|4|4|**3,88**|**3,00**|3|3|4|4|**3,50**|
|`qwen3.5:4b`|Determinístico|4|4|3|4|4|**3,80**|4|4|4|4|**4,00**|**3,00**|3|3|3|3|**3,00**|
|`gemma4:12b`|Padrão atual|4|4|3|3|4|**3,60**|3|4|4|4|**3,88**|**1,00**|3|3|3|2|**2,75**|
|`gemma4:12b`|Moderado|4|4|3|3|4|**3,60**|3|4|4|4|**3,88**|**1,00**|3|3|3|2|**2,75**|
|`gemma4:26b`|Estresse|4|4|3|3|4|**3,60**|4|4|4|4|**4,00**|**0,00**|3|3|3|3|**3,00**|
|`qwen3.5:4b`|Estresse|3|4|3|3|3|**3,20**|3|4|3|4|**3,75**|**3,00**|3|2|1|0|**1,50**|
|`gemma4:26b`|Determinístico|4|4|3|3|4|**3,60**|3|4|4|4|**3,88**|**0,00**|3|3|3|3|**3,00**|
|`gemma4:12b`|Recomendado|3|4|3|2|3|**3,00**|3|4|4|4|**3,88**|**1,00**|3|3|3|2|**2,75**|
|`gemma4:12b`|Determinístico|3|4|3|2|3|**3,00**|3|4|4|4|**3,88**|**1,00**|3|3|3|2|**2,75**|
|`gemma4:26b`|Padrão atual|4|3|3|3|3|**3,20**|3|4|4|2|**3,63**|**0,00**|3|3|3|3|**3,00**|
|`gemma4:26b`|Moderado|3|3|3|2|3|**2,80**|3|4|3|2|**3,50**|**0,00**|3|3|3|3|**3,00**|
|`gemma4:26b`|Recomendado|3|3|3|2|3|**2,80**|3|4|3|2|**3,50**|**0,00**|3|3|3|3|**3,00**|
|`qwen3.5:9b`|Todos (5 perfis)|4|0|0|3|4|**2,20**|0|4|4|4|**3,50**|**2,00**|0|0|4|4|**2,00**|
|`gemma4:e4b`|Det/Rec/Pad/Mod|2|3|1|0|2|**1,60**|1|4|0|4|**3,13**|**4,00**|1|1|2|3|**1,75**|
|`gemma4:12b`|Estresse|3|2|2|3|3|**2,60**|2|4|4|0|**3,25**|**1,00**|2|2|2|2|**2,00**|
|`qwen3.5:4b`|Padrão atual|0|4|3|2|0|**1,80**|3|0|2|4|**3,13**|**3,00**|3|3|0|1|**1,75**|

_(Nota: os cálculos finais utilizam os valores exatos das frações antes do arredondamento para duas casas decimais.)_

## 11. Ranking Geral das 25 Combinações

|**Posição**|**Modelo**|**Perfil**|**Qualidade técnica**|**Aderência ao prompt**|**Velocidade**|**Abrangência**|**Nota final (0–4)**|**Pontuação (0–100)**|
|---|---|---|---|---|---|---|---|---|
|**1º**|**`qwen3.5:4b`**|**Recomendado**|**4,00**|**4,00**|3,00|3,25|**3,74**|**93,44**|
|**2º**|**`gemma4:e4b`**|**Estresse**|3,60|3,88|**4,00**|3,00|**3,67**|**91,66**|
|**3º**|**`qwen3.5:4b`**|**Moderado**|3,80|3,88|3,00|**3,50**|**3,66**|**91,53**|
|**4º**|**`qwen3.5:4b`**|**Determinístico**|3,80|**4,00**|3,00|3,00|**3,63**|**90,75**|
|**5º**|`gemma4:12b`|Padrão atual|3,60|3,88|1,00|2,75|3,18|79,47|
|**6º**|`gemma4:12b`|Moderado|3,60|3,88|1,00|2,75|3,18|79,47|
|**7º**|`gemma4:26b`|Estresse|3,60|**4,00**|0,00|3,00|3,11|77,75|
|**8º**|`qwen3.5:4b`|Estresse|3,20|3,75|3,00|1,50|3,11|77,69|
|**9º**|`gemma4:26b`|Determinístico|3,60|3,88|0,00|3,00|3,07|76,66|
|**10º**|`gemma4:12b`|Recomendado|3,00|3,88|1,00|2,75|2,97|74,22|
|**11º**|`gemma4:12b`|Determinístico|3,00|3,88|1,00|2,75|2,97|74,22|
|**12º**|`gemma4:26b`|Padrão atual|3,20|3,63|0,00|3,00|2,84|70,97|
|**13º**|`gemma4:26b`|Moderado|2,80|3,50|0,00|3,00|2,66|66,38|
|**14º**|`gemma4:26b`|Recomendado|2,80|3,50|0,00|3,00|2,66|66,38|
|**15º**|`qwen3.5:9b`|Estresse|2,20|3,50|2,00|2,00|2,60|64,88|
|**16º**|`qwen3.5:9b`|Padrão atual|2,20|3,50|2,00|2,00|2,60|64,88|
|**17º**|`qwen3.5:9b`|Moderado|2,20|3,50|2,00|2,00|2,60|64,88|
|**18º**|`qwen3.5:9b`|Recomendado|2,20|3,50|2,00|2,00|2,60|64,88|
|**19º**|`qwen3.5:9b`|Determinístico|2,20|3,50|2,00|2,00|2,60|64,88|
|**20º**|`gemma4:e4b`|Padrão atual|1,60|3,13|**4,00**|1,75|2,52|62,91|
|**21º**|`gemma4:e4b`|Determinístico|1,60|3,13|**4,00**|1,75|2,52|62,91|
|**22º**|`gemma4:e4b`|Moderado|1,60|3,13|**4,00**|1,75|2,52|62,91|
|**23º**|`gemma4:e4b`|Recomendado|1,60|3,13|**4,00**|1,75|2,52|62,91|
|**24º**|`gemma4:12b`|Estresse|2,60|3,25|1,00|2,00|2,50|62,44|
|**25º**|`qwen3.5:4b`|Padrão atual|1,80|3,13|3,00|1,75|2,44|60,91|

_(Desempates aplicados pelo 5º critério — menor tempo total — nos pares 5º/6º, 10º/11º, 13º/14º, bloco 15º–19º e bloco 20º–23º.)_

**Destaques do ranking:** a combinação vencedora é **`qwen3.5:4b` + Recomendado (93,44 pontos)**, única a combinar nota máxima em qualidade técnica (4,00) e aderência ao prompt (4,00) com alta velocidade (3,00) e excelente cobertura (3,25). Há um bloco de elite claramente destacado acima de 90 pontos (1º ao 4º lugar), seguido por um salto de mais de 11 pontos até o 5º lugar (`gemma4:12b` Padrão atual, 79,47). Ressalva importante: o 2º lugar (`gemma4:e4b` Estresse, 91,66) representa um resultado isolado sob alta aleatoriedade em um modelo que obteve apenas 62,91 nos outros quatro perfis.

## 12. Ranking Consolidado dos Modelos

|**Posição**|**Modelo**|**Média técnica**|**Média de aderência**|**Média de velocidade**|**Média de abrangência**|**Pontuação geral**|**Melhor perfil**|
|---|---|---|---|---|---|---|---|
|**1º**|**`qwen3.5:4b`**|**3,32**|**3,75**|3,00|2,60|**82,86**|Recomendado (93,44)|
|**2º**|**`gemma4:12b`**|3,16|**3,75**|1,00|2,60|**73,96**|Padrão atual (79,47)|
|**3º**|**`gemma4:26b`**|3,20|3,70|0,00|**3,00**|**71,63**|Estresse (77,75)|
|**4º**|**`gemma4:e4b`**|2,00|3,28|**4,00**|2,00|**68,66**|Estresse (91,66)|
|**5º**|**`qwen3.5:9b`**|2,20|3,50|2,00|2,00|**64,88**|Estresse (64,88)|

- **`qwen3.5:4b` (1º — 82,86):** líder absoluto na média geral e em 3 dos 5 perfis (Recomendado, Moderado e Determinístico), destacando-se pela combinação de profundidade técnica, aderência e agilidade (15,35 s médios). Sua principal limitação é a vulnerabilidade no perfil _Padrão atual_ (60,91), onde violou restrição de conteúdo.
- **`gemma4:12b` (2º — 73,96):** bom equilíbrio técnico e aderência regular em 4 perfis, mas penalizado pelo tempo médio elevado (52,30 s) e pela degradação no perfil _Estresse_.
- **`gemma4:26b` (3º — 71,63):** líder em média de abrangência (3,00) e vice-líder em qualidade técnica (3,20), mas fortemente penalizado pela lentidão (78,78 s médios, nota 0 em velocidade).
- **`gemma4:e4b` (4º — 68,66):** líder incontestável em velocidade (9,44 s médios), porém com baixa qualidade técnica em 4 dos 5 perfis devido à repetição do termo vago vedado.
- **`qwen3.5:9b` (5º — 64,88):** previsível e livre de erros, mas incapaz de complementar a lista-base em qualquer perfil avaliado.

## 13. Ranking Consolidado dos Perfis de Configuração

|**Posição**|**Perfil**|**Média técnica**|**Média de aderência**|**Média de velocidade**|**Média de abrangência**|**Pontuação geral**|**Melhor modelo**|
|---|---|---|---|---|---|---|---|
|**1º**|**Estresse**|**3,04**|**3,68**|2,00|2,30|**74,88**|`gemma4:e4b` (91,66)|
|**2º**|**Determinístico**|2,84|**3,68**|2,00|2,50|**73,88**|`qwen3.5:4b` (90,75)|
|**3º**|**Moderado**|2,80|3,58|2,00|**2,60**|**73,03**|`qwen3.5:4b` (91,53)|
|**4º**|**Recomendado**|2,72|3,60|2,00|2,55|**72,36**|`qwen3.5:4b` (93,44)|
|**5º**|**Padrão atual**|2,48|3,45|2,00|2,25|**67,83**|`gemma4:12b` (79,47)|

**Interpretação crítica do ranking de perfis:** embora o perfil **Estresse** lidere a média consolidada dos cinco modelos (74,88), essa liderança deve-se fortemente ao salto isolado do `gemma4:e4b` (+28,75 pontos) e à melhora do `gemma4:26b`, ao mesmo tempo em que provocou perdas relevantes em `gemma4:12b` e `qwen3.5:4b`. Já os perfis **Determinístico** (73,88), **Moderado** (73,03) e **Recomendado** (72,36) produziram os resultados mais confiáveis no modelo líder (`qwen3.5:4b`, todos acima de 90 pontos). O perfil **Padrão atual** (67,83) apresentou o pior desempenho médio em qualidade técnica, aderência, abrangência e tempo bruto médio.

## 14. Vantagens e Limitações

- **`qwen3.5:4b`:** _Vantagens:_ melhor relação qualidade/velocidade, extração profunda de técnicas específicas (circularização, observação direta, amostragem estatística/não probabilística, SA), forte aderência em perfis controlados. _Limitações:_ sensibilidade negativa ao perfil _Padrão atual_ (inclusão de revisão de papéis de trabalho) e redundância excessiva sob _Estresse_.
- **`gemma4:26b`:** _Vantagens:_ maior média de abrangência (3,00), boa fundamentação conceitual, introdução de _benchmarking_ e ABR. _Limitações:_ tempo de execução muito elevado (~78,8 s), tendência pontual a listar tipos de auditoria e títulos bibliográficos.
- **`gemma4:12b`:** _Vantagens:_ boa regularidade nos perfis _Padrão atual_ e _Moderado_, redação limpa. _Limitações:_ lentidão significativa (~52,3 s), redundâncias parciais com a lista-base e colapso qualitativo em _Estresse_.
- **`gemma4:e4b`:** _Vantagens:_ velocidade excepcional (~9,4 s), baixo custo temporal. _Limitações:_ extrema tendência a gerar termo vago vedado nos perfis de baixa temperatura; resultado excelente em _Estresse_ requer cautela por se tratar de observação única em temperatura 0,80.
- **`qwen3.5:9b`:** _Vantagens:_ zero alucinações, JSON e lista-base perfeitos. _Limitações:_ falha completa na tarefa de complementação normativa em todos os perfis.

## 15. Conclusão e Recomendações

Com base exclusivamente nos dados observados nas 25 execuções e nas referências normativas analisadas (_Manual de Auditoria do Poder Judiciário_ e _Resolução CNJ nº 309/2020_), conclui-se especificamente para a tarefa de elaboração da seção **"Metodologia"** do documento **"Entendimento do Objeto"**:

1. **Vencedor geral e melhor equilíbrio:** **`qwen3.5:4b` + Perfil Recomendado** (Temperatura 0,10; Top-P 0,75; Top-K 20) obteve a maior pontuação geral (**93,44 / 100**), liderando em qualidade técnica (4,00) e aderência ao prompt (4,00), com alta velocidade (16,15 s; nota 3,00) e a maior cobertura de técnicas normativas distintas (12 tópicos relevantes em 15).
2. **Alternativas de destaque por finalidade específica:**
    
    - **Máxima concisão sem redundância (alta confiabilidade):** **`qwen3.5:4b` + Perfil Moderado** (3º geral, 91,53 pontos, 14,42 s, 100% de aproveitamento em 9 tópicos) ou **`qwen3.5:4b` + Perfil Determinístico** (4º geral, 90,75 pontos, 14,92 s, aderência 4,00).
    - **Máxima velocidade de geração:** **`gemma4:e4b` + Perfil Estresse** (2º geral, 91,66 pontos, 9,49 s). _Ressalva:_ não é recomendado como padrão imediato sem testes repetidos, pois os demais quatro perfis deste modelo ficaram abaixo de 63 pontos e a temperatura 0,80 eleva o risco de variabilidade.
    - **Melhor desempenho sem considerar restrição de tempo:** **`qwen3.5:4b` + Recomendado** ainda permanece superior em qualidade técnica (4,00) a qualquer configuração do `gemma4:26b` (máximo 3,60 em Estresse e Determinístico).
        
3. **Modelo e perfil recomendados para adoção na tarefa avaliada:** recomenda-se adotar o modelo **`qwen3.5:4b`** com o perfil **Recomendado** (ou **Determinístico**, caso se priorize previsibilidade máxima com leve redução na quantidade de itens), **substituindo o perfil `Padrão atual`**, que apresentou o pior desempenho médio do estudo (67,83) e induziu falha de restrição de conteúdo no modelo vencedor.
4. **Limitações antes da padronização em produção:** antes de consolidar definitivamente a configuração padrão, recomenda-se (a) executar múltiplas rodadas ($n \ge 5$) de `qwen3.5:4b` (Recomendado/Determinístico) para verificar estabilidade estatística; (b) incluir casos de teste negativos (ausência de uma das fontes ou anexos incompatíveis) para validar a regra de fidelidade em cenário adverso; (c) explicitar no prompt a vedação de redundância semântica com a lista-base e a não extração de títulos da seção de referências bibliográficas; e (d) auditar a instrumentação do cálculo de `tempo_total_segundos` em relação aos timestamps de início e término da requisição.