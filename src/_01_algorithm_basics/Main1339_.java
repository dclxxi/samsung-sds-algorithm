package _01_algorithm_basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main1339_ {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int[] alphabets = new int[26];
        
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            String word = br.readLine();
            int length = word.length();
            
            int power = 1;
            for (int i = length - 1; i >= 0; i--) {
                alphabets[word.charAt(i) - 'A'] += power;
                power *= 10;
            }
        }
        
        Arrays.sort(alphabets);
        
        int sum = 0, digit = 9;
        for (int i = 25; i >= 0; i--) {
            if (alphabets[i] == 0) {
                break;
            }
            
            sum += alphabets[i] * digit;
            digit--;
        }
        
        System.out.println(sum);
    }
}
