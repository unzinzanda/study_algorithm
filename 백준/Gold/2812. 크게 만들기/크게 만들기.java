import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);

        Stack<Integer> stack = new Stack<>();

        str = br.readLine().split("");

        int numbers[] = new int[str.length];
        for(int i = 0;i < str.length;i++) numbers[i] = Integer.parseInt(str[i]);

        stack.add(numbers[0]);
        for(int i = 1;i < N;i++) {
            if(K == 0) stack.add(numbers[i]);
            else {
                if(stack.peek() < numbers[i]) {
                    while(!stack.isEmpty() && stack.peek() < numbers[i] && K != 0) {
                        stack.pop();
                        K -= 1;
                    }
                }
                stack.add(numbers[i]);
            }
        }

        String result = stack.toString().replaceAll(", ", "");

        System.out.println(result.substring(1, result.length() - 1 - K));
    }
}