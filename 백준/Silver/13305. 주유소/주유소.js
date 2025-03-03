/**
 * 직전에 구매 했던 기름 값 > 현재 도착한 주유소의 기름값 => 주요소 변경
 * < => 그래도 직전 주유소에서 쭉 구매
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N] = input.shift()

let answer = 0
let minCost = input[1][0]

for (let i = 0; i < N - 1; i++) {
  if (minCost > input[1][i]) minCost = input[1][i]

  answer += input[0][i] * minCost
}

console.log(answer)