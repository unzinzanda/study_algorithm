const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .split('\n')
  .map((el) => el.trim())

const N = parseInt(input.shift())
input = input.map((el) => el.split(''))
const visited = Array.from(Array(N), () => Array(N).fill(false))
const dx = [1, -1, 0, 0],
  dy = [0, 0, 1, -1]

function findDir(x, y) {
  for (let i = 0; i < 4; i++) {
    const nx = x + dx[i]
    const ny = y + dy[i]

    if (nx < 0 || ny < 0 || nx >= N || ny >= N || input[nx][ny] === '_') continue

    return i
  }
  return -1
}

let flag = false
let heart,
  lens = []

for (i = 0; i < N; i++) {
  for (j = 0; j < N; j++) {
    if (!flag && input[i][j] === '*') {
      heart = { x: i + 2, y: j + 1 }
      visited[i + 1][j] = true
      flag = true
    } else if (!visited[i][j] && input[i][j] === '*') {
      len = 1
      const dir = findDir(i, j)
      if (dir === -1) {
        lens.push(len)
        continue
      }

      let nx = i,
        ny = j
      while (true) {
        nx += dx[dir]
        ny += dy[dir]

        if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || input[nx][ny] === '_')
          break
        visited[nx][ny] = true
        len += 1
      }
      lens.push(len)
    }
  }
}

console.log(heart.x, heart.y)
console.log(lens.toString().replaceAll(',', ' '))