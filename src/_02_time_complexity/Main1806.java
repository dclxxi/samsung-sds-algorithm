package _02_time_complexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1806 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        
        int[] sequence = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        
        int start = 0, end = 0, sum = 0;
        int min = Integer.MAX_VALUE;
        while (end <= N) {
            if (sum >= S) {
                min = Math.min(min, end - start);
                if (min == 1) {
                    System.out.println(1);
                    return;
                }
                
                sum -= sequence[start++];
            } else {
                sum += sequence[end++];
            }
        }
        
        if (min == Integer.MAX_VALUE) {
            System.out.println(0);
            return;
        }
        
        System.out.println(min);
    }
}
