import java.util.*;
import java.io.*;

public class Day11 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./Day11.txt"));
        part1(in);
        
        // make sure to run both parts together because connections from input is reused from part1 in part2
        part2(in);

        in.close();
    }

    private static HashMap<String, ArrayList<String>> connections = new HashMap<>();
    private static ArrayList<String> order = new ArrayList<>();
    private static HashSet<String> visited = new HashSet<>();

    private static void dfs(String node){
        visited.add(node);
        for(String next : connections.getOrDefault(node, new ArrayList<>())){
            if(!visited.contains(next)) dfs(next);
        }
        order.add(node);
    }

    public static void part1(Scanner in) {
        // organize data into graph
        while(in.hasNextLine()){
            String line = in.nextLine();
            ArrayList<String> edges = new ArrayList<>(Arrays.asList(line.substring(line.indexOf(": ") + 2).split(" ")));
            connections.put(line.substring(0, line.indexOf(":")), edges);
        }
        // System.out.println(connections.toString());

        // using dfs, create order from "you" to "out"
        // want a good order to do next part, dont want to start "in middle" of graph
        for(String node : connections.keySet()){
            if(!visited.contains(node)){
                dfs(node);
            }
        }
        Collections.reverse(order);
        // System.out.println(order.toString());

        // dp(x) is num of ways to reach node x from "you"
        HashMap<String, Long> dp = new HashMap<>();
        for(String node : connections.keySet()) dp.put(node, 0L);
        dp.put("you", 1L);

        for(String node : order){
            long ways = dp.getOrDefault(node, 0L);
            if(ways == 0) continue;
            for(String conn : connections.getOrDefault(node, new ArrayList<>())){
                dp.put(conn, dp.getOrDefault(conn, 0L) + ways);
            }
        }

        System.out.println(dp.get("out"));
    }

    // simply repurpose part 1
    // instead of going from "you" to "out", we can split into 3 different segments
    // svr-dac x dac-fft x fft-out + svr-fft x fft-dac x dac-out
    // can either visit dac or fft first so must account for both ways by adding
    private static long p2_helper(String start, String end){
        // make sure to clear stuff so parts doent affect one another
        order.clear();
        visited.clear();
        
        for(String node : connections.keySet()) if(!visited.contains(node)) dfs(node);
        Collections.reverse(order);

        HashMap<String, Long> dp = new HashMap<>();
        for(String node : connections.keySet()) dp.put(node, 0L);
        dp.put(start, 1L);

        for(String node : order){
            long ways = dp.getOrDefault(node, 0L);
            if(ways == 0) continue;
            for(String conn : connections.getOrDefault(node, new ArrayList<>())){
                dp.put(conn, dp.getOrDefault(conn, 0L) + ways);
            }
        }

        return dp.get(end);
    }

    public static void part2(Scanner in) {
        long D1 = p2_helper("svr", "dac");
        long D2 = p2_helper("dac", "fft");
        long D3 = p2_helper("fft", "out");

        long F1 = p2_helper("svr", "fft");
        long F2 = p2_helper("fft", "dac");
        long F3 = p2_helper("dac", "out");

        System.out.println(D1*D2*D3 + F1*F2*F3);
    }
}
