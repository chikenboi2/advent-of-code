import java.util.*;
import java.io.*;

public class Day1 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./Day1.txt"));
        int pos = 50; // position
        int tot = 0; // total number of 0's
        
        part1(pos,tot,in);
        
        in = new Scanner(new FileReader("./Day1.txt"));
        part2(pos, tot, in);

        in.close();
    }

    public static void part1(int pos, int tot, Scanner in){
        while(in.hasNextLine()){ // loop thru each move
            //System.out.println(pos);
            // assigning variables based on moves
            String turn = in.nextLine();
            char dir = turn.charAt(0);
            int num = Integer.parseInt(turn.substring(1));
            //System.out.println(dir + " " + num);
            // actually doing the moves
            if(dir == 'R') pos += num;
            else pos -= num;

            // updating total and position for next move
            pos %= 100;
            if(pos < 0) pos += 100;
            if(pos == 0) tot++;
            //System.out.println(pos + " after mod");
        }
        System.out.println(tot);
    }

    public static void part2(int pos, int tot, Scanner in) {
        int prev = pos; // edge case variable
        while(in.hasNextLine()){ // loop thru each move
            // assign important variables 
            String turn = in.nextLine();
            char dir = turn.charAt(0);
            int num = Integer.parseInt(turn.substring(1));

            // ty for hint, L1001 can be simplified to L1 and +10 for tot
            tot += (num - (num % 100) )/100;
            num %= 100;

            // actually do the simplified move
            if(dir == 'R') pos += num;
            else pos -= num;
            
            // checking for midroll 0's
            if(pos < 0 && prev != 0) tot++; // have to put in the prev != 0 bc going L5 from 0 doesnt activate 0 duh
            if(pos > 100) tot++;

            // checking for landing on 0's
            pos %= 100;
            if(pos < 0) pos += 100;
            if(pos == 0) tot++;

            //System.out.println(pos + " tot: " + tot);
            prev = pos;
        }
        System.out.println(tot);
    }
}
