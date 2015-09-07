#!/usr/bin/env python

'''
CS 4341
Assignment 1
9/6/2015
'''
import csv
class parseFile():

    def __init__(self):
        self.gridWeights = []

    def readFile(self):
        fileToParse = 'board.txt'
        with open(fileToParse, 'r') as f:
             reader = csv.reader(f, delimiter="\t")
             d = list(reader)
             for inY,y in enumerate(d):
                 for inX,x in enumerate(y):
                     try:
                        c = int(x)
                     except ValueError:
                        c = 1
                     self.gridWeights.append( [inX, inY, c] )

    def printWeights(self):
        print(self.gridWeights)

p = parseFile()
p.readFile()
p.printWeights()
