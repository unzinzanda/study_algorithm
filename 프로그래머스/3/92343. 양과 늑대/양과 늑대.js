function solution(info, edges) {
    let answer = 0;
    const graph = new Array(info.length).fill(0).map(e => []);
    
    for(let i of edges) {
        graph[i[0]].push(i[1]);
    }
    
    let visited = new Array(info.length).fill(false);
    visited[0] = true;
    
    function dfs(node, wolf, sheep, possible) {
        let newPossible = [...possible];
        
        info[node] === 0 ? sheep++ : wolf++;
        
        if(sheep === wolf) return;
        
        answer = Math.max(answer, sheep);
        newPossible.push(...graph[node]);
        
        const curIndex = newPossible.indexOf(node);
        newPossible.splice(curIndex, 1);
        
        for(let next of newPossible) {
            dfs(next, wolf, sheep, newPossible);
        }
        
    }
    
    
    dfs(0, 0, 0, [0]);
    
    return answer;
}