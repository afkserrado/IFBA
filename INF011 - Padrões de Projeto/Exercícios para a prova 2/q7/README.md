## Exercício 7 — Compartilhamento de assets repetidos da Timeline

**Padrão-alvo: Flyweight**
**Origem: [Perplexity — hipótese resumida] + [Adicional]**

Durante testes com timelines de cinema, a equipe percebeu que muitos elementos visuais são repetidos em várias trilhas e versões da mesma `Timeline`, como:

* logotipos de abertura;
* vinhetas;
* planos de fundo estáticos;
* imagens de preenchimento;
* cartelas de encerramento.

Inicialmente, cada `VideoTrack` mantinha sua própria instância completa desses elementos. Com o crescimento dos projetos, a aplicação começou a apresentar alto consumo de memória.

Deseja-se modificar o sistema para compartilhar os dados pesados dos assets visuais quando eles forem iguais, mantendo em cada ocorrência apenas as informações específicas de uso, como:

* posição na timeline;
* tempo de início;
* duração;
* escala;
* opacidade.

### Questão única

Proponha uma solução baseada em padrão de projeto que permita compartilhar objetos pesados de assets visuais entre várias `VideoTracks`.

Sua solução deve separar:

* estado compartilhável, como nome do asset, caminho do arquivo, resolução e dados binários;
* estado não compartilhável, como posição temporal, escala e opacidade.

Forneça as classes principais, os papéis do padrão e demonstre o uso criando várias ocorrências do mesmo logotipo em momentos diferentes da `Timeline`, compartilhando o mesmo objeto pesado.

---
