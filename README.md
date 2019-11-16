# Sujet Aventurier

A hero adventures himself in a dangerous world fighting is way through the dark woods.

Reproduce the movements of the hero on a map.The map is modelled using characters in a text file in UTF-8 format.

The movements of the hero are defined by a file with the following characteristics:

encoding: UTF-8

First line:
    Contains the initial coordinates of the hero in the form "x, y"
    The coordinates (0,0) correspond to the upper left corner of the map    

Second line:
    Movement of the hero defined as a succession of characters representing the cardinal directions (N, S, E and O, note that O accounts for West)
    Each character corresponds to the displacement of a box    

The hero cannot go beyond the edges of the map.

The hero cannot go on the squares occupied by the impenetrable woods.

# Extra Instructions

The files (map and movements) must be defined at config.properties

When reach the north (top) edge, move west (left).

When reach the south (down) edge, move east (right).

When reach the east (right) edge, move south (down).

When reach the west (left) edge, move north (up).

When reach the northwest corner (top, left), stop moving.

When reach the southeast corner (down, right), stop moving.
