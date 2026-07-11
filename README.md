# IFBAINF011Aval32026.1

Avaliação 3 da disciplina INF011 - Padrões de Projeto

## Questão I: Composite + Builder

### Composite

O problema descreve uma estrutura hierárquica em que um pacote pode conter tanto produtos individuais (filmes, séries, episódios etc.) quanto outros pacotes. O carrinho deve tratar produtos avulsos e pacotes complexos (aninhados) exatamente da mesma maneira, tanto para calcular o preço quanto a duração.

O Composite permite representar objetos individuais e composições por meio de uma mesma abstração, possibilitando que os clientes utilizem ambos de forma uniforme. Assim, o carrinho não precisa saber se está manipulando um único filme ou uma coleção inteira: basta invocar a mesma operação sobre o produto.

### Builder

O enunciado expõe um problema relacionado à construção dos objetos: a criação de pacotes promocionais com diversos níveis de aninhamento passou a exigir construtores grandes, repetitivos e de difícil leitura, tornando o código propenso a erros e difícil de manter.

Com o Builder, é possível separar o processo de construção da representação final do objeto. Ele permite montar estruturas complexas, personalizadas e de forma gradual, eliminando construtores excessivamente longos e facilitando a criação de novas promoções sem comprometer a legibilidade do código.

Portanto, o Composite resolve o problema de como proporcionar uma estrutura que organize os diferentes objetos, tratando-os de forma uniforme. Por outro lado, o Builder oferece uma forma de construir estruturas complexas de maneira simples, gradual e personalizada.

### Implementação

- Interface Component: `ProdutoComponent`;
- Leaves, que implementam `ProdutoComponent`: `Filme` e `Episodio`;
- Composites, que implementam `ProdutoComponent`:
    - `Serie`, que contém episódios;
    - `Pacote`, que pode conter qualquer objeto do tipo `ProdutoComponent`, como filmes, episódios, séries e outros pacotes.
    
- Interface Builder: `ProdutoBuilder`;
- Concrete Builder: `PacoteBuilder`, que implementa `ProdutoBuilder` e constrói uma instância de `Pacote`.

O Director do Builder foi evitado porque não foram identificadas sequências fixas e recorrentes para a construção dos pacotes. A composição de cada promoção pode variar conforme a necessidade. Por exemplo, a coleção “Sci-Fi” pode conter os pacotes “Trilogia Matrix” e “Star Wars”, a série “Black Mirror” e o filme “Blade Runner”, mas sua composição pode ser alterada posteriormente. Dessa forma, o próprio cliente pode utilizar diretamente o `PacoteBuilder` para definir os elementos de cada pacote.

### Requisitos de implementação

* **Composite transparente:** o enunciado determina que filmes avulsos e pacotes complexos aninhados sejam tratados exatamente da mesma maneira. Portanto, produtos individuais e composições compartilharão as mesmas operações por meio da interface `ProdutoComponent`.

* **Sem referência para o pai e sem cache:** o enunciado pede uma solução elegante, mas não apresenta requisitos relacionados a desempenho, otimização ou redução de recálculos de preço e duração. Por isso, não será mantida referência para o elemento pai nem serão armazenados resultados em cache.

* **Restrição de tipo dos filhos:** `Pacote` poderá conter qualquer objeto que implemente `ProdutoComponent`, enquanto `Serie` poderá conter apenas objetos do tipo `Episodio`, preservando as regras do domínio.

* **Composite estrutural:** os filhos apenas participam da estrutura hierárquica e podem existir independentemente dos objetos compostos. Um filme ou episódio, por exemplo, pode existir mesmo sem pertencer a um pacote ou série.

### Dúvidas

- Só para confirmar, séries só podem conter episódios, certo (restrição de tipo)?
- Episódios avulsos podem ser adicionados a um pacote sem estarem encapsulados em séries?
