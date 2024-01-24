package _02_time_complexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1072 {
    
    private static int X, Y;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        
        long Z = getZ(0);
        if (Z >= 99) {
            System.out.println(-1);
            return;
        }
        
        int start = 1, end = X, min = 0;
        while (start <= end) {
            int middle = start + ((end - start) >> 1);
            
            if (getZ(middle) == Z) {
                start = middle + 1;
            } else {
                end = middle - 1;
                min = middle;
            }
        }
        
        System.out.println(min);
    }
    
    private static long getZ(int count) {
        return (Y + count) * 100L / (X + count);
    }
}
