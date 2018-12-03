import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Day3Solution {
    public static void main(String[] args) {
        File file = new File("src/day3.txt");
        String str = "";
        int lines = 0;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                str += (scan.nextLine() + " ");
                lines++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boolean isOverlapped = false;
        int overlap = 0;

        int [] xPosArr = new int[lines];
        int [] yPosArr = new int[lines];
        int [] widthArr = new int[lines];
        int [] heightArr = new int[lines];
        int [] IDArr = new int[lines];
        int currentLine = 0;
        boolean afterAt = false, afterComma = false, afterColon = false, afterX = false;
        for(int i = 0; i < str.length(); i++){
            if (afterAt && afterComma && afterColon && afterX && str.charAt(i) == ' '){
                afterAt = false;
                afterComma = false;
                afterColon = false;
                afterX = false;
                currentLine++;
            } else if (afterAt && afterComma && afterColon && afterX) {
                heightArr[currentLine] = (10 * heightArr[currentLine]) + Integer.parseInt("" + str.charAt(i));
            } else if (afterAt && afterComma && afterColon && str.charAt(i) == 'x'){
                afterX = true;
            } else if (afterAt && afterComma && afterColon && str.charAt(i) != ' '){
                widthArr[currentLine] = (10 * widthArr[currentLine]) + Integer.parseInt("" + str.charAt(i));
            } else if (afterAt && afterComma && str.charAt(i) == ':'){
                afterColon = true;
            } else if (afterAt && afterComma && str.charAt(i) != ' '){
                yPosArr[currentLine] = (10 * yPosArr[currentLine]) + Integer.parseInt("" + str.charAt(i));
            } else if (afterAt && str.charAt(i) == ','){
                afterComma = true;
            } else if (afterAt && str.charAt(i) != ' '){
                xPosArr[currentLine] = (10 * xPosArr[currentLine]) + Integer.parseInt("" + str.charAt(i));
            } else if (str.charAt(i) == '@'){
                afterAt = true;
            } else if (str.charAt(i) != '#' && str.charAt(i) != ' '){
                IDArr[currentLine] = (10 * IDArr[currentLine]) + Integer.parseInt("" + str.charAt(i));
            }
        }
        int [][] grid = new int[1000][1000];
        for (int i = 0; i < 1000; i++){
            for (int j = 0; j < 1000; j++){
                grid[i][j] = 0;
            }
        }
        for (int i = 0; i < lines; i++){
            for (int y = yPosArr[i]; y < yPosArr[i] + heightArr[i]; y++){
                for (int x = xPosArr[i]; x < xPosArr[i] + widthArr[i]; x++){
                    grid[x][y]++;
                }
            }
        }
        for (int i = 0; i < lines; i++){
            for (int y = yPosArr[i]; y < yPosArr[i] + heightArr[i]; y++){
                for (int x = xPosArr[i]; x < xPosArr[i] + widthArr[i]; x++){
                    if(grid[x][y] > 1) isOverlapped = true;
                }
            }
            if (!isOverlapped){
                System.out.println("Cloth not overlapped: ID#" + IDArr[i]);
            }
            isOverlapped = false;
        }
        for (int i = 0; i < 1000; i++){
            for (int j = 0; j < 1000; j++){
                if(grid[i][j] > 1){
                    overlap++;
                }
            }
        }
        System.out.println("Amount of cloth overlap: " + overlap);
    }
}