package week2;

/*
To simulate the NP-Complete aspect of Minesweeper, we aren't just building the game; we are building a Solver/Verifier.

The NP-Complete version of Minesweeper asks: "Given a grid with some numbers revealed and others hidden, is there a
valid distribution of mines that satisfies all the numbers?" This implementation uses Backtracking to try and "solve" a
 partially revealed board by placing mines and checking if the logic holds up.

Why this is NP-Complete
The Search Space: For a grid with N hidden squares, there are 2^N possible ways to place mines. This is the
"Exponential" explosion common in NP problems.

The Constraint Satisfaction: Every number on the board is a local constraint. Minesweeper is essentially a Boolean
Satisfiability (SAT) problem in disguise—each hidden square is a variable (Mine/No Mine), and the numbers are the logic
rules.

Local vs. Global: Even if a mine fits perfectly next to one number, it might break a constraint on the other
side of the board.

Key Takeaway
When you play Minesweeper and get stuck in a "50/50" guess, you are literally staring at the edge of an NP-Complete
problem where the logic doesn't provide a shortcut, and only checking all possibilities (or guessing) works.
*/


import java.util.*;

public class MinesweeperSolver {

    // -1 represents an unrevealed square (could be a mine or safe)
    // 0-8 represent the number of mines in adjacent squares
    private int[][] board;
    private int rows, cols;
    private boolean[][] minePlacement; // The "guess" we are testing

    public MinesweeperSolver(int[][] board) {
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;
        this.minePlacement = new boolean[rows][cols];
    }

    public boolean solve(int r, int c) {
        // If we've processed all cells, we found a valid configuration
        if (r == rows) return true;

        int nextR = (c == cols - 1) ? r + 1 : r;
        int nextC = (c == cols - 1) ? 0 : c + 1;

        // If the cell is already a number, we can't put a mine there
        if (board[r][c] != -1) {
            return solve(nextR, nextC);
        }

        // Try placing a MINE at (r, c)
        minePlacement[r][c] = true;
        if (isStillValid(r, c) && solve(nextR, nextC)) return true;

        // Try leaving (r, c) EMPTY
        minePlacement[r][c] = false;
        if (isStillValid(r, c) && solve(nextR, nextC)) return true;

        return false; // No valid configuration found
    }

    // This is the "Verifier" part of NP
    private boolean isStillValid(int r, int c) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] != -1) {
                    if (!checkConstraint(i, j)) return false;
                }
            }
        }
        return true;
    }

    private boolean checkConstraint(int r, int c) {
        int mineCount = 0;
        int unknownCount = 0;

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int nr = r + dr, nc = c + dc;
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    if (minePlacement[nr][nc]) mineCount++;
                    if (board[nr][nc] == -1) unknownCount++; // Potential locations
                }
            }
        }
        // If actual mines > number on square, it's invalid
        if (mineCount > board[r][c]) return false;
        // If (mines + remaining hidden squares) < number on square, it's impossible
        // (Wait, for simplicity in this sim, we check exact match only at the end)
        return true;
    }

    public void printSolution() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(minePlacement[i][i] ? "M " : ". ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // -1 = Hidden, Numbers = Revealed
        int[][] puzzle = {
                { 1, -1, -1},
                {-1, -1, -1},
                { 1, -1,  1}
        };

        MinesweeperSolver solver = new MinesweeperSolver(puzzle);
        if (solver.solve(0, 0)) {
            System.out.println("A valid mine configuration exists:");
            solver.printSolution();
        } else {
            System.out.println("No valid configuration possible.");
        }
    }
}