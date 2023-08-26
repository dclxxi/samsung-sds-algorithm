package _06_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1516 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        List<Integer>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        int[] buildTimes = new int[N + 1];
        int[] indegrees = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            buildTimes[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int building = Integer.parseInt(st.nextToken());
                if (building == -1) {
                    break;
                }
                
                graph[building].add(i);
                indegrees[i]++;
            }
        }
        
        int[] minTimes = new int[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
                minTimes[i] = buildTimes[i];
            }
        }
        
        while (!queue.isEmpty()) {
            int building = queue.poll();
            
            for (int next : graph[building]) {
                minTimes[next] = Math.max(minTimes[next], minTimes[building] + buildTimes[next]);
                
                if (--indegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            sb.append(minTimes[i]).append("\n");
        }
        
        System.out.print(sb);
    }
}
