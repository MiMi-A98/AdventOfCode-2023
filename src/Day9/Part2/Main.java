package Day9.Part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static List<List<Integer>> oasisReport = new ArrayList<>();

    public static void main(String[] args) {
        String inputFileName = "src/Day9/Input2.txt";

        try (FileReader fileReader = new FileReader(inputFileName);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                String[] lineSplit = inputLine.split("\\s");
                List<Integer> historyNumbers = new ArrayList<>();
                for (String elem : lineSplit) {
                    int element = Integer.parseInt(elem);
                    historyNumbers.add(element);
                }
                oasisReport.add(historyNumbers);
            }
            int sum = getSum(getExtrapolatedValues());

            System.out.println(sum);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static List<Integer> getExtrapolatedValues() {
        List<Integer> extrapolatedValues = new ArrayList<>();

        for (List list: oasisReport) {
            Collections.reverse(list);
            int nextValue = getNextNumber(list);
            extrapolatedValues.add(nextValue);
        }
        return extrapolatedValues;
    }
    private static int getNextNumber(List<Integer> arrayList) {
        List<Integer> differences = getDifferences(arrayList);
        if (differences.stream().anyMatch(x -> x != 0)) {
            return arrayList.get(arrayList.size()-1) + getNextNumber(differences);
        } else {
            return arrayList.get(arrayList.size()-1);
        }
    }

    private static List<Integer> getDifferences(List<Integer> arrayList) {
        List<Integer> differences = new ArrayList<>();
        for (int i = 0; i < arrayList.size()-1; i++) {

            int difference = arrayList.get(i + 1) - arrayList.get(i);
            differences.add(difference);
        }
        return differences;
    }

    private static int getSum(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
