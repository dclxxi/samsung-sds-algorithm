package _02_time_complexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2805 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] trees = new int[N];
        
        long start = 0, end = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, trees[i]);
        }
        
        long max = 0;
        while (start <= end) {
            long middle = start + ((end - start) >> 1);
            
            long sum = 0;
            for (int tree : trees) {
                if (tree > middle) {
                    sum += tree - middle;
                }
            }
            
            if (sum == M) {
                System.out.println(middle);
                return;
            }
            
            if (sum < M) {
                end = middle - 1;
            } else {
                start = middle + 1;
                max = middle;
            }
        }
        
        System.out.println(max);
    }
}
