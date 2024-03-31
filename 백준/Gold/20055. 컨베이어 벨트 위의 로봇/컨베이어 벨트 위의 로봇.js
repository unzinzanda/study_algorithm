const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, K] = input.shift()
input = input.flat()

const conveyor = Array.from(Array(2 * N), () => Array(2).fill(0))
for (let i = 0; i < 2 * N; i++) {
  conveyor[i][0] = 0
  conveyor[i][1] = input[i]
}

function rotation() {
  const temp = conveyor.pop()
  conveyor.unshift([0, temp[1]])
}

function robotMove() {
  for (let i = N - 1; i >= 0; i--) {
    if (conveyor[i][0] === 1) {
      if (i === N - 1) conveyor[i][0] = 0
      else if (conveyor[i + 1][0] === 0 && conveyor[i + 1][1] > 0) {
        ;[conveyor[i][0], conveyor[i + 1][0]] = [
          conveyor[i + 1][0],
          conveyor[i][0],
        ]
        conveyor[i + 1][1] -= 1
        if (i + 1 === N - 1) conveyor[i + 1][0] = 0
      }
    }
  }
}

function countNotDurable() {
  let count = 0
  for (let i = 0; i < 2 * N; i++) {
    if (conveyor[i][1] === 0) count++
  }

  return count
}

let answer = 0 // 단계 카운트

while (true) {
  answer++
  rotation()
  robotMove()
  if (conveyor[0][1] > 0) {
    conveyor[0][0] = 1
    conveyor[0][1] -= 1
  }
  if (countNotDurable() >= K) break
}

console.log(answer)