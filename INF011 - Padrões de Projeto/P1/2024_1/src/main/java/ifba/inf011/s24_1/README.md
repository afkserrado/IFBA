Raciocínio

- Construção passo a passo -> Builder
- Classes concretas com valores pré-definidos: método no Director
- Startet com valores default e arma inicial: método no Director
- Armas criadas com valores default, mas para o starter o habilitada = true: método no Director
- Aplicação isolada da criação dos personagens -> implementar Director
- Implementar Cruzado starter

Estrutura:

- Interfaces: BuilderPersonagem e BuilderArma;
- Builders concretos: BuilderCruzado, BuilderEspada etc.
- Produtos: Cruzado, SoladoGuerraSecessao, Espada, Bacamarte etc.
- Director: métodos para criar personagem e arma.