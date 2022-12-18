package advent.day14;

public class Sand {
    Position position;
    Cave cave;
    public Sand(Position start, Cave cave){
        this.position = start;
        this.cave = cave;
    }

    public boolean fall() {   
        if (!cave.isOccupied(this.position.add(Position.down))){
            this.position = this.position.add(Position.down);
            return true;
        }
        if (!cave.isOccupied(this.position.add(Position.downLeft))){
            this.position = this.position.add(Position.downLeft);
            return true;
        }
        if (!cave.isOccupied(this.position.add(Position.downRight))){
            this.position = this.position.add(Position.downRight);
            return true;
        }
        return false;
    }
}
