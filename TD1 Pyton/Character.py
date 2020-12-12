class Character:
    def __init__(self,nom,prenom,age,profession,moral_value):
        self.__nom = nom
        self.__prenom = prenom
        self.__age = age
        self.__profession = profession
        self.__moral_value = moral_value
    #getteur
    def get_nom(self):
        return self.__nom

    def get_prenom(self):
        return self.__prenom

    def get_age(self):
        return self.__age

    def get_profession(self):
        return self.__profession

    def get_moral_value(self):
        return self.__moral_value

    #setteur
    def set_nom(self,nom):
        self.__nom = nom

    def set_prenom(self,prenom):
        self.__prenom = prenom

    def set_age(self,age):
        self.__age = age

    def set_profession(self,profession):
        self.__profession = profession

    def set_moral_value(self,moral_value):
        self.__moral_value = moral_value

    # le tostring
    def __repr__(self) -> str:
        return self.__nom