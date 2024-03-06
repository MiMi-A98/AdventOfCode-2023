package Day12.Part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "src/Day12/Input2.txt";

        try (FileReader fileReader = new FileReader(inputFileName);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String inputLine;
            String springs = null;
            List<Integer> groups = null;
            long permutationsSum = 0;

            while ((inputLine = reader.readLine()) != null) {
                String[] lineSplit = inputLine.split("\\s");
                springs = unfoldSprings(lineSplit[0], 5);
                groups = unfoldGroups(Arrays.stream(lineSplit[1].split(",")).map(Integer::valueOf).toList(), 5);
                permutationsSum += Rows.countPermutations(springs, groups);
            }

            System.out.println(permutationsSum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String unfoldSprings(String inputSprings, int numOfCopies) {
        String springs = inputSprings;

        for (int i = 1; i < numOfCopies; i++){
            springs = springs.concat("?").concat(inputSprings);
        }

        return springs;
    }

    private static List<Integer> unfoldGroups(List<Integer> inputGroups, int numOfCopies) {
        List<Integer> groups = new ArrayList<>();

        for (int i = 0; i < numOfCopies; i++){
            groups.addAll(inputGroups);
        }

        return groups;
    }
}
