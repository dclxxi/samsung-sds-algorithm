package _06_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main11438 {
    
    private static int height;
    private static int[] depths;
    private static int[][] parents;
    private static List<Integer>[] tree;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        tree = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            tree[a].add(b);
            tree[b].add(a);
        }
        
        depths = new int[N + 1];
        depths[1] = 1;
        
        height = (int) (Math.ceil(Math.log(N) / Math.log(2)) + 1);
        parents = new int[N + 1][height];
        
        dfs(1);
        
        for (int ancestor = 1; ancestor < height - 1; ancestor++) {
            for (int i = 1; i <= N; i++) {
                parents[i][ancestor] = parents[parents[i][ancestor - 1]][ancestor - 1];
            }
        }
        
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb.append(findLCA(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
        }
        
        System.out.print(sb);
    }
    
    private static void dfs(int curr) {
        int depth = depths[curr] + 1;
        
        for (int next : tree[curr]) {
            if (depths[next] == 0) {
                parents[next][0] = curr;
                depths[next] = depth;
                dfs(next);
            }
        }
    }
    
    private static int findLCA(int u, int v) {
        if (depths[u] < depths[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        
        int diff = depths[u] - depths[v], ancestor = 0;
        while (diff > 0) {
            if ((diff & 1) == 1) {
                u = parents[u][ancestor];
            }
            
            ancestor++;
            diff >>= 1;
        }
        
        if (u == v) {
            return u;
        }
        
        for (int i = height - 1; i >= 0; i--) {
            if (parents[u][i] != parents[v][i]) {
                u = parents[u][i];
                v = parents[v][i];
            }
        }
        
        return parents[u][0];
    }
}
