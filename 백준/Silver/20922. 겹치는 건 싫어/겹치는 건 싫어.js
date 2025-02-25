const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, K] = input.shift()
input = input.flat()

let answer = 0
const arr = []

let dup = new Map()
for (let i = 0; i < N; i++) {
  if (!dup.has(input[i])) {
    dup.set(input[i], 1)
    arr.push(input[i])
  } else {
    // 해당 숫자를 이미 K개 포함했다면
    if (dup.get(input[i]) === K) {
      answer = Math.max(answer, arr.length)
      if (dup.get(arr[0]) === 1) dup.delete(arr[0])
      else dup.set(arr[0], dup.get(arr[0]) - 1)
      arr.shift()
      i -= 1
    } else {
      dup.set(input[i], dup.get(input[i]) + 1)
      arr.push(input[i])
    }
  }
}
answer = Math.max(answer, arr.length)
console.log(answer)