#Lista de exercícios 001
'''
11. Você e os outros integrantes da sua república (Joca, Moacir, Demival e Jackson) foram no supermercado e
compraram alguns itens:
• 75 latas de cerveja: R$ 2,20 cada (da ruim ainda, pra fazer o dinheiro render)
• 2 pacotes de macarrão: R$ 8,73 cada
• 1 pacote de Molho de tomate: R$ 3,45
• 420g Cebola: R$ 5,40/kg
• 250g de Alho: R$ 30/kg
• 450g de pães franceses: R$ 25/kg

Calcule quanto ficou para cada um.
'''

Total = 75*2.2 + 2*8.73 + 1*3.45 + (420/1000)*5.40 + (250/1000)*30 + (450/1000)*25
print("O total deu R$",Total, "e cada um pagará R$", Total/5)