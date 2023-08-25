package _06_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2458 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        boolean[][] graph = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a][b] = true;
        }
        
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (!graph[i][k]) {
                    break;
                }
                
                for (int j = 0; j < N; j++) {
                    if (graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < N; i++) {
            int connected = 0;
            for (int j = 0; j < N; j++) {
                if (graph[i][j] || graph[j][i]) {
                    connected++;
                }
            }
            
            if (connected == N - 1) {
                count++;
            }
        }
        
        System.out.println(count);
    }
}
