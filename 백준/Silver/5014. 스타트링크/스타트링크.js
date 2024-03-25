const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const [F, S, G, U, D] = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split(' ')
  .map(Number)

const visited = Array(F + 1).fill(false)
let answer = 'use the stairs'
visited[S] = true
const queue = [[S, 0]]

while (queue.length) {
  const temp = queue.shift()

  if (temp[0] === G) {
    answer = temp[1]
    break
  }

  const tempUp = temp[0] + U
  const tempDown = temp[0] - D
  if (tempUp <= F && !visited[tempUp]) {
    visited[tempUp] = true
    queue.push([tempUp, temp[1] + 1])
  }
  if (tempDown > 0 && !visited[tempDown]) {
    visited[tempDown] = true
    queue.push([tempDown, temp[1] + 1])
  }
}

console.log(answer)