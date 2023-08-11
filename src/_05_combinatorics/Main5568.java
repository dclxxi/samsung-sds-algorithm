package _05_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main5568 {
    
    private static final Set<String> set = new HashSet<>();
    private static final StringBuilder sb = new StringBuilder();
    
    private static int n, k;
    private static boolean[] visited;
    private static String[] cards;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        
        cards = new String[n];
        for (int i = 0; i < n; i++) {
            cards[i] = br.readLine();
        }
        
        visited = new boolean[n];
        permute(0);
        
        System.out.println(set.size());
    }
    
    private static void permute(int count) {
        if (count == k) {
            set.add(sb.toString());
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int length = sb.length();
                sb.append(cards[i]);
                
                permute(count + 1);
                
                sb.setLength(length);
                visited[i] = false;
            }
        }
    }
}
