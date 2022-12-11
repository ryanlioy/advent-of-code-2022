package advent.day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class Day9 {
    public static class Point{
        public int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString(){
            return "("+this.x+","+this.y+")";
        }
    }

    public static void main(String[] args) {
        var input = getInput("src/com/advent/day9/input.txt");
        System.out.println(calculate(2, input));
        System.out.println(calculate(10, input));
    }

    public static void follow(Point h, Point t){
        if (Math.abs(h.x - t.x) <= 1 && Math.abs(h.y - t.y) <= 1){
            return;
        }
        int dx = h.x - t.x;
        int dy = h.y - t.y;
        t.x += Math.signum(dx);
        t.y += Math.signum(dy);
    }

    public static int calculate(int n, Vector<String> moves){
        // toString() is easier than hashCode() to implement
        Set<String> visited = new HashSet<>(); 
        Vector<Point> knots = new Vector<>();
        
        for (int i = 0; i < n; i++){
            knots.add(new Point(0,0));
        }
        visited.add(knots.get(0).toString());

        Point head = knots.get(0);
        Point tail = knots.get(knots.size()-1); 

        for (var command : moves){
            String direction = command.split(" ")[0];
            int times = Integer.parseInt((command.split(" ")[1]));
            for (int i =0 ; i < times; i++){
                if (direction.equals("U")){
                    (head.y)++;
                }
                else if (direction.equals("D")){
                    head.y--;
                }
                else if (direction.equals("L")){
                    head.x--;
                }
                else if (direction.equals("R")){
                    head.x++;
                }

                for (int j = 1; j < knots.size(); j++){
                    Point t = knots.get(j);
                    Point h = knots.get(j-1);
                    follow(h,t);
                    visited.add(tail.toString());
                }
            }
        }
        return visited.size();
    }

    public static Vector<String> getInput(String path){
        Vector<String> moves = new Vector<>();
        try {
            Scanner scan = new Scanner(new File(path));
            while (scan.hasNext()) {
                moves.add(scan.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        return moves;
    }
}
