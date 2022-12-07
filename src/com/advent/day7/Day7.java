package advent.day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Day7 {
    private static class Node {
        public int size;
        public Node parent;
        public Vector<Node> children = new Vector<>();

        public Node(){}
    }

    public static void main(String[] args) {
        Node root = generateTree("src/com/advent/day7/input.txt");

        int part1 = part1(root);
        System.out.println(part1);

        int spaceNeeded = 30_000_000 - (70_000_000 - sumSize(root));
        int part2 = part2(root, spaceNeeded);
        System.out.println(part2);
    }

    public static Node generateTree(String path){
        Node root = new Node();
        Node current = root;
        try {
            Scanner scan = new Scanner(new File(path));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                String[] split = line.split(" ");
                if (line.endsWith("/") || line.startsWith("dir")){ // we can ignore these lines
                    continue;
                }
                else if (split[0].equals("$")){
                    if (split[1].equals("cd")) {
                        if (split[2].equals("..")){
                            current = current.parent;
                        }
                        else {
                            Node next = new Node();
                            current.children.add(next);
                            next.parent = current;
                            current = next;
                        }
                    }
                }
                else {
                    current.size += Integer.parseInt(split[0]);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        return root;
    }

    public static int part1(Node root){
        int total = 0;
        for (Node n : root.children){
            total += part1(n);
        }

        int sum = sumSize(root);
        if (sum <= 100_000){
            total += sum;
        }
        return total;
    }

    public static int part2(Node root, int spaceNeeded){
        int smallest = 2_000_000_000; // hope this is large enough

        for (Node n : root.children){
            smallest = Math.min(smallest, part2(n, spaceNeeded));
        }

        int sum = sumSize(root);
        if (sum > spaceNeeded){
            smallest = Math.min(smallest, sum);
        }
        return smallest;
    }

    public static int sumSize(Node root){
        int sum = 0;
        for (Node n : root.children){
            sum += sumSize(n);
        }
        sum += root.size;
        return sum;
    }
}
