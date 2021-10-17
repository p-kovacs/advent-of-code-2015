package pkovacs.aoc.y2015;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import pkovacs.aoc.AocUtils;
import pkovacs.util.InputUtils;

public class Day05 {

    public static void main(String[] args) {
        var lines = InputUtils.readLines(AocUtils.getInputPath());

        System.out.println("Part 1: " + lines.stream().filter(Day05::isNice1).count());
        System.out.println("Part 2: " + lines.stream().filter(Day05::isNice2).count());
    }

    private static boolean isNice1(String s) {
        long vowelCount = IntStream.range(0, s.length()).filter(i -> "aeiou".contains(s.substring(i, i + 1))).count();
        boolean containsDouble = IntStream.range(0, s.length() - 1).anyMatch(i -> s.charAt(i) == s.charAt(i + 1));
        return vowelCount >= 3 && containsDouble && Stream.of("ab", "cd", "pq", "xy").noneMatch(s::contains);
    }

    private static boolean isNice2(String s) {
        boolean rep1 = IntStream.range(0, s.length() - 3)
                .anyMatch(i -> IntStream.range(i + 2, s.length() - 1)
                        .anyMatch(j -> s.charAt(i) == s.charAt(j) && s.charAt(i + 1) == s.charAt(j + 1)));
        boolean rep2 = IntStream.range(0, s.length() - 2)
                .anyMatch(i -> s.charAt(i) == s.charAt(i + 2));
        return rep1 && rep2;
    }

}
