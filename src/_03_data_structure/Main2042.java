package _03_data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2042 {
    
    private static int S;
    private static long[] tree;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
        
        S = 1;
        while (S < N) {
            S <<= 1;
        }
        
        tree = new long[S << 1];
        for (int i = S; i < S + N; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }
        
        init();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            if (Integer.parseInt(st.nextToken()) == 1) {
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                update(1, S, 1, b, c - tree[S + b - 1]);
            } else {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                sb.append(query(1, S, 1, b, c)).append("\n");
            }
        }
        
        System.out.print(sb);
    }
    
    private static void init() {
        for (int parent = S - 1; parent >= 1; parent--) {
            int child = parent << 1;
            tree[parent] = tree[child] + tree[child | 1];
        }
    }
    
    private static void update(int left, int right, int node, int target, long diff) {
        if (target < left || target > right) {
            return;
        }
        
        tree[node] += diff;
        if (left != right) {
            int middle = left + ((right - left) >> 1);
            int child = node << 1;
            update(left, middle, child, target, diff);
            update(middle + 1, right, child | 1, target, diff);
        }
    }
    
    private static long query(int left, int right, int node, int queryLeft, int queryRight) {
        if (queryLeft > right || queryRight < left) {
            return 0;
        }
        
        if (queryLeft <= left && queryRight >= right) {
            return tree[node];
        }
        
        int middle = left + ((right - left) >> 1);
        int child = node << 1;
        
        return query(left, middle, child, queryLeft, queryRight)
                + query(middle + 1, right, child | 1, queryLeft, queryRight);
    }
}
