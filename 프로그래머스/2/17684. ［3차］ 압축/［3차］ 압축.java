import java.util.*;
class Solution {
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> dictionary = new HashMap<>();
        String msgArr[] = msg.split("");
        
        for(int i = 0;i < 26;i++) {
            dictionary.put(Character.toString('A' + i), i + 1);
        }
        
        int numbering = 27;
        
        for(int i = 0;i < msgArr.length;i++) {
            String wc = msgArr[i];
            int result = dictionary.get(wc);
            for(int j = i + 1; j < msgArr.length;j++) {
                wc += msgArr[j];
                if(!dictionary.containsKey(wc)) {
                    answer.add(result);
                    dictionary.put(wc, numbering++);
                    i = j - 1;
                    break;
                }
                else {
                    result = dictionary.get(wc);
                    if(j == msgArr.length - 1) {
                        answer.add(result);
                        i = j;
                        break;
                    }
                }
            }
            if(i == msgArr.length - 1 && (result >= 1 && result <= 26)) {
                answer.add(result);
                break;
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}