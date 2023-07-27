package _01_algorithm_basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main1713 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        Student[] students = new Student[101];
        List<Student> frames = new ArrayList<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int number = Integer.parseInt(st.nextToken());
            
            if (students[number] == null) {
                if (frames.size() == N) {
                    Collections.sort(frames);
                    Student removed = frames.remove(N - 1);
                    students[removed.number] = null;
                }
                
                Student student = new Student(number, 1, i);
                students[number] = student;
                frames.add(student);
            } else {
                students[number].count++;
            }
        }
        
        frames.sort(Comparator.comparingInt(o -> o.number));
        for (Student finalist : frames) {
            sb.append(finalist.number).append(" ");
        }
        
        System.out.println(sb);
    }
    
    static class Student implements Comparable<Student> {
        
        int number, count, timestamp;
        
        Student(int number, int count, int timestamp) {
            this.number = number;
            this.count = count;
            this.timestamp = timestamp;
        }
        
        @Override
        public int compareTo(Student o) {
            if (this.count == o.count) {
                return Integer.compare(o.timestamp, this.timestamp);
            }
            
            return Integer.compare(o.count, this.count);
        }
    }
}
