#14.3
frutas = ['Manga', 'Maca', 'Uva', 'Morango', 'Limao']
doces = ['Brigadeiro','casadinho','beijinho']
feijoada = ['Feijao','Bacon','Calabresa']

#a
listona = [frutas,doces,feijoada]

#b
print("Existe brigadeiro na listona? ", 'Brigadeiro' in listona)

#c
listona.insert(1,'Mais brigadeiros')
print("Listona com mais brigadeiros: ", listona)

#d
listona.append('Coca gelada')
listona.append('Suco')
listona.append('Fanta')
print("Listona com bebidas: ", listona)

#14.4
del listona[0::]
print("lista deletada: ", listona)