# IFBAINF011Aval32026.1

Avaliação 3 da disciplina INF011 - Padrões de Projeto.

Este projeto implementa as soluções da Avaliação III da disciplina INF011.

---

## Questão I: Composite + Builder

### Composite

O problema descreve uma estrutura hierárquica em que um pacote pode conter tanto produtos individuais quanto outros pacotes. O carrinho deve tratar produtos avulsos e pacotes complexos (aninhados) exatamente da mesma maneira, tanto para calcular o preço quanto a duração.

O Composite permite representar objetos individuais e composições por meio de uma mesma abstração, possibilitando que os clientes utilizem ambos de forma uniforme. Assim, o carrinho não precisa saber se está manipulando um único filme ou uma coleção inteira: basta invocar a mesma operação sobre o produto.

### Builder

O enunciado expõe um problema relacionado à criação de pacotes promocionais, que passou a exigir construtores muito grandes, repetitivos e de difícil leitura, tornando o código propenso a erros e difícil de manter.

Com o Builder, é possível separar o processo de construção da representação final do objeto. Ele permite montar estruturas complexas, personalizadas e de forma gradual, eliminando construtores excessivamente longos e facilitando a criação de novas promoções sem comprometer a legibilidade do código.

Portanto, o Composite resolve o problema de como proporcionar uma estrutura que organize os diferentes objetos, tratando-os de forma uniforme. Por outro lado, o Builder oferece uma forma de construir estruturas complexas de maneira simples, gradual e personalizada.

### Implementação

#### Participantes do Composite

- **Component:** `ProdutoComponent`;
- **Classe base auxiliar do domínio comercial:** `AbstractProdutoComponent`, responsável por concentrar comportamento comum, especialmente o atributo `titulo` e sua validação;
- **Leaves:** `Filme` e `Episodio`, que implementam `ProdutoComponent`;
- **Composites:**
  - `Serie`, que implementa `ProdutoComponent` e agrega episódios;
  - `Pacote`, que implementa `ProdutoComponent` e agrega outros objetos do tipo `ProdutoComponent`, incluindo `Pacote`.

#### Participantes do Builder

- **Builder:** `PacoteBuilder`;
- **Concrete Builder:** `ConcretePacoteBuilder`;
- **Product:** `Pacote`;
- **Client:** classe `ClienteAval3` para teste e execução, que utiliza o builder para montar pacotes promocionais.

O Director do Builder foi dispensado porque não foram identificadas sequências fixas e recorrentes para a construção dos pacotes. A composição de cada promoção pode variar conforme a necessidade. Por exemplo, a coleção “Sci-Fi” pode conter os pacotes “Trilogia Matrix” e “Star Wars”, a série “Black Mirror” e o filme “Blade Runner”, mas sua composição pode ser alterada posteriormente. Dessa forma, o próprio cliente pode utilizar diretamente o `PacoteBuilder` para definir os elementos de cada pacote.

### Considerações

- **Composite transparente:** o enunciado determina que filmes avulsos e pacotes complexos aninhados sejam tratados exatamente da mesma maneira. Portanto, produtos individuais e composições compartilham as mesmas operações por meio da interface `ProdutoComponent`.

- **Sem referência para o pai e sem cache:** o enunciado pede uma solução elegante, mas não apresenta requisitos relacionados a desempenho, otimização ou redução de recálculos de preço e duração. Por isso, optou-se por não manter uma referência para o elemento pai nem armazenar resultados em cache.

- **Restrição de tipo dos filhos:** `Pacote` pode conter objetos que implementem `ProdutoComponent`, enquanto `Serie` pode conter apenas objetos do tipo `Episodio`, preservando as regras do domínio inferidas do enunciado.

- **Composite estrutural:** os filhos apenas participam da estrutura hierárquica e podem existir independentemente dos objetos compostos. Um filme ou episódio, por exemplo, pode existir mesmo sem pertencer a um pacote ou série.

- **Reutilização deliberada do estado do builder:** no código, o método `build()` do `ConcretePacoteBuilder` não chama `reset()` automaticamente. Essa decisão foi usada para permitir a criação de mais de uma instância de `Pacote` a partir do mesmo estado acumulado, como no caso de um mesmo conjunto promocional com e sem desconto.

- Adotou-se a interpretação de que cada pacote é responsável por calcular seu próprio preço. Assim, quando um pacote é incluído dentro de outro, o super pacote pode aplicar seu desconto sobre a soma dos preços retornados pelos itens que contém. Isso preserva o encapsulamento e evita efeitos colaterais. O Builder implementado permite a construção de pacotes com e sem desconto. Portanto, para evitar descontos encadeados, é possível compor um pacote sem desconto dentro de outro pacote com desconto, preservando a autonomia de cada objeto composto.

- O enunciado fala em “pacotes fechados de conteúdo com desconto”. Dessa forma, o desconto foi pensado como uma responsabilidade de `Pacote`,  e não de produtos individuais como `Filme` ou `Serie`. Assim, cada pacote calcula seu preço e aplica seu próprio desconto. Em outras palavras, não é possível criar um `Filme` e aplicar-lhe um desconto, porque apenas pacotes podem aplicar descontos.

---

## Questão II: Visitor

### Visitor

O problema descreve uma estrutura de objetos heterogêneos sobre a qual surgem repetidamente novas operações, como cálculo de largura de banda, geração de relatórios e exportação para XML. Isso faz com que as classes dos elementos precisem ser modificadas a cada nova demanda, violando os princípios do Aberto/Fechado e da Responsabilidade Única.

O Visitor resolve esse cenário separando as operações da estrutura de objetos. Assim, os elementos da playlist permanecem estáveis, enquanto novos comportamentos são implementados em visitantes, permitindo a evolução do sistema sem alterar as classes do domínio.

### Implementação

#### Participantes do Visitor

- **Visitor:** `VisitorPlaylist`;
- **Concrete Visitors:**
  - `VisitorLarguraBanda`;
  - `VisitorRelatorioNomes`;
  - `VisitorExportadorXML`;
- **Element:** `PlaylistItem`;
- **Concrete Elements:**
  - `MP3`;
  - `Video`;
  - `Filme`;
  - `Episodio`;
  - `Pacote`;
  - `Serie`.

#### Papel da Playlist

A classe `Playlist` não implementa `PlaylistItem`. Ela atua como objeto agregador e ponto de entrada para as operações, oferecendo acesso à coleção de itens visitáveis, mas não sendo ela própria um elemento visitado.

### Estratégias de implementação

- Como o enunciado afirma que as playlists podem combinar tanto elementos do catálogo comercial da operadora quanto elementos externos, como MP3 e pequenos videoclipes, a estrutura visitável foi definida a partir da interface `PlaylistItem`, que representa especificamente os elementos aceitos em playlist.

- `PlaylistItem` e `ProdutoComponent` não foram unificados na mesma hierarquia principal, pois representam papéis distintos no domínio. `ProdutoComponent` modela a composição comercial de produtos; `PlaylistItem` modela a capacidade de um objeto participar de uma playlist e receber visitantes. Essa separação evita forçar todo produto comercial a ser, obrigatoriamente, um item de playlist.

- A travessia foi concentrada nos próprios visitors concretos. Isso oferece flexibilidade para decidir como tratar objetos simples e compostos, inclusive controlando recursão, indentação textual e serialização XML de acordo com a necessidade da análise.

---

## Classes criadas ou modificadas

- Pacote `avaliacao3.composite`;
- Pacote `avaliacao3.builder`;
- Pacote `avaliacao3.visitor`;
- Classes do domínio comercial relacionadas à Questão I:
  - `Filme`;
  - `Episodio`;
  - `Serie`;
  - `Pacote`;

- Classes do domínio playlist relacionadas à Questão II:
  - `MP3`;
  - `Playlist`;
  - `PlaylistItem`;
  - `Video`;

- Classe utilitária de validação:
  - `ValidadorUtil`;

- Classe cliente:
  - `ClienteAval3`. 

---

## Execução e demonstração

A demonstração das soluções foi centralizada na classe `ClienteAval3`.

Nelas, são exemplificados:

- a construção de pacotes promocionais e super pacotes com `ConcretePacoteBuilder`;
- o cálculo de preço e duração de estruturas compostas;
- o uso de playlists heterogêneas contendo elementos do catálogo e elementos externos;
- a aplicação dos visitors `VisitorLarguraBanda`, `VisitorRelatorioNomes` e `VisitorExportadorXML`.