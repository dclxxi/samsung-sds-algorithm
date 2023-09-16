package _07_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2449 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] bulbs = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            bulbs[i] = Integer.parseInt(st.nextToken());
            if (bulbs[i - 1] == bulbs[i]) {
                N--;
                i--;
            }
        }
        
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
            
            dp[i][i] = 0;
        }
        
        for (int size = 2; size <= N; size++) {
            for (int start = 1; start <= N - size + 1; start++) {
                int end = start + size - 1;
                
                for (int k = start; k < end; k++) {
                    int temp = dp[start][k] + dp[k + 1][end];
                    if (bulbs[start] != bulbs[k + 1]) {
                        temp++;
                    }
                    
                    dp[start][end] = Math.min(dp[start][end], temp);
                }
            }
        }
        
        System.out.println(dp[1][N]);
    }
}
