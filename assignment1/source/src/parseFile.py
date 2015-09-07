#!/usr/bin/env python

'''
CS 4341
Assignment 1
9/6/2015
'''

class parseFile():
    def readFile():
        fileToParse = 'board.txt'
        with open(fileToParse, 'r') as f:
            print(f.read())

parseFile.readFile()
