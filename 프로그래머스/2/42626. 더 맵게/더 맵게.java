import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        
        for(int i = 0;i < scoville.length;i++) heap.add(scoville[i]);
        
        boolean canMake = false;
        while(!heap.isEmpty()) {
            if(heap.peek() >= K) {
                canMake = true;
                break;
            }
            if(heap.size() < 2) break;
            
            answer++;
            
            int firstMin = heap.poll();
            int secondMin = heap.poll();
            int make = firstMin + (secondMin * 2);
            
            heap.add(make);
        }
        if(!canMake) answer = -1;
        
        return answer;
    }
}