import random


class World(object):

    def __init__(self, m, n, init=True):
        if init:
            self.rows = [[0]*n for x in range(m)]
        else:
            self.rows = []
        self.m = m
        self.n = n

    def __str__(self):
        s='\n'.join([' '.join([str(item) for item in row]) for row in self.rows])
        return s + '\n'

    @classmethod
    def makeRandom(cls, m, n, low=1, high=9):
        """ Make a random matrix with elements in range (low-high) """

        obj = World(m, n, init=False)
        for x in range(m):
            obj.rows.append([random.randrange(low, high) for i in range(obj.n)])

        return obj


class MatrixTests(object):
        m2 = World.makeRandom(5, 4)
        print m2
