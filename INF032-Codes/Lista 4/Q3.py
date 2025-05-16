'''
3. Crie um dicionário vazio filmes = {}. Utilize o nome de um filme como chave. E, como valor,
outro dicionário contendo o vilão e o ano em que o filme foi lançado. Preencha 5 filmes.
'''

filmes = {}

filmes["O Exorcista"] = dict({"Pazuzu": 1973})
filmes["Hallowen"] = dict({"Michael Myers": 1978})
filmes["A Hora do Pesadelo"] = dict({"Freddy Krueger": 1984})
filmes["O Iluminado"] = dict({"Jack Torrance": 1980})
filmes["It: A coisa"] = dict({"Pennywise": 2017})

print("Filmes:", filmes)