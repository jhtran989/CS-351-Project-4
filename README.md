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
