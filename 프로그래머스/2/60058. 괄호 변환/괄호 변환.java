/*
    1 ~ 4 단계까지 수행하는 함수(재귀적으로 호출될 예정)
    올바른 괄호 문자열인지 확인하는 함수
    올바른 괄호 문자열을 만드는 함수
*/

import java.util.*;
import java.io.*;

class Solution {
    public boolean checkCorrect(String str) {
        // 처음부터 )이면 무조건 false
        if(str.charAt(0) == ')') return false;
        
        Stack<String> stack = new Stack<>();
        String[] arr = str.split("");
        
        for(int i = 0;i < arr.length;i++) {
            if(stack.isEmpty()) {
                if(arr[i].equals(")")) return false;
                stack.add(arr[i]);
            }
            else {
                if(arr[i].equals("(")) stack.add(arr[i]);
                else if(arr[i].equals(")")) {
                    if(stack.peek().equals("(")) stack.pop();
                    else stack.add(arr[i]);
                }
            }
        }
        
        if(!stack.isEmpty()) return false;
        
        return true;
    }
    
    public boolean checkBalance(String str) {
        String[] arr = str.split("");
        int a = 0, b = 0;
        
        for(int i = 0;i < arr.length;i++) {
            if(arr[i].equals("(")) a++;
            else if(arr[i].equals(")")) b++;
        }
        
        if(a == b) return true;
        return false;
    }
    
    public String makeNewU(String str) {
        if(str.equals("")) return "";
        str = str.substring(1, str.length() - 1);
        String[] arr = str.split("");
        
        for(int i = 0;i < arr.length;i++) {
            if(arr[i].equals("(")) arr[i] = ")";
            else if(arr[i].equals(")")) arr[i] = "(";
        }
        
        return String.join("", arr);
    }
    
    public String makeCorrect(String str) {
        if(str.equals("")) return "";
        
        String result = "";
        String[] arr = str.split("");
        int a = 0, b = 0, i;
        
        for(i = 0;i < arr.length;i++) {
            if(arr[i].equals("(")) a++;
            else if(arr[i].equals(")")) b++;
            
            if(a == b) break;
        }
        
        String u = str.substring(0, i + 1);
        String v = str.length() < i + 1 ? "" : str.substring(i + 1);
        
        if(checkBalance(u) && checkCorrect(u)) result = u + makeCorrect(v);
        else result = "(" + makeCorrect(v) + ")" + makeNewU(u);
        
        if(checkBalance(result) && checkCorrect(result)) return result;
        return makeCorrect(result);
    }
    
    public String solution(String p) {
        String answer = "";
        
        if(checkCorrect(p)) answer = p;
        else answer = makeCorrect(p);
        
        return answer;
    }
}