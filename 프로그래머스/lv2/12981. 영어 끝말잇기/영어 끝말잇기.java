import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        ArrayList<String> used = new ArrayList<>();
        
        int i = 1;
        int turn = 1;
        char last = words[0].charAt(words[0].length() - 1);
        used.add(words[0]);
        for(;i < words.length;i++) {
            if(used.contains(words[i])) break;
            else {
                if(words[i].charAt(0) != last) break;
                else { 
                    last = words[i].charAt(words[i].length() - 1);
                    used.add(words[i]);
                }
                if(i % n == n - 1) turn++; // 인덱스가 0부터 시작하므로 한 라운드가 끝날 때 i % n = n - 1이 됨
            }
        }
        
        System.out.println(i);
        int [] answer = new int[2];
        if(i == words.length) {
            answer[0] = 0;
            answer[1] = 0;
        }
        else {
            answer[0] = i % n + 1;
            answer[1] = turn;
        }
        return answer;
    }
}
