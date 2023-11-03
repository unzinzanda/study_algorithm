import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        for(int i = 0;i < discount.length;i++) {
            // 10일 혹은 남은 기간동안 할인하는 물품과 수량 기록
            Map<String, Integer> discountItem = new HashMap<>();
            int end = Math.min(10, discount.length - i);
            
            for(int j = i;j < i + end;j++) {
                if(discountItem.containsKey(discount[j])) discountItem.put(discount[j], discountItem.get(discount[j]) + 1);
                
                else discountItem.put(discount[j], 1);
            }
            
            boolean isSignUp = true;
            
            for(int j = 0;j < want.length;j++) {
                if(!discountItem.containsKey(want[j]) || discountItem.get(want[j]) != number[j]) {
                    isSignUp = false;
                    break;
                }
            }
            
            if(!isSignUp) continue;
            answer++;
        }
        
        return answer;
    }
}