const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, M] = input.shift()
const dp = Array.from(Array(N), () => Array.from(Array(M), () => Array(3).fill(Infinity)))

// 0: 오른쪽에서 온, 1: 중앙에서 온, 2: 왼쪽에서 온
for (let i = 0; i < M; i++) {
  dp[0][i][0] = input[0][i]
  dp[0][i][1] = input[0][i]
  dp[0][i][2] = input[0][i]
}

for (let i = 1; i < N; i++) {
  for (let j = 0; j < M; j++) {
    if (j === 0) {
      dp[i][j][0] = Math.min(dp[i - 1][j + 1][1], dp[i - 1][j + 1][2]) + input[i][j]
      dp[i][j][1] = dp[i - 1][j][0] + input[i][j]
    } else if (j === M - 1) {
      dp[i][j][1] = dp[i - 1][j][2] + input[i][j]
      dp[i][j][2] = Math.min(dp[i - 1][j - 1][0], dp[i - 1][j - 1][1]) + input[i][j]
    } else {
      dp[i][j][0] = Math.min(dp[i - 1][j + 1][1], dp[i - 1][j + 1][2]) + input[i][j]
      dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + input[i][j]
      dp[i][j][2] = Math.min(dp[i - 1][j - 1][0], dp[i - 1][j - 1][1]) + input[i][j]
    }
  }
}

let answer = Infinity

for (let i = 0; i < M; i++) {
  for (let j = 0; j < 3; j++) answer = Math.min(answer, dp[N - 1][i][j])
}

console.log(answer)
