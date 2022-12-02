package advent.daytwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day2{
    public static void main(String[] args){
        HashMap<String, Integer> rule1 = new HashMap<>();
        rule1.put("A X", 4);
        rule1.put("A Y", 8);
        rule1.put("A Z", 3);
        rule1.put("B X", 1);
        rule1.put("B Y", 5);
        rule1.put("B Z", 9);
        rule1.put("C X", 7);
        rule1.put("C Y", 2);
        rule1.put("C Z", 6);

        HashMap<String, Integer> rule2 = new HashMap<>();
        rule2.put("A X", 3);
        rule2.put("A Y", 4);
        rule2.put("A Z", 8);
        rule2.put("B X", 1);
        rule2.put("B Y", 5);
        rule2.put("B Z", 9);
        rule2.put("C X", 2);
        rule2.put("C Y", 6);
        rule2.put("C Z", 7);
        
        day2("advent/daytwo/input.txt", rule1);
        day2("advent/daytwo/input.txt", rule2);
    }

    public static void day2(String path, HashMap<String, Integer> rules){
        int sum = 0;
        try {
            Scanner scan = new Scanner(new File(path));
            while (scan.hasNext()){
                String s = scan.nextLine();
                sum += rules.get(s);
            }
            scan.close();
        }
        catch (FileNotFoundException e){
            System.out.println(String.format("File %s not found", path));
        }
        System.out.println(sum);
    }
}