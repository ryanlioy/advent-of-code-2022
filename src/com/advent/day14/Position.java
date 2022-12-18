package advent.day14;

import java.util.HashSet;

public class Position {
    public int x, y;

    public static Position down = new Position(0, 1);
    public static Position downLeft = new Position(-1, 1);
    public static Position downRight = new Position(1, 1);

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position add(Position p){
        return new Position(this.x + p.x, this.y + p.y);
    }

    public static Position parse(String position){
        String[] tokens = position.split(",");
        return new Position(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
    }

    public static HashSet<Position> BuildSegment(Position start, Position end){ // this is infinite, need toCompare method
        HashSet<Position> ps = new HashSet<>();
        ps.add(start);
        while (!start.equals(end)) {
            int diffX = (int) Math.signum(end.x - start.x);
            int diffY = (int) Math.signum(end.y - start.y);
            start = new Position(start.x + diffX, start.y + diffY);
            ps.add(start);
        }
        return ps;
    }

    @Override
    public String toString(){
        return "("+this.x+" "+this.y+")";
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position p = (Position) o;
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode(){
        return 19 * this.x + 17*this.y;
    }
}
