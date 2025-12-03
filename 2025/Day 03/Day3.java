import java.util.*;
import java.io.*;

public class Day3 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./Day3.txt"));
        part1(in);
        
         in = new Scanner(new FileReader("./Day3.txt"));
        part2(in);

        in.close();
    }

    // brute force: O(N^2 * x)
    // x lines
    // N is length of each line
    // 100^2 * 200 = 2000000 is good enough
    public static void part1(Scanner in) {
        double sum = 0;
        while(in.hasNextLine()){ // work through each line
            String bank = in.nextLine();
            String[] bank_list = bank.split("");
            // System.out.println(Arrays.toString(bank_list));
            int max = 0;
            int temp = 0;
            // check all combinations of two numbers in order to find biggest 2 digit number
            for(int i = 0; i < bank_list.length; i++){
                for(int j = i+1; j < bank_list.length; j++){
                    temp = Integer.parseInt(bank_list[i] + bank_list[j]);
                    if(temp > max) max = temp;
                }
            }
            // System.out.println(max);
            sum += max;
        }
        System.out.println(sum);
    }

    // maybe cant brute force this one :(
    // 100C12 * 200 = 2E17
    public static void part2(Scanner in) {
        double sum = 0;
        String temp = "";
        String[] digit_list = {"9", "8", "7", "6", "5", "4", "3", "2", "1"};
        while(in.hasNextLine()){
            String bank = in.nextLine();
            temp = "";
            ArrayList<String> bank_list = new ArrayList<>(Arrays.asList(bank.split("")));
            // System.out.println(bank_list.toString());

            while(temp.length() < 12){ // want 12 digit number
                for(String digit : digit_list) { // look for biggest digit
                    // if digit is in list and enough numbers after to put 12 digit number
                    if(bank_list.indexOf(digit) != -1 && bank_list.indexOf(digit) <= bank_list.size() + temp.length() - 12){
                        temp += digit;
                        // remove numbers up to digit just added to temp
                        bank_list = new ArrayList<>(bank_list.subList(bank_list.indexOf(digit) + 1, bank_list.size()));
                        // System.out.println(bank_list);
                        // System.out.println(temp);
                        break;
                    }
                }
            }
            // System.out.println(temp);
            sum += Double.parseDouble(temp);
        }
        System.out.println(sum);
    }
}
