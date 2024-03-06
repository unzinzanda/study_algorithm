import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split("");
		
		Stack<String> stack1 = new Stack<>();
		Stack<String> stack2 = new Stack<>();
		for(int i = 0;i < str.length;i++) stack1.push(str[i]);
		
		int num = Integer.parseInt(br.readLine());
		
		for(int i = 0;i < num;i++) {
			str = br.readLine().split(" ");
			
			switch(str[0]) {
			case "L":
				if(!stack1.isEmpty()) stack2.push(stack1.pop());
				break;
			case "D":
				if(!stack2.isEmpty()) stack1.push(stack2.pop());
				break;
			case "B":
				if(!stack1.isEmpty()) stack1.pop();
				break;
			case "P":
				stack1.push(str[1]);
				break;
			}
		}
		
		while(!stack1.isEmpty()) stack2.push(stack1.pop());
		
		StringBuilder sb = new StringBuilder();
		while(!stack2.isEmpty()) sb.append(stack2.pop());
		
		System.out.println(sb.toString());
	}
}
