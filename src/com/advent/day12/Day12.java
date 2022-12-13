package advent.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Day12 {
    public static void main(String[] args) {
        String[][] grid = getInput("src/com/advent/day12/input.txt");
        System.out.println(part1(grid));
        System.out.println(part2(grid));
    }

    public static int part1(String[][] grid){
        HashMap<Node, Integer> heights = new HashMap<>();
        Node start = null, end = null;
        for(int i = 0; i < grid.length; i++) {
            String[] line = grid[i];
            for(int j = 0; j < line.length; j++) {
                if(line[j].equals("S")){
                    start = new Node(j,i);
                }
                else if(line[j].equals("E")){
                    end = new Node(j,i);
                }
                else{
                    heights.put(new Node(j,i), (int) line[j].charAt(0));
                }
            }
        }
                
        heights.put(start, (int) 'a');
        heights.put(end, (int)'z');

        return bfs(start, end, heights);
    }

    public static int part2(String[][] grid){
        HashMap<Node, Integer> heights = new HashMap<>();
        Node start = null, end = null;
        for(int i = 0; i < grid.length; i++) {
            String[] line = grid[i];
            for(int j = 0; j < line.length; j++) {
                if(line[j].equals("S")){
                    start = new Node(j,i);
                }
                else if(line[j].equals("E")){
                    end = new Node(j,i);
                }
                else{
                    heights.put(new Node(j,i), (int) line[j].charAt(0));
                }
            }
        }
                
        heights.put(start, (int) 'a');
        heights.put(end, (int)'z');

        int smallest = 1_000_000_000;
        for (Node n : heights.keySet()){
            if (heights.get(n) == 'a'){
                int i = bfs(n, end, heights);
                smallest = i < smallest ? i : smallest;
            }
        }
        return smallest;
    }
    
    public static int bfs(Node start, Node end, HashMap<Node, Integer> heights){
        HashMap<Node,Integer> cost = new HashMap<>();
        HashMap<Node,Node> parents = new HashMap<>();
        Vector<Node> visited = new Vector<>();
        cost.put(start, 0);
        visited.add(start);
        while(visited.size() > 0) {
            Node current = visited.firstElement();
            visited.remove(0);
            if(current.equals(end)) {
                Vector<Node> path = new Vector<>();
                while(parents.containsKey(current)) {
                    path.add(current);
                    current = parents.get(current);
                }
                return path.size();
            }
            for(Node n : current.neighbors()) {
                if(!heights.containsKey(n) || heights.get(n) > heights.get(current) + 1){
                    continue;
                }
                int g = cost.get(current) + 1;
                if(g < cost.getOrDefault(n, Integer.MAX_VALUE)) {
                    cost.put(n, g);
                    parents.put(n, current);
                    visited.add(n);
                }
            }
        }
        return 100_000_000;
    }

    public static String[][] getInput(String path){
        String[][] grid = new String[41][];
        try {
            Scanner scan = new Scanner(new File(path));
            int i = 0;
            while (scan.hasNext()) {
                grid[i] = scan.nextLine().split("");
                i++;
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        return grid;
    }
}
