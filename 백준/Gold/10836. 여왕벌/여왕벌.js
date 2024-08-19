const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, M] = input.shift()

const answer = Array.from(Array(N), () => Array(N).fill(1))

function transInput(arr) {
  let temp = []
  for (let i = 0; i < 3; i++) {
    for (let j = 0; j < arr[i]; j++) temp.push(i)
  }

  return temp
}

for (let i = 0; i < M; i++) {
  const selfGrowth = transInput(input[i])
  const growthArr = Array.from(Array(N), () => Array(N).fill(0))

  // 0행 0열 성장
  let idx = 0
  for (let j = N - 1; j >= 0; j--) {
    answer[j][0] += selfGrowth[idx]
    growthArr[j][0] = selfGrowth[idx++]
  }
  for (let j = 1; j < N; j++) {
    answer[0][j] += selfGrowth[idx]
    growthArr[0][j] = selfGrowth[idx++]
  }

  // 주변 탐색
  for (let j = 1; j < N; j++) {
    for (let k = 1; k < N; k++) {
      growthArr[j][k] = Math.max(growthArr[j][k - 1], growthArr[j - 1][k - 1], growthArr[j - 1][k])
      answer[j][k] += growthArr[j][k]
    }
  }
}

for (let i = 2; i < N; i++) {
  answer[i].splice(1, N, ...answer[1].slice(1))
}

for (let i = 0; i < answer.length; i++) {
  console.log(answer[i].toString().replaceAll(',', ' '))
}