import java.util.*;
import java.io.*;

public class Day6 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./Day6.txt"));
        part1(in);
        
        in = new Scanner(new FileReader("./Day6.txt"));
        part2(in);

        in.close();
    }

    public static void part1(Scanner in) {
        // organizing data
        ArrayList<ArrayList<String>> probs = new ArrayList<>();
        while(in.hasNextLine()){
            ArrayList<String> row = new ArrayList<>(Arrays.asList(in.nextLine().split(" +")));
            probs.add(row);
        }
        // for(int i = 0; i < probs.size(); i++) System.out.println(probs.get(i));

        // column major loop
        double tot = 0;
        for(int c = 0; c < probs.get(0).size(); c++){
            double sum = 0, prod = 1;
            String func = probs.get(probs.size()-1).get(c);
            for(int r = 0; r < probs.size()-1; r++){
                if(func.equals("+")) sum += Double.parseDouble(probs.get(r).get(c));
                else prod *= Double.parseDouble(probs.get(r).get(c));
            }
            if(func.equals("+")) tot += sum;
            else tot += prod;
        }
        System.out.println(tot);
    }

    public static void part2(Scanner in) {
        // organize data
        ArrayList<String> data = new ArrayList<>();
        while(in.hasNextLine()) data.add(in.nextLine());
        // store operators in seperate string for easy access
        String ops = data.get(data.size()-1);
        data.remove(data.size()-1);
        // for(String line : data) System.out.println(line);
        // System.out.println(ops);

        double total = 0;
        double sum = 0, prod = 1;
        ArrayList<Integer> values = new ArrayList<>();

        // backwards loop through each character 
        for(int i = data.get(0).length() - 1; i >= 0; i--){
            // store addend in values array
            String temp = "";
            for(int r = 0; r < data.size(); r++) temp += data.get(r).charAt(i);
            values.add(Integer.parseInt(temp.strip()));

            // read string of operators
            if(ops.charAt(i) == '+') for(int value : values) sum += value;
            else if(ops.charAt(i) == '*') for(int value : values) prod *= value;
            // if no operator at current character then should continue adding addends to values array
            else continue;

            // if an operator was at current character, add to total and reset to solve next math problem
            if(sum != 0) total += sum;
            if(prod != 1) total += prod;
            values.clear();
            sum = 0;
            prod = 1;
            i--; // skips the empty space between two math problems
        }
        System.out.println(total);
    }
}
