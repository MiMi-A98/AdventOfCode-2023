package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        try {
            FileReader fileReader = new FileReader("src/Day1/Input2.txt");
            BufferedReader reader = new BufferedReader(fileReader);

            String inputLine;
//            ArrayList<Integer> calibrationValues = new ArrayList<>();

            while ((inputLine = reader.readLine()) != null) {
                
            }
            fileReader.close();

            //...

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}