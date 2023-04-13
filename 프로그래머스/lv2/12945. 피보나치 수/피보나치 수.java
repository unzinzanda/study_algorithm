class Solution {
    static int dp[];
    private static int fibo(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(dp[n] == 0) {
            dp[n] = fibo(n - 1) % 1234567 + fibo(n - 2) % 1234567;
        }
        
        return dp[n] % 1234567;
    }
    public int solution(int n) {
        dp = new int[n + 1];
        return fibo(n);
    }
}