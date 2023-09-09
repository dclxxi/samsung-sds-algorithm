package _07_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9252 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        char[] sequence1 = br.readLine().toCharArray();
        char[] sequence2 = br.readLine().toCharArray();
        
        int length1 = sequence1.length;
        int length2 = sequence2.length;
        
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (sequence1[i - 1] == sequence2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        int length = dp[length1][length2];
        
        while (length1 != 0 && length2 != 0) {
            if (sequence1[length1 - 1] == sequence2[length2 - 1]) {
                sb.insert(0, sequence1[length1 - 1]);
                length1--;
                length2--;
            } else if (dp[length1][length2] == dp[length1 - 1][length2]) {
                length1--;
            } else if (dp[length1][length2] == dp[length1][length2 - 1]) {
                length2--;
            }
        }
        
        sb.insert(0, length + "\n");
        System.out.println(sb);
    }
}

