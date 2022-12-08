package advent.day8;

import java.util.Vector;

public class Day8Part1 {
    public static int part1(Vector<Vector<Integer>> grid) {
        int total = ((2 * grid.size()) + (2 * grid.get(0).size()) - 4);
        for (int i = 1; i < grid.size() - 1; i++) {
            var row = grid.get(i);
            for (int j = 1; j < row.size() - 1; j++) {
                if (checkRight(grid, row.get(j), i, j) ||
                        checkLeft(grid, row.get(j), i, j) ||
                        checkUp(grid, row.get(j), i, j) ||
                        checkDown(grid, row.get(j), i, j)) {
                    total++;
                }
            }
        }
        return total;
    }

    public static boolean checkRight(Vector<Vector<Integer>> grid, int n, int r, int c) {
        for (int i = c + 1; i < grid.get(0).size(); i++) {
            if (n <= grid.get(r).get(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkLeft(Vector<Vector<Integer>> grid, int n, int r, int c) {
        for (int i = c - 1; i >= 0; i--) {
            if (n <= grid.get(r).get(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkUp(Vector<Vector<Integer>> grid, int n, int r, int c) {
        for (int i = r - 1; i >= 0; i--) {
            if (n <= grid.get(i).get(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDown(Vector<Vector<Integer>> grid, int n, int r, int c) {
        for (int i = r + 1; i < grid.size(); i++) {
            if (n <= grid.get(i).get(c)) {
                return false;
            }
        }
        return true;
    }
}
