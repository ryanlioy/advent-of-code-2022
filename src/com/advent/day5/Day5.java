package advent.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

// was using stacks but stacks can't be empty
// ArrayList has no last element function
// vector does which makes things cleaner, also had no idea vector exists until I did this
// could do this in the same loop but it is 2:10 am and I have stand up at 9:15
// also kinda cheating because I'm not reading from the actual input, but they made parsing the inputs annoying
public class Day5 {
    public static void main(String[] args) {
        Vector<Vector<Integer>> input = getInput("src/com/advent/day5/input.txt");
        Vector<Vector<String>> stacks = getStacks("src/com/advent/day5/stacks.txt");
        part1(input, stacks);
        part2(input, stacks);
    }

    public static void part1(Vector<Vector<Integer>> input, Vector<Vector<String>> stacks){
        for (Vector<Integer> movement : input){
            Vector<String> source = stacks.get(movement.get(1)-1);
            Vector<String> destination = stacks.get(movement.get(2)-1);
            int amount = movement.get(0);
            for (int i = 0; i < amount; i++){
                String crate = source.lastElement();
                source.removeElementAt(source.size()-1);
                destination.add(crate);
            }
        }

        String top = "";
        for (Vector<String> stack : stacks){
            top += stack.lastElement();
        }
        System.out.println(top);
    }

    public static void part2(Vector<Vector<Integer>> input, Vector<Vector<String>> stacks){
        for (Vector<Integer> movement : input){
            Vector<String> source = stacks.get(movement.get(1)-1);
            Vector<String> destination = stacks.get(movement.get(2)-1);
            
            int amount = movement.get(0);
            int lowerBound = source.size()-amount;

            for (int i = lowerBound >= 0 ? lowerBound : 0; i < source.size();){
                destination.add(source.get(i));
                source.remove(i);
            }
        }

        String top = "";
        for (Vector<String> stack : stacks){
            if (!stack.isEmpty())
            top += stack.lastElement();

        }
        System.out.println(top);
    }

    public static Vector<Vector<String>> getStacks(String path){
        Vector<Vector<String>> lines = new Vector<>();
        try {
            Scanner scan = new Scanner(new File(path));
            while (scan.hasNext()) {
                Vector<String> stack = new Vector<>();
                String[] characters = scan.nextLine().split(",");
                for (String s : characters){
                    stack.add(s);
                }
                lines.add(stack);
            }
            scan.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        return lines;
    }

    // read input file
    private static Vector<Vector<Integer>> getInput(String path) {
        Vector<Vector<Integer>> lines = new Vector<>();
        try {
            Scanner scan = new Scanner(new File(path));
            while (scan.hasNext()) {
                Vector<Integer> currentLine = new Vector<>();
                String[] words = scan.nextLine().split(" ");
                for (int i = 1; i < 6; i += 2){
                    currentLine.add(Integer.parseInt(words[i]));
                }
                lines.add(currentLine);
            }
            scan.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        return lines;
    }
}
