package _03_data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main11279_ {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        PriorityQueue queue = new PriorityQueue(N);
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            
            if (x == 0) {
                sb.append(queue.poll()).append("\n");
            } else {
                queue.offer(x);
            }
        }
        
        System.out.print(sb);
    }
    
    static class PriorityQueue {
        
        int[] heap;
        int size;
        
        private PriorityQueue(int N) {
            this.heap = new int[N + 1];
        }
        
        void offer(int x) {
            heap[++size] = x;
            shiftUp();
        }
        
        private void shiftUp() {
            int child = size;
            while (true) {
                int parent = child >> 1;
                if (parent == 0 || heap[parent] >= heap[child]) {
                    break;
                }
                
                swap(child, parent);
                child = parent;
            }
        }
        
        int poll() {
            if (size == 0) {
                return 0;
            }
            
            int top = heap[1];
            swap(1, size--);
            siftDown();
            return top;
        }
        
        private void siftDown() {
            int parent = 1;
            while (true) {
                int left = parent << 1;
                if (left > size) {
                    break;
                }
                
                int right = left | 1, max = left;
                if (right <= size && heap[left] < heap[right]) {
                    max = right;
                }
                
                if (heap[parent] >= heap[max]) {
                    break;
                }
                
                swap(parent, max);
                parent = max;
            }
        }
        
        void swap(int a, int b) {
            int temp = heap[a];
            heap[a] = heap[b];
            heap[b] = temp;
        }
    }
}
