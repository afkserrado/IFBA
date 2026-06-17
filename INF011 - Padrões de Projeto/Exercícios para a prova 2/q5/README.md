## Exercício 5 — Agrupamento de tracks e controle de acesso a tracks protegidas

**Padrões-alvo: Composite + Proxy**
**Origem: [Perplexity pós-P3]**

A `Timeline` é composta por tracks de diferentes tipos:

* `VideoTrack`;
* `AudioTrack`;
* `SubTitleTrack`.

Além disso, deseja-se criar um `TrackGroup`, que pode agrupar múltiplas tracks simples ou outros grupos de tracks. Um `TrackGroup` deve ser tratado pela `Timeline` da mesma forma que uma track individual.

O método:

```java
int getDuracao();
```

deve retornar:

* para uma track simples, sua duração individual;
* para um `TrackGroup`, a duração da track mais longa dentre as suas componentes.

O método:

```java
String render();
```

deve retornar:

* para uma track simples, sua representação textual;
* para um `TrackGroup`, a concatenação das representações de todas as tracks internas.

A `Timeline` deve ser capaz de chamar `getDuracao()` e `render()` em qualquer track, simples ou grupo, sem saber com qual tipo está lidando.

### Questão I

Apresente uma nova estrutura de classes que suporte os requisitos descritos. Identifique o padrão aplicado e os papéis de cada classe.

Forneça os métodos necessários para `getDuracao()` e `render()` de um `TrackGroup`, considerando a possibilidade de grupos aninhados.

Demonstre o uso com uma `Timeline` contendo um `TrackGroup` chamado `"Cena 01"` com uma `VideoTrack`, uma `AudioTrack` e um `TrackGroup` interno chamado `"Flashback"`.

### Questão II

Algumas `VideoTracks` contêm conteúdo de propriedade intelectual protegida. O acesso ao método `render()` de uma `VideoTrackProtegida` deve ser controlado: somente usuários com `Credencial` válida podem executar a renderização.

Se as credenciais forem inválidas, uma exceção `AcessoNegadoException` deve ser lançada. O objeto `VideoTrack` original não deve ser modificado.

Identifique e apresente o padrão adequado, indicando os participantes. Forneça o código Java esqueleto e demonstre o uso tentando renderizar com credenciais válidas e inválidas.