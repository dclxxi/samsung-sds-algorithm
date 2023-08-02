package _03_data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1202 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        List<Jewel> jewels = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        
        Collections.sort(jewels);
        
        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(bags);
        
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        long max = 0;
        int index = 0;
        for (int bag : bags) {
            while (index < N) {
                Jewel jewel = jewels.get(index);
                if (bag < jewel.mass) {
                    break;
                }
                
                queue.offer(jewel.value);
                index++;
            }
            
            if (!queue.isEmpty()) {
                max += queue.poll();
            }
        }
        
        System.out.println(max);
    }
    
    static class Jewel implements Comparable<Jewel> {
        
        int mass, value;
        
        Jewel(int mass, int value) {
            this.mass = mass;
            this.value = value;
        }
        
        @Override
        public int compareTo(Jewel o) {
            return this.mass - o.mass;
        }
    }
}
