#!/usr/bin/env python3
from src.a_star_logic import *


def printGraphLayout():
    print("Graph Layout")
    print(" " + " x " + u"\u2192") #Right arrow
    print("y")
    print(u"\u2193") #Down arrow
    print("End Graph Layout")
    print("")

gridData = GridWithWeights(4, 3)
gridData.weights = {
  (0, 0): 4, (1, 0): 1, (2, 0): 4, (3, 0): 6,
  (0, 1): 2, (1, 1): 9, (2, 1): 9, (3, 1): 6,
  (0, 2): 1, (1, 2): 4, (2, 2): 1, (3, 2): 3,
}
print(gridData.weights)

start = (2, 2)
goal = (1, 0)

printGraphLayout()

came_from, cost_so_far = a_star_search(gridData, start, goal)
draw_grid(gridData, width=4, point_to=came_from, start=start, goal=goal)
print("")
draw_grid(gridData, width=4, number=cost_so_far, start=start, goal=goal)
path = reconstruct_path(came_from, start, goal)
print("")
draw_grid(gridData, width=4, path=path, start=start, goal=goal)
print(path)
print(cost_so_far)
