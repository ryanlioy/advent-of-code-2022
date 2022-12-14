package advent.day13;

import java.util.List;
import java.util.Vector;

public class Tree implements Comparable<Tree> {
    private String contents;
    private final Vector<Tree> children = new Vector<>();
    private int value;
    private boolean isInteger = true;

    public Tree(String input) {
        this.contents = input;
        if (input.startsWith("[")) {
            parse(input);   
        }
        else {
            this.value = Integer.parseInt(this.contents);
        }
    }

    private void parse(String input) {
        input = input.substring(1, input.length() - 1);
        this.isInteger = false;
        int depth = 0;
        String num = "";
        for (char c : input.toCharArray()) {
            if (depth == 0 && c == ',') {
                children.add(new Tree(num.toString()));
                num = "";
            } else {
                if (c == '[') {
                    depth++;
                } else if (c == ']') {
                    depth--;
                }
                num += c;
            }
        }
        if (!num.isEmpty()) {
            children.add(new Tree(num));
        }
    }

    @Override
    public String toString() {
        return contents;
    }

    @Override
    public int compareTo(Tree t) {
        if (this.isInteger() && t.isInteger()) {
            // ints
            return Integer.compare(this.value(), t.value());
        }
        else if (!this.isInteger() && !t.isInteger()) {
            // lists
            for (int i = 0; i < Math.min(this.children().size(), t.children().size()); i++) {
                int cmp = this.children().get(i).compareTo(t.children().get(i));
                if (cmp != 0) {
                    return cmp;
                }
            }
            return Integer.compare(this.children().size(), t.children().size());
        }
        else if (this.isInteger()) {
            // int list
            return new Tree("[" + value() + "]").compareTo(t);
        }
        else {
            // list int
            return this.compareTo(new Tree("[" + t.value() + "]"));
        }
    }

    @Override
    public int hashCode() {
        return 17 + ((contents == null) ? 0 : contents.hashCode());
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj){
            return true;
        }            
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        final Tree other = (Tree) obj;
        if (contents == null) {
            if (other.contents != null){
                return false;
            }
        } else if (!this.contents.equals(other.contents)){
            return false;
        }
        return true;
    }

    public List<Tree> children() {
        return this.children;
    }

    public int value() {
        return this.value;
    }

    public boolean isInteger() {
        return this.isInteger;
    }
}
