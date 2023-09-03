package _06_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main5719 {
    
    private static final int INF = 987654321;
    private static final int[] distances = new int[500];
    private static final boolean[][] visited = new boolean[500][500];
    private static final List<Integer>[] shortest = new List[500];
    private static final List<Road>[] roads = new List[500];
    private static final Queue<Road> queue = new PriorityQueue<>();
    
    private static int N, S;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }
            
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            
            for (int i = 0; i < N; i++) {
                roads[i] = new ArrayList<>();
                shortest[i] = new ArrayList<>();
                Arrays.fill(visited[i], 0, N, false);
            }
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());
                
                roads[U].add(new Road(V, P));
            }
            
            dijkstra();
            removeShortestPath(S, D);
            dijkstra();
            
            if (distances[D] == INF) {
                sb.append(-1);
            } else {
                sb.append(distances[D]);
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
    }
    
    private static void dijkstra() {
        queue.offer(new Road(S, 0));
        
        Arrays.fill(distances, 0, N, INF);
        distances[S] = 0;
        
        while (!queue.isEmpty()) {
            Road road = queue.poll();
            int curr = road.destination;
            
            if (distances[curr] < road.length) {
                continue;
            }
            
            List<Road> neighbors = roads[curr];
            for (Road neighbor : neighbors) {
                int next = neighbor.destination;
                if (visited[curr][next]) {
                    continue;
                }
                
                int distance = distances[curr] + neighbor.length;
                if (distances[next] > distance) {
                    distances[next] = distance;
                    queue.offer(new Road(next, distance));
                    
                    shortest[next] = new ArrayList<>();
                    shortest[next].add(curr);
                } else if (distances[next] == distance) {
                    shortest[next].add(curr);
                }
            }
        }
    }
    
    private static void removeShortestPath(int start, int end) {
        if (start == end) {
            return;
        }
        
        for (int next : shortest[end]) {
            if (!visited[next][end]) {
                visited[next][end] = true;
                removeShortestPath(start, next);
            }
        }
    }
    
    static class Road implements Comparable<Road> {
        
        int destination, length;
        
        Road(int destination, int length) {
            this.destination = destination;
            this.length = length;
        }
        
        @Override
        public int compareTo(Road o) {
            return this.length - o.length;
        }
    }
}
