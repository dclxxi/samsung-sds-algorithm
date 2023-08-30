package _06_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main1626 {
    
    private static int height;
    private static int[] parents, depths;
    private static int[][] ancestors;
    private static int[][][] largest;
    private static List<Vertex>[] tree;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        if (V > E) {
            System.out.println(-1);
            return;
        }
        
        parents = new int[V + 1];
        tree = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
            tree[i] = new ArrayList<>();
        }
        
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            edges.add(new Edge(u, v, weight));
        }
        
        Collections.sort(edges);
        
        int cost = 0, count = 1;
        for (Edge edge : edges) {
            int u = edge.u;
            int v = edge.v;
            
            if (union(u, v)) {
                int weight = edge.weight;
                
                cost += weight;
                edge.included = true;
                
                tree[u].add(new Vertex(v, weight));
                tree[v].add(new Vertex(u, weight));
                
                if (++count == V) {
                    break;
                }
            }
        }
        
        if (count != V) {
            System.out.println(-1);
            return;
        }
        
        height = (int) (Math.ceil(Math.log(V) / Math.log(2)) + 1);
        depths = new int[V + 1];
        ancestors = new int[V + 1][height + 1];
        largest = new int[V + 1][height + 1][2];
        
        dfs(1);
        
        for (int ancestor = 1; ancestor <= height; ancestor++) {
            for (int i = 1; i <= V; i++) {
                int parent = ancestors[i][ancestor - 1];
                if (parent == 0 || ancestors[parent][ancestor - 1] == 0) {
                    continue;
                }
                
                int[] weights = largest[i][ancestor - 1];
                int max1 = weights[0];
                int max2 = weights[1];
                
                weights = largest[parent][ancestor - 1];
                int parentMax1 = weights[0];
                int parentMax2 = weights[1];
                
                if (max1 > parentMax1) {
                    largest[i][ancestor][0] = max1;
                    largest[i][ancestor][1] = Math.max(max2, parentMax1);
                } else if (max1 < parentMax1) {
                    largest[i][ancestor][0] = parentMax1;
                    largest[i][ancestor][1] = Math.max(max1, parentMax2);
                } else {
                    largest[i][ancestor][0] = max1;
                    largest[i][ancestor][1] = Math.max(max2, parentMax2);
                }
                
                ancestors[i][ancestor] = ancestors[parent][ancestor - 1];
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (Edge edge : edges) {
            if (edge.included) {
                continue;
            }
            
            int weight = edge.weight;
            int secondMax = findLCA(edge.u, edge.v, weight);
            if (secondMax == -1 || secondMax == weight) {
                continue;
            }
            
            min = Math.min(min, weight - secondMax);
        }
        
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(cost + min);
        }
    }
    
    private static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        
        if (u == v) {
            return false;
        }
        
        parents[v] = u;
        return true;
    }
    
    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        
        return parents[x] = find(parents[x]);
    }
    
    private static void dfs(int curr) {
        int depth = depths[curr] + 1;
        
        for (Vertex next : tree[curr]) {
            int number = next.number;
            if (depths[number] == 0) {
                ancestors[number][0] = curr;
                depths[number] = depth;
                largest[number][0][0] = next.weight;
                
                dfs(number);
            }
        }
    }
    
    private static int findLCA(int u, int v, int weight) {
        if (depths[u] < depths[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        
        int max = -1;
        
        int diff = depths[u] - depths[v], ancestor = 0;
        while (diff > 0) {
            if ((diff & 1) == 1) {
                int[] weights = largest[u][ancestor];
                if (weights[0] != weight) {
                    max = Math.max(max, weights[0]);
                } else if (weights[1] != 0) {
                    max = Math.max(max, weights[1]);
                }
                
                u = ancestors[u][ancestor];
            }
            
            ancestor++;
            diff >>= 1;
        }
        
        if (u == v) {
            return max;
        }
        
        for (int i = height; i >= 0; i--) {
            if (ancestors[u][i] != ancestors[v][i]) {
                int[] weights = largest[u][i];
                if (weights[0] != weight) {
                    max = Math.max(max, weights[0]);
                } else if (weights[1] != 0) {
                    max = Math.max(max, weights[1]);
                }
                
                weights = largest[v][i];
                if (weights[0] != weight) {
                    max = Math.max(max, weights[0]);
                } else if (weights[1] != 0) {
                    max = Math.max(max, weights[1]);
                }
                
                u = ancestors[u][i];
                v = ancestors[v][i];
            }
        }
        
        int[] weights = largest[u][0];
        if (weights[0] != weight) {
            max = Math.max(max, weights[0]);
        } else if (weights[1] != 0) {
            max = Math.max(max, weights[1]);
        }
        
        weights = largest[v][0];
        if (weights[0] != weight) {
            max = Math.max(max, weights[0]);
        } else if (weights[1] != 0) {
            max = Math.max(max, weights[1]);
        }
        
        return max;
    }
    
    static class Edge implements Comparable<Edge> {
        
        int u, v, weight;
        boolean included;
        
        private Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    
    static class Vertex {
        
        int number, weight;
        
        private Vertex(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }
    }
}
