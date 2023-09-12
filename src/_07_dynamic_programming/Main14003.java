package _07_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main14003 {

    private static int[] indices;
    private static List<Integer> lis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            sb.append("1\n").append(br.readLine());
            System.out.println(sb);
            return;
        }

        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        lis = new ArrayList<>();
        lis.add(A[0]);

        int end = 0;
        indices = new int[N];
        for (int i = 1; i < N; i++) {
            int number = A[i];

            if (number > lis.get(end)) {
                lis.add(number);
                indices[i] = ++end;
            } else {
                binarySearch(end, number, i);
            }
        }

        sb.append(end + 1).append("\n");

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = N - 1; i >= 0; i--) {
            if (indices[i] == end) {
                deque.push(A[i]);
                end--;
            }
        }

        while (!deque.isEmpty()) {
            sb.append(deque.pop()).append(" ");
        }

        System.out.println(sb);
    }

    private static void binarySearch(int end, int number, int index) {
        int start = 0, min = 0;
        while (start <= end) {
            int middle = start + ((end - start) >> 1);

            if (lis.get(middle) < number) {
                start = middle + 1;
            } else {
                end = middle - 1;
                min = middle;
            }
        }

        lis.set(min, number);
        indices[index] = min;
    }
}