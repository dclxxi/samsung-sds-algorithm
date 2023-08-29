package _06_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3830 {
    
    private static final int MAX = 100001;
    private static final int[] parents = new int[MAX];
    private static final int[] distances = new int[MAX];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }
            
            for (int i = 1; i <= N; i++) {
                parents[i] = i;
                distances[i] = 0;
            }
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                char command = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                if (command == '!') {
                    union(a, b, Integer.parseInt(st.nextToken()));
                } else {
                    if (find(a) == find(b)) {
                        sb.append(distances[b] - distances[a]);
                    } else {
                        sb.append("UNKNOWN");
                    }
                    sb.append("\n");
                }
            }
        }
        
        System.out.print(sb);
    }
    
    private static void union(int a, int b, int w) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA == rootB) {
            return;
        }
        
        distances[rootB] = distances[a] - distances[b] + w;
        parents[rootB] = rootA;
    }
    
    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        
        int root = find(parents[x]);
        distances[x] += distances[parents[x]];
        
        return parents[x] = root;
    }
}