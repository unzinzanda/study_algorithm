import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");

        int numbers[] = new int[N], result[] = new int[N];
        for(int i = 0;i < N;i++) numbers[i] = Integer.parseInt(str[i]);

        Stack<Integer> stack = new Stack<>();
        for(int i = N - 1;i >= 0;i--) {
            while(true) {
                if(stack.isEmpty()) {
                  result[i] = -1;
                  stack.push(numbers[i]);
                  break;
                } else if(stack.peek() > numbers[i]) {
                    result[i] = stack.peek();
                    stack.push(numbers[i]);
                    break;
                } else stack.pop();
            }
        }

        for(int res : result) sb.append(res).append(" ");
        System.out.println(sb.toString());
    }
}