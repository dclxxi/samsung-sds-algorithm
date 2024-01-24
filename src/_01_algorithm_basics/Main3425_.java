package _01_algorithm_basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main3425_ {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            List<String> commands = new ArrayList<>();
            
            while (true) {
                String command = br.readLine();
                
                if (command.equals("END")) {
                    break;
                }
                
                if (command.length() > 3) {
                    commands.add("NUM");
                    commands.add(command.substring(4));
                } else {
                    commands.add(command);
                }
            }
            
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                int V = Integer.parseInt(br.readLine());
            }
            
            if (br.readLine().equals("QUIT")) {
                break;
            }
        }
        
        
    }
}
