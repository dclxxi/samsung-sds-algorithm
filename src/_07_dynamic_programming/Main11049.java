package _07_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11049 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[][] sizes = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sizes[i][0] = Integer.parseInt(st.nextToken());
            sizes[i][1] = Integer.parseInt(st.nextToken());
        }
        
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
            dp[i][i] = 0;
        }
        
        for (int size = 2; size <= N; size++) {
            for (int start = 0; start <= N - size; start++) {
                int end = start + size - 1;
                
                for (int k = start; k < end; k++) {
                    dp[start][end] = Math.min(dp[start][end],
                            dp[start][k] + dp[k + 1][end] + sizes[start][0] * sizes[k][1] * sizes[end][1]);
                }
            }
        }
        
        System.out.println(dp[0][N - 1]);
    }
}