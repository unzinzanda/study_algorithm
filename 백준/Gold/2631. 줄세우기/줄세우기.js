/**
 * 총 학생수 - 최장 증가 부분 수열의 길이
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .flatMap((el) => Number(el))

const N = input.shift()
const store = Array(N).fill(0)
store[0] = 1

for (let i = 1; i < N; i++) {
  for (let j = i - 1; j >= 0; j--) {
    if (input[i] > input[j]) {
      store[i] = Math.max(store[i], store[j] + 1)
    }
  }

  if (store[i] === 0) store[i] = 1
}

console.log(N - Math.max(...store))
