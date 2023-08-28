import java.util.*;

class Solution {
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    
    static class Point {
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        boolean visited[][] = new boolean[m][n];
        
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                if(!visited[i][j] && picture[i][j] != 0) {
                    numberOfArea++;
                    visited[i][j] = true;
                    Queue<Point> q = new LinkedList<>();
                    q.add(new Point(i, j));
                    
                    int cntArea = 1;
                    
                    while(!q.isEmpty()) {
                        Point temp = q.poll();
                        
                        for(int k = 0;k < 4;k++) {
                            int nx = temp.x + dx[k];
                            int ny = temp.y + dy[k];
                            
                            if(nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny] || picture[i][j] != picture[nx][ny]) continue;
                            
                            visited[nx][ny] = true;
                            cntArea++;
                            q.add(new Point(nx, ny));
                        }
                    }
                    
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cntArea);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}