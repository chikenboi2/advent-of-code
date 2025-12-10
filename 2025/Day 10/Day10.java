import java.util.*;
import java.io.*;

public class Day10 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./Day10.txt"));
        part1(in);
        
        in = new Scanner(new FileReader("./Day10.txt"));
        // part2(in);

        in.close();
    }

    // 166 lines, max 12 buttons to press
    // 166 * 2^12 = 679936
    public static void part1(Scanner in) {
        double tot = 0;
        while(in.hasNextLine()){
            // organize indicator lights 
            ArrayList<Integer> real_indicator = new ArrayList<>();
            String line = in.nextLine();
            String[] indic_line = line.substring(1, line.indexOf("]")).split("");
            for(int i = 0; i < indic_line.length; i++) if(indic_line[i].equals("#")) real_indicator.add(i);

            // organize buttons
            ArrayList<ArrayList<Integer>> buttons = new ArrayList<>();
            String[] butt_line = line.split(" ");
            for(int i = 1; i < butt_line.length - 1; i++){
                String butt_line_nums = butt_line[i].substring(1, butt_line[i].length() - 1);
                ArrayList<Integer> butt_nums = new ArrayList<>();
                for(String num : butt_line_nums.split(",")) butt_nums.add(Integer.parseInt(num));
                buttons.add(butt_nums);
            }
            // System.out.println(Arrays.toString(buttons.toArray()) + real_indicator);

            // should never have to press same button twice, so
            // create on/off combinations for each button to simulate every possible situation
            // Integer.toBinaryString() is a cool new thing i discovered
            ArrayList<String[]> combinations = new ArrayList<>();
            for(int i = (int) Math.pow(2, buttons.size()); i < Math.pow(2, buttons.size()+1); i++){
                combinations.add(Integer.toBinaryString(i).substring(1).split(""));
            }
            // System.out.println(Arrays.deepToString(combinations.toArray()));

            // go through each combination, press certain buttons, then check if our indicator matches real
            // if matches, track least amt of button presses
            ArrayList<Integer> my_indicator = new ArrayList<>();
            double min = Double.MAX_VALUE;
            for(String[] combo : combinations){
                my_indicator = new ArrayList<>();
                for(int i = 0; i < combo.length; i++) if(combo[i].equals("1")) {
                    for(int num : buttons.get(i)){
                        if(my_indicator.contains(num)) my_indicator.remove(my_indicator.indexOf(num));
                        else my_indicator.add(num);
                    }
                }
                Collections.sort(my_indicator);
                if(my_indicator.equals(real_indicator)){
                    // System.out.println(Arrays.toString(combo)+my_indicator);
                    double temp = 0;
                    for(String num : combo) temp += Integer.parseInt(num);
                    if(temp < min) min = temp;
                }
            }
            // System.out.println(min);
            tot += min;
        }

        System.out.println(tot);
    }

    public static void part2(Scanner in) {
        double tot = 0;
        while(in.hasNextLine()){
            // organize joltage requirements 
            ArrayList<Integer> real_joltage = new ArrayList<>();
            String line = in.nextLine();
            String[] jolt_line = line.substring(line.indexOf("{") + 1, line.indexOf("}")).split(",");
            for(String num : jolt_line) real_joltage.add(Integer.parseInt(num));

            // organize buttons
            ArrayList<ArrayList<Integer>> buttons = new ArrayList<>();
            String[] butt_line = line.split(" ");
            for(int i = 1; i < butt_line.length - 1; i++){
                String butt_line_nums = butt_line[i].substring(1, butt_line[i].length() - 1);
                ArrayList<Integer> butt_nums = new ArrayList<>();
                for(String num : butt_line_nums.split(",")) butt_nums.add(Integer.parseInt(num));
                buttons.add(butt_nums);
            }
            // System.out.println(Arrays.toString(buttons.toArray()) + real_joltage);

            // lowkenuinely lost, prolly some recursive thing?
            // ill ponder on it
        }
        // System.out.println(tot);
    }
}
