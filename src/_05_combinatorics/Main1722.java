package _05_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1722 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        long[] factorials = new long[N + 1];
        factorials[1] = 1;
        for (int i = 2; i < N; i++) {
            factorials[i] = factorials[i - 1] * i;
        }
        
        boolean[] visited = new boolean[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        if (Integer.parseInt(st.nextToken()) == 1) {
            StringBuilder sb = new StringBuilder();
            long k = Long.parseLong(st.nextToken());
            
            Permutation:
            for (int digit = 1; digit <= N; digit++) {
                for (int number = 1; number <= N; number++) {
                    if (visited[number]) {
                        continue;
                    }
                    
                    long permutations = factorials[N - digit];
                    if (permutations < k) {
                        k -= permutations;
                    } else {
                        sb.append(number).append(" ");
                        visited[number] = true;
                        
                        if (permutations == k) {
                            break Permutation;
                        }
                        
                        break;
                    }
                }
            }
            
            for (int number = N; number > 0; number--) {
                if (!visited[number]) {
                    sb.append(number).append(" ");
                }
            }
            
            System.out.println(sb);
        } else {
            long sum = 0;
            for (int digit = 1; digit <= N; digit++) {
                int number = Integer.parseInt(st.nextToken());
                visited[number] = true;
                
                int count = 0;
                for (int prev = 1; prev < number; prev++) {
                    if (!visited[prev]) {
                        count++;
                    }
                }
                
                sum += count * factorials[N - digit];
            }
            
            System.out.println(sum + 1);
        }
    }
}
