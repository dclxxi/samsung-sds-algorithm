package _05_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main5557 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int[] numbers = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        long[][] dp = new long[N + 1][21];
        dp[1][numbers[1]] = 1;
        
        for (int i = 2; i < N; i++) {
            for (int number = 0; number <= 20; number++) {
                if (dp[i - 1][number] == 0) {
                    continue;
                }
                
                int sum = number + numbers[i];
                if (sum <= 20) {
                    dp[i][sum] += dp[i - 1][number];
                }
                
                sum = number - numbers[i];
                if (sum >= 0) {
                    dp[i][sum] += dp[i - 1][number];
                }
            }
        }
        
        System.out.println(dp[N - 1][numbers[N]]);
    }
}
