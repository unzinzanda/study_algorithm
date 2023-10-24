import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        List<Integer> peopleList = new ArrayList<>();
        
        for(int weight : people) peopleList.add(weight);
        Collections.sort(peopleList);
        
        int index = 0;
        for(int i = people.length - 1;i >= index;i--) {
            if(peopleList.get(i) + peopleList.get(index) <= limit) {
                 answer++;
                index++;
            }
            else answer++;
        }
        
        return answer;
    }
}