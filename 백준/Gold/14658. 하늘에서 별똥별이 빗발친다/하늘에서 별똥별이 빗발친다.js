const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, M, L, K] = input.shift()
let answer = 0
for (let i = 0; i < K; i++) {
  for (let j = 0; j < K; j++) {
    const row = input[i][0],
      col = input[j][1]

    let count = 0

    for (let k = 0; k < K; k++) {
      if (
        input[k][0] >= row &&
        input[k][0] <= row + L &&
        input[k][1] >= col &&
        input[k][1] <= col + L
      )
        count += 1
    }

    answer = Math.max(answer, count)
  }
}

console.log(K - answer)