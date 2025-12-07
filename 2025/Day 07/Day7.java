import java.util.*;
import java.io.*;

public class Day7 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./Day7.txt"));
        part1(in);
        
        in = new Scanner(new FileReader("./Day7.txt"));
        part2(in);

        in.close();
    }

    public static void part1(Scanner in) {
        // organize data
        ArrayList<ArrayList<Integer>> coords = new ArrayList<>();
        while(in.hasNextLine()){
            ArrayList<Integer> temp = new ArrayList<>();
            String line = in.nextLine();
            for(int i = 0; i < line.length(); i++) if(line.charAt(i) == '^') temp.add(i);
            coords.add(temp);
            in.nextLine(); // skips "empty" line
        }
        coords.remove(0); // dont need 'S' because always at first splitter
        // System.out.println(coords.toString());

        ArrayList<Integer> beams = new ArrayList<>(coords.get(0));
        double splits = 0;
        // loops through each row of splitters
        for(ArrayList<Integer> row : coords){
            // System.out.println(beams.toString() + row.toString());
            ArrayList<Integer> beams_new = new ArrayList<>(beams);
            for(int beam : beams){
                // when beam hits splitter, remove x and splits into x+1 and x-1
                // if statements to not have multiple beams at one location
                if(row.contains(beam)){
                    if(!beams_new.contains(beam-1)) beams_new.add(beam-1);
                    if(!beams_new.contains(beam+1)) beams_new.add(beam+1);
                    beams_new.remove(beams_new.indexOf(beam));
                    splits++;
                }
            }
        beams = beams_new;
        }

        // Collections.sort(beams);
        // System.out.println(beams);
        System.out.println(splits);
    }

    public static void part2(Scanner in) {
        // organize data
        ArrayList<ArrayList<Integer>> coords = new ArrayList<>();
        while(in.hasNextLine()){
            ArrayList<Integer> temp = new ArrayList<>();
            String line = in.nextLine();
            for(int i = 0; i < line.length(); i++) if(line.charAt(i) == '^') temp.add(i);
            coords.add(temp);
            in.nextLine(); // skips "empty" line
        }
        coords.remove(0); // dont need 'S' because always at first splitter

        // switched to maps because too much to simulate, represent beams as location, amount pairs
        HashMap<Integer, Double> beams = new HashMap<>();
        beams.put(coords.get(0).get(0), 1.0); // the beam to start it all
        for(ArrayList<Integer> row : coords){
            HashMap<Integer, Double> beams_new = new HashMap<>(beams);
            for(Map.Entry<Integer, Double> beam : beams.entrySet()){
                if(row.contains(beam.getKey())){
                    // no more if statement bc we want all possible paths
                    // x amount of beams will split into x beams at +1 and -1 location
                    // also include the getordefault bc paths will intersect
                    beams_new.put(beam.getKey()+1, beams_new.getOrDefault(beam.getKey()+1, 0.0) + beam.getValue());
                    beams_new.put(beam.getKey()-1, beams_new.getOrDefault(beam.getKey()-1, 0.0) + beam.getValue());
                    beams_new.remove(beam.getKey());
                }
            }
            beams = beams_new;
        }

        double paths = 0;
        for(double val : beams.values()) paths += val;
        System.out.println(paths);
    }
}
