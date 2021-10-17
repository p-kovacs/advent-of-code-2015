package pkovacs.aoc.y2015;

import pkovacs.aoc.AocUtils;
import pkovacs.util.InputUtils;

public class Day01 {

    public static void main(String[] args) {
        var line = InputUtils.readSingleLine(AocUtils.getInputPath());

        int floor = 0;
        int firstBasement = -1;
        for (int i = 0; i < line.length(); i++) {
            floor += line.charAt(i) == '(' ? 1 : -1;
            if (floor < 0 && firstBasement < 0) {
                firstBasement = i + 1;
            }
        }

        System.out.println("Part 1: " + floor);
        System.out.println("Part 2: " + firstBasement);
    }

}
