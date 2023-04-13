class Solution {
    public int solution(int n) {
        String str = Integer.toBinaryString(n);
        int one = 0;
        for(int i = 0;i < str.length();i++) {
            if(str.charAt(i) == '1') one++;
        }
        
        while(true) {
            int cnt = 0;
            n += 1;
            str = Integer.toBinaryString(n);
            for(int i = 0;i < str.length();i++) {
                if(str.charAt(i) == '1') cnt++;
            }
            if(cnt == one) break;
        }
    
        return n;
    }
}