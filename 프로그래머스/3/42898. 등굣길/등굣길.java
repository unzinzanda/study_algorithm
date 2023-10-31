class Solution {
    static int route[][];
    static boolean isPuddle[][];
    
    static int dp(int x, int y) {
        if(x == 0 && y == 0) return 1;
        if(isPuddle[x][y]) return 0;
        
        if(route[x][y] == 0) {
            if(x == 0) route[x][y] = dp(x, y - 1);
            else if(y == 0) route[x][y] = dp(x - 1, y);
            else route[x][y] = dp(x - 1, y) + dp(x, y - 1);
        }
        
        return route[x][y] % 1000000007;
    }
    
    public int solution(int n, int m, int[][] puddles) {
        isPuddle = new boolean[n][m];
        route = new int[n][m];
        
        for(int i = 0;i < puddles.length;i++) {
            isPuddle[puddles[i][0] - 1][puddles[i][1] - 1] = true;
        }
        
        dp(n - 1, m - 1);
        
        return route[n - 1][m - 1] % 1000000007;
    }
}