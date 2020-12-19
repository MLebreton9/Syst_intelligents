
from Character import *
from Army import *
import csv
import random

if __name__ == '__main__':          #S'execute si ce fichier est le fichier principal
    characters_list = []
    army_list = []
    with open('characters.csv', newline='') as csvfile:     #Lecture du fichier csv
        next(csvfile)
        spamreader = csv.reader(csvfile, delimiter=',', quotechar='|')
        for row in spamreader:
            characters_list.append(Character(row[0], row[1], row[2], row[3], row[4]))                             #Création des character
            army_list.append(Army(Character(row[0], row[1], row[2], row[3], row[4]),random.randrange(20,100)))    #Création des armées

    morale_total = 0
    for army in army_list:                                                                                        #Caclul du moral total de l'armée
        morale_total = morale_total + army.get_total_moral()

    print(morale_total)

    moral_combatant = [random.randrange(20, 100) for i in range (0,5)]
    print(moral_combatant)
    moral_personnage = ([0.97,2,1.3,1.5,0.1])
    total =sum([moral_combatant[x] * moral_personnage[x] for x in range (0,5)])
    print(total)
