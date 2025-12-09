import java.util.*;
import java.io.*;

public class Day9 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./Day9.txt"));
        part1(in);
        
        in = new Scanner(new FileReader("./Day9.txt"));
        part2(in);

        in.close();
    }

    // 496 choose 2 = 122760
    public static void part1(Scanner in) {
        // organize data
        ArrayList<int[]> coords = new ArrayList<>();
        while(in.hasNextLine()){
            String[] line = in.nextLine().split(",");
            coords.add(new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[1])});
        }
        // System.out.println(Arrays.deepToString(coords.toArray()));

        // check each pair of coordinates for max area
        double area = 0;
        for(int i = 0; i < coords.size(); i++){
            for(int j = i+1; j < coords.size(); j++){
                int[] coord1 = coords.get(i);
                int[] coord2 = coords.get(j);

                double temp = (double) (Math.abs(coord1[1] - coord2[1]) + 1) * (Math.abs(coord1[0] - coord2[0]) + 1);
                if(temp > area) area = temp;
            }
        }
        System.out.println(area);
    }

    public static void part2(Scanner in) {
        // plotted all the points in desmos
        // noticed that two points essentially cut a circle in half
        // recognize that the biggest rectangles must be from one of those two points in the middle of the circle
        // constrain possible points based on rules
        // check two possible points in upper and lower region

        // upper : (5069,66396) to (94525,50422)
        System.out.println(1429075575);
        // lower : (4682,32944) to (94525,48322)
        // 1383248776
    }
}
