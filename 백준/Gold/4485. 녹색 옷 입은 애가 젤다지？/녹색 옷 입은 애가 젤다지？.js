/**
 * 다익스트라
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

let dx = [0, 0, 1, -1],
  dy = [1, -1, 0, 0],
  round = 1

class PriorityQueue {
  constructor() {
    this.values = []
  }
  enqueue(x, y, cost) {
    this.values.push({ x, y, cost })
    this.sort()
  }
  dequeue() {
    return this.values.shift()
  }
  sort() {
    this.values.sort((a, b) => a.cost - b.cost)
  }
}

while (true) {
  const [N] = input.shift()
  if (N === 0) break
  const map = input.splice(0, N)

  const dist = Array.from(Array(N), () => Array(N).fill(Infinity))

  dist[0][0] = map[0][0]
  const queue = new PriorityQueue()
  queue.enqueue(0, 0, map[0][0])

  while (queue.values.length > 0) {
    const temp = queue.dequeue()

    if (temp.x === N - 1 && temp.y === N - 1) {
      dist[temp.x][temp.y] = temp.cost
      break
    }

    for (let i = 0; i < 4; i++) {
      const nx = temp.x + dx[i]
      const ny = temp.y + dy[i]

      if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue

      if (dist[nx][ny] > temp.cost + map[nx][ny]) {
        dist[nx][ny] = temp.cost + map[nx][ny]
        queue.enqueue(nx, ny, dist[nx][ny])
      }
    }
  }

  console.log(`Problem ${round++}: ${dist[N - 1][N - 1]}`)
}