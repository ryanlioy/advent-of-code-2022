package advent.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        ArrayList<String> input = getInput("src/com/advent/day4/input.txt");
        int total1 = 0;
        int total2 = 0;

        for (String line : input) {
            // split the ranges of each partner
            String[] split = line.split(",");

            // split the ranges
            String[] partnerRange1 = split[0].split("-");
            String[] partnerRange2 = split[1].split("-");

            // expand the ranges
            ArrayList<Integer> partner1 = expandRange(Integer.parseInt(partnerRange1[0]), Integer.parseInt(partnerRange1[1]));
            ArrayList<Integer> partner2 = expandRange(Integer.parseInt(partnerRange2[0]), Integer.parseInt(partnerRange2[1]));

            total1 += part1(partner1, partner2);
            total2 += part2(partner1, partner2);
        }

        System.out.println(total1);
        System.out.println(total2);
    }

    public static int part1(ArrayList<Integer> partner1, ArrayList<Integer> partner2) {
        if (partner1.containsAll(partner2) || partner2.containsAll(partner1)) {
            return 1;
        }
        return 0;
    }

    public static int part2(ArrayList<Integer> partner1, ArrayList<Integer> partner2) {
        for (Integer i : partner1) {
            if (partner2.contains(i)) {
                return 1;
            }
        }
        return 0;
    }

    // read input file
    private static ArrayList<String> getInput(String path) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(path));
            while (scan.hasNext()) {
                lines.add(scan.nextLine());
            }
            scan.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        return lines;
    }

    // expand a range, so 1-5 is 1,2,3,4,5
    private static ArrayList<Integer> expandRange(int min, int max) {
        ArrayList<Integer> range = new ArrayList<>();
        for (int i = min; i < max + 1; i++) {
            range.add(i);
        }
        return range;
    }
}
