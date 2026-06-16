## Exercício 1 — Exportação de Timeline em formatos e níveis diferentes

**Padrões-alvo: Bridge + Decorator**
**Origem: [Perplexity]**

Você continua o desenvolvimento do framework de edição e renderização de vídeo não-linear iniciado na Avaliação I. Nesta fase, o foco será a exportação da `Timeline` para diferentes sistemas parceiros e distribuidores.

O sistema deve exportar os dados da `Timeline` para diferentes formatos de arquivo, cada um exigido por um tipo de destino:

* `MP4` — para distribuição em plataformas de streaming e redes sociais;
* `MOV` — para pós-produção em plataformas profissionais;
* `MXF` — para sistemas de broadcast e emissoras de televisão.

Além dos diferentes formatos, a exportação deve suportar diferentes níveis de conteúdo, que podem ser combinados de forma independente com qualquer um dos formatos acima:

1. **Simples** — exporta apenas as `VideoTracks`;
2. **Completo** — exporta `VideoTracks`, `AudioTracks` e `SubTitleTracks`;
3. **Certificado** — exporta `VideoTracks`, `AudioTracks`, `SubTitleTracks` e metadados técnicos da `Timeline`, como `Canvas`, `Renderer` e `Encoder`.

Uma primeira abordagem resultou na criação de classes como:

```java
MP4ExportadorSimples
MP4ExportadorCompleto
MP4ExportadorCertificado
MOVExportadorSimples
MOVExportadorCompleto
MOVExportadorCertificado
MXFExportadorSimples
...
```

Essa solução foi considerada inflexível, pois a adição de um novo formato, como `WebM`, ou de um novo nível de conteúdo resultaria em mudanças acentuadas na estrutura de classes.

### Questão I

Proponha uma nova estrutura de classes, utilizando um ou mais padrões de projeto, que resolva o problema da inflexibilidade. Sua solução deve permitir adicionar novos formatos e novos níveis de conteúdo com o mínimo de impacto no código existente.

Forneça as assinaturas das classes e métodos, bem como o papel de cada participante no padrão aplicado. Demonstre o uso criando um exportador de nível **Completo** no formato **MOV**.

### Questão II

O sistema deve ainda suportar a adição de funcionalidades opcionais e combináveis ao processo de exportação. Por exemplo, deve ser possível adicionar dinamicamente:

* marca d’água com o nome do estúdio;
* compressão adicional de áudio;
* injeção de timecode;
* metadados de direitos autorais.

Uma abordagem com subclasses como:

```java
MOVExportadorCompletoComMarcaDagua
MP4ExportadorSimplesComTimecodeEMetadados
```

foi descartada por levar novamente a uma explosão de classes e não permitir combinação dinâmica.

Use um ou mais padrões de projeto para incorporar as funcionalidades desejadas. Demonstre o uso criando um `ExportadorCompleto` em formato `MOV` com **MarcaDagua** e **Timecode**.