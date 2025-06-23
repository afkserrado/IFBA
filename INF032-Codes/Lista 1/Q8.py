#Lista de exercícios 001

#8. supondo um numero 123, imprimi-lo invertido. Exemplo (123, 321). O numero deverá ser armazenado em outra variável.

numero = 123

n1 = numero//100
numero = numero%100
n2 = numero//10
n3 = numero%10

print("Número invertido:", n3*100+n2*10+n1)