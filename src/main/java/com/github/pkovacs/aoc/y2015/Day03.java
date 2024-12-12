package com.github.pkovacs.aoc.y2015;

import java.util.HashSet;
import java.util.Set;

import com.github.pkovacs.util.InputUtils;
import com.github.pkovacs.util.data.Tile;

public class Day03 {

    public static void main(String[] args) {
        var input = InputUtils.readSingleLine(AocUtils.getInputPath());

        var set1 = run(input, 0, 1);

        var set2 = run(input, 0, 2);
        var robot = run(input, 1, 2);
        set2.addAll(robot);

        System.out.println("Part 1: " + set1.size());
        System.out.println("Part 2: " + set2.size());
    }

    private static Set<Tile> run(String input, int start, int step) {
        Tile pos = new Tile(0, 0);
        var result = new HashSet<Tile>();
        result.add(pos);
        for (int i = start; i < input.length(); i += step) {
            char dir = switch (input.charAt(i)) {
                case '^' -> 'N';
                case 'v' -> 'S';
                case '>' -> 'E';
                case '<' -> 'W';
                default -> throw new IllegalArgumentException();
            };
            pos = pos.neighbor(dir);
            result.add(pos);
        }
        return result;
    }

}
