# Composite seguro + parent reference + cache + estrutural

* Interface `Component` contendo apenas operações comuns;
* Apenas o `Composite` possui `add` e `remove`;
* Referência para o pai (`parent`);
* Cache (`cachedSize`);
* Invalidação de cache propagada para cima;
* Sem restrição de tipos dos filhos;
* Estrutura ordenada (`List`);
* Composite estrutural (apenas delega comportamento);
* Sem agregação de valores.

Obs.: a variação "safe component" não exige referência para o pai ou cache.

