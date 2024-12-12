package com.github.pkovacs.aoc.y2015;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.github.pkovacs.util.InputUtils;
import com.github.pkovacs.util.data.CounterMap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

public class Day07 {

    enum SourceType {
        DIRECT, NOT, AND, OR, LSHIFT, RSHIFT
    }

    public static void main(String[] args) {
        var input1 = InputUtils.readLines(AocUtils.getInputPath());

        int solution1 = solve(input1);

        var input2 = Stream.concat(input1.stream().filter(line -> !line.endsWith(" -> b")),
                Stream.of(solution1 + " -> b")).toList();
        int solution2 = solve(input2);

        System.out.println("Part 1: " + solution1);
        System.out.println("Part 2: " + solution2);
    }

    private static int solve(List<String> input) {
        return computeSignalValues(input).get("a");
    }

    private static Map<String, Integer> computeSignalValues(List<String> input) {
        // Read input, build data structures
        ListMultimap<String, String> graph = MultimapBuilder.hashKeys().arrayListValues().build();
        ListMultimap<String, String> revGraph = MultimapBuilder.hashKeys().arrayListValues().build();
        var type = new HashMap<String, SourceType>();
        var deg = new CounterMap<String>();
        var signal = new HashMap<String, Integer>();
        var nodes = new HashSet<String>();
        for (var line : input) {
            var parts = line.split(" -> ");
            var source = parts[0];
            var target = parts[1];

            var st = getSourceType(source);
            type.put(target, st);
            nodes.add(target);
            if (!source.contains(" ") || source.contains("NOT")) {
                var srcArg = source.replace("NOT ", "");
                graph.put(srcArg, target);
                revGraph.put(target, srcArg);
                deg.put(target, 1);
                nodes.add(srcArg);
            } else {
                var srcArgs = source.split(" " + st + " ");
                graph.put(srcArgs[0], target);
                graph.put(srcArgs[1], target);
                revGraph.put(target, srcArgs[0]);
                revGraph.put(target, srcArgs[1]);
                deg.put(target, 2);
                nodes.add(srcArgs[0]);
                nodes.add(srcArgs[1]);
            }
        }

        // Calculate signal values based on topological ordering of this DAG
        var queue = new ArrayDeque<>(nodes.stream().filter(n -> deg.getValue(n) == 0).toList());
        while (!queue.isEmpty()) {
            var node = queue.remove();
            for (var neighbor : graph.get(node)) {
                if (deg.dec(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }

            if (!type.containsKey(node)) {
                continue;
            }
            switch (type.get(node)) {
                case DIRECT -> signal.put(node, getSignal(signal, revGraph.get(node).get(0)));
                case NOT -> signal.put(node, ~getSignal(signal, revGraph.get(node).get(0)));
                case AND -> signal.put(node, getSignal(signal, revGraph.get(node).get(0))
                        & getSignal(signal, revGraph.get(node).get(1)));
                case OR -> signal.put(node, getSignal(signal, revGraph.get(node).get(0))
                        | getSignal(signal, revGraph.get(node).get(1)));
                case LSHIFT -> signal.put(node, getSignal(signal, revGraph.get(node).get(0))
                        << getSignal(signal, revGraph.get(node).get(1)));
                case RSHIFT -> signal.put(node, getSignal(signal, revGraph.get(node).get(0))
                        >> getSignal(signal, revGraph.get(node).get(1)));
            }
        }

        return signal;
    }

    private static SourceType getSourceType(String source) {
        if (!source.contains(" ")) {
            return SourceType.DIRECT;
        } else if (source.contains("NOT")) {
            return SourceType.NOT;
        } else if (source.contains("AND")) {
            return SourceType.AND;
        } else if (source.contains("OR")) {
            return SourceType.OR;
        } else if (source.contains("LSHIFT")) {
            return SourceType.LSHIFT;
        } else if (source.contains("RSHIFT")) {
            return SourceType.RSHIFT;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static int getSignal(Map<String, Integer> signal, String key) {
        int intValue = signal.containsKey(key) ? signal.get(key) : Integer.parseInt(key);
        return intValue & 0xffff;
    }

}
