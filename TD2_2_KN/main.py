# -*- coding: utf-8 -*-
"""
Created on Tue Dec  1 10:03:14 2020

@author: Maxime
"""
import csv
import numpy as np
from perceptron import *
import matplotlib.pyplot as plt

if __name__ == "__main__":          #ne s'execute que si ce fichier est le fichier principal
    inputs = [[0, 0], [0, 1], [1, 0], [1, 1]]       #entrées de la porte AND
    sortieAttendues = [0, 0, 0, 1]                  #sorties attendues
    p = Perceptron(2, 10, 0.01)                     #création du perceptron
    p.train(inputs, sortieAttendues)                #entrainement du perceptron
    with open('poids.csv', mode='w') as f:          #enregistrement des poids
        f_writer = csv.writer(f, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        f_writer.writerow([p.poids[0]])
        f_writer.writerow([p.poids[1]])

