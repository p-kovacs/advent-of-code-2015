package com.github.pkovacs.aoc.y2015;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class AocSolutionsTest {

    private final PrintStream origOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private static Stream<Arguments> test() {
        return Stream.of(
                new Arguments("Day01", Day01::main, "280", "1797"),
                new Arguments("Day02", Day02::main, "1606483", "3842356"),
                new Arguments("Day03", Day03::main, "2592", "2360"),
                new Arguments("Day04", Day04::main, "254575", "1038736"),
                new Arguments("Day05", Day05::main, "258", "53"),
                new Arguments("Day06", Day06::main, "377891", "14110788"),
                new Arguments("Day07", Day07::main, "956", "40149"),
                new Arguments("Day08", Day08::main, "0", "0"),
                new Arguments("Day09", Day09::main, "0", "0"),
                new Arguments("Day11", Day11::main, "0", "0"),
                new Arguments("Day12", Day12::main, "0", "0"),
                new Arguments("Day13", Day13::main, "0", "0"),
                new Arguments("Day14", Day14::main, "0", "0"),
                new Arguments("Day15", Day15::main, "0", "0"),
                new Arguments("Day16", Day16::main, "0", "0"),
                new Arguments("Day17", Day17::main, "0", "0"),
                new Arguments("Day18", Day18::main, "0", "0"),
                new Arguments("Day19", Day19::main, "0", "0"),
                new Arguments("Day21", Day21::main, "0", "0"),
                new Arguments("Day22", Day22::main, "0", "0"),
                new Arguments("Day23", Day23::main, "0", "0"),
                new Arguments("Day24", Day24::main, "0", "0"),
                new Arguments("Day25", Day25::main, "0", "0")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void test(Arguments args) {
        args.mainMethod().accept(null);
        assertSolution1(args.expected1());
        assertSolution2(args.expected2());
    }

    @BeforeEach
    public void changeSystemOut() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(origOut);
    }

    void assertSolution1(String expected) {
        assertSolution(0, expected);
    }

    void assertSolution2(String expected) {
        assertSolution(1, expected);
    }

    private void assertSolution(int index, String expected) {
        var output = outputStream.toString(StandardCharsets.UTF_8);
        var parts = output.split(System.lineSeparator())[index].split(": ");
        var value = parts.length < 2 ? "" : parts[1].trim();
        Assertions.assertEquals(expected, value);
    }

    private static record Arguments(String name, Consumer<String[]> mainMethod, String expected1, String expected2) {
        @Override
        public String toString() {
            return name;
        }
    }

}
