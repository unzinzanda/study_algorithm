class Solution {
    public String solution(String s) {
        String str[] = s.split("");
        String answer = "";
        String temp = "";
        boolean flag = false;
        for(int i = 0;i < str.length;i++) {
            if(str[i].equals(" ")) {
                if(temp.equals("")) answer += str[i];
                else {
                    temp += str[i];
                    answer += temp;
                    flag = false;
                    temp = "";
                }
                continue;
            } 
            if(!flag) {
                flag = true;
                temp += str[i].toUpperCase();
            }
            else temp += str[i].toLowerCase();
        }
        answer += temp;
        return answer;
    }
}