package advent.day8;

import java.util.Vector;

public class Day8Part2 {
    public static int part2(Vector<Vector<Integer>> grid) {
        int total = 0;
        for (int i = 1; i < grid.size() - 1; i++) {
            var row = grid.get(i);
            for (int j = 1; j < row.size() - 1; j++) {
                int n = grid.get(i).get(j);
                int score = checkRight(grid, n, i, j) * checkLeft(grid, n, i, j) * checkUp(grid, n, i, j)
                        * checkDown(grid, n, i, j);
                total = total < score ? score : total;
            }
        }
        return total;
    }

    public static int checkRight(Vector<Vector<Integer>> grid, int n, int r, int c) {
        int score = 0;
        for (int i = c + 1; i < grid.get(0).size(); i++) {
            if (n <= grid.get(r).get(i)) {
                score++;
                break;
            }
            score++;
        }
        return score;
    }

    public static int checkLeft(Vector<Vector<Integer>> grid, int n, int r, int c) {
        int score = 0;
        for (int i = c - 1; i >= 0; i--) {
            if (n <= grid.get(r).get(i)) {
                score++;
                break;
            }
            score++;
        }
        return score;
    }

    public static int checkUp(Vector<Vector<Integer>> grid, int n, int r, int c) {
        int score = 0;
        for (int i = r - 1; i >= 0; i--) {
            if (n <= grid.get(i).get(c)) {
                score++;
                break;
            }
            score++;
        }
        return score;
    }

    public static int checkDown(Vector<Vector<Integer>> grid, int n, int r, int c) {
        int score = 0;
        for (int i = r + 1; i < grid.size(); i++) {
            if (n <= grid.get(i).get(c)) {
                score++;
                break;
            }
            score++;
        }
        return score;
    }
}
