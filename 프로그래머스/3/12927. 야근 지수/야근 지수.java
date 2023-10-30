/*
    값이 큰 수부터 줄여 나가기?
*/
import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int work : works) pq.add(work);
        
        while(n-- > 0) {
            if(pq.peek() == 0) break;
            pq.add(pq.poll() - 1);
        }
        
        while(!pq.isEmpty()) {
            int num = pq.poll();
            answer += num * num;
        }
        
        return answer;
    }
}