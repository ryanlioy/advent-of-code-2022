package advent.day14;

import java.util.HashSet;
import java.util.Vector;

public class Cave {
    public static final Position Origin = new Position(500, 0);
    public static HashSet<Position> settledSand = new HashSet<>();
    public static HashSet<Position> rocks = new HashSet<>();
    public static int bottom;

    public Cave(HashSet<Position> rocks, int bottom){
        Cave.rocks = rocks;
        Cave.bottom = bottom;
    }

    public static boolean isOccupied(Position p){
        // third condition will always be false
        // so it won't effect part 1
        return settledSand.contains(p) || rocks.contains(p) || p.y == bottom+2;
    }

    public boolean dropSand(){
        Sand s = new Sand(Origin, this);
        while (s.fall()){
            if (isFinished(s.position)){
                return false;
            }
        }
        settledSand.add(s.position);

        return true;
    }

    public static boolean isFinished(Position p){
        return p.y >= bottom; // part 1
        // return p.y == 0; // part 2
    }

    public static Cave parse(Vector<String> rows){
        HashSet<Position> occupied = new HashSet<>();
        int bottomY = 0;
        for (String row : rows){
            String[] tokens = row.split(" -> ");
            Position[] positions = new Position[tokens.length];
            for (int i = 0; i < positions.length; i++){
                positions[i] = new Position(0, 0);
            }
            for (int i = 0; i < tokens.length; i++){
                positions[i] = Position.parse(tokens[i]);
            }
            
            for (int i = 0; i < positions.length - 1; i++){
                bottomY = Math.max(bottomY, positions[i].y);
                bottomY = Math.max(bottomY, positions[i + 1].y);
                HashSet<Position> segment = Position.BuildSegment(positions[i], positions[i + 1]);
                occupied.addAll(segment);
            }
        }
        return new Cave(occupied, bottomY);
    }
}
