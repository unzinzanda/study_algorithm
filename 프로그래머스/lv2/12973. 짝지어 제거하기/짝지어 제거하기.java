import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        
        char input[] = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0;i < input.length;i++) {
            if(stack.isEmpty()) stack.push(input[i]);
            else {
                if(stack.peek() == input[i]) {
                    stack.pop();
                } else stack.push(input[i]);
            }
        }
        
        answer = stack.isEmpty() == true ? 1 : 0;
        
        return answer;
    }
}