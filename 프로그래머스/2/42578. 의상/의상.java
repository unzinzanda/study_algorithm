import java.util.*;

// 의상 종류마다 개수를 구하고 조합을 더함

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> cloth = new HashMap<>();
        
        for(int i = 0;i < clothes.length;i++) {
            if(cloth.containsKey(clothes[i][1])) cloth.put(clothes[i][1], cloth.get(clothes[i][1]) + 1);
            
            else cloth.put(clothes[i][1], 1);
        }
        
        for(String str : cloth.keySet()) {
            answer *= (cloth.get(str) + 1);
        }
        
        return answer - 1;
    }
}