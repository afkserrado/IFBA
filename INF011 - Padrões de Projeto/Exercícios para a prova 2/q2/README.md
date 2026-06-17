## Exercício 2 — Clips simples, clips compostos e efeitos visuais

**Padrões-alvo: Composite + Decorator**
**Origem: [Perplexity]**

Você continua o desenvolvimento do framework de edição e renderização de vídeo não-linear. Nesta fase, o foco é a modelagem dos clips de vídeo dentro de uma `VideoTrack` e a aplicação dinâmica de efeitos.

Uma `VideoTrack` é composta por `Clips`. Um `Clip` possui os seguintes métodos:

```java
String render();
int getDuracao();
```

O sistema deve suportar dois tipos de clips:

* `ClipSimples`: representa um clipe de vídeo bruto, com nome do arquivo e duração;
* `ClipComposto`: representa um agrupamento de outros clips, simples ou compostos.

A duração total de um `ClipComposto` deve ser a soma das durações dos seus componentes. O método `render()` do `ClipComposto` deve concatenar o resultado do `render()` dos clips internos, respeitando a ordem de inserção.

A forma de tratar `ClipSimples` e `ClipComposto` deve ser a mais transparente possível para a classe cliente `VideoTrack`.

### Questão I

Escolha adequadamente um padrão de projeto que melhor apoie os requisitos apresentados. Reescreva livremente as classes do sistema e forneça, para todas as classes, apenas os métodos necessários para:

1. calcular a duração total de um `ClipComposto`;
2. gerar o `render()` completo de um `ClipComposto`.

Demonstre o uso com um `ClipComposto` chamado `"Cena Principal"`, contendo dois `ClipSimples` e um `ClipComposto` interno chamado `"Flashback"`.

### Questão II

O sistema deve permitir que efeitos visuais sejam adicionados a qualquer `Clip`, seja simples ou composto, de forma dinâmica e combinável. Os efeitos inicialmente previstos são:

* `FiltroCorClip`;
* `MarcaDaguaClip`;
* `LegendaEmbutidaClip`.

A adição de um novo efeito não deve exigir modificações nas classes de `Clip` existentes.

Apresente o padrão de projeto adequado. Implemente `FiltroCorClip` e `LegendaEmbutidaClip`. Demonstre o uso com um `ClipSimples` recebendo ambos os efeitos, e com um `ClipComposto` recebendo apenas `FiltroCorClip`.