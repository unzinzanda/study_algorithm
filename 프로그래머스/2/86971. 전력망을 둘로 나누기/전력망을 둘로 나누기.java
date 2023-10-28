class Solution {
    static int parent[];
    
    static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
    
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if(a != b) {
            if(a < b) parent[b] = a;
            else parent[a] = b;
        }
    }
    
    public int solution(int n, int[][] wires) {
        
        int answer = Integer.MAX_VALUE;
        
        for(int i = 0;i < wires.length;i++) {
            // union-find를 위한 준비
            parent = new int[n + 1];
            for(int j = 0;j <= n;j++) parent[j] = j;
            
            // 하나의 전선을 빼고 union-find 진행
            for(int j = 0;j < wires.length;j++) {
                if(j != i) {
                    union(wires[j][0], wires[j][1]);
                }
            }
            
            // 나눠진 전력망 각각이 지닌 송전탑의 수 구하기
            int cur = parent[1];
            int cntCur = 1;
            int cntOther = 0;
            for(int j = 2; j <= n;j++) {
                if(find(j) == cur) cntCur++;
                else cntOther++;
            }
            
            for(int j = 1;j<=n;j++) {
                System.out.print(parent[j] + " ");
            }
            System.out.println();
            
            answer = Math.min(answer, Math.abs(cntCur - cntOther));
        }
        
        return answer;
    }
}