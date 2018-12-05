import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Day5Solution {
    public static void main(String[] args) {
        File file = new File("src/day5.txt");
        String str = "";
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                str += (scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean isReducing = true;
        String newStr = str;
        for (int j = 'a'; j <= 'z'; j++) {
            while (isReducing) {
                isReducing = false;

                for (int i = 0; i < newStr.length() - 1; i++) {
                    if (newStr.charAt(i) == j || newStr.charAt(i) == j - 32) {
                        newStr = newStr.substring(0, i) + newStr.substring(i + 1, newStr.length());
                        i--;
                    }
                }
                for (int i = 0; i < newStr.length() - 1; i++) {
                    if (Math.abs(newStr.charAt(i) - newStr.charAt(i + 1)) == 32) {
                        isReducing = true;
                        newStr = newStr.substring(0, i) + newStr.substring(i + 2, newStr.length());
                    }
                }


            }
            System.out.println("Removing \"" + (char)j + "\" gives a length of " + newStr.length());
            newStr = str;
            isReducing = true;
        }

        while (isReducing) {
            isReducing = false;
            for (int i = 0; i < str.length() - 1; i++) {
                if (Math.abs(str.charAt(i) - str.charAt(i + 1)) == 32) {
                    isReducing = true;
                    str = str.substring(0, i) + str.substring(i + 2, str.length());
                }
            }
        }
        System.out.println("\nLength of original reduced string: " + str.length());
    }
}
