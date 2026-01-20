'''
2) Na usina de Angra dos Reis, os técnicos analisam a perda de massa de um material radioativo. Sabendo-se que este perde 25% de sua massa a cada 30 segundos, criar um programa que imprima o tempo necessário para que a massa deste material se torne menor que 0,10 gramas. O programa pode calcular o tempo para várias massas.
'''

while True:
    massa = float(input("Informe a massa inicial do material ou digite -1 para sair: "))

    if massa == -1:
        print("Programa encerrado.")
        break

    elif massa <= 0:
        print("A massa deve ser um número maior que 0.")
        print()

    else:
        segundos = 0 # Inicialização

        while massa >= 0.10:
            massa *= 0.75 # Perde 25% da massa a cada 30s
            segundos += 30

        # Calculo do tempo em minutos e horas
        minutos = segundos / 60
        horas = segundos / 3600

        # Resultado
        print(f"O material levará {segundos} segundos, ou {minutos:.2f} minutos, ou {horas:.2f} horas, para atingir uma massa inferior a 0.10 gramas.")
        print()    