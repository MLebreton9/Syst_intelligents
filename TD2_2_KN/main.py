# -*- coding: utf-8 -*-
"""
Created on Tue Dec  1 10:03:14 2020

@author: Maxime
"""
import csv
import numpy as np
from perceptron import *
import matplotlib.pyplot as plt

if __name__ == "__main__":
    error_surface=np.zeros((20,20))
    inputs = [[0, 0], [0, 1], [1, 0], [1, 1]]
    sortieAttendues = [0, 0, 0, 1]
    p = Perceptron(2, 20, 0.001)
    p.train(inputs, sortieAttendues)
    with open('poids.csv', mode='w') as f:
        f_writer = csv.writer(f, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        f_writer.writerow([p.poids[0]])
        f_writer.writerow([p.poids[1]])

