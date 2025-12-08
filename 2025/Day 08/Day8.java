import java.util.*;
import java.io.*;

public class Day8 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./Day8.txt"));
        part1(in);
        
        in = new Scanner(new FileReader("./Day8.txt"));
        part2(in);

        in.close();
    }

    // 1000 choose 2 = 499500
    public static void part1(Scanner in) {
        // organize data
        ArrayList<int[]> coords = new ArrayList<>();
        while(in.hasNextLine()){
            String[] line = in.nextLine().split(",");
            coords.add(new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2])});
        }
        // System.out.println(Arrays.deepToString(coords.toArray()));

        // TreeMap to sort pairs by distance
        // i really hope all pairs have diff distances
        TreeMap<Double, int[]> dists = new TreeMap<>();
        for(int i = 0; i < coords.size(); i++){
            for(int j = i+1; j < coords.size(); j++){
                Double dist = Math.sqrt(Math.pow(coords.get(j)[0]-coords.get(i)[0], 2) 
                + Math.pow(coords.get(j)[1]-coords.get(i)[1], 2) 
                + Math.pow(coords.get(j)[2]-coords.get(i)[2], 2));
                dists.put(dist, new int[]{i, j});
            }
        }
        // System.out.println(dists.size()); // all good

        // loop through first 1000 pairs
        int count = 0;
        HashSet<HashSet<Integer>> circs = new HashSet<>(); // HashSet bc no duplicates for indices and fast lookup
        for (Double dist : dists.keySet()){
            // if(count < 10){ // 10 for testing
            if(count < 1000){ // 1000 for real
                int index1 = dists.get(dist)[0];
                int index2 = dists.get(dist)[1];

                // find where each index fits in previous circuits
                HashSet<Integer> circ1 = new HashSet<>(); circ1.add(index1);
                HashSet<Integer> circ2 = new HashSet<>(); circ2.add(index2);
                // System.out.println(index1 + " " + index2);
                for(HashSet<Integer> circ: circs){
                    if(circ.contains(index1)) circ1 = circ;
                    if(circ.contains(index2)) circ2 = circ;
                }

                HashSet<Integer> temp = new HashSet<>();
                temp.addAll(circ1);
                temp.addAll(circ2);
                circs.add(temp);
                if(circ1 != circ2){ // if both index in same circuit nothing happens
                    if(circ1.size() != 1) circs.remove(circ1);
                    if(circ2.size() != 1) circs.remove(circ2);
                }
                // System.out.println(circs.toString());
                count++;
            }
            else break;
        }

        // find sizes of largest three sets
        int max1 = 0, max2 = 0, max3 = 0;
        for(HashSet<Integer> circ : circs){
            int temp = circ.size();
            if(temp > max1){
                max3 = max2;
                max2 = max1;
                max1 = temp;
            }
            else if (temp > max2 && temp != max1){
                max3 = max2;
                max2 = temp;
            }
            else if (temp > max3 && temp != max2 && temp != max1){
                max3 = temp;
            }
        }
        // System.out.println(max1 + " " + max2 + " " + max3);
        System.out.println(max1 * max2 * max3);
    }

    public static void part2(Scanner in) {
        ArrayList<int[]> coords = new ArrayList<>();
        while(in.hasNextLine()){
            String[] line = in.nextLine().split(",");
            coords.add(new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2])});
        }

        TreeMap<Double, int[]> dists = new TreeMap<>();
        for(int i = 0; i < coords.size(); i++){
            for(int j = i+1; j < coords.size(); j++){
                Double dist = Math.sqrt(Math.pow(coords.get(j)[0]-coords.get(i)[0], 2) 
                + Math.pow(coords.get(j)[1]-coords.get(i)[1], 2) 
                + Math.pow(coords.get(j)[2]-coords.get(i)[2], 2));
                dists.put(dist, new int[]{i, j});
            }
        }

        // removed the counter and instead loop ends when trying to add circuit of same size as total coords
        HashSet<HashSet<Integer>> circs = new HashSet<>();
        for (Double dist : dists.keySet()){
            int index1 = dists.get(dist)[0];
            int index2 = dists.get(dist)[1];
            HashSet<Integer> circ1 = new HashSet<>(); circ1.add(index1);
            HashSet<Integer> circ2 = new HashSet<>(); circ2.add(index2);
            for(HashSet<Integer> circ: circs){
                if(circ.contains(index1)) circ1 = circ;
                if(circ.contains(index2)) circ2 = circ;
            }
            HashSet<Integer> temp = new HashSet<>();
            temp.addAll(circ1);
            temp.addAll(circ2);

            if(temp.size() == coords.size()){
                System.out.println((double) coords.get(index1)[0] * coords.get(index2)[0]); // i love when integer overflow so (double)
                break;
            }
            circs.add(temp);
            if(circ1 != circ2){
                if(circ1.size() != 1) circs.remove(circ1);
                if(circ2.size() != 1) circs.remove(circ2);
            }
        }
    }
}
