/*
    방문 안된 노드부터 dfs
*/
function solution(n, computers) {
    var answer = 0;
    const visited = new Array(n).fill(false);
    
    function dfs(node) {
        for(let i = 0;i < n;i++) {
            if(node !== i && computers[node][i] === 1 && !visited[i]) {
                visited[i] = true;
                dfs(i);
            }
        }
    }
    
    for(let i = 0;i < n;i++) {
        if(!visited[i]) {
            visited[i] = true;
            dfs(i);
            answer++;
        }
    }
    
    return answer;
}