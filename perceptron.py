# -*- coding: utf-8 -*-
"""
Created on Tue Dec  1 09:01:56 2020

@author: Maxime
"""
from numpy import *

class Perceptron:
    def __init__(self, nbInputs, nbEpoch, learningRate):
        self.nbEpoch = nbEpoch
        self.learningRate = learningRate
        self.nbInputs = nbInputs
        self.poids = [0,0]
        self.biais = 0
    
    def predict(self, input1, input2):
        return ((self.biais + input1*self.poids[0] + input2*self.poids[1]) > 0)
    
    def train(self, inputs, sortieAttendues):
        for loop in range (0, self.nbEpoch+1, 1):
            for i in range (0, 4, 1):
                prediction = int(self.predict(inputs[i][0], inputs[i][1]))
                erreur = 0.5*(prediction - sortieAttendues[i])**2
                print("iteration n°" + str(loop) + " prediction : " + str(prediction) + " erreur : " + str(erreur) + " poids0 : " + str(self.poids[0]) + " poids1 : " + str(self.poids[1]) + " biais : " + str(self.biais) + "\n")
            for indexPoids in range (0, self.nbInputs, 1):
                self.poids[indexPoids] = self.poids[indexPoids] + self.learningRate*(sortieAttendues[i] - prediction)*inputs[i][indexPoids]
            self.biais = self.biais + self.learningRate*(prediction - sortieAttendues[i])
