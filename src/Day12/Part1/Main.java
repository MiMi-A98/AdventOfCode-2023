package Day12.Part1;

import java.io.BufferedReader;
import java.io.FileReader;
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
            long count = 0;

            while ((inputLine = reader.readLine()) != null) {
                String[] lineSplit = inputLine.split("\s");
                springs = lineSplit[0];
                groups = Arrays.stream(lineSplit[1].split(",")).map(Integer::valueOf).toList();
                count += Rows.countPermutations(springs, groups);
            }

            System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
