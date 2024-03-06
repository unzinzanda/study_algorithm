/**
 * DP
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs.readFileSync(filePath).toString().trim().split('\n').map(Number)

const T = input.shift()

const store = Array.from(Array(10001), () => Array(4).fill(0))

store[1][1] = 1
store[2][1] = 1
store[2][2] = 1
store[3][1] = 1
store[3][2] = 1
store[3][3] = 1

// 입력값 중 최대값까지 dp 다 구하기
for (let i = 4; i <= 10000; i++) {
  store[i][1] = store[i - 1][1]
  store[i][2] = store[i - 2][1] + store[i - 2][2]
  store[i][3] = store[i - 3][1] + store[i - 3][2] + store[i - 3][3]
}

for (let t = 0; t < T; t++) {
  console.log(store[input[t]].reduce((val, sum) => val + sum, 0))
}