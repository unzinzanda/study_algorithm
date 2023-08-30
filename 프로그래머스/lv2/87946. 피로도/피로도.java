class Solution {
    
    static int answer = -1;
    static boolean isSelected[];
    static int order[];
    
    static void perm(int cnt, int n, int k, int[][] dungeons) {
        if(cnt == n) {
            int cntClear = 0;
            for(int i = 0;i < n;i++) {
                if(k < dungeons[order[i]][0]) break;
                
                cntClear++;
                k -= dungeons[order[i]][1];
            }
            
            if(cntClear != 0) answer = Math.max(answer, cntClear);
        }
        
        for(int i = 0;i < n;i++) {
            if(!isSelected[i]) {
                isSelected[i] = true;
                order[cnt] = i;
                perm(cnt + 1, n, k, dungeons);
                isSelected[i] = false;
            }
        }
    } 
    
    public int solution(int k, int[][] dungeons) {
        isSelected = new boolean[dungeons.length];
        order = new int[dungeons.length];
        
        perm(0, dungeons.length, k, dungeons);
        
        return answer;
    }
}