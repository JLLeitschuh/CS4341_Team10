#!/usr/bin/env python3

# Implementatioin from:
# http://www.redblobgames.com/pathfinding/a-star/implementation.html
#

#BEGIN A STAR IMPLMEMENTATION
import collections
import sys
from itertools import takewhile
import heapq
from enum import Enum, unique

@unique
class Direction(Enum):
    NORTH = 1
    EAST = 2
    SOUTH = 3
    WEST = 4

    def direction_location(self, currentLoc):
        (x, y) = currentLoc
        options = {
            Direction.NORTH : (x, y+1),
            Direction.EAST  : (x+1, y),
            Direction.SOUTH : (x, y-1),
            Direction.WEST  : (x-1, y)
        }
        return options[self]

    # Determines if the directions are oppisites
    # @param compare The direction to compare against
    # @return True if oposites
    def is_oposite(self, compare):
        # Defines the opposite of all directions
        options = {
            Direction.NORTH : Direction.SOUTH,
            Direction.EAST : Direction.WEST,
            Direction.SOUTH : Direction.NORTH,
            Direction.WEST : Direction.EAST
        }
        return options[self] == compare

    # Calculates the turn multiplier
    # @param turnToDir The direction to turn to
    # @return a double value representing the turn rotation cost
    def turn_multiplier(self, turnToDir):
        if self == turnToDir:
            return 0
        elif self.is_oposite(turnToDir):
            return (2.0/3.0)
        else:
            return (1.0/3.0)

class PriorityQueue(object):
    def __init__(self):
        self.elements = []

    def empty(self):
        return len(self.elements) == 0

    def put(self, item, priority):
        heapq.heappush(self.elements, (priority, item))

    def get(self):
        return heapq.heappop(self.elements)[1]

class Neighbor(object):
    #
    # @param facingDirection The direction that is currently being faced
    # at the present node.
    def __init__(self, currentLoc, baseNodeCost, direction, facingDirection):
        self.oldLoc = currentLoc
        self.loc = direction.direction_location(currentLoc)
        self.cost = direction.turn_multiplier(facingDirection) * baseNodeCost
        # The direction that this neighbor would be facing would
        # be defined by the next direction
        self.facingDirection = direction

    def __repr__(self):
         return "Neighbor(" + self.__str__() +")"

    def __str__(self):
        return "Loc: " + str(self.loc) + " Cost: " + str(self.cost)

class SquareGrid(object):
    def __init__(self, width, height):
        self.width = width
        self.height = height
        self.walls = []

    def in_bounds(self, id):
        (x, y) = id
        return 0 <= x < self.width and 0 <= y < self.height

    def passable(self, id):
        return id not in self.walls

class GridWithWeights(SquareGrid):
    def __init__(self, width, height):
        super(self.__class__, self).__init__(width, height)
        self.weights = {}

    def cost(self, a, b):
        return self.weights.get(b, 1)

    #
    #
    # @param id current location
    # @param direction that the robot is facing
    # Future prototype
    def neighbors(self, neighbor):
        (x, y) = neighbor.loc
        direction = neighbor.facingDirection
        # Does not include diagonals
        results = [
          Neighbor(neighbor.loc, self.cost(None, neighbor.loc), Direction.WEST, direction),
          Neighbor(neighbor.loc, self.cost(None, neighbor.loc), Direction.SOUTH, direction),
          Neighbor(neighbor.loc, self.cost(None, neighbor.loc), Direction.EAST, direction),
          Neighbor(neighbor.loc, self.cost(None, neighbor.loc), Direction.NORTH, direction),
        ]
        if (x + y) % 2 == 0: results.reverse() # aesthetics
        for elt in results:
            if not self.in_bounds(elt.loc):
                # Costs 100 to fall off of the edge of the map
                elt.cost += 100
        #results = ifilter(self.passable, results)
        return results

def reconstruct_path(came_from, start, goal):
    current = goal
    path = [current]
    while current != start:
        current = came_from[current]
        path.append(current)
    return path

def heuristic(a, b):
    (x1, y1) = a
    (x2, y2) = b
    return abs(x1 - x2) + abs(y1 - y2)

def a_star_search(graph, start, goal):
    frontier = PriorityQueue()
    frontier.put(Neighbor(start, 1, Direction.NORTH, Direction.NORTH), 1)
    came_from = {}
    cost_so_far = {}
    came_from[start] = None
    cost_so_far[start] = 0

    while not frontier.empty():
        current = frontier.get()

        if current.loc == goal:
            break

        for nextNeighbor in graph.neighbors(current):
            next = nextNeighbor.loc
            print(cost_so_far)
            new_cost = cost_so_far[current.oldLoc] + graph.cost(current, next) + nextNeighbor.cost
            if next not in cost_so_far or new_cost < cost_so_far[next]:
                (x, y) = next
                cost_so_far[next] = new_cost
                priority = new_cost + heuristic(goal, next)
                frontier.put(nextNeighbor, priority)
                came_from[next] = current

    return came_from, cost_so_far

def draw_tile(graph, id, style, width):
    r = u"."
    if u'number' in style and id in style[u'number']: r = u"%d" % style[u'number'][id]
    if u'point_to' in style and style[u'point_to'].get(id, None) is not None:
        (x1, y1) = id
        (x2, y2) = style[u'point_to'][id]
        if x2 == x1 + 1: r = u"\u2192"
        if x2 == x1 - 1: r = u"\u2190"
        if y2 == y1 + 1: r = u"\u2193"
        if y2 == y1 - 1: r = u"\u2191"
    if u'start' in style and id == style[u'start']: r = u"A"
    if u'goal' in style and id == style[u'goal']: r = u"Z"
    if u'path' in style and id in style[u'path']: r = u"@"
    if id in graph.walls: r = u"#" * width
    return r

def draw_grid(graph, width=2, **style):
    for y in range(graph.height):
        for x in range(graph.width):
            print("%%-%ds" % width % draw_tile(graph, (x, y), style, width), end="")
        print()

## END A STAR SOURCE IMPLEMENTATION
