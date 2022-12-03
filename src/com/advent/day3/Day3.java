package advent.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        ArrayList<String> rucksacks = getRucksacks("src/com/advent/daythree/input.txt");
        part1(rucksacks);
        part2(rucksacks);
    }

    public static void part2(ArrayList<String> rucksacks) {
        int sum = 0;
        final String alphabet = "-abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < rucksacks.size(); i+=3) {
            String a = rucksacks.get(i);
            String b = rucksacks.get(i+1);
            String c = rucksacks.get(i+2);

            for (int j = 0; j < a.length(); j++) {
                char letter = a.charAt(j);
                if (b.indexOf(Character.toString(letter)) != -1 && c.indexOf(Character.toString(letter)) != -1)  {
                    sum += alphabet.indexOf(Character.toString(letter));
                    break;
                }
            }
        }
        System.out.println(sum);
    }

    public static void part1(ArrayList<String> rucksacks) {
        int sum = 0;
        final String alphabet = "-abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (String s : rucksacks) {
            String firstHalf = s.substring(0, s.length() / 2);
            String secondHalf = s.substring(s.length() / 2);
            for (int i = 0; i < firstHalf.length(); i++) {
                char c = firstHalf.charAt(i);
                if (secondHalf.indexOf(Character.toString(c)) != -1) {
                    sum += alphabet.indexOf(Character.toString(c));
                    break;
                }
            }
        }
        System.out.println(sum);
    }

    public static ArrayList<String> getRucksacks(String path) {
        ArrayList<String> rucksacks = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(path));

            while (scan.hasNext()) {
                rucksacks.add(scan.nextLine());

            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        return rucksacks;
    }
}
