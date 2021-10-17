package pkovacs.aoc.y2015;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;
import pkovacs.aoc.AocUtils;
import pkovacs.util.InputUtils;

public class Day04 {

    public static void main(String[] args) {
        var secret = InputUtils.readSingleLine(AocUtils.getInputPath());

        int index1 = -1;
        int index2 = -1;
        for (int i = 1; index1 < 0 || index2 < 0; i++) {
            var hash = getMd5Hash(secret + i);
            if (hash.startsWith("00000") && index1 < 0) {
                index1 = i;
            }
            if (hash.startsWith("000000") && index2 < 0) {
                index2 = i;
            }
        }

        System.out.println("Part 1: " + index1);
        System.out.println("Part 2: " + index2);
    }

    @SuppressWarnings({ "deprecated", "UnstableApiUsage" })
    private static String getMd5Hash(String s) {
        return Hashing.md5().hashString(s, StandardCharsets.UTF_8).toString();
    }

}
