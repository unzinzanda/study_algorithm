function solution(maps) {
    var answer = [];
    const dx = [1, -1, 0, 0], dy = [0, 0, 1, -1];
    const visited = Array.from(new Array(maps.length), () => new Array(maps[0].length).fill(false));
    
    let feed = 0;
    
    function dfs(x, y) {
        visited[x][y] = true;
        feed += Number(maps[x][y]);
        
        for(let i = 0;i < 4;i++) {
            const nx = x + dx[i];
            const ny = y + dy[i];
            
            if(nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length || visited[nx][ny] || maps[nx][ny] === 'X') continue;
            
            dfs(nx, ny);
        }
    }
    
    for(let i = 0;i < maps.length;i++) {
        for(let j = 0;j < maps[0].length;j++) {
            if(!visited[i][j] && maps[i][j] !== 'X') {
                feed = 0;
                dfs(i, j);
                answer.push(feed);
            }
        }
    }

    // sort() : 문자열이 아닌 숫자 기준으로 정렬하려면 sort((a, b) => a - b) 해야 함
    return answer.length === 0 ? [-1] : answer.sort((a, b) => a - b);
}
