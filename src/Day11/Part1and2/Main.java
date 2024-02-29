package Day11.Part1and2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static Map<Integer, Coordinates> galaxyCoordinates = new HashMap<>();
    static List<String> input = new ArrayList<>();

    public static void main(String[] args) {

        String inputFileName = "src/Day11/Input2.txt";

        try (FileReader fileReader = new FileReader(inputFileName);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String inputLine;
            int i = 0;
            while ((inputLine = reader.readLine()) != null) {
                input.add(inputLine);
                i++;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        List<Long> rowsToExpand = getRowsToExpand();
        List<Long> columnsToExpand = getColumnsToExpand();
        getGalaxyCoordinates();
        long distancePart1 = getDistance(rowsToExpand, columnsToExpand, 2);
        long distancePart2 = getDistance(rowsToExpand, columnsToExpand, 1000000);

        System.out.println(distancePart1);
        System.out.println(distancePart2);
    }

    private static long getDistance(List<Long> rowsToExpand, List<Long> columnsToExpand, long timesToExpand) {
        long distance = 0;
        for (int element = 1; element < galaxyCoordinates.size(); ) {
            for (int secondElement = element + 1; secondElement <= galaxyCoordinates.size(); secondElement++) {
                Coordinates firstCoordinates = galaxyCoordinates.get(element);
                Coordinates secondCoordinates = galaxyCoordinates.get(secondElement);
                long rowSum = firstCoordinates.row() - secondCoordinates.row();
                long columnSum = firstCoordinates.column() - secondCoordinates.column();
                long sum = Math.abs(rowSum) + Math.abs(columnSum)
                        + getNrToAdd(firstCoordinates.row(), secondCoordinates.row(), rowsToExpand, timesToExpand)
                        + getNrToAdd(firstCoordinates.column(), secondCoordinates.column(), columnsToExpand, timesToExpand);
                distance += sum;
            }
            element++;

        }
        return distance;
    }

    private static void getGalaxyCoordinates() {
        char[][] galaxies = getGalaxyArray();
        int elemCounter = 1;
        for (int row = 0; row < galaxies.length; row++) {
            for (int column = 0; column < galaxies[row].length; column++) {
                char currentCharacter = galaxies[row][column];
                if (currentCharacter == '#') {
                    galaxyCoordinates.put(elemCounter, new Coordinates(row, column));
                    elemCounter++;
                }
            }
        }
    }

    private static List<Long> getRowsToExpand() {
        List<Long> rowsToExpand = new ArrayList<>();
        for (long row = 0; row < input.size(); row++) {
            int elem = Math.toIntExact(row);
            String inputRow = input.get(elem);
            if (inputRow.chars().allMatch(character -> character == '.')) {
                rowsToExpand.add(row);
            }
        }
        return rowsToExpand;
    }

    private static List<Long> getColumnsToExpand() {

        char[][] galaxies = getGalaxyArray();

        List<Long> columnsToExpand = new ArrayList<>();
        for (long column = 0; column < galaxies[0].length; column++) {
            boolean foundGalaxy = false;
            for (int row = 0; row < galaxies.length; row++) {
                int elemColumn = Math.toIntExact(column);
                char currentColumn = galaxies[row][elemColumn];
                if (currentColumn == '#') {
                    foundGalaxy = true;
                    break;
                }
            }
            if (foundGalaxy == false) {
                columnsToExpand.add(column);
            }
        }
        return columnsToExpand;
    }

    private static char[][] getGalaxyArray() {
        char[][] galaxies = new char[input.size()][];
        int i = 0;
        for (String row : input) {
            galaxies[i] = input.get(i).toCharArray();
            i++;
        }
        return galaxies;
    }

    private static long getNrToAdd(long first, long second, List<Long> toAdd, long timesToExpand) {
        long total = 0;
        long start = Math.min(first, second);
        long end = Math.max(first, second);

        for (long i = start; i < end; i++) {
            if (toAdd.contains(i)) {
                total++;
            }
        }
        return total * (timesToExpand - 1);
    }

}
