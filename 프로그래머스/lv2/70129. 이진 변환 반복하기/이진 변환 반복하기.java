class Solution {
    public int[] solution(String s) {
        
        int cnt = 0;
        int zero = 0;
        int len = 0;
        while(true) {
            cnt++;
            for(int i = 0;i < s.length();i++) {
                if(s.charAt(i) == '1') len++;
                else zero++;
            }
            if(len == 1) break;
            s = Integer.toBinaryString(len);
            System.out.println(s);
            len = 0;
        }
        int[] answer = {cnt, zero};
        return answer;
    }
}