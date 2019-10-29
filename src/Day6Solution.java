import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.DecimalFormat;
public class Day6Solution {
    public static void main(String[] args) {
        File file1 = new File("src/day6.txt");
        String str = "";
        int lines = 0;
        try {
            Scanner scan = new Scanner(file1);
            while (scan.hasNextLine()) {
                str += (scan.nextLine() + ", ");
                lines++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DecimalFormat df = new DecimalFormat("00");
        int[] xPos = new int[lines];
        int[] yPos = new int[lines];
        for (int i = 0; i < lines * 2; i += 2){
            xPos[i / 2] = Integer.parseInt(str.split(", ")[i]);
            yPos[i / 2] = Integer.parseInt(str.split(", ")[i + 1]);
        }
        String[][] grid = new String[400][400];
        int[][] grid2 = new int[400][400];
        for (int i = 0; i < 400; i++){
            for (int j = 0; j < 400; j++){
                grid[i][j] = "? 400";
                grid2[i][j] = 0;
            }
        }
        for (int i = lines - 1; i >= 0; i--){
            for (int j = 0; j < 400; j++){
                for (int k = 0; k < 400; k++){
                    if ((Math.abs(j - yPos[i]) + Math.abs(k - xPos[i])) < Integer.parseInt(grid[j][k].split(" ")[1])){
                        grid[j][k] = (char)(i + 65) + " " + df.format(Math.abs(j - yPos[i]) + Math.abs(k - xPos[i]));
                    } else if ((Math.abs(j - yPos[i]) + Math.abs(k - xPos[i])) == Integer.parseInt(grid[j][k].split(" ")[1])){
                        grid[j][k] = "* " + df.format(Integer.parseInt(grid[j][k].split(" ")[1]));
                    }
                    grid2[j][k] += (Math.abs(j - yPos[i]) + Math.abs(k - xPos[i]));
                }
            }
        }
        int[] areas= new int[lines];
        String[] atEdge = new String[lines];
        for (int i = 0; i < lines; i++) {
            atEdge[i] = "";
        }
        for (int i = 0; i < 400; i++){
            for (int j = 0; j < 400; j++){
                if (!grid[i][j].split(" ")[0].equals("*")){
                    areas[grid[i][j].split(" ")[0].charAt(0) - 65]++;
                    if (i == 0 || j == 0 || i == 399 || j == 399){
                        atEdge[grid[i][j].split(" ")[0].charAt(0) - 65] = "yes";
                    }
                }
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }
        int largest = 0;
        char largestID = ' ';
        for (int i = 0; i < lines; i++) {
            if (!atEdge[i].equals("yes")) {
                System.out.println((char) (i + 65) + " " + areas[i]);
                if (areas[i] > largest){
                    largest = areas[i];
                    largestID = (char) (i + 65);
                }
            }
        }
        System.out.println("Region " + largestID + " has the largest area, with an area of " + largest);
        int region = 0;
        for (int i = 0; i < 400; i++){
            for (int j = 0; j < 400; j++){
                if (grid2[i][j] < 10000){
                    region++;
                    System.out.print("*");
                }else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("The size of the region with a sum of distances less than 10000 is: " + region);
    }
}
