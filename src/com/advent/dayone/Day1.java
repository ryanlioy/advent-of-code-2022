package advent.dayone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day1{
    public static void main(String[] args){
        ArrayList<ArrayList<Integer>> input = parseFile("advent/dayone/input.txt");
        System.out.println(findLargest(input));
        System.out.println(sumOfTopThree(input));
    }

    public static int sumOfTopThree(ArrayList<ArrayList<Integer>> elves){
        ArrayList<Integer> sums = new ArrayList<>();
        for (ArrayList<Integer> array : elves){
            int sum = 0;
            for (Integer i : array){
                sum += i;
            }
            sums.add(sum);
        }
        Collections.sort(sums, Collections.reverseOrder());

        return sums.get(0)+sums.get(1)+sums.get(2);
    }

    public static int findLargest(ArrayList<ArrayList<Integer>> elves){
        int largest = 0;
        for (ArrayList<Integer> array : elves){
            int sum = 0;
            for (Integer i : array){
                sum += i;
            }
            if (sum > largest){
                largest = sum;
            }
        }
        return largest;
    }

    public static ArrayList<ArrayList<Integer>> parseFile(String path){
        ArrayList<String> lines = new ArrayList<>();
        try{
            Scanner scan = new Scanner(new File(path));
            while (scan.hasNext()){
                String s = scan.nextLine();
                lines.add(s);
            }
            scan.close();
        }
        catch (FileNotFoundException e){
            System.out.println(String.format("File %s not found", path));
        }

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++){
            for (int j = i; j < lines.size(); j++){
                if (lines.get(j).equals("")){
                    ArrayList<Integer> ints = new ArrayList<>();
                    for (int k = i; k < j; k++){
                        ints.add(Integer.parseInt(lines.get(k)));
                    }
                    list.add(ints);
                    i = j;
                    break;
                }
            }
        }
        return list;
    }
}