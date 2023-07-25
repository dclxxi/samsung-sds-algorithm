package _01_algorithm_basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1759 {
    
    private static final StringBuilder sb = new StringBuilder();
    
    private static int L, C;
    private static char[] alphabets, password;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        alphabets = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabets[i] = st.nextToken().charAt(0);
        }
        
        Arrays.sort(alphabets);
        
        password = new char[L];
        dfs(0, 0, 0, 0);
        
        System.out.print(sb);
    }
    
    private static void dfs(int start, int depth, int vowels, int consonants) {
        if (depth == L) {
            if (vowels >= 1 && consonants >= 2) {
                sb.append(password).append("\n");
            }
            
            return;
        }
        
        for (int i = start; i < C; i++) {
            password[depth] = alphabets[i];
            
            if (isVowel(alphabets[i])) {
                dfs(i + 1, depth + 1, vowels + 1, consonants);
            } else {
                dfs(i + 1, depth + 1, vowels, consonants + 1);
            }
        }
    }
    
    private static boolean isVowel(char alphabet) {
        return alphabet == 'a' || alphabet == 'e' || alphabet == 'i' || alphabet == 'o' || alphabet == 'u';
    }
}
