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

- Interface Component do Composite: `ProdutoComponent`;
- Leaves do Composite, que implementam `ProdutoComponent`: `Filme` e `Episodio`;
- Composites do Composite, que implementam `ProdutoComponent`:
    - `Serie`, que contém episódios;
    - `Pacote`, que pode conter qualquer objeto do tipo `ProdutoComponent`, como filmes, episódios, séries e outros pacotes.
    
- Interface Builder: `ProdutoBuilder`;
- Builder concreto: `PacoteBuilder`, que implementa `ProdutoBuilder` e constrói uma instância de `Pacote`.
- Product: é o próprio `Pacote`, objeto de criação do `PacoteBuilder`.

O Director do Builder foi dispensado porque não foram identificadas sequências fixas e recorrentes para a construção dos pacotes. A composição de cada promoção pode variar conforme a necessidade. Por exemplo, a coleção “Sci-Fi” pode conter os pacotes “Trilogia Matrix” e “Star Wars”, a série “Black Mirror” e o filme “Blade Runner”, mas sua composição pode ser alterada posteriormente. Dessa forma, o próprio cliente pode utilizar diretamente o `PacoteBuilder` para definir os elementos de cada pacote.

### Requisitos de implementação

- **Composite transparente:** o enunciado determina que filmes avulsos e pacotes complexos aninhados sejam tratados exatamente da mesma maneira. Portanto, produtos individuais e composições compartilharão as mesmas operações por meio da interface `ProdutoComponent`.

- **Sem referência para o pai e sem cache:** o enunciado pede uma solução elegante, mas não apresenta requisitos relacionados a desempenho, otimização ou redução de recálculos de preço e duração. Por isso, não será mantida referência para o elemento pai nem serão armazenados resultados em cache.

- **Restrição de tipo dos filhos:** `Pacote` poderá conter qualquer objeto que implemente `ProdutoComponent`, enquanto `Serie` poderá conter apenas objetos do tipo `Episodio`, preservando as regras do domínio.

- **Composite estrutural:** os filhos apenas participam da estrutura hierárquica e podem existir independentemente dos objetos compostos. Um filme ou episódio, por exemplo, pode existir mesmo sem pertencer a um pacote ou série.

* **Builder concreto:** o builder é responsável por construir pacotes. Para isso, deve receber objetos que implementem `ProdutoComponent`, já previamente instanciados. Criar métodos específicos para cada tipo de produto, como `Filme`, ou receber parâmetros destinados à criação desses objetos comprometeria a flexibilidade proporcionada pelo Composite, faria o builder acumular responsabilidades, aumentaria o acoplamento e prejudicaria a extensibilidade e o princípio Aberto/Fechado. Além disso, os produtos individuais existem independentemente do builder e do pacote, que apenas os organiza em uma composição.

### Classes criadas ou modificadas

- Todas as classes dos pacotes Builder, Composite e Comercial.

### Dúvidas

- Só para confirmar, séries só podem conter episódios, certo (restrição de tipo)?
- Episódios avulsos podem ser adicionados a um pacote sem estarem encapsulados em séries?

- Precisamos definir o valor do desconto na criação do pacote?
- Se um pacote tem um desconto e ele é adicionado a um super pacote, o super pacote tem um desconto próprio? Se sim, esse desconto é aplicado ao preço original do pacote ou ao preço descontado do pacote?

- A questão não fala sobre desempenho, mas exige uma solução "elegante". Isso de alguma forma significa dizer que precisamos manter um cache nos pacotes para o preço e duração deles em vez de sempre recalcular esses valores quando os getters forem chamados?

- O builder é para criar pacotes, não séries, né?

- É preciso um director para instanciar pacotes pré-definidos (configuração fixa)? Digo, o conteúdo do pacote Sci-Fi sempre vai ser o mesmo ou pode mudar? Se sempre for o mesmo, ter um Director faria sentido. Se não for o caso, faria sentido ter Director?


## Questão II: Visitor

### Visitor

O problema descreve uma estrutura de objetos heterogêneos sobre a qual surgem repetidamente novas operações, como cálculo de largura de banda, geração de relatórios e exportação para XML. Isso faz com que as classes dos elementos precisem ser modificadas a cada nova demanda, violando os princípios do Aberto/Fechado e da Responsabilidade Única.

O Visitor resolve esse cenário, separando as operações da estrutura de objetos. Assim, os elementos da playlist permanecem estáveis, enquanto novos comportamentos são implementados em visitantes, permitindo a evolução do sistema sem alterar as classes do domínio.

### Implementação

- Interface Visitor: `VisitorPlaylist`;
- Visitantes concretos, que implementam `VisitorPlaylist`: `VisitorLarguraBanda`, `VisitorRelatorioNome`, `VisitorExportadorXML`;
- Interface Element: `PlaylistItem`;
- Elementos concretos que implementam `PlaylistItem`: `MP3`, `Video`, `Filme`, `Episodio`, `Pacote`, `Serie`.

### Dúvidas

- Na q2, o enunciado diz "Estas operações resultaram em mudanças nas classes MP3, VideoClipe, Episodio e Filme.". Porém, Serie e Episodio não estão implementando PlaylistItem na classe base, mas tem um método toXML(). Ou seja, eles não precisam de getBandWidth?
- Na q2, Episodio e Serie vão precisar de todas as operações também? Porque na implementação base elas só possuem o método toXML().
