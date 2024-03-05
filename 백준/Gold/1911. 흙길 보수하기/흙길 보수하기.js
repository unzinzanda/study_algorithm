const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, L] = input.shift()

/**
 * 정렬하고 앞에서 부터 웅덩이에 판자 채우기
 */

input.sort((a, b) => a[0] - b[0])

let answer = 0
let now = 0
for (let i = 0; i < N; i++) {
  now = Math.max(input[i][0], now)
  if (now >= input[i][1]) {
    continue
  }

  let cnt = 0
  if ((input[i][1] - now) % L === 0) {
    cnt = Math.floor((input[i][1] - now) / L)
  } else {
    cnt = Math.floor((input[i][1] - now) / L) + 1
  }

  now += L * cnt
  answer += cnt
}

console.log(answer)