const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N] = input.shift()
const [M] = input.shift()
input = input.flat()
let answer = Math.max(input[0], N - input[M - 1])

for (let i = 1; i < M - 2; i++) {
  answer = Math.max(
    answer,
    Math.floor((input[i + 1] - input[i]) / 2) + ((input[i + 1] - input[i]) % 2),
  )
}

console.log(answer)