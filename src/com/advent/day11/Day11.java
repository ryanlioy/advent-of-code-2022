package advent.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;


public class Day11 {
    public static class Monkey{
        Vector<Long> items = new Vector<>();
        String operation;
        long test;
        int throwIfTrue, throwIfFalse;
        public Monkey(Vector<Long> items, String operation, long test, int throwIfTrue, int throwIfFalse){
            this.items = items;
            this. operation = operation;
            this.test = test;
            this.throwIfFalse = throwIfFalse;
            this.throwIfTrue = throwIfTrue;
        }

        @Override
        public String toString(){
            return items + "\n" + operation + "\n" + test + "\n" + throwIfTrue + "\n" + throwIfFalse;
        }
    }

    public static void main(String[] args) {
        var monkies1 = getInput("src/com/advent/day11/input.txt");
        System.out.println( part1(monkies1));
        var monkies2 = getInput("src/com/advent/day11/input.txt");
        System.out.println(part2(monkies2));
    }

    public static String part2(Vector<Monkey> monkeys){
        long[] inspections = new long[monkeys.size()];
        long prod = 1;
        for (Monkey m : monkeys){
            prod *= m.test;
        }

        for (int round = 0; round < 10_000; round++){
            int mNum = 0;
            for (Monkey m : monkeys){
                for (int a = 0; a < m.items.size();){
                    inspections[mNum]++;
                    long i = m.items.get(0);
                    String[] split = m.operation.split(" ");
                    long worry = 0;
                    if (split[2].equals("old")){
                        worry = i*i;
                    }
                    else {
                        switch (split[1]){
                            case "*":
                                worry = i * Long.parseLong(split[2]);
                                break;
                            case "+":
                                worry = i + Long.parseLong(split[2]);
                                break;
                            case "/":
                                worry = i / Long.parseLong(split[2]);
                                break;
                            case "-":
                                worry = i - Long.parseLong(split[2]);
                                break;
                        }
                    }
                    worry %= prod;
                    if (worry % m.test == 0){
                        monkeys.get(mNum).items.remove(a);
                        monkeys.get(m.throwIfTrue).items.add(worry);
                    }
                    else {
                        monkeys.get(mNum).items.remove(a);
                        monkeys.get(m.throwIfFalse).items.add(worry);
                    }
                }
                mNum++;
            }
            
        }
        Arrays.sort(inspections);

        BigInteger a = new BigInteger(Long.toString(inspections[inspections.length-1]));
        BigInteger b = new BigInteger(Long.toString(inspections[inspections.length-2]));
        return a.multiply(b).toString();
    }

    public static int part1(Vector<Monkey> monkeys){
        int[] inspections = new int[monkeys.size()];
        for (int round = 0; round < 20; round++){
            int mNum = 0;
            for (Monkey m : monkeys){
                for (int a = 0; a < m.items.size();){
                    inspections[mNum]++;
                    long i = m.items.get(0);
                    String[] split = m.operation.split(" ");
                    long worry = 0;
                    if (split[2].equals("old")){
                        worry = i*i;
                    }
                    else {
                        switch (split[1]){
                            case "*":
                                worry = i * Integer.parseInt(split[2]);
                                break;
                            case "+":
                                worry = i + Integer.parseInt(split[2]);
                                break;
                            case "/":
                                worry = i / Integer.parseInt(split[2]);
                                break;
                            case "-":
                                worry = i - Integer.parseInt(split[2]);
                                break;
                        }
                    }
                    worry /= 3;
                    if (worry % m.test == 0){
                        monkeys.get(mNum).items.remove(a);
                        monkeys.get(m.throwIfTrue).items.add(worry);
                    }
                    else {
                        monkeys.get(mNum).items.remove(a);
                        monkeys.get(m.throwIfFalse).items.add(worry);
                    }
                }
                mNum++;
            }
        }
        Arrays.sort(inspections);
        return inspections[inspections.length-1] * inspections[inspections.length-2];
    }

    private static Vector<Monkey> getInput(String path) {
        Vector<Monkey> lines = new Vector<>();
        try {
            Scanner scan = new Scanner(new File(path));
            Vector<Long> startingItems = new Vector<>();
            String op = "";
            long t = 0;
            int ifTrue = 0, ifFalse = 0;
            while (scan.hasNext()){
                String s = scan.nextLine();
                
                if (s.startsWith("  S")){
                    startingItems = new Vector<>();
                    String ss = s.substring(18);
                    // System.out.println(ss);
                    String[] items = ss.split(", ");
                    for (String i : items){
                        startingItems.add(Long.parseLong(i));
                    }
                }
                else if (s.startsWith("  O")){
                    op = s.substring(19);
                }
                else if (s.startsWith("  T")){
                    t = Integer.parseInt(s.substring(21));
                }
                else if (s.startsWith("    If true")){
                    ifTrue = Integer.parseInt(s.substring(29));
                }
                else if (s.startsWith("    If false")){
                    ifFalse = Integer.parseInt(s.substring(30));
                }

                if (s.startsWith("Monkey")){
                    lines.add(new Monkey(startingItems, op, t, ifTrue, ifFalse));
                }
            }
            lines.add(new Monkey(startingItems, op, t, ifTrue, ifFalse));
            scan.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        lines.remove(0);
        return lines;
    }
}
