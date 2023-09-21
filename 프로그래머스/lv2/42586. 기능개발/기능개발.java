import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        List<Integer> ans = new ArrayList<>();
        
        int day = 1;
        for(int i = 0;i < progresses.length;i++) {
            int cnt = 0;
            while(true) {
                if(i < progresses.length && progresses[i] + (day * speeds[i]) >= 100) {
                    cnt++;
                    i++;
                }
                else {
                    if(cnt > 0) {
                        ans.add(cnt);
                    }
                    i--;
                    day++;
                    break;
                }
            }
        }
        
        int[] answer = new int[ans.size()];
        
        int idx = 0;
        for(int a : ans) answer[idx++] = a;
        
        return answer;
    }
}