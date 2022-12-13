package advent.day12;

import java.util.Objects;
import java.util.Vector;

public class Node {
	public int x = 0;
	public int y = 0;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Node() {
		this.x = 0;
		this.y = 0;
	}	

	@Override
	public int hashCode() {
		return Objects.hash(y,x);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
            return true;
        }
		if (obj == null){
            return false;
        }
		if (getClass() != obj.getClass()){
			return false;
        }
		Node other = (Node) obj;
		if (this.x != other.x){
			return false;
        }
		if (this.y != other.y){
			return false;
        }
		return true;
	}
	
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

	public Vector<Node> neighbors() {
        // thank you stack overflow
		Vector<Node> neighbors = new Vector<>();
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(j == 0 || i == 0) {
					neighbors.add(new Node(this.x+j,this.y+i));
				}
			}
		}
		return neighbors;
	}
}
