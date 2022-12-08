package advent.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class ReadInput {
    public static Vector<Vector<Integer>> getInput(String path){
        Vector<Vector<Integer>> grid = new Vector<>();
        try {
            Scanner scan = new Scanner(new File(path));
            while (scan.hasNext()) {
                String[] line = scan.nextLine().split("");
                Vector<Integer> vector = new Vector<>();
                for (String s : line){
                    vector.add(Integer.parseInt(s));
                }
                grid.add(vector);
                
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found", path));
        }
        return grid;
    }
}
