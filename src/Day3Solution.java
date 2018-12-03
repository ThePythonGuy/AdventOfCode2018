import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Day3Solution {
    public static void main(String[] args) {
        File file = new File("src/day3.txt");
        String str = "";
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                str += (scan.nextLine() + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}