package _05_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1010 {
    
    private static final int[][] dp = new int[30][30];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            sb.append(combination(M, N)).append("\n");
        }
        
        System.out.print(sb);
    }
    
    private static int combination(int m, int n) {
        if (dp[m][n] != 0) {
            return dp[m][n];
        }
        
        if (m == n || n == 0) {
            return dp[m][n] = 1;
        }
        
        return dp[m][n] = combination(m - 1, n - 1) + combination(m - 1, n);
    }
}
