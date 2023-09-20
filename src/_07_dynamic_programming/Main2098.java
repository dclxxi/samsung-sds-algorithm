package _07_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2098 {
    
    private static final int INF = 987654321;
    
    private static int N, bitmask;
    private static int[][] W, dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        bitmask = (1 << N) - 1;
        W = new int[N][N];
        dp = new int[N][bitmask];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
            
            Arrays.fill(dp[i], -1);
        }
        
        System.out.println(dfs(0, 1));
    }
    
    private static int dfs(int city, int visited) {
        if (visited == bitmask) {
            if (W[city][0] == 0) {
                return INF;
            }
            
            return W[city][0];
        }
        
        if (dp[city][visited] != -1) {
            return dp[city][visited];
        }
        
        dp[city][visited] = INF;
        for (int i = 0; i < N; i++) {
            if (W[city][i] == 0) {
                continue;
            }
            
            int neighbor = 1 << i;
            if ((visited & neighbor) != 0) {
                continue;
            }
            
            dp[city][visited] = Math.min(dp[city][visited], dfs(i, visited | neighbor) + W[city][i]);
        }
        
        return dp[city][visited];
    }
}
