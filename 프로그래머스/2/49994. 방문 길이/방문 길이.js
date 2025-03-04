function solution(dirs) {
    dirs = dirs.split('')
    const dirIdx = {
        'U': 0,
        'D': 1,
        'R': 2,
        'L': 3,
    }

    const dx = [-1, 1, 0, 0], dy = [0, 0, 1, -1]

    let answer = 0;
    let cur = [0, 0]
    const line = []

    for(let i = 0;i < dirs.length;i++) {
        const nx = cur[0] +  dx[dirIdx[dirs[i]]]
        const ny = cur[1] + dy[dirIdx[dirs[i]]]
        
        if(nx < -5 || ny < -5 || nx > 5 || ny > 5) continue;
        
        const newLine = [[cur[0], cur[1]], [nx, ny]]
        newLine.sort((a, b) => {
            if(a[0] === b[0]) return a[1] - b[1]
            return a[0] - b[0]
        })
    
        cur[0] = nx
        cur[1] = ny
        
        let flag = true
        for(let j = 0;j < line.length;j++) {
            const a = newLine.flat()
            const b = line[j].flat()
            
            let same = true
            
            for(let k = 0;k < 4;k++) {
                if(a[k] !== b[k]) {
                    same = false
                    break
                }
            }
            if(same) {
                flag = false
                break
            }
        }
        
        if(flag) {
            answer += 1
            line.push(newLine)
        }
    }
    
    
    return answer;
}