# -*- coding: utf-8 -*-
"""
Created on Sat Dec 12 16:21:54 2020

@author: Maxime
"""
import csv
import numpy as np
import keras.backend as K
import keras.utils 
from keras.models import Sequential
from keras.layers import Dense, Dropout
from keras.optimizers import SGD
import matplotlib as plt

inputs=[]
expected_output=[]

inputs_predict=[]

inputs_fitting=[]
fitting_output=[]
with open('segments_inputs.csv', mode='r') as f:
    f_reader=csv.reader(f, delimiter=';')
    for row in f_reader:
        inputs.append(row[:7])
        expected_output.append(row[7])
        inputs[-1]=[int(element)for element in inputs[-1]]

with open('predict.csv', mode='r') as f:
    f_reader=csv.reader(f, delimiter=';')
    for row in f_reader:
        inputs_predict.append(row[:7])
        inputs_predict[-1]=[int(element)for element in inputs_predict[-1]]

with open('fitting.csv', mode='r') as f:
    f_reader=csv.reader(f, delimiter=';')
    for row in f_reader:
        inputs_fitting.append(row[:7])
        fitting_output.append(row[7])
        inputs_fitting[-1]=[int(element)for element in inputs_fitting[-1]]

inputs=np.asarray(inputs)
inputs_predict=np.asarray(inputs_predict)
expected_output=np.asarray(expected_output)
inputs_fitting=np.asarray(inputs_predict)
fitting_output=np.asarray(expected_output)
        
inputs=np.reshape(inputs, (len(inputs), 1 , 7))
inputs_predict=np.reshape(inputs_predict, (len(inputs_predict), 1 , 7))
inputs_fitting=np.reshape(inputs_fitting, (len(inputs_fitting), 1 , 7))
expected_output=keras.utils.to_categorical(expected_output)
expected_output=np.reshape(expected_output, (len(expected_output), 1, 10))
fitting_output=keras.utils.to_categorical(fitting_output)
fitting_output=np.reshape(fitting_output, (len(fitting_output), 1, 10))

sgd = SGD(lr=0.1)
model = Sequential()
model.add(Dense(16, input_dim=7, activation='relu'))
model.add(Dropout(0.1)) #On supprime l'importance de certaines synapses de manière aléatoire afin d'en prendre plus en compte
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.1))
model.add(Dense(10, activation='softmax'))

callback=keras.callbacks.EarlyStopping(monitor='val_loss', patience=20) #On regarde si val loss remonte (de base)

model.summary()

model.compile(optimizer=sgd, loss='categorical_crossentropy', metrics=['accuracy'])

hist=model.fit(inputs, expected_output, epochs=500, callbacks=[callback], validation_data=(inputs_fitting, fitting_output))

prediction=model.predict(inputs_predict)

print([np.argmax(element)for element in prediction])

plt.
