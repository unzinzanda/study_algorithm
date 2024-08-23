/**
 * BFS
 *
 * 방을 청소하면 queue, visited clear 후, 다시 BFS를 돌린다.
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs.readFileSync(filePath).toString().trim().split('\n')

input.reverse()

let result = Infinity,
  dx = [1, -1, 0, 0],
  dy = [0, 0, 1, -1]
let w, h
let room = []

while (true) {
  result = Infinity
  const size = input.pop().split(' ').map(Number)
  w = size[0]
  h = size[1]

  if (w === 0 && h === 0) break

  // 방 입력 정리
  room = Array(h)
  for (let i = 0; i < h; i++) {
    room[i] = input.pop().trim().split('')
  }

  // 더러운 방 갯수 세고 청소기 위치 찾기
  let dirty = 0,
    robotX = -1,
    robotY = -1

  for (let i = 0; i < h; i++) {
    for (let j = 0; j < w; j++) {
      if (room[i][j] === '*') dirty += 1
      else if (room[i][j] === 'o') {
        robotX = i
        robotY = j
      }
    }
  }

  // 청소기에서부터 bfs 돌기
  bfs(robotX, robotY, 0, dirty)

  if (result === Infinity) console.log(-1)
  else console.log(result)
}

function bfs(x, y, depth, dirty) {
  if (depth > result) return
  if (dirty === 0) {
    result = Math.min(result, depth)
    return
  }
  let visited = Array.from(Array(h), () => Array(x).fill(false))
  let queue = []
  queue.push({ x, y, depth, dirty })
  visited[x][y] = true

  while (queue.length > 0) {
    const temp = queue.shift()

    if (temp.depth > result) break

    if (room[temp.x][temp.y] === '*') {
      room[temp.x][temp.y] = '.'
      bfs(temp.x, temp.y, temp.depth, temp.dirty - 1)
      room[temp.x][temp.y] = '*'
      continue
    }

    for (let i = 0; i < 4; i++) {
      const nx = temp.x + dx[i]
      const ny = temp.y + dy[i]

      if (nx < 0 || ny < 0 || nx >= h || ny >= w || visited[nx][ny] || room[nx][ny] === 'x')
        continue

      visited[nx][ny] = true
      queue.push({ x: nx, y: ny, depth: temp.depth + 1, dirty: temp.dirty })
    }
  }
}