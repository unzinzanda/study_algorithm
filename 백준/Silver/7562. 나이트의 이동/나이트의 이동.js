const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const dx = [-2, -2, -1, 1, 2, 2, 1, -1],
  dy = [-1, 1, 2, 2, 1, -1, -2, -2]
let [t] = input.shift()

input.reverse()

while (t-- > 0) {
  const [l] = input.pop()

  const board = Array.from(Array(l), () => Array(l).fill(false))
  let result = Infinity

  const start = input.pop()
  const end = input.pop()

  const queue = [{ x: start[0], y: start[1], depth: 0 }]
  board[start[0]][start[1]] = true

  while (queue.length > 0) {
    const temp = queue.shift()

    for (let i = 0; i < 8; i++) {
      const nx = temp.x + dx[i]
      const ny = temp.y + dy[i]

      if (nx < 0 || ny < 0 || nx >= l || ny >= l || board[nx][ny]) continue

      board[nx][ny] = true
      queue.push({ x: nx, y: ny, depth: temp.depth + 1 })

      if (nx === end[0] && ny === end[1]) result = Math.min(result, temp.depth + 1)
    }
  }

  console.log(result !== Infinity ? result : 0)
}