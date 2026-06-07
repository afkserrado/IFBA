# Composite transparente + estrutural

* Interface `Component` comum entre `Composite` e `Leaf`;
* Operações de gerenciamento dos filhos (`add` e `remove`) declaradas no componente comum;
* Sem referência para o pai (`parent`);
* Sem cache;
* Sem restrição de tipos dos filhos;
* Estrutura ordenada (`List`);
* Composite estrutural (apenas delega comportamento);
* Sem agregação de valores.

Em termos de implementação:

```java
TransparentComponent
├── TransparentLeaf
└── TransparentComposite
```



