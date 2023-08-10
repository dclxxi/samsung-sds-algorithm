package _05_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1256 {
    
    private static final int INF = 1000000001;
    private static final StringBuilder sb = new StringBuilder();
    
    private static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        dp = new int[N + 1][M + 1];
        if (K > calculateDP(N, M)) {
            System.out.println(-1);
        } else {
            findString(N, M, K);
            System.out.println(sb);
        }
    }
    
    private static int calculateDP(int a, int z) {
        if (dp[a][z] != 0) {
            return dp[a][z];
        }
        
        if (a == 0 || z == 0) {
            return dp[a][z] = 1;
        }
        
        return dp[a][z] = Math.min(calculateDP(a - 1, z) + calculateDP(a, z - 1), INF);
    }
    
    private static void findString(int a, int z, int k) {
        if (a == 0) {
            sb.append("z".repeat(z));
            return;
        }
        
        if (z == 0) {
            sb.append("a".repeat(a));
            return;
        }
        
        int aCount = calculateDP(a - 1, z);
        if (k <= aCount) {
            sb.append("a");
            findString(a - 1, z, k);
        } else {
            sb.append("z");
            findString(a, z - 1, k - aCount);
        }
    }
}
