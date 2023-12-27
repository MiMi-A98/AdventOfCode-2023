package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        try {
            FileReader fileReader = new FileReader("src/Day2/Input2.txt");
            BufferedReader reader = new BufferedReader(fileReader);

            int maxRedCubes = 12;
            int maxGreenCubes = 13;
            int maxBlueCubes = 14;

            String inputLine;
            ArrayList<Integer> calibrationValues = new ArrayList<>();

            while ((inputLine = reader.readLine()) != null) {
                ArrayList<Integer> blueDigits = new ArrayList<>();
                ArrayList<Integer> redDigits = new ArrayList<>();
                ArrayList<Integer> greenDigits = new ArrayList<>();

                Pattern digitPattern = Pattern.compile("(\\d+ blue|\\d+ red|\\d+ green)");
                Matcher patternMatcher = digitPattern.matcher(inputLine);

                while (patternMatcher.find()) {
                    String foundMatch = patternMatcher.group();
                    String foundNumber;
                    if (foundMatch.contains("blue")) {
                        foundNumber = foundMatch.replaceAll("[^0-9]", "");
                        blueDigits.add(Integer.parseInt(foundNumber));
                    } else if (foundMatch.contains("red")) {
                        foundNumber = foundMatch.replaceAll("[^0-9]", "");
                        redDigits.add(Integer.parseInt(foundNumber));
                    } else if (foundMatch.contains("green")) {
                        foundNumber = foundMatch.replaceAll("[^0-9]", "");
                        greenDigits.add(Integer.parseInt(foundNumber));
                    }
                }
                int blueSum = getSum(blueDigits);
                int redSum = getSum(redDigits);
                int greenSum = getSum(greenDigits);

                if (blueSum <= maxBlueCubes && redSum <= maxRedCubes && greenSum <= maxGreenCubes) {
                    calibrationValues.add(Integer.parseInt(inputLine.substring(5, 8).replaceAll("[^0-9]", "")));
                }

            }
            fileReader.close();
            System.out.println(calibrationValues);
            System.out.println(getSum(calibrationValues));
            // 4, 21, 27, 37, 53, 74, 75, 78

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static int getSum(ArrayList<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}