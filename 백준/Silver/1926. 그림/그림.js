/**
 * BFS or DFS
 *
 * (0, 0)부터 이중 for문을 돌며 방문하지 않았고 1인 칸에서부터 돌기
 * -> 더이상 방문할 수 있는 칸이 없으면 탈출 -> 그림 한 장!
 *
 * */
const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [n, m] = input.shift()

const visited = Array.from(Array(n), () => Array(m).fill(false))
const dx = [0, 0, 1, -1],
  dy = [1, -1, 0, 0]
let count = 0,
  largest = 0

function bfs(x, y) {
  const queue = [{ x, y }]
  visited[x][y] = true
  let size = 1

  while (queue.length > 0) {
    const temp = queue.shift()

    for (let i = 0; i < 4; i++) {
      const nx = temp.x + dx[i]
      const ny = temp.y + dy[i]

      if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || input[nx][ny] !== 1) continue

      size += 1
      visited[nx][ny] = true
      queue.push({ x: nx, y: ny })
    }
  }

  count += 1
  largest = Math.max(largest, size)
}

for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (!visited[i][j] && input[i][j] === 1) bfs(i, j)
  }
}

console.log(count)
console.log(largest)