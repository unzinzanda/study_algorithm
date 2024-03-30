const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs.readFileSync(filePath).toString().trim().split('\n')

const temp = input.shift()
input = input.map((el) => el.trim().split(''))

const [R, C] = temp.split(' ').map(Number)

const dx = [1, -1, 0, 0],
  dy = [0, 0, 1, -1]
const visited = Array.from(Array(R), () => Array(C).fill(false))
const fire = []
const q = []

for (let i = 0; i < R; i++) {
  for (let j = 0; j < C; j++) {
    if (input[i][j] === 'J') {
      q.push([i, j, 0])
      visited[i][j] = true
    } else if (input[i][j] === 'F') {
      fire.push([i, j])
    }
  }
}

function fireSpreads() {
  const size = fire.length
  for (let i = 0; i < size; i++) {
    const f = fire.shift()

    for (let j = 0; j < 4; j++) {
      const nx = f[0] + dx[j]
      const ny = f[1] + dy[j]

      if (
        nx < 0 ||
        ny < 0 ||
        nx >= R ||
        ny >= C ||
        input[nx][ny] === '#' ||
        input[nx][ny] === 'F'
      )
        continue

      input[nx][ny] = 'F'
      fire.push([nx, ny])
    }
  }
}

let isEscape = false
let answer = 0
let curDep = 0
fireSpreads()
while (q.length) {
  const temp = q.shift()

  if (temp[2] !== curDep) {
    fireSpreads()
    curDep = temp[2]
  }

  for (let i = 0; i < 4; i++) {
    const nx = temp[0] + dx[i]
    const ny = temp[1] + dy[i]
    if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
      answer = temp[2] + 1
      isEscape = true
      break
    }
    if (input[nx][ny] === '#' || input[nx][ny] === 'F' || visited[nx][ny])
      continue

    visited[nx][ny] = true
    q.push([nx, ny, temp[2] + 1])
  }
  if (isEscape) break
}
console.log(isEscape ? answer : 'IMPOSSIBLE')