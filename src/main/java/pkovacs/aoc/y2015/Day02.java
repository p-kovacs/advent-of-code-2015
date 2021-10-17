package pkovacs.aoc.y2015;

import java.util.Arrays;

import pkovacs.aoc.AocUtils;
import pkovacs.util.InputUtils;

public class Day02 {

    public static void main(String[] args) {
        var lines = InputUtils.readLines(AocUtils.getInputPath());

        int paper = 0;
        int ribbon = 0;
        for (var line : lines) {
            int[] dim = InputUtils.parseInts(line);
            int[] area = new int[] { dim[0] * dim[1], dim[0] * dim[2], dim[1] * dim[2] };
            int[] perim = new int[] { 2 * (dim[0] + dim[1]), 2 * (dim[0] + dim[2]), 2 * (dim[1] + dim[2]) };
            paper += 2 * Arrays.stream(area).sum() + Arrays.stream(area).min().orElseThrow();
            ribbon += Arrays.stream(perim).min().orElseThrow() + dim[0] * dim[1] * dim[2];
        }

        System.out.println("Part 1: " + paper);
        System.out.println("Part 2: " + ribbon);
    }

}
