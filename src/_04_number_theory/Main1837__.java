package _04_number_theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1837__ {
    
    private static char[] P;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = st.nextToken().toCharArray();
        int K = Integer.parseInt(st.nextToken());
        
        boolean[] visited = new boolean[K + 1];
        for (int number = 2; number < K; number++) {
            if (visited[number]) {
                continue;
            }
            
            if (isBad(number)) {
                System.out.println("BAD " + number);
                return;
            }
            
            for (int i = number << 1; i <= K; i += number) {
                visited[i] = true;
            }
        }
        
        System.out.println("GOOD");
    }
    
    private static boolean isBad(int prime) {
        int mod = 0;
        for (char digit : P) {
            mod = (mod * 10 + (digit - '0')) % prime;
        }
        
        return mod == 0;
    }
}
