import java.util.*;
import java.io.*;

public class Day4 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./Day4.txt"));
        part1(in);
        
        in = new Scanner(new FileReader("./Day4.txt"));
        part2(in);

        in.close();
    }

    // 139*139*8 = 154568
    public static void part1(Scanner in) {
        int size = 10; // test case
        //int size = 139; // real case
        String[][] grid = new String[size][size];
        for(int i = 0; i < size; i++){
            String line = in.nextLine();
            grid[i] = line.split("");
        }

        int tot = 0;
        int surr = 0;
        for(int r = 0; r < size; r++){
            for(int c = 0; c < size; c++){
                if(!grid[r][c].equals("@")) continue;
                surr = 0;

                if(r-1 >= 0) if(grid[r-1][c].equals("@")) surr++;
                if(r+1 < size) if(grid[r+1][c].equals("@")) surr++;
                if(c-1 >= 0) if(grid[r][c-1].equals("@")) surr++;
                if(c+1 < size) if(grid[r][c+1].equals("@")) surr++;

                if(r-1 >= 0 && c-1 >= 0) if(grid[r-1][c-1].equals("@")) surr++;
                if(r-1 >= 0 && c+1 < size) if(grid[r-1][c+1].equals("@")) surr++;
                if(r+1 < size && c-1 >= 0) if(grid[r+1][c-1].equals("@")) surr++;
                if(r+1 < size && c+1 < size) if(grid[r+1][c+1].equals("@")) surr++;

                if(surr < 4){
                    // System.out.println(r + " " + c);
                    tot++;
                }
            }
        }
        // System.out.println(Arrays.deepToString(grid));
        System.out.println(tot);
    }

    public static void part2(Scanner in) {
        // int size = 10; // test case
        int size = 139; // real case
        String[][] grid = new String[size][size];
        for(int i = 0; i < size; i++){
            String line = in.nextLine();
            grid[i] = line.split("");
        }
        String[][] grid_new = new String[size][size];
        int tot_new = 0;
        int tot = -1; // just temporary value to start while loop
        while(tot != 0){ // run p1 until no more stuff to remove
            // dont want to update grid while running through it
            // so, slightly edited p1 to update grid_new based on new conditions
            // then, replace grid with grid_new to run again
            tot = 0;
            int surr = 0;
            for(int r = 0; r < size; r++){
                for(int c = 0; c < size; c++){
                    if(!grid[r][c].equals("@")) {
                        grid_new[r][c] = ".";
                        continue;
                    }
                    surr = 0;
                    
                    if(r-1 >= 0) if(grid[r-1][c].equals("@")) surr++;
                    if(r+1 < size) if(grid[r+1][c].equals("@")) surr++;
                    if(c-1 >= 0) if(grid[r][c-1].equals("@")) surr++;
                    if(c+1 < size) if(grid[r][c+1].equals("@")) surr++;

                    if(r-1 >= 0 && c-1 >= 0) if(grid[r-1][c-1].equals("@")) surr++;
                    if(r-1 >= 0 && c+1 < size) if(grid[r-1][c+1].equals("@")) surr++;
                    if(r+1 < size && c-1 >= 0) if(grid[r+1][c-1].equals("@")) surr++;
                    if(r+1 < size && c+1 < size) if(grid[r+1][c+1].equals("@")) surr++;

                    if(surr < 4){
                        grid_new[r][c] = ".";
                        tot++;
                    }
                    else grid_new[r][c] = "@";
                }
            }
            // System.out.println(tot);
            // for(int r = 0; r < size; r++){
            //     for(int c = 0; c < size; c++){
            //         System.out.print(grid[r][c]);
            //     }
            //     System.out.println();
            // }
            tot_new += tot;
            grid = grid_new;
        }
        // System.out.println(Arrays.deepToString(grid));
        System.out.println(tot_new);
    }
}
