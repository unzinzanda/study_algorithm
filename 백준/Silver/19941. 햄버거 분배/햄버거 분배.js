const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' '))

const [N, K] = input.shift().map(Number)
const arr = input[0][0].split('')
const isEaten = Array(arr.length).fill(false)
let answer = 0
// 나의 생각 : 최대한 앞쪽 햄버거를 먹자!
for (let i = 0; i < arr.length; i++) {
  if (arr[i] === 'H') continue
  let flag = false
  // 자신 왼쪽에 있는 버거
  for (let j = K; j > 0; j--) {
    if (i - j < 0 || arr[i - j] === 'P' || isEaten[i - j]) continue

    isEaten[i - j] = true
    flag = true
    answer++
    break
  }

  if (flag) continue

  for (let j = 1; j <= K; j++) {
    if (i + j >= N || arr[i + j] === 'P' || isEaten[i + j]) continue

    isEaten[i + j] = true
    flag = true
    answer++
    break
  }
}

console.log(answer)
