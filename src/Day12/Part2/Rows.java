package Day12.Part2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rows {
    public record Row(String springs, List<Integer> groups) {

    }
    private static final Map<Row, Long> memorizationMap = new HashMap<>();

    public static long countPermutations(String springs, List<Integer> groups) {

        long permutationsCount = 0;
        Row row = new Row(springs, groups);

        if (memorizationMap.containsKey(row)) {
            return memorizationMap.get(row);
        }

        if (springs.isBlank()) {
            return groups.isEmpty() ? 1 : 0;
        }

        char firstChar = springs.charAt(0);

        if (firstChar == '.') {
            permutationsCount = countPermutations(springs.substring(1), groups);
        } else if (firstChar == '?') {
            permutationsCount = countPermutations("." + springs.substring(1), groups)
                    + countPermutations("#" + springs.substring(1), groups);
        } else {
            // firstChar == #
            if (groups.isEmpty()) {
                permutationsCount = 0;
            } else {

                int damagedCount = groups.get(0);
                if (damagedCount <= springs.length() && springs.chars().limit(damagedCount).allMatch(c -> c == '#' || c == '?')) {
                    List<Integer> newGroups = groups.subList(1, groups.size());

                    if (damagedCount == springs.length()) {
                        permutationsCount = newGroups.isEmpty() ? 1 : 0;
                    } else if (springs.charAt(damagedCount) == '.') {
                        permutationsCount = countPermutations(springs.substring(damagedCount + 1), newGroups);
                    } else if (springs.charAt(damagedCount) == '?') {
                        permutationsCount = countPermutations("." + springs.substring(damagedCount + 1), newGroups);
                    } else {
                        permutationsCount = 0;
                    }

                } else {
                    permutationsCount = 0;
                }
            }
        }
        memorizationMap.put(row, permutationsCount);

        return permutationsCount;
    }
}
