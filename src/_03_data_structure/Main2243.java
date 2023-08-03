package _03_data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2243 {
    
    private static int[] tree;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int S = 1;
        while (S < 1000000) {
            S <<= 1;
        }
        
        tree = new int[S << 1];
        
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            if (Integer.parseInt(st.nextToken()) == 1) {
                int B = Integer.parseInt(st.nextToken());
                int index = query(1, S, 1, B);
                update(1, S, 1, index, -1);
                
                sb.append(index).append("\n");
            } else {
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                update(1, S, 1, B, C);
            }
        }
        
        System.out.print(sb);
    }
    
    private static int query(int left, int right, int node, int target) {
        if (left == right) {
            return left;
        }
        
        int middle = left + ((right - left) >> 1);
        int child = node << 1;
        if (tree[child] >= target) {
            return query(left, middle, child, target);
        }
        
        return query(middle + 1, right, child | 1, target - tree[child]);
    }
    
    private static void update(int left, int right, int node, int target, int diff) {
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
}
