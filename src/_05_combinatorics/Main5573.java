package _05_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main5573 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        boolean[][] map = new boolean[H + 1][W + 1];
        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }
        
        int[][] dp = new int[H + 2][W + 2];
        dp[1][1] = N - 1;
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                int letter = dp[i][j];
                
                int half = letter >> 1;
                int remainder = letter & 1;
                if (map[i][j]) {
                    dp[i][j + 1] += half + remainder;
                    dp[i + 1][j] += half;
                } else {
                    dp[i + 1][j] += half + remainder;
                    dp[i][j + 1] += half;
                }
            }
        }
        
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                if ((dp[i][j] & 1) == 1) {
                    map[i][j] = !map[i][j];
                }
            }
        }
        
        int i = 1, j = 1;
        while (i <= H && j <= W) {
            if (map[i][j]) {
                j++;
            } else {
                i++;
            }
        }
        
        System.out.println(i + " " + j);
    }
}
