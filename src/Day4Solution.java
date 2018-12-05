import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Day4Solution {
    public static void main(String[] args) {
        File file = new File("src/day4.txt");
        int lines = 0;
        String[] input;
        String[] dates;
        String[] times;
        String[] actions;
        try {
            Scanner scan1 = new Scanner(file);
            while (scan1.hasNextLine()) {
                lines++;
                scan1.nextLine();
            }
            Scanner scan2 = new Scanner(file);
            input = new String[lines];
            lines = 0;
            while (scan2.hasNextLine()) {
                input[lines] = scan2.nextLine();
                lines++;
            }
            dates = new String[lines];
            times = new String[lines];
            actions = new String[lines];
            for (int i = 0; i < lines; i++) {
                dates[i] = input[i].substring(1, 11);
                times[i] = input[i].substring(12, 17);
                actions[i] = input[i].substring(25, input[i].length());
            }
            int[] intTime = new int[lines];
            int[] intActs = new int[lines];
            int[] order = new int[lines];

            int sto1 = 0, sto2 = 0, min;
            for (int i = 0; i < lines; i++) {
                order[i] = i;
                intTime[i] = Integer.parseInt(dates[i].split("-")[1]
                        + dates[i].split("-")[2] + times[i].split(":")[0]
                        + times[i].split(":")[1]);
            }
            for (int i = 0; i < lines; i++) {
                min = intTime[order[i]];
                for (int j = i; j < lines; j++) {
                    if(intTime[order[j]] <= min) {
                        min = intTime[order[j]];
                        sto1 = j;
                        sto2 = order[j];
                    }
                }
                order[sto1] = order[i];
                order[i] = sto2;
            }
            for (int i = 0; i < lines; i++){
                if (actions[order[i]].charAt(0) == '#'){
                    intActs[order[i]] = Integer.parseInt(actions[order[i]].split(" ")[0].substring(1, actions[order[i]].split(" ")[0].length()));
                } else if (actions[order[i]].charAt(0) == 'a') {
                    intActs[order[i]] = 0;
                } else {
                    intActs[order[i]] = 1;
                }
            }
            int maxID = 0;
            for (int i = 0; i < lines; i++){
                if (intActs[order[i]] > maxID){
                    maxID = intActs[order[i]];
                }
            }
            int[][] minutes = new int[maxID + 1][60];
            for (int i = 0; i < maxID; i++){
                for (int j = 0; j < 60; j++){
                    minutes[i][j] = 0;
                }
            }
            int currentID = 0;
            for (int i = 0; i < lines; i++){
                if (intActs[order[i]] > 1){
                    currentID = intActs[order[i]];
                } else if (intActs[order[i]] == 0){
                    for (int j = intTime[order[i]] % 100; j < intTime[order[i + 1]] % 100; j++){
                        minutes[currentID][j]++;
                    }
                }
            }
            int maxMins = 0;
            int maxMinute = 0;
            int maxMinID = 0;
            int maxMinute2 = 0;
            int maxMinID2 = 0;
            int sum = 0;
            for (int i = 0; i < maxID; i++){
                for (int j = 0; j < 60; j++){
                    sum += minutes[i][j];
                }
                if (sum > maxMins){
                    maxMins = sum;
                    for (int j = 0; j < 60; j++){
                        if (minutes[i][j] > minutes[i][maxMinute]){
                            maxMinute = j;
                        }
                    }
                    maxMinID = i;
                }
                for (int j = 0; j < 60; j++){
                    if (minutes[i][j] > minutes[maxMinID2][maxMinute2]) {
                        maxMinID2 = i;
                        maxMinute2 = j;
                    }
                }
                sum = 0;
            }
            System.out.println("ID of guard who slept most: " + maxMinID + "\nMinute with most sleep: " + maxMinute
                    + "\nTotal minutes slept: " + maxMins);
            System.out.println("\nID of guard who slept the most on a particular minute: " + maxMinID2
                    + "\nMinute that guard slept on most: " + maxMinute2);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
