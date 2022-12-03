package com.github.pkovacs.aoc.y2015;

import java.util.Arrays;
import java.util.List;

import com.github.pkovacs.aoc.AocUtils;
import com.github.pkovacs.util.InputUtils;

public class Day06 {

    public static void main(String[] args) {
        var lines = InputUtils.readLines(AocUtils.getInputPath());

        System.out.println("Part 1: " + solve(lines, true));
        System.out.println("Part 2: " + solve(lines, false));
    }

    private static long solve(List<String> lines, boolean simple) {
        int[][] lights = new int[1000][1000];
        for (var line : lines) {
            var parts = InputUtils.scan(line, "%s %d,%d through %d,%d");
            var cmd = parts.get(0).get();
            var x1 = parts.get(1).asInt();
            var y1 = parts.get(2).asInt();
            var x2 = parts.get(3).asInt();
            var y2 = parts.get(4).asInt();

            if (cmd.startsWith("turn")) {
                boolean on = cmd.endsWith("on");
                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        lights[x][y] = simple
                                ? (on ? 1 : 0)
                                : (on ? lights[x][y] + 1 : Math.max(lights[x][y] - 1, 0));
                    }
                }
            } else if (cmd.equals("toggle")) {
                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        lights[x][y] = simple ? 1 - lights[x][y] : lights[x][y] + 2;
                    }
                }
            } else {
                throw new IllegalArgumentException("Unknown command: " + cmd);
            }
        }

        return Arrays.stream(lights).mapToLong(a -> Arrays.stream(a).sum()).sum();
    }

}
