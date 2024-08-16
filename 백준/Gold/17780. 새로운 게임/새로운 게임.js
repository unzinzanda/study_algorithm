/**
 * 칸마다 가장 위, 아래 말 정보만 가지면 됨
 * 말의 정보로 필요한 것: 번호, 방향
 */

const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

class Piece {
  constructor() {
    this.bottom = { number: 0, dir: -1 }
    this.top = { number: 0, dir: -1 }
    this.count = 0
  }
}

const dx = [0, 0, -1, 1],
  dy = [1, -1, 0, 0]

const [N, K] = input.shift()

const board = Array.from(Array(N), () => Array(N))
for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) board[i][j] = new Piece()
}
const sequence = Array(K + 1)
const empty = { number: 0, dir: -1 }

for (let i = 0; i < K; i++) {
  const temp = input.pop()

  board[temp[0] - 1][temp[1] - 1].bottom.number = K - i
  board[temp[0] - 1][temp[1] - 1].bottom.dir = temp[2] - 1
  board[temp[0] - 1][temp[1] - 1].count = 1

  sequence[K - i] = { x: temp[0] - 1, y: temp[1] - 1 }
}

let round = 0

while (++round <= 1000) {
  let flag = false

  for (let i = 1; i <= K; i++) {
    if (board[sequence[i].x][sequence[i].y].bottom.number !== i) continue

    let dir = board[sequence[i].x][sequence[i].y].bottom.dir
    let nx = sequence[i].x + dx[dir]
    let ny = sequence[i].y + dy[dir]

    if (nx < 0 || ny < 0 || nx >= N || ny >= N || input[nx][ny] === 2) {
      //파랑
      switch (dir) {
        case 0:
          dir = 1
          break
        case 1:
          dir = 0
          break
        case 2:
          dir = 3
          break
        case 3:
          dir = 2
          break
      }

      board[sequence[i].x][sequence[i].y].bottom.dir = dir
      nx = sequence[i].x + dx[dir]
      ny = sequence[i].y + dy[dir]

      if (nx < 0 || ny < 0 || nx >= N || ny >= N || input[nx][ny] === 2) {
        // 방향만 변경
        continue
      } else if (input[nx][ny] === 1) {
        // 빨강
        red(sequence[i].x, sequence[i].y, nx, ny)
      } else {
        // 흰 칸
        white(sequence[i].x, sequence[i].y, nx, ny)
      }
    } else if (input[nx][ny] === 0) {
      // 흰 칸
      white(sequence[i].x, sequence[i].y, nx, ny)
    } else if (input[nx][ny] === 1) {
      // 빨강
      red(sequence[i].x, sequence[i].y, nx, ny)
    }

    sequence[board[nx][ny].bottom.number].x = nx
    sequence[board[nx][ny].bottom.number].y = ny

    if (board[nx][ny].count >= 4) {
      flag = true
      break
    }
  }
  if (flag) break
}

if (round > 1000) console.log(-1)
else console.log(round)

function red(x, y, nx, ny) {
  if (board[nx][ny].bottom.number === 0) {
    if (board[x][y].top.number === 0) {
      board[nx][ny].bottom = { ...board[x][y].bottom }
      board[x][y].bottom = { ...empty }
    } else {
      board[nx][ny].bottom = { ...board[x][y].top }
      board[nx][ny].top = { ...board[x][y].bottom }
      board[x][y].bottom = { ...empty }
      board[x][y].top = { ...empty }
    }
  } else {
    board[nx][ny].top = { ...board[x][y].bottom }
    board[x][y].bottom = { ...empty }
    board[x][y].top = { ...empty }
  }
  board[nx][ny].count += board[x][y].count
  board[x][y].count = 0
}

function white(x, y, nx, ny) {
  if (board[nx][ny].bottom.number === 0) {
    board[nx][ny].bottom = { ...board[x][y].bottom }
    board[nx][ny].top = { ...board[x][y].top }
    board[x][y].bottom = { ...empty }
    board[x][y].top = { ...empty }
  } else if (board[x][y].top.number === 0) {
    board[nx][ny].top = { ...board[x][y].bottom }
    board[x][y].bottom = { ...empty }
  } else {
    board[nx][ny].top = { ...board[x][y].top }
    board[x][y].bottom = { ...empty }
    board[x][y].top = { ...empty }
  }
  board[nx][ny].count += board[x][y].count
  board[x][y].count = 0
}