package _07_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main5582 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[] sequence1 = br.readLine().toCharArray();
        char[] sequence2 = br.readLine().toCharArray();
        
        int length1 = sequence1.length;
        int length2 = sequence2.length;
        
        int max = 0;
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (sequence1[i - 1] == sequence2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        
        System.out.println(max);
    }
}
