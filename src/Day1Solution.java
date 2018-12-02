import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Day1Solution {
    public static void main(String [] args){
        File file = new File("src/day1.txt");

        boolean isAddition = false, isDuplicated = false, hasVal = false;
        int num = 0, sum = 0, j = 0, dupVal = 0;
        String str = "";
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                str += (scan.nextLine() + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int [] sumArr = new int[str.length() * 50];

        while(!isDuplicated) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '+') {
                    isAddition = true;
                } else if (str.charAt(i) == '-') {
                    isAddition = false;
                } else if (str.charAt(i) == ' ') {
                    if (isAddition) {
                        sum += num;
                    } else {
                        sum -= num;
                    }
                    num = 0;
                    for(int k = 0; k < sumArr.length; k++){
                        if (sum == sumArr[k]) hasVal = true;
                    }
                    if (!isDuplicated && hasVal){
                        sumArr[j] = sum;
                        j++;
                        dupVal = sum;
                        isDuplicated = true;
                    } else {
                        sumArr[j] = sum;
                        j++;
                    }
                } else {
                    num = (10 * num) + (str.charAt(i) - 48);
                }
            }
            System.out.println("attempt " + j / 959);
            System.out.println("running total = " + sumArr[j-1]);
        }
        System.out.println();
        System.out.println("sum: " + sumArr[958] + "\nduplicate: " + dupVal);
    }
}
