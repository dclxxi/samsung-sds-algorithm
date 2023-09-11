package _07_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1102 {
    
    private static int N, P;
    private static int[][] costs, dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        costs = new int[N][N];
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
            
            for (int j = 0; j < (1 << N); j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        String state = br.readLine();
        P = Integer.parseInt(br.readLine());
        
        int count = 0, bitmask = 0;
        for (int i = 0; i < N; i++) {
            if (state.charAt(i) == 'Y') {
                bitmask |= (1 << i);
                count++;
            }
        }
        
        int min = dfs(count, bitmask);
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
    
    private static int dfs(int count, int bitmask) {
        if (count >= P) {
            return 0;
        }
        
        if (dp[count][bitmask] != Integer.MAX_VALUE) {
            return dp[count][bitmask];
        }
        
        for (int i = 0; i < N; i++) {
            if ((bitmask & (1 << i)) != 0) {
                for (int j = 0; j < N; j++) {
                    if (i == j || ((bitmask & (1 << j)) != 0)) {
                        continue;
                    }
                    
                    dp[count][bitmask] = Math.min(dp[count][bitmask], dfs(count + 1, bitmask | (1 << j)) + costs[i][j]);
                }
            }
        }
        
        return dp[count][bitmask];
    }
}
