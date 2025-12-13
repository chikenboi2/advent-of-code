import java.util.*;
import java.io.*;

public class Day12 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./Day12.txt"));
        part1(in);
        
        in = new Scanner(new FileReader("./Day12.txt"));
        // part2(in);

        in.close();
    }

    // cheesed
    public static void part1(Scanner in) {
        String[] blocks = new String[6];
        for(int block = 0; block < blocks.length; block++){
            in.nextLine();
            blocks[block] = in.nextLine();
            blocks[block] += in.nextLine();
            blocks[block] += in.nextLine();
            in.nextLine();
        }
        int tot = 0;
        while(in.hasNextLine()){
            String test = in.nextLine();
            int w = Integer.parseInt(test.substring(0, test.indexOf("x")));
            int l = Integer.parseInt(test.substring(test.indexOf("x")+1, test.indexOf(":")));

            int w_block = (int) w/3;
            int l_block = (int) l/3;

            long test_blocks = w_block * l_block;
            long need_blocks = 0;

            String[] temp = test.substring(test.indexOf(" ")+1).split(" ");
            int[] amts = new int[blocks.length];
            for(int i = 0; i < temp.length; i++) {
                amts[i] = Integer.parseInt(temp[i]);
                need_blocks += Integer.parseInt(temp[i]);
            }

            if(test_blocks >= need_blocks) tot++;

        }
        System.out.println(tot);
    }

    public static void part2(Scanner in) {

    }
}
