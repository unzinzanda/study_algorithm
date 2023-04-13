class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int w = 3;
        int total = brown + yellow; // 타일이 딱 이만큼 있어야 정답일 가능성 O
        
        find:
        while(true) {
            for(int h = 3;h <= w;h++) {
                if(h * w != total) continue;
                else {
                    int sum = 2 * w + ((h - 2) * 2);    // 가로 길이 + 가로 길이 + (세로에서 위아래 두 칸 뺀 거) * 2 (중복으로 더할 꺼 빼는 것)
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
