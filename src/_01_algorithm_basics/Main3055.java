package _01_algorithm_basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main3055 {
    
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        char[][] map = new char[R][C];
        boolean[][] visited = new boolean[R][C];
        Deque<Point> deque = new ArrayDeque<>();
        
        for (int x = 0; x < R; x++) {
            String line = br.readLine();
            for (int y = 0; y < C; y++) {
                map[x][y] = line.charAt(y);
                
                switch (map[x][y]) {
                    case '*':
                        deque.offerFirst(new Point(false, x, y));
                        visited[x][y] = true;
                        break;
                    
                    case 'S':
                        deque.offerLast(new Point(true, x, y));
                        visited[x][y] = true;
                        map[x][y] = '.';
                        break;
                }
            }
        }
        
        int time = 1;
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- > 0) {
                Point point = deque.poll();
                boolean empty = point.empty;
                int x = point.x;
                int y = point.y;
                
                for (int direction = 0; direction < 4; direction++) {
                    int nx = x + dx[direction];
                    int ny = y + dy[direction];
                    
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) {
                        continue;
                    }
                    
                    if (empty && map[nx][ny] == 'D') {
                        System.out.println(time);
                        return;
                    }
                    
                    if (map[nx][ny] != '.') {
                        continue;
                    }
                    
                    deque.offer(new Point(empty, nx, ny));
                    visited[nx][ny] = true;
                }
            }
            
            time++;
        }
        
        System.out.println("KAKTUS");
    }
    
    static class Point {
        
        boolean empty;
        int x, y;
        
        Point(boolean empty, int x, int y) {
            this.empty = empty;
            this.x = x;
            this.y = y;
        }
    }
}
