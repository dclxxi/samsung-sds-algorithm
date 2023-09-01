package _06_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main11266 {
    
    private static int order;
    private static int[] visited;
    private static boolean[] disconnected;
    private static List<Integer>[] graph;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        graph = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            graph[A].add(B);
            graph[B].add(A);
        }
        
        order = 1;
        visited = new int[V + 1];
        disconnected = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            if (visited[i] == 0) {
                dfs(i, true);
            }
        }
        
        int count = 0;
        sb.append("\n");
        for (int i = 1; i <= V; i++) {
            if (disconnected[i]) {
                sb.append(i).append(" ");
                count++;
            }
        }
        
        sb.insert(0, count);
        System.out.println(sb);
    }
    
    private static int dfs(int curr, boolean root) {
        int min = visited[curr] = order++;
        
        int childCount = 0;
        for (int child : graph[curr]) {
            if (visited[child] == 0) {
                childCount++;
                
                int minChild = dfs(child, false);
                if (!root && visited[curr] <= minChild) {
                    disconnected[curr] = true;
                }
                
                min = Math.min(min, minChild);
            } else {
                min = Math.min(min, visited[child]);
            }
        }
        
        if (root && childCount >= 2) {
            disconnected[curr] = true;
        }
        
        return min;
    }
}
