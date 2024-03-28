const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N] = input.shift()

const dp = Array(N + 1).fill(0)

for (let i = 0; i < N; i++) {
  if (i !== 0) dp[i] = Math.max(dp[i], dp[i - 1])
  if (i + input[i][0] <= N) {
    dp[i + input[i][0]] = Math.max(dp[i + input[i][0]], input[i][1] + dp[i])
  }
}

console.log(Math.max(...dp))