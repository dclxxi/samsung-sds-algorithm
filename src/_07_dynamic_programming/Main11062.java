package _07_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11062 {

    private static final int[] cards = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }

            boolean turn = (N & 1) == 1;
            int[][] dp = new int[N + 2][N + 1];
            for (int count = 1; count <= N; count++) {
                for (int start = 1; start <= N - count + 1; start++) {
                    int end = count + start - 1;

                    if (turn) {
                        dp[start][end] = Math.max(dp[start + 1][end] + cards[start], dp[start][end - 1] + cards[end]);
                    } else {
                        dp[start][end] = Math.min(dp[start + 1][end], dp[start][end - 1]);
                    }
                }

                turn = !turn;
            }

            sb.append(dp[1][N]).append("\n");
        }

        System.out.print(sb);
    }
}
