const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, K] = input.shift()
const arr = input[0]
let sum = 0

for (let i = 0; i < K; i++) sum += arr[i]
let answer = sum

let start = 0,
  end = K

while (end < arr.length) {
  sum -= arr[start++]
  sum += arr[end++]

  answer = Math.max(answer, sum)
}

console.log(answer)
