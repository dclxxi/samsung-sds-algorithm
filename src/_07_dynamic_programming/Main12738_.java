package _07_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main12738_ {
    
    private static List<Integer> lis;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(1);
            return;
        }
        
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        lis = new ArrayList<>();
        lis.add(A[0]);
        
        int end = 0;
        for (int i = 1; i < N; i++) {
            int number = A[i];
            
            if (number > lis.get(end)) {
                lis.add(number);
                end++;
            } else {
                binarySearch(end, number);
            }
        }
        
        System.out.println(end + 1);
    }
    
    private static void binarySearch(int end, int number) {
        int start = 0, min = 0;
        while (start <= end) {
            int middle = start + ((end - start) >> 1);
            
            if (lis.get(middle) < number) {
                start = middle + 1;
            } else {
                end = middle - 1;
                min = middle;
            }
        }
        
        lis.set(min, number);
    }
}