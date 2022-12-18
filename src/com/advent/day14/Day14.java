package advent.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Day14 {

    public static void main(String[] args) {
        var rocks = getInput("src/com/advent/day14/input.txt");
        Cave cave = Cave.parse(rocks);
        int prev = 0;
        do {
            prev = cave.settledSand.size();
            cave.dropSand();
        }
        while (cave.settledSand.size() > prev);
        System.out.println(cave.settledSand.size());
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
            scan.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        return lines;
    }
}
