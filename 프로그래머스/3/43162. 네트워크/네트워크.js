function solution(n, computers) {
    let answer = 0;
    const visited = Array(n).fill(false)
    
    const dfs = (node) => {
        visited[node] = true
        
        for(let i = 0;i < n;i++) {
            if(!visited[i] && computers[node][i] === 1) dfs(i)
        }
    }
    
    for(let i = 0;i < n;i++) {
        if(visited[i]) continue
        
        dfs(i)
        answer += 1
    }
    
    return answer;
}