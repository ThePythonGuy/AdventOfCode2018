import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Day2Solution {
    public static void main (String [] args){
        File file = new File("src/day2.txt");
        String input = "";
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                input += (scan.nextLine() + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int diff = 0;
        int [] charArr = new int[26];
        int [] valArr = new int[charArr.length/27];
        for(int i = 0; i < valArr.length; i++){
            valArr[i] = 0;
        }
        for(int i = 0; i < charArr.length; i++){
            charArr[i] = 0;
        }
        String[] list = new String[input.length()/27];
        for (int i = 0; i < list.length; i++){
            list[i] = "";
            for (int j = 0; j < 26; j++){
                list[i] = list[i].concat(""+input.charAt(i * 27 + j));
            }
        }
        for (int i = 0; i < list.length; i++){
            for (int j = i + 1; j < list.length; j++){
                for (int k = 0; k < list[j].length(); k++){
                    if (list[i].charAt(k) != list[j].charAt(k)) diff++;
                }
                if (diff == 1){
                    System.out.println(list[i] + "\n" + list[j]);
                }
                diff = 0;
            }
        }

        boolean isTwice = false, isThrice = false;
        int twice = 0, thrice = 0;

        for(int i = 0; i < input.length() / 27; i++){
            for(int j = 0; j < 26; j++){
                charArr[input.charAt((i * 27)+j) - 'a']++;
            }
            for(int j = 0; j < 26; j++) {
                if (charArr[input.charAt((i * 27) + j) - 'a'] == 2) {
                    isTwice = true;
                } else if (charArr[input.charAt((i * 27) + j) - 'a'] == 3){
                    isThrice = true;
                }
            }
            if(isTwice) twice++;
            if(isThrice) thrice++;
            isTwice = false;
            isThrice = false;
            for(int k = 0; k < charArr.length; k++){
                charArr[k] = 0;
            }
        }
        System.out.println("doubles: " + twice + "\n" + "triples: " +
                thrice + "\n" + "product: " + twice*thrice);
    }
}
