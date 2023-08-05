package _04_number_theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14476 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int[] numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] prefix = new int[N];
        prefix[0] = numbers[0];
        for (int i = 1; i < N; i++) {
            prefix[i] = getGCD(prefix[i - 1], numbers[i]);
        }
        
        int[] suffix = new int[N];
        suffix[N - 1] = numbers[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            suffix[i] = getGCD(suffix[i + 1], numbers[i]);
        }
        
        int max = -1, number = 0;
        for (int i = 0; i < N; i++) {
            int gcd;
            if (i == 0) {
                gcd = suffix[1];
            } else if (i == N - 1) {
                gcd = prefix[N - 2];
            } else {
                gcd = getGCD(prefix[i - 1], suffix[i + 1]);
            }
            
            if (max < gcd && numbers[i] % gcd != 0) {
                max = gcd;
                number = numbers[i];
            }
        }
        
        if (max == -1) {
            System.out.println(-1);
            return;
        }
        
        System.out.println(max + " " + number);
    }
    
    private static int getGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        
        return getGCD(b, a % b);
    }
}
