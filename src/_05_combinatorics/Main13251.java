package _05_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main13251 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int M = Integer.parseInt(br.readLine());
        if (M == 1) {
            System.out.println(1.0);
            return;
        }
        
        int N = 0;
        int[] stones = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            N += stones[i] = Integer.parseInt(st.nextToken());
        }
        
        int K = Integer.parseInt(br.readLine());
        if (K == 1) {
            System.out.println(1.0);
            return;
        }
        
        double total = 1;
        for (int i = 0; i < K; i++) {
            total *= (N - i);
        }
        
        double same = 0;
        for (int stone : stones) {
            if (stone < K) {
                continue;
            }
            
            double color = 1;
            for (int i = 0; i < K; i++) {
                color *= stone - i;
            }
            
            same += color;
        }
        
        System.out.println(same / total);
    }
}
