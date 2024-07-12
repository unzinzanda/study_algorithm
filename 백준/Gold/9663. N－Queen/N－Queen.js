const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const [N] = fs.readFileSync(filePath).toString().trim().split('\n').map(Number)

const board = Array.from(Array(N), () => Array(N).fill(false))
const dx = [-1, -1, -1, 0, 0, 1, 1, 1],
  dy = [-1, 0, 1, -1, 1, -1, 0, 1]
let answer = 0

// 한 줄에서 놓을 수 있는 위치에 퀸을 놓음
function backtracking(row) {
  if (row === N) {
    answer += 1
    return
  }

  for (let i = 0; i < N; i++) {
    if (check(row, i)) {
      board[row][i] = true
      backtracking(row + 1)
      board[row][i] = false
    }
  }
}

// 퀸을 놓을 수 있는지 여부를 확인하는 함수
function check(x, y) {
  for (let i = 0; i < 8; i++) {
    let k = 1
    while (true) {
      const nx = x + dx[i] * k
      const ny = y + dy[i] * k

      if (nx < 0 || ny < 0 || nx >= N || ny >= N) break
      if (board[nx][ny]) return false

      k += 1
    }
  }
  return true
}

backtracking(0)

console.log(answer)