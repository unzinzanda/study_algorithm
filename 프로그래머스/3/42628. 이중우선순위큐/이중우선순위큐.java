import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        
        Set<Integer> removed = new HashSet<>();
        
        for(int i = 0;i < operations.length;i++) {
            String str[] = operations[i].split(" ");
            
            if(str[0].equals("I")) {
                maxQ.add(Integer.parseInt(str[1]));
                minQ.add(Integer.parseInt(str[1]));
            }
            else if(str[0].equals("D")) {
                    if(str[1].equals("1")) {
                        if(!maxQ.isEmpty()) {
                            while(true) {
                                if(!removed.contains(maxQ.peek())) {
                                    removed.add(maxQ.poll());
                                    break;
                                }
                                maxQ.poll();
                                
                            }
                        }
                    } else {
                        if(!minQ.isEmpty()) {
                            while(true) {
                                if(!removed.contains(minQ.peek())) {
                                    removed.add(minQ.poll());
                                    break;
                                }
                                minQ.poll();
                            }
                        }
                    }
                }
            
        }
        
        
        if(minQ.isEmpty()) {
            answer[0] = answer[1] = 0;
        } else {
            while(true) {
           if(!removed.contains(minQ.peek())) {
               break;
           }
            minQ.poll();
       }
        
        while(true) {
           if(!removed.contains(maxQ.peek())) {
               break;
           }
            maxQ.poll();
       }
            answer[0] = maxQ.peek();
            answer[1] = minQ.peek();
        }
        
        return answer;
    }
}