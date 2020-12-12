# -*- coding: utf-8 -*-
"""
Created on Fri Nov 27 15:52:14 2020

@author: Maxime
"""
import numpy as np
import matplotlib.pyplot as plt

sum_error=0

error_surface = np.zeros((21,21))

input = [[0, 0], [0, 1], [1, 0], [1, 1]]
output = [0, 0, 0, 1]

for w1 in range(-10, 10, 1):
    for w2 in range(-10, 10, 1):
        sum_error=0
        for i in range(4):
            sum=np.matmul([input[i]],[w1, w2])
            if sum <= 0:
                y=0
            else:
                y=1
            sum_error=sum_error+0.5*((y-output[i])*(y-output[i]))
        print(w1)
        error_surface[(w1+10)][(w2+10)]=sum_error
plt.imshow(error_surface)
plt.show()
