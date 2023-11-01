class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        
        // 문자열의 길이가 1인 경우는 바로 1 리턴
        if(s.length() == 1) return 1;
        
        // 자를 문자열의 길이를 늘려가며 진행
        for(int i = 1;i < s.length();i++) {
            int ans = 0, subStrCnt = 1;
            String cur = "";
            
            for(int j = 0;j < s.length();j += i) {
                // 남은 문자열의 길이가 자를 문자열의 길이보다 짧은 경우,
                // 남은 문자열의 길이를 바로 더해버림.
                if(j + i > s.length()) {
                    String temp = s.substring(j, s.length());
                    ans += temp.length();
                }
                else {
                    String temp = s.substring(j, j + i);
                    // 처음 시작
                    if(cur.equals("")) {
                        cur = temp;
                    }
                    // 반복되는 문자열인 경우
                    else if(cur.equals(temp)) {
                        subStrCnt++;
                    }
                    // 반복되지 않는 문자열인 경우
                    else if (!cur.equals(temp)) {
                        // 반복이 2번 이상된 경우, 추가로 반복된 횟수를 더해야 함
                        // 리턴값이 최종 문자열의 길이이기 때문에 subStrCnt을 문자열로 바꾼 길이를 ans에 더함
                        if(subStrCnt > 1) {
                            ans += Integer.toString(subStrCnt).length();
                        }
                        // cur의 길이를 더해야 함
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