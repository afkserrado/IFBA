class Ponto:

    # Construtor
    def __init__(self, *args):

        # Construtor 1
        if len(args) == 2 and all(isinstance(el, (int, float)) for el in args):
            self.__x = args[0] # Atributos privados
            self.__y = args[1]

        elif len(args) == 0:
            self.__x = 0
            self.__y = 0

        else:
            raise TypeError("Indicar 2 parâmetros (x,y) ou nenhum. Os parâmetros devem ser int ou float.")
        
    # Getters
    def getX(self):
        return self.__x
    
    def getY(self):
        return self.__y