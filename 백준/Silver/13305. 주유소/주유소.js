const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N] = input.shift()

let answer = Infinity
let visitAll = 0
let distance = input[0].reduce((pre, cur) => pre + cur)

for (let i = 0; i < N - 1; i++) {
  visitAll += input[0][i] * input[1][i]
  distance -= input[0][i]
  answer = Math.min(answer, visitAll + input[0][i] * distance)
}

console.log(Math.min(visitAll, answer))