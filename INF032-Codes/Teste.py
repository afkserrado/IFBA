import matplotlib.pyplot as plt
import numpy as ny

x = ny.arange(1, 20) # Cria um vetor x
y = 3 * x - 3 # Cria um vetor y

plt.plot(x, y)  # Cria um gráfico
plt.title("Reta") # Título do gráfico
plt.xlabel("Eixo x") # Título do eixo x
plt.ylabel("Eixo y") # Título do eixo y
plt.show() # Exibe o gráfico

# Teste