package _02_time_complexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main2143 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        
        List<Long> subA = getSubArray(A, n);
        subA.sort(Comparator.naturalOrder());
        
        List<Long> subB = getSubArray(B, m);
        subB.sort(Comparator.reverseOrder());
        
        int sizeA = subA.size();
        int sizeB = subB.size();
        
        int pointerA = 0, pointerB = 0;
        long count = 0;
        while (pointerA < sizeA && pointerB < sizeB) {
            long a = subA.get(pointerA);
            long b = subB.get(pointerB);
            
            long sum = a + b;
            if (sum == T) {
                long countA = 0;
                while (pointerA < sizeA && subA.get(pointerA) == a) {
                    countA++;
                    pointerA++;
                }
                
                long countB = 0;
                while (pointerB < sizeB && subB.get(pointerB) == b) {
                    countB++;
                    pointerB++;
                }
                
                count += countA * countB;
            } else if (sum < T) {
                pointerA++;
            } else {
                pointerB++;
            }
        }
        
        System.out.println(count);
    }
    
    private static List<Long> getSubArray(int[] A, int n) {
        List<Long> subArray = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                subArray.add(sum);
            }
        }
        
        return subArray;
    }
}
