/**
 * BFS 돌고 나온 거리 중 최장 거리 찾기
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs.readFileSync(filePath).toString().trim().split('\n')

const [R, C] = input.shift().split(' ').map(Number)
input = input.map((el) => el.trim().split(''))

let visited = []
const dx = [1, -1, 0, 0],
  dy = [0, 0, 1, -1]
let result = 0

for (let i = 0; i < R; i++) {
  for (let j = 0; j < C; j++) {
    if (input[i][j] === 'L') bfs(i, j)
  }
}

console.log(result)

function bfs(x, y) {
  visited = Array.from(Array(R), () => Array(C).fill(false))
  visited[x][y] = true
  const queue = []
  queue.push({ x, y, depth: 0 })

  while (queue.length > 0) {
    const temp = queue.shift()

    for (let i = 0; i < 4; i++) {
      const nx = temp.x + dx[i]
      const ny = temp.y + dy[i]

      if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny] || input[nx][ny] === 'W')
        continue

      visited[nx][ny] = true
      queue.push({ x: nx, y: ny, depth: temp.depth + 1 })
      result = Math.max(result, temp.depth + 1)
    }
  }
}
