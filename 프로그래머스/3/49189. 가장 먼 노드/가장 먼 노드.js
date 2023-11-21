function solution(n, edge) {
    let graph = new Array(n+1).fill(0).map(e => [])

    for (let i of edge) {
        graph[i[0]].push(i[1]);
        graph[i[1]].push(i[0]);
    }
    
    const q = [];
    const depth = Array(n + 1).fill(0);

    q.push([1, 1]);
    depth[1] = 1;
    
    while(q.length !== 0) {
        const [node, dep] = q.shift();
        
        for(let i = 1;i < n + 1;i++) {
            if(graph[node].indexOf(i) !== -1 && depth[i] === 0) {
                depth[i] = dep + 1;
                q.push([i, dep + 1])
            }
        }
    }
    
    const maxDepth = Math.max(...depth);
    let count = 0;
    
    depth.map(dep => {
        if(dep === maxDepth) count++;
    })
    
    return count;
}