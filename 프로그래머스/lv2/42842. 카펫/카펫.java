class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int w = 3;
        int total = brown + yellow;
        
        find:
        while(true) {
            for(int h = 3;h <= w;h++) {
                if(h * w != total) continue;
                else {
                    int sum = 2 * w + ((h - 2) * 2);
                    if(total - sum == yellow) {
                        answer[0] = w;
                        answer[1] = h;
                        break find;
                    } 
                }
            }
            w += 1;
        }
        
        return answer;
    }
}