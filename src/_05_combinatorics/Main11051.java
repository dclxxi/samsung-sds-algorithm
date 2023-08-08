package _05_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11051 {
    
    private static final int MOD = 10007;
    
    private static int N, K;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        System.out.println((combination() * inverse()) % MOD);
    }
    
    private static int combination() {
        int result = 1;
        for (int i = N; i > N - K; i--) {
            result = (result * i) % MOD;
        }
        
        return result % MOD;
    }
    
    private static int inverse() {
        int a = 1;
        int p = MOD - 2;
        
        for (int i = 2; i <= K; i++) {
            a = (a * i) % MOD;
        }
        
        int result = 1;
        while (p > 0) {
            if ((p & 1) == 1) {
                result = (result * a) % MOD;
            }
            
            a = (a * a) % MOD;
            p >>= 1;
        }
        
        return result % MOD;
    }
}
