import java.util.*;

class Solution {
    static class Point {
        int x;
        int y;
        int depth;
        
        public Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
    
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;
        
        int visited[][] = new int[n][m];
        
        for(int i = 0;i < n;i++) {
            Arrays.fill(visited[i], -1);
        }
        
        Queue<Point> q = new LinkedList<>();
        
        q.add(new Point(0, 0, 1));
        
        while(!q.isEmpty()) {
            Point temp = q.poll();
            
            for(int i = 0;i < 4;i++) {
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= n || ny >= m || maps[nx][ny] == 0 || visited[nx][ny] != -1) continue;
                
                visited[nx][ny] = temp.depth + 1;
                
                q.add(new Point(nx, ny, visited[nx][ny]));
            }
        }
        
        return visited[n - 1][m - 1];
    }
}