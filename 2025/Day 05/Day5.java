import java.util.*;
import java.io.*;

public class Day5 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./Day5.txt"));
        part1(in);
        
        in = new Scanner(new FileReader("./Day5.txt"));
        part2(in);

        in.close();
    }

    // 181 * 1000 = 181000
    public static void part1(Scanner in) {
        // put the ranges into a list
        ArrayList<String> ranges = new ArrayList<>();
        String line;
        while((line = in.nextLine()) != "") ranges.add(line);
        int tot = 0;

        // for each value, for each range, if value in range done
        while(in.hasNextLine()){
            double value = Double.parseDouble(in.nextLine());
            for(String range : ranges){
                double min = Double.parseDouble(range.substring(0, range.indexOf("-")));
                double max = Double.parseDouble(range.substring(range.indexOf("-") + 1));
                if(min <= value && value <= max){
                    tot++;
                    break;
                }
            }
        }
        System.out.println(tot);
    }


    public static void part2(Scanner in) {
        // list of ranges as [min, max] 
        ArrayList<Double[]> ranges = new ArrayList<>();
        String line;
        while((line = in.nextLine()) != ""){
            Double[] temp = new Double[2];
            temp[0] = Double.parseDouble(line.substring(0, line.indexOf("-")));
            temp[1] = Double.parseDouble(line.substring(line.indexOf("-") + 1));
            ranges.add(temp);
        }

        // ripped ts off stack overflow bc ive never used Comparator but it mostly makes sense to me
        // sorts my ranges based on min
        Collections.sort(ranges, new Comparator<Double[]>(){
            public int compare(Double[] arr1, Double[] arr2){
                return arr1[0].compareTo(arr2[0]);
            }
        });
        // for(Double[] val : ranges) System.out.println(Arrays.toString(val));
        // System.out.println(ranges.size());

        ArrayList<Double[]> combined_ranges = new ArrayList<>();
        // added the first range just to get smth in there to start loop
        combined_ranges.add(ranges.get(0));

        for(int i = 1; i < ranges.size(); i++){
            double min = ranges.get(i)[0];
            double max = ranges.get(i)[1];
            Double[] prev = combined_ranges.get(combined_ranges.size()-1);
            // because our range is sorted, if range min is inside previous range, update previous max if necessary
            if(min <= prev[1]) {
                if(prev[1] < max) prev[1] = max;
                combined_ranges.set(combined_ranges.size()-1, prev);
            }
            // otherwise, previous range is finished and put new "prev" range for possible updating
            else combined_ranges.add(new Double[]{min, max});
        }
        // for(Double[] val : combined_ranges) System.out.println(Arrays.toString(val));
        // System.out.println(combined_ranges.size());

        // loop through combined ranges and see how many values in each range
        double tot = 0;
        for(Double[] range : combined_ranges) tot += (range[1]-range[0] + 1);
        System.out.println(tot);
    }
}
