package _05_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main5569 {
    
    private static final int MOD = 100000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        
        long[][][] dp = new long[w + 1][h + 1][4];
        for (int x = 2; x <= w; x++) {
            dp[x][1][0] = 1;
        }
        
        for (int y = 2; y <= w; y++) {
            dp[1][y][1] = 1;
        }
        
        dp[2][2][2] = dp[2][2][3] = 1;
        
        for (int x = 2; x <= w; x++) {
            for (int y = 2; y <= h; y++) {
                if (x == 2 && y == 2) {
                    continue;
                }
                
                if (x > 2) {
                    long[] road = dp[x - 2][y];
                    dp[x][y][0] = (road[0] + road[1] + road[2]) % MOD;
                }
                
                if (y > 2) {
                    long[] road = dp[x][y - 2];
                    dp[x][y][1] = (road[0] + road[1] + road[3]) % MOD;
                }
                
                long[] road = dp[x - 1][y - 1];
                dp[x][y][2] = (road[1] + road[3]) % MOD;
                dp[x][y][3] = (road[0] + road[2]) % MOD;
            }
            
        }
        
        int sum = 0;
        for (long road : dp[w][h]) {
            sum += road;
        }
        
        System.out.println(sum % MOD);
    }
}
