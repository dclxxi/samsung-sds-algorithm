package _05_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main15664 {
    
    private static final StringBuilder sb = new StringBuilder();
    
    private static int N, M;
    private static int[] numbers;
    private static int[] permutation;
    private static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(numbers);
        
        permutation = new int[M];
        visited = new boolean[N];
        findPermutations(0, 0);
        
        System.out.print(sb);
    }
    
    private static void findPermutations(int start, int index) {
        if (index == M) {
            for (int i = 0; i < M; i++) {
                sb.append(permutation[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        int prev = 0;
        for (int i = start; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            
            int curr = numbers[i];
            if (prev != curr) {
                visited[i] = true;
                
                permutation[index] = prev = curr;
                findPermutations(i + 1, index + 1);
                
                visited[i] = false;
            }
        }
    }
}
