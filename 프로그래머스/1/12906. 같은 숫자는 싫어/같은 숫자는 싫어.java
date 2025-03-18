import java.util.*;

public class Solution {
    public int[] solution(int []arr) {        
        Stack<Integer> stack = new Stack();
        
        stack.add(arr[0]);
        for(int i = 1;i < arr.length;i++) {
            if(stack.peek() != arr[i]) stack.add(arr[i]);
        }
        
        Object[] a = stack.toArray();
        int[] answer = new int[a.length];
        for(int i = 0;i < answer.length;i++) answer[i] = (int)a[i];

        return answer;
    }
}