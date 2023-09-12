import java.util.*;
class Solution {
    static boolean checkS(char[] s) {
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0;i < s.length;i++) {
            if(stack.isEmpty() && (s[i] == ')' || s[i] == ']' || s[i] == '}')) return false;
            else if(stack.isEmpty()) stack.push(s[i]);   
            else {
                if(stack.peek() == '(' && s[i] == ')') stack.pop();
                else if(stack.peek() == '[' && s[i] == ']') stack.pop();
                else if(stack.peek() == '{' && s[i] == '}') stack.pop();
                else stack.push(s[i]);
            }
        }
        
        if(stack.isEmpty()) return true;
        else return false;
    }
    public int solution(String s) {
        int answer = 0;
        
        char sArray[] = s.toCharArray();
        
        for(int i = 0;i < sArray.length;i++) {
            char temp = sArray[0];
            for(int j = 1;j < sArray.length;j++) {
                sArray[j - 1] = sArray[j];
            }
            sArray[sArray.length - 1] = temp;
            if(checkS(sArray)) answer++;
        }
        
        return answer;
    }
}