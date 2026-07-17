### Dúvidas

#### Q1

- Só para confirmar, séries só podem conter episódios, certo (restrição de tipo)?
Pelo contexto, acredito que sim. (OK)

- Episódios avulsos podem ser adicionados a um pacote sem estarem encapsulados em séries?
No exemplo fornecido, os episódios são criados dentro de série. O enunciado também fala apenas de "filmes avulsos".

- Precisamos definir o valor do desconto na criação do pacote?
O professor já respondeu que não precisa ser fixo. (OK)

- Se um pacote tem um desconto e ele é adicionado a um super pacote, o super pacote tem um desconto próprio? Se sim, esse desconto é aplicado ao preço original do pacote ou ao preço descontado do pacote?
Eu acredito que todo desconto é aplicado ao preço original. A solução contempla a possibilidade de criarmos pacotes com e sem desconto. Dessa forma, um pacote sem desconto pode ser adicionado a outro pacote.

- A questão não fala sobre desempenho, mas exige uma solução "elegante". Isso de alguma forma significa dizer que precisamos manter um cache nos pacotes para o preço e duração deles em vez de sempre recalcular esses valores quando os getters forem chamados? Eu acho que não.

- O builder é para criar pacotes, não séries, né?
Pelo enunciado, sim. (OK)

- É preciso um director para instanciar pacotes pré-definidos (configuração fixa)? Digo, o conteúdo do pacote Sci-Fi sempre vai ser o mesmo ou pode mudar? Se sempre for o mesmo, ter um Director faria sentido. Se não for o caso, faria sentido ter Director?
Eu não acho que os pacotes vão ser sempre os mesmos. Criar um Director implicaria em ter que criar métodos que retornem pacotes específicos. Acho que não há necessidade aqui.



