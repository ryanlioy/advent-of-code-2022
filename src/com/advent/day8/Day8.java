package advent.day8;

public class Day8 {
    public static void main(String[] args) {
        // this one was a lot to have in one class
        var grid = ReadInput.getInput("src/com/advent/day8/input.txt");
        System.out.println(Day8Part1.part1(grid));
        System.out.println(Day8Part2.part2(grid));
    }
}
