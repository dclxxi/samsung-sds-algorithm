//package _06_graph;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.StringTokenizer;
//
//public class Main14003 {
//
//    private static final int GRASS = 0;
//    private static final int GRAVESTONE = 1;
//    private static final int GHOST_HOLE = 2;
//    private static final int[] dx = {-1, 1, 0, 0};
//    private static final int[] dy = {0, 0, -1, 1};
//    private static final long[][] distances = new long[30][30];
//
//    private static int W, H;
//    private static List<Edge> edges;
//
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        while (true) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            W = Integer.parseInt(st.nextToken());
//            H = Integer.parseInt(st.nextToken());
//            if (W == 0 && H == 0) {
//                break;
//            }
//
//            int G = Integer.parseInt(br.readLine());
//            int[][] cemetery = new int[W][H];
//            for (int i = 0; i < G; i++) {
//                st = new StringTokenizer(br.readLine());
//                int X = Integer.parseInt(st.nextToken());
//                int Y = Integer.parseInt(st.nextToken());
//
//                cemetery[X][Y] = GRAVESTONE;
//            }
//
//            int E = Integer.parseInt(br.readLine());
//            edges = new ArrayList<>();
//            for (int i = 0; i < E; i++) {
//                st = new StringTokenizer(br.readLine());
//                int X1 = Integer.parseInt(st.nextToken());
//                int Y1 = Integer.parseInt(st.nextToken());
//                int X2 = Integer.parseInt(st.nextToken());
//                int Y2 = Integer.parseInt(st.nextToken());
//                int T = Integer.parseInt(st.nextToken());
//
//                edges.add(new Edge(X1, Y1, X2, Y2, T));
//                cemetery[X1][Y1] = GHOST_HOLE;
//            }
//
//            for (int x = 0; x < W; x++) {
//                for (int y = 0; y < H; y++) {
//                    if (cemetery[x][y] != GRASS || (x == W - 1 && y == H - 1)) {
//                        continue;
//                    }
//
//                    for (int direction = 0; direction < 4; direction++) {
//                        int nx = x + dx[direction];
//                        int ny = y + dy[direction];
//
//                        if (nx < 0 || nx >= W || ny < 0 || ny >= H || cemetery[nx][ny] == GRAVESTONE) {
//                            continue;
//                        }
//
//                        edges.add(new Edge(x, y, nx, ny, 1));
//                    }
//                }
//            }
//
//            if (bellmanFord()) {
//                long distance = distances[W - 1][H - 1];
//                if (distance == Long.MAX_VALUE) {
//                    sb.append("Impossible");
//                } else {
//                    sb.append(distance);
//                }
//            } else {
//                sb.append("Never");
//            }
//            sb.append("\n");
//        }
//
//        System.out.print(sb);
//    }
//
//    private static boolean bellmanFord() {
//        for (int i = 0; i < W; i++) {
//            Arrays.fill(distances[i], 0, H, Long.MAX_VALUE);
//        }
//        distances[0][0] = 0;
//
//        int area = W * H;
//        for (int i = 0; i <= area; i++) {
//            for (Edge edge : edges) {
//                if (distances[edge.x1][edge.y1] == Long.MAX_VALUE) {
//                    continue;
//                }
//
//                long distance = distances[edge.x1][edge.y1] + edge.time;
//                if (distances[edge.x2][edge.y2] > distance) {
//                    if (i == area) {
//                        return false;
//                    }
//
//                    distances[edge.x2][edge.y2] = distance;
//                }
//            }
//        }
//
//        return true;
//    }
//
//    static class Edge {
//
//        int x1, y1, x2, y2, time;
//
//        Edge(int x1, int y1, int x2, int y2, int time) {
//            this.x1 = x1;
//            this.y1 = y1;
//            this.x2 = x2;
//            this.y2 = y2;
//            this.time = time;
//        }
//    }
//}
