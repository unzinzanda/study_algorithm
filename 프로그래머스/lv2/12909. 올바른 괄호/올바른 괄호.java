import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;

        char str[] = s.toCharArray();
        
        Stack<Character> stack = new Stack<>();
        
        if(str[0] == ')' || str[str.length - 1] == '(') answer = false;
        else {
            for(int i = 0;i < str.length;i++) {
            if(str[i] == ')') {
                if(stack.isEmpty()) {
                    answer = false;
                    break;
                }
                else
                    stack.pop();
            }
            else stack.push(str[i]);
        }
        
        if(!stack.isEmpty()) answer = false;
        }

        return answer;
    }
}