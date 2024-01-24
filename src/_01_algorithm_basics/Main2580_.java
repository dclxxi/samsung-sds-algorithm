package _01_algorithm_basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2580_ {
    
    private static final int[][] board = new int[9][9];
    private static final List<Integer> blanks = new ArrayList<>();
    private static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                
                if (board[i][j] == 0) {
                    blanks.add(i * 10 + j);
                }
            }
        }
        
        visited = new boolean[blanks.size()];
        
        for (int blank : blanks) {
            int row = blank / 10;
            int col = blank % 10;
            
        }
        
        
    }
    
    private static void dfs(int index) {
        //1. 체크인
        visited[index] = true;
        
        //2. 목적지인가?
        if (index == blanks.size()) {
            for (int i = 0; i < 9; i++) {
            }
            
        }
        
        //3. 연결된 곳을 순회
        for (int i = index + 1; i <= 9; i++) {
            //4. 갈 수 있는가?
            int blank = blanks.get(i);
            int row = blank / 10;
            int col = blank % 10;
            
            board[row][col] = i;
            
            //5. 간다(DFS)
            dfs(i);
        }
        
        //6. 체크아웃
        visited[index] = false;
    }
}
