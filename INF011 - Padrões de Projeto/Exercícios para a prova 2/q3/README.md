## Exercício 3 — Efeitos em VideoTracks e controle de acesso a AudioTracks

**Padrões-alvo: Decorator + Proxy**
**Origem: [Perplexity]**

Você continua o desenvolvimento do framework de edição e renderização de vídeo não-linear. Nesta fase, o foco será a adição de efeitos visuais às `VideoTracks` e o controle de acesso a trilhas de áudio licenciadas.

Uma `VideoTrack` possui o método:

```java
String render();
```

O sistema deve permitir que efeitos visuais sejam adicionados a qualquer `VideoTrack` de forma dinâmica e combinável. Os efeitos inicialmente previstos são:

* `ColorGrading`;
* `BorderEffect`;
* `Watermark`.

Por exemplo, uma mesma `VideoTrack` pode receber filtro de cor e marca d’água, enquanto outra recebe apenas borda. Novos efeitos devem poder ser adicionados sem modificar as classes existentes.

### Questão I

Apresente uma solução baseada em padrão de projeto que permita adicionar os efeitos visuais descritos de forma dinâmica e combinável às `VideoTracks`.

Implemente os efeitos `ColorGrading` e `Watermark`. Demonstre o uso com uma `VideoTrack` que receba ambos os efeitos.

### Questão II

Algumas `AudioTracks` do framework contêm conteúdo licenciado. O acesso ao método abaixo deve ser controlado:

```java
byte[] getContent();
```

Apenas usuários autenticados com credenciais corretas podem obter o conteúdo de áudio. Caso o acesso seja negado, uma exceção `AccessDeniedException` deve ser lançada.

O objeto `AudioTrack` original não deve ser modificado.

Identifique e apresente o padrão de projeto adequado para implementar este controle de acesso. Forneça o código Java esqueleto da solução, indicando o papel de cada classe no padrão.