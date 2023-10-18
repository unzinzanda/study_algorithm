import java.util.*;

class Solution {
    static List<String> list = new ArrayList<>();
    static char charWord[] = {'A', 'E', 'I', 'O', 'U'};
    static void generateWord(int cnt, int r, String word) {
        if(cnt == r) {
            list.add(word);
            return;
        }
        
        for(int i = 0;i < 5;i++) {
            generateWord(cnt + 1, r, word + charWord[i]);
        }
    }
    
    public int solution(String word) {
        for(int i = 1;i <= 5;i++) {
            generateWord(0, i, "");
        }
        Collections.sort(list);
        return list.indexOf(word) + 1;
    }
}