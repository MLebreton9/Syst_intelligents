class Army:

    def __init__(self,chef,moral_base):     #Constructeur
        self.__chef = chef
        self.__moral_base = moral_base

    def get_chef(self):                     #Getter
        return self.__chef
    def get_moral_base(self):
        return self.__moral_base

    def set_chef(self,chef):                #Setter
        self.__chef = chef
    def set_moral_base(self,moral):
        self.__moral_base = moral

    def __repr__(self) -> str:              #Affichage
        text = self.__chef.get_nom() + ' ' + str(self.__moral_base)
        return text

    def get_total_moral(self) -> float:     #Fonctionne qui retourne le moral total d'une armée
        return self.__moral_base * float(self.__chef.get_moral_value())
