class Solution {
    static int one, zero;
    static int copyArr[][];
    
    static void quadTree(int x, int y, int n) {
        int sum = 0;
        
        for(int i = x;i < x + n;i++) {
            for(int j = y;j < y + n;j++) sum += copyArr[i][j];
        }
        
        if(sum == n * n) one++;
        else if(sum == 0) zero++;
        
        else {
            int half = n / 2;
        
            quadTree(x, y, half);
            quadTree(x + half, y, half);
            quadTree(x, y + half, half);
            quadTree(x + half, y + half, half);
        }
    }
    
    public int[] solution(int[][] arr) {
        copyArr = new int[arr.length][arr.length];
        
        for(int i = 0;i < arr.length;i++) copyArr[i] = arr[i].clone();
        
        quadTree(0, 0, arr.length);
        
        int[] answer = {zero, one};
        return answer;
    }
}