package _03_data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9202 {
    
    private static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    private static final int[] scores = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    private static final char[][] board = new char[4][4];
    private static final Node trie = new Node();
    private static final StringBuilder word = new StringBuilder();
    
    private static int score, longestLength, count;
    private static boolean[][] visited;
    private static StringBuilder longest;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int w = Integer.parseInt(br.readLine());
        for (int i = 0; i < w; i++) {
            trie.insert(br.readLine());
        }
        br.readLine();
        
        int b = Integer.parseInt(br.readLine());
        while (b-- > 0) {
            for (int i = 0; i < 4; i++) {
                board[i] = br.readLine().toCharArray();
            }
            br.readLine();
            
            score = 0;
            count = 0;
            longestLength = 0;
            longest = new StringBuilder("Z");
            visited = new boolean[4][4];
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    if (!visited[x][y]) {
                        dfs(x, y, trie.getChild(board[x][y]));
                    }
                }
            }
            
            trie.clearHit();
            sb.append(score).append(" ").append(longest).append(" ").append(count).append("\n");
        }
        
        System.out.print(sb);
    }
    
    private static void dfs(int x, int y, Node node) {
        if (node == null) {
            return;
        }
        
        visited[x][y] = true;
        word.append(board[x][y]);
        
        if (node.end && !node.hit) {
            int length = word.length();
            if (longestLength < length || longestLength == length && longest.compareTo(word) > 0) {
                longest.setLength(0);
                longest.append(word);
                longestLength = length;
            }
            
            score += scores[length];
            count++;
            
            node.hit = true;
        }
        
        for (int direction = 0; direction < 8; direction++) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            
            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || visited[nx][ny]) {
                continue;
            }
            
            dfs(nx, ny, node.getChild(board[nx][ny]));
        }
        
        word.setLength(word.length() - 1);
        visited[x][y] = false;
    }
    
    static class Node {
        
        Node[] children = new Node[26];
        boolean end, hit;
        
        void insert(String word) {
            Node node = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'A';
                if (node.children[index] == null) {
                    node.children[index] = new Node();
                }
                
                node = node.children[index];
            }
            
            node.end = true;
        }
        
        Node getChild(char ch) {
            return children[ch - 'A'];
        }
        
        void clearHit() {
            hit = false;
            for (Node child : children) {
                if (child != null) {
                    child.clearHit();
                }
            }
        }
    }
}
