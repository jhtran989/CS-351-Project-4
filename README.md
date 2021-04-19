# README
## CS 351 - 001/002
### Project 4: Mazes
#### Todd Sipe & John Tran

### Introduction

For this project, I suspect that three late extension days will be needed.

In this Mazes project, we created a series of Maze Generators and Solvers and will list the ones implemented below:

Maze Generators
- DFS (Depth First Search)
- Kruskal
- Prim
- Aldous-Broder (***Extra Credit***)

Maze Solvers
- Mouse (NO Multithreading)
- Wall
- Pledge

Due to time constraints, NO Multithreading of any kind was implemented in this project.

### IMPORTANT

Throughout the project, we decided to split up the work and Todd worked on a temporary project (named "MazesTemporary"), which will also be shared with you, separate from the "main" project (just named "Mazes") and I helped port over everything (including implementing some of the generators/solvers mentioned above), handling all the abstraction and organization of the main project.

Due to time constraints, a lot of the porting from Todd's project was pretty raw and didn't fully utilize the abstraction of the project (however, the port itself was relatively simple, changing the variable names to match the main project).

### Instructions

The `.jar` files are configured with the debug messages ON. However, since the major part is the JavaFX (animation), hopefully the console outputs won't be too much of an issue. As a result, however, it might take a few seconds for everything to load up (maze grid printed out during steps in the generator/solver).

Actually, while testing the `mazes.jar`, it took a little longer than expected (at least, when compared to running it in IntelliJ) to load up the "action sequence" (mentioned below), so we'll try to remove the print statements of the board while the internal maze generation/solver is executing...

EDIT: With the new jar having removed most of the initial print statements (at least, before the animation begins), there are now TWO .jar files (one before and one after the removal of the print statements): `oldMazes.jar` and `newMazes.jar` where the "old" jar is the one before the removal, and the "new" jar is the one after the removal.

The `.jar` files are found at the root and can be run with the usual
```
java -jar JAR_NAME input_file_name.txt
```
where `JAR_NAME` is the name of the jar file. For this project, only one `.jar` file was created: `mazes.jar` (EDIT: see note above). In addition, as specified in the project spec, there will be a command-line argument where `input_file_name.txt` specifies which input file to initialize the maze (generator and solver).

There will be a total of 12 input files (one for each combination of 4 maze generators with 3 maze solvers with an arbitrary maze dimension &mdash; same for all of the input files). The details of the input files can be viewed directly from the `resources` folder at the root of the main project.

### Doc notes

As usual, our design doc will be located in the `Doc` folder at the root of the project. However, with time constraints, the doc was fairly simple this time and there wasn't enough time to add descriptions of the objects in the doc. The design of the project should help with the relationship of the objects and how they're related (any comments should also help as well as this README).

### Design Overview

The overall layout of the project was pretty simple, breaking up the generators/solvers into a general abstract class where the specific implementation will implement the abstract `generateMaze()` and abstract `solveMaze()` methods, respectively. In addition, there's a "factory" method that generates the specified generator/solver in the given input file.

There were also various "Type" enums that held constants for the different types of generators/solvers as well as cells and other aspects of the maze. They help encapsulate the various types and makes reusing and fetching the types relatively straightforward for processing.

Also, to handle the actual visualization itself (separate from the internal maze generation/solver), we implemented an "action sequence" of sorts that held a list of "actions" to be displayed by the animation (each action executed with each call of the `run()` method of our animation timer). Thus, there will be some sort of "tracker" for the solvers (and for the Aldous-Broder maze generation, mentioned below in the Afternotes section) to visually display where the current cell being checked is.

In Todd's temporary project, he decided to focus solely on the "path cells" and visually represent the "walls" as edges to the path cells (updating as the animation progresses). However, I decided to incorporate "walls" as actual cells and that did make things a little more complicated, but the same logic (dealing with only "paths") was used and a little code had to be written up to account for updating the walls (especially with the tracker so the tracker would also go through walls marked as a path, i.e. already broken down).

There are various colors used to denote the different "states" we coded for the cells, which can be found in the `CellType` class.

Specifically relating to the DFS, we implemented a visual aid for the backtracking where a different color is used for the backtracking (in this case, blue). Then, once the maze was generated, the "marker for the backtracking" is removed and the color returns back to white (color for normal path cells).

### Rushed Comments in the Code

Again, given the time constraints, many of the comments were a little rushed and only the public methods were commented. Further, if there were some methods that were overridden, only the topmost super class will have a comment (generally speaking, but the only difference should be implementation and the design document should help clarify the differences between the two versions).

Also, any get and set methods are generally inferable, so they won't be commented (including simple methods, like drawing a domino). The exceptions are also an exception (no pun intended) since the exception itself can be inferred from the class name.

EDIT: the comments were REALLY rushed this time, but the overall layout of the project should help break down the key elements of the project (only the more important methods will be commented &mdash; those that require a brief explanation besides the hint given from the name of the method).

### Known Bugs

Everything should be working as intended (unless a specific bug with the initial configuration of the maze generator/solver was missed). However, as said above, NO Multithreading was implemented.

### Afternotes

No afternotes this time...

- For the Aldous-Broder maze generation algorithm, there was a "tracker" unique to it (for maze generators) that showed where the current cell being checked is.

### Resources

https://hurna.io/academy/algorithms/maze_generator/index.html

### Initial Logs

4/6/2021:
Todd added logic to main to read in the file.
Todd added the classes mazePieces.Cell, mazeGenerator.MazeGenerator, mazeSolver.MazeSolver, and enums
mazeGenerator.MazeGeneratorType, mazeSolver.MazeSolverType.

4/7/2021:
Todd added GUI elements to get started. The program appropriately creates a
Canvas in a Pane to work with of the correct size. (The passed in mazeSize).
Todd created the mazePieces.Cell object, assigning it various values.

John:
The `Cell` class could extend `Rectange` so any `Cell` objects could just be added to the children of the pane.
The field for a `Pane` object isn't needed in the `Cell` class and could be done outside (like the `MazeBoard` class).
The member variables (`boolean`) `left`, `down`, `left`, `right` could also be moved to a higher abstaction (again, the `MazeBoard` class) and it allows for much easier access to the entire board of `Cell` objects.

4/8/2021:
John:
When finding the neighbors when working with the cells directly, we check for cells two "cells" away from the current cell being checked in all four directions. Here, the constant `CELL_OUT_OF_BOUNDS` is useful here since its a cell type it bypasses some problems had we used `null` instead.

Also, when creating `List` objects, they'll be implemented as an `ArrayList`.

For the cell types, we might want to split it up further into `WALL` and `PATH` (both visited and unvisited), maybe with an interface (or abstract class with a field for if the cell was visited for a `PATH`).
