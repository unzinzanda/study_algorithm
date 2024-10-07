import java.util.*;
import java.io.*;

/*
    1. 알파벳을 맞추는 조작 횟수 먼저 세기(커서 고려 X)
    2. 효율적인 커서 이동 구하기
*/

class Solution {
    public int solution(String name) {
        int answer = 0;
        
        char[] arr = name.toCharArray();
        int move = arr.length - 1;
        
        for(int i = 0;i < arr.length;i++) {
            int ascii = (int)arr[i];
            answer += Math.min(ascii - 65, 90 - ascii + 1);
            
            int idx = i + 1;
            
            while(idx < arr.length && arr[idx] == 'A') {
                idx++;
            }
            
            move = Math.min(move, i * 2 + arr.length - idx);
            move = Math.min(move, (arr.length - idx) * 2 + i);
        }
        
        return answer + move;
    }
}