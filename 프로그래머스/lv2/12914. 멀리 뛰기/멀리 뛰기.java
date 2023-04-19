class Solution {
    static long store[];
    public long dp(int n) {
        if(n == 1) return store[n] = 1;
        if(n == 2) return store[n] = 2;
        if(store[n] == 0) {
            store[n] = (dp(n - 1) + dp(n - 2)) % 1234567;
        }
        
        return store[n];
    }
    
    public long solution(int n) {
        long answer = 0;
        store = new long[n + 1];
        answer = dp(n);
        return answer;
    }
}