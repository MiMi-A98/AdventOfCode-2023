package Day12.Part2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Row {
    public record SpringRow(String springs, List<Integer> groups) {

    }
    private static final Map<SpringRow, Long> memorizationMap = new HashMap<>();

    public static long countPermutations(String spring, List<Integer> groups) {

        long permutationsCount = 0;
        SpringRow row = new SpringRow(spring, groups);

        if (memorizationMap.containsKey(row)) {
            return memorizationMap.get(row);
        }

        if (spring.isBlank()) {
            return groups.isEmpty() ? 1 : 0;
        }

        char firstChar = spring.charAt(0);

        if (firstChar == '.') {
            permutationsCount = countPermutations(spring.substring(1), groups);
        } else if (firstChar == '?') {
            permutationsCount = countPermutations("." + spring.substring(1), groups)
                    + countPermutations("#" + spring.substring(1), groups);
        } else {
            // firstChar == #
            if (groups.isEmpty()) {
                permutationsCount = 0;
            } else {

                int damagedCount = groups.get(0);
                if (damagedCount <= spring.length() && spring.chars().limit(damagedCount).allMatch(c -> c == '#' || c == '?')) {
                    List<Integer> newGroups = groups.subList(1, groups.size());

                    if (damagedCount == spring.length()) {
                        permutationsCount = newGroups.isEmpty() ? 1 : 0;
                    } else if (spring.charAt(damagedCount) == '.') {
                        permutationsCount = countPermutations(spring.substring(damagedCount + 1), newGroups);
                    } else if (spring.charAt(damagedCount) == '?') {
                        permutationsCount = countPermutations("." + spring.substring(damagedCount + 1), newGroups);
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
