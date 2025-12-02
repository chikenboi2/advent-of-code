import java.util.*;
import java.io.*;

public class Day2 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./Day2.txt"));
        part1(in);

        in = new Scanner(new FileReader("./Day2.txt"));
        part2(in);

        in.close();
    }

    public static void part1(Scanner in) {
        double sum = 0; // double because big numbers, integer overflow is NOT cool
        String[] id_list = in.nextLine().split(",");
        // System.out.println(Arrays.toString(id_list));

        long start = 0, end = 0;
        for(String ids : id_list){
            start = Long.parseLong(ids.substring(0,ids.indexOf('-')));
            end = Long.parseLong(ids.substring(ids.indexOf('-') + 1));
            // System.out.println(id1 + " " + id2);
            // PLEASE let me brute force ts

            // i brute forced it
            for(Long i = start; i <= end; i++){ // <= because want to check end point as well
                // System.out.println(i);
                String num = i + "";
                if(num.length() % 2 == 0){ // only want even length numbers
                    // checks if first half of number is same as second half of number
                    if(num.substring(0, num.length()/2).equals(num.substring(num.length()/2))){
                        sum += i;
                    }
                }
            }
        }
        System.out.println(sum);
    }

    public static void part2(Scanner in) {
        double sum = 0;
        String[] id_list = in.nextLine().split(",");

        long start = 0, end = 0;
        for(String ids : id_list){
            start = Long.parseLong(ids.substring(0,ids.indexOf('-')));
            end = Long.parseLong(ids.substring(ids.indexOf('-') + 1));

            for(Long i = start; i <= end; i++){ 
                // biggest number is length 10
                // basically if number is length N, then invalid iff divisble by one of the numbers following the :
                
                // 10: 1111111111, 101010101, 100001
                // 9: 111111111, 1001001
                // 8: 11111111, 1010101, 10001
                // 7: 1111111
                // 6: 111111, 10101, 1001
                // 5: 11111
                // 4: 1111, 101
                // 3: 111
                // 2: 11
                
                // ts is gross and i hope it doesn't work
                switch ((i + "").length()) {
                    case 10: if(i % 1111111111 == 0 || i % 101010101 == 0 || i % 100001 == 0) sum += i; break;
                    case 9: if(i % 111111111 == 0 || i % 1001001 == 0) sum += i; break;
                    case 8: if(i % 11111111 == 0 || i % 1010101 == 0 || i % 10001 == 0) sum += i; break;
                    case 7: if(i % 1111111 == 0) sum += i; break;
                    case 6: if(i % 111111 == 0 || i % 10101 == 0 || i % 1001 == 0) sum += i; break;
                    case 5: if(i % 11111 == 0) sum += i; break;
                    case 4: if(i % 1111 == 0 || i % 101 == 0) sum += i; break;
                    case 3: if(i % 111 == 0) sum += i; break;
                    case 2: if(i % 11 == 0) sum += i; break;
                }
                // it worked.

            }
        }
        System.out.println(sum);
    }
}
