class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        
        if(a < b && a % 2 == 1) {
                if(b == a + 1) return answer;
            }
        if(b < a && b % 2 == 1) {
            if(a == b + 1) return answer;
        }

        while(true) {
            a = a % 2 + a / 2;
            b = b % 2 + b / 2;
            
            answer++;
            
            if(a < b && a % 2 == 1) {
                if(b == a + 1) break;
            }
            if(b < a && b % 2 == 1) {
                if(a == b + 1) break;
            }
        }

        return answer;
    }
}