1. Entendendo os cinco perfis de configuração

A tabela apresenta cinco combinações de parâmetros.
Perfil	Temperatura	Top-P	Top-K
Determinístico	0.00	0.10	1
Recomendado	0.10	0.75	20
Padrão atual	0.10	0.90	40
Moderado	0.35	0.85	40
Estresse	0.80	0.95	80

O que cada parâmetro representa?

Temperatura

Controla a aleatoriedade na escolha dos próximos tokens.

- 0.00: geração mais previsível e conservadora.
- 0.10: pouca variação, boa para tarefas técnicas.
- 0.35: maior flexibilidade na redação.
- 0.80: maior liberdade para variar as respostas.

Para seu caso, temperaturas elevadas podem fazer o modelo acrescentar técnicas que parecem apropriadas, mas que não necessariamente estão previstas nos documentos.

Top-P

Controla a seleção de tokens considerando a probabilidade acumulada.
- 0.10: seleção extremamente restritiva.
- 0.75: seleção mais controlada.
- 0.90: maior diversidade.
- 0.95: ainda mais liberdade de escolha.

Top-K

Limita a quantidade de tokens candidatos considerados em cada geração.
- 1: considera apenas o candidato mais provável.
- 20: permite uma seleção relativamente restrita.
- 40: oferece mais alternativas.
- 80: oferece um conjunto ainda maior de alternativas.

Um detalhe importante: Top-P e Top-K atuam em conjunto. Portanto, não devemos analisar seus valores isoladamente.
