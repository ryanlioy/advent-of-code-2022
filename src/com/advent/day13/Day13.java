package advent.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Day13 {
    public static void main(String[] args) {
        var lines = getInput("src/com/advent/day13/input.txt");
        System.out.println(part1(lines));
        System.out.println(part2(lines));
    }

    public static int part1(Vector<String> input) {
        int sum = 0;
        for (int i = 0, pair = 1; i < input.size(); i += 2, pair++) {
            Tree left = new Tree(input.get(i));
            Tree right = new Tree(input.get(i + 1));
            sum += left.compareTo(right) < 0 ? pair : 0;
        }
        return sum;
    }

    public static int part2(Vector<String> input) {
        Vector<Tree> trees = new Vector<>();
        Tree divider1 = new Tree("[[2]]");
        Tree divider2 = new Tree("[[6]]");
        trees.add(divider1);
        trees.add(divider2);
        for (int i = 0; i < input.size(); i += 2) {
            trees.add(new Tree(input.get(i)));
            trees.add(new Tree(input.get(i + 1)));
        }
        Collections.sort(trees);
        return (trees.indexOf(divider1)+1) * (trees.indexOf(divider2)+1);
    }

    public static Vector<String> getInput(String path){
        Vector<String> lines = new Vector<>();
        try {
            Scanner scan = new Scanner(new File(path));
            
            while (scan.hasNext()) {
                String s =scan.nextLine();
                if (!s.equals("")){
                    lines.add(s);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        return lines;
    }
}
