/**
 * 배열 [N + 1][N + 1] 사이즈
 * 벽인 칸을 1로 표시
 * 각 열을 지나가면서 처음 1을 마주하면 start 플래그 true처리 cnt 세기 시작
 * 다음 1을 마주하면 cnt를 sum에 더하고 cnt = 0 처리
 */

const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

let answer = 0

const [H, W] = input.shift()
input = input.flat()

const ground = Array.from(Array(H), () => Array(W).fill(0))

for (let i = 0; i < input.length; i++) {
  for (let j = 0; j < input[i]; j++) ground[j][i] = 1
}

for (let i = 0; i < H; i++) {
  let start = false
  let cnt = 0
  for (let j = 0; j < W; j++) {
    if (start && ground[i][j] === 1) {
      answer += cnt
      cnt = 0
    } else if (!start && ground[i][j] === 1) start = true
    else if (start && ground[i][j] === 0) cnt++
  }
}

console.log(answer)