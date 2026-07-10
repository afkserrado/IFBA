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

- Interface ProdutoComponent
