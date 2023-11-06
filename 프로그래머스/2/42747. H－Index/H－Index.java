import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        for(int i = 1;i < citations[citations.length - 1];i++) {
            int cnt = 0, remain = 0;
            for(int j = 0;j < citations.length;j++) {
                if(i <= citations[j]) cnt++;
                else remain++;
            }
            
            if(cnt >= i && remain <= i) {
                answer = Math.max(answer, i);
            }
        }
        
        return answer;
    }
}