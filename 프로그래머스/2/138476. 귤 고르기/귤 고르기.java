import java.util.*;
class Solution {
    static int answer = 0;
    static int order[];
    static Map<Integer, Integer> tanger = new HashMap<>();
    
    public int solution(int k, int[] tangerine) {
        order = new int[tangerine.length];
        
        for(int i = 0;i < tangerine.length;i++) {
            if(tanger.containsKey(tangerine[i])) {
                tanger.put(tangerine[i], tanger.get(tangerine[i]) + 1);
            }
            else tanger.put(tangerine[i], 1);
        }
        List<Integer> keySet = new ArrayList<>(tanger.keySet());
        keySet.sort((value1, value2) -> (tanger.get(value2).compareTo(tanger.get(value1))));
        
        int tangerCnt = 0;
        for(int key : keySet) {
            tangerCnt += tanger.get(key);
            answer++;
            if(tangerCnt >= k) break;
        }
        
        return answer;
    }
}