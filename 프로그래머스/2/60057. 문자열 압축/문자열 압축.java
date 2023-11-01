class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        
        if(s.length() == 1) return 1;
        
        for(int i = 1;i < s.length();i++) {
            int ans = 0, subStrCnt = 1;
            String cur = "";
            
            for(int j = 0;j < s.length();j += i) {
                if(j + i > s.length()) {
                    String temp = s.substring(j, s.length());
                    ans += temp.length();
                }
                else {
                    String temp = s.substring(j, j + i);
                    if(cur.equals("")) {
                        cur = temp;
                    }
                    else if(cur.equals(temp)) {
                        subStrCnt++;
                    } else if (!cur.equals(temp)) {
                        if(subStrCnt > 1) {
                            ans += Integer.toString(subStrCnt).length();
                        }
                        ans += i;
                        cur = temp;
                        subStrCnt = 1;
                    }
                }
            }
            
            if(subStrCnt > 1) ans += Integer.toString(subStrCnt).length();
            ans += i;
            
            answer = Math.min(answer, ans);
        }
        
        return answer;
    }
}