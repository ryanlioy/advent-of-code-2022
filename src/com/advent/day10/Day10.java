package advent.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Day10 {
    public static void main(String[] args) {
        var instructions = getInput("src/com/advent/day10/input.txt");
        part1(instructions);
        part2(instructions);
    }

    public static void part2(Vector<String> instructions) {
        int cycle = 0, reg = 1;
        String[][] image = new String[6][40];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                image[i][j] = ".";
            }
        }
        for (String s : instructions) {
            if (s.startsWith("noop")) {
                int pos = reg;
                int draw = cycle % 40;
                if (pos == draw || pos == draw + 1 || pos == draw - 1) {
                    image[cycle / 40][draw % 40] = "#";
                }
                cycle++;
            }
            else {
                for (int i = 0; i < 2; i++) {
                    int pos = reg;
                    int draw = cycle % 40;
                    if (pos == draw || pos == draw + 1 || pos == draw - 1) {
                        image[cycle / 40][draw % 40] = "#";
                    }
                    cycle++;
                }

                reg += Integer.parseInt(s.split(" ")[1]);
            }

        }
        for (String[] line : image) {
            String s = "";
            for (String pixel : line) {
                s += pixel;
            }
            System.out.println(s);
        }
    }

    public static void part1(Vector<String> instructions) {
        int cycle = 0, reg = 1, sum = 0;
        for (String s : instructions) {
            if (s.startsWith("noop")) {
                cycle++;
                if (isInteresting(cycle) && cycle < 221) {
                    sum += cycle * reg;
                }
            }
            else {
                for (int i = 0; i < 2; i++) {
                    cycle++;
                    if (isInteresting(cycle) && cycle < 221) {
                        sum += cycle * reg;
                    }
                }

                reg += Integer.parseInt(s.split(" ")[1]);
            }
        }
        System.out.println(sum);
    }

    public static boolean isInteresting(int n) {
        return n == 20 || (n - 20) % 40 == 0;
    }

    public static Vector<String> getInput(String path) {
        Vector<String> moves = new Vector<>();
        try {
            Scanner scan = new Scanner(new File(path));
            while (scan.hasNext()) {
                moves.add(scan.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        return moves;
    }
}
