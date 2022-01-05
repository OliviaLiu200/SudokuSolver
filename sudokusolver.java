public class sudokusolver {
    // returns true if num can be placed in the row and, false otherwise
    private static boolean canPutNumInRow(int[][] grid, int num, int r) {
        for (int i = 0; i < 9; i++) {
            if (grid[r][i] == num) {
                return false;
            }
        }
        return true;
    }

    // returns true if num can be placed in the column, and false otherwise
    private static boolean canPutNumInCol(int[][] grid, int num, int c) {
        for (int i = 0; i < 9; i++) {
            if (grid[i][c] == num) {
                return false;
            }
        }
        return true;
    }

    // returns true if num can be placed in the 3x3 square, and false otherwise
    private static boolean canPutNumInSqr(int[][] grid, int num, int r, int c) {
        int lowerBoundRow = r - r % 3;
        int lowerBoundCol = c - c % 3;
        int upperBoundRow = lowerBoundRow + 3;
        int upperBoundCol = lowerBoundCol + 3;

        for (int i = lowerBoundRow; i < upperBoundRow; i++) {
            for (int j = lowerBoundCol; j < upperBoundCol; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // returns true if num can be placed in the position, and false otherwise
    private static boolean canPutNumThere(int[][] grid, int num, int r, int c) {
        return canPutNumInRow(grid, num, r) && canPutNumInCol(grid, num, c)
                && canPutNumInSqr(grid, num, r, c);
    }

    // solves the sudoku
    private static boolean solveGrid(int[][] grid) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (grid[r][c] == 0) {
                    for (int i = 1; i <= 9; i++) {
                        if (canPutNumThere(grid, i, r, c)) {
                            grid[r][c] = i; // if the number can be placed there, try it
                            // recurse through the rest of the grid to make sure the number that is tried
                            // works
                            if (solveGrid(grid)) { // if solveGrid is true, meaning all the recursive calls beyond that
                                                   // is also true
                                return true;
                            } else { // else, it clears out the number we are trying back to 0
                                grid[r][c] = 0;
                            }
                            ;
                        }
                    }
                    // when we run through every number (1-9) and non of them are valid, return
                    // false
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 8, 0, 0, 0, 0, 1, 3 },
                { 0, 0, 0, 9, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 8, 0, 0, 4 },
                { 0, 1, 0, 0, 4, 0, 5, 0, 0 },
                { 0, 3, 0, 0, 0, 9, 0, 0, 0 },
                { 0, 8, 5, 0, 0, 0, 0, 7, 0 },
                { 0, 2, 0, 0, 0, 7, 3, 0, 0 },
                { 0, 0, 0, 0, 9, 4, 0, 0, 6 },
                { 4, 0, 0, 0, 6, 0, 0, 0, 0 }
        };
        if (solveGrid(grid)) {
            System.out.println("Here! Solved! Hoorayyy!");
        } else {
            System.out.println("This Sudoko is unsolvable :((");
        }

        // outputting the grid
        System.out.println("------------");
        for (int r = 0; r < 9; r++) {
            if ((r == 3) || (r == 6)) {
                System.out.println("------------");
            }
            for (int c = 0; c < 9; c++) {
                if ((c == 3) || (c == 6)) {
                    System.out.print("|");
                }
                System.out.print(grid[r][c]);
            }
            System.out.println();
        }
        System.out.println("------------");
    }
}