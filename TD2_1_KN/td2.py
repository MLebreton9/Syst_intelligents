# -*- coding: utf-8 -*-
"""
Created on Fri Nov 27 15:52:14 2020

@author: Maxime
"""
import numpy as np              #import de la librairie numpy pour les calculs matriciels
import matplotlib.pyplot as plt #import de mtplotlib pour les graphiques

sum_error=0

error_surface = np.zeros((10,10))

input = [[0, 0], [0, 1], [1, 0], [1, 1]]    #déclaration des valeurs d'input
output = [0, 0, 0, 1]                       #déclaration des sorties attendues

for w1 in range(-5, 5, 1):
    for w2 in range(-5, 5, 1):
        sum_error=0
        for i in range(4):
            sum=np.matmul([input[i]],[w1, w2])  #Calcul de la sortie
            if sum <= 0:                        #Fonction de seuil
                y=0
            else:
                y=1
            sum_error=sum_error+0.5*((y-output[i])*(y-output[i])) #Calcul de l'erreur
        print(w1)
        error_surface[(w1+5)][(w2+5)]=sum_error
plt.imshow(error_surface)                       #Affichage de la surface d'erreur
plt.show()
