package advent.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

// this one is pretty easy... that is scary
public class Day6 {
    public static void main(String[] args) {
        var input = getInput("src/com/advent/day6/input.txt");
        part1(input);
        part2(input);
    }

    public static void part1(String dataStream){
        for (int i = 0; i < dataStream.length()-4; i++){
            char[] subString = dataStream.substring(i, i+4).toCharArray();
            // use a set to test if all the elements are unique
            var set = new HashSet<>();
            for (char c : subString){
                set.add(c);
            }
            if (set.size() == 4){
                System.out.println(i+4);
                break;
            }
        }
    }

    public static void part2(String dataStream){
        for (int i = 0; i < dataStream.length()-14; i++){
            char[] subString = dataStream.substring(i, i+14).toCharArray();
            var set = new HashSet<>();
            for (char c : subString){
                set.add(c);
            }
            if (set.size() == 14){
                System.out.println(i+14);
                break;
            }
        }
    }

    private static String getInput(String path) {
        String line = "";
        try {
            // only one line in the file
            Scanner scan = new Scanner(new File(path));
            line = scan.nextLine();
            scan.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        return line;
    }
}