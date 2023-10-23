import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0;i < priorities.length;i++) pq.add(priorities[i]);
        
        int answer = 1;
        
        search:
        while(!pq.isEmpty()) {
            for(int i = 0;i < priorities.length;i++) {
                if(priorities[i] == pq.peek()) {
                    if(i == location) {
                        break search;
                    }
                    pq.poll();
                    answer++;
                }
            }
        }
        
        return answer;
    }
}