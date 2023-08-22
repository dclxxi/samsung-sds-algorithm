package _06_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main1922 {
    
    private static int[] parents;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        
        int M = Integer.parseInt(br.readLine());
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            edges.add(new Edge(a, b, c));
        }
        
        Collections.sort(edges);
        
        int cost = 0, count = 1;
        for (Edge edge : edges) {
            int a = edge.a;
            int b = edge.b;
            
            if (union(a, b)) {
                cost += edge.c;
                
                if (++count == N) {
                    break;
                }
            }
        }
        
        System.out.println(cost);
    }
    
    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if (a == b) {
            return false;
        }
        
        parents[a] = b;
        return true;
    }
    
    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        
        return parents[x] = find(parents[x]);
    }
    
    static class Edge implements Comparable<Edge> {
        
        int a, b, c;
        
        Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }
}
