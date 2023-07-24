package _01_algorithm_basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1062 {
    
    private static final char LETTER_A = 'a';
    private static final char[] taughtLetters = {'a', 'n', 't', 'i', 'c'};
    private static final boolean[] visited = new boolean[26];
    
    private static int max;
    private static String[] words;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        if (K < 5) {
            System.out.println(0);
            return;
        }
        
        if (K == 26) {
            System.out.println(N);
            return;
        }
        
        words = new String[N];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = word.replaceAll("[antic]", "");
        }
        
        for (char taughtLetter : taughtLetters) {
            visited[taughtLetter - LETTER_A] = true;
        }
        
        dfs(1, K - 5);
        System.out.println(max);
    }
    
    private static void dfs(int start, int remain) {
        if (remain == 0) {
            int count = 0;
            for (String word : words) {
                boolean readable = true;
                for (int i = 0; i < word.length(); i++) {
                    if (!visited[word.charAt(i) - LETTER_A]) {
                        readable = false;
                        break;
                    }
                }
                
                if (readable) {
                    count++;
                }
            }
            
            max = Math.max(max, count);
            return;
        }
        
        for (int i = start; i < 26; i++) {
            if (!visited[i]) {
                dfs(i + 1, remain - 1);
                visited[i] = false;
            }
        }
    }
}