function solution(maps) {
    var answer = 0;
    var n = maps.length, m = maps[0].length;
    var dx = [1, -1, 0, 0], dy = [0, 0, 1, -1];
    const visited = Array(n).fill(0).map(() => Array(m).fill(0));
    const q = [];
    q.push([0, 0]);
    visited[0][0] = 1;
    
    // 도착지의 상,좌가 막혀있으면 -1 리턴
    if(maps[n - 1][m - 2] === 0 && maps[n - 2][m - 1] === 0) return -1;
    
    while(q.length) {
        const [y, x] = q.shift();
        
        for(let i = 0;i < 4;i++) {
            let nx = x + dx[i];
            let ny = y + dy[i];
            
            if(ny < 0 || nx < 0 || ny >= n || nx >= m || maps[ny][nx] === 0 || visited[ny][nx]) continue;
            
            q.push([ny, nx]);
            visited[ny][nx] = visited[y][x] + 1;
        }
    }
    
    result = visited[n - 1][m - 1];
    
    if(!result) return -1;
    else return result;
    
    return answer;
}