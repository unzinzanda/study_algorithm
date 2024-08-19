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

  // 0행 0열 성장
  let idx = 0
  for (let j = N - 1; j >= 0; j--) {
    if (selfGrowth[idx] === 0) {
      idx += 1
      continue
    }
    answer[j][0] += selfGrowth[idx++]
  }
  for (let j = 1; j < N; j++) {
    if (selfGrowth[idx] === 0) {
      idx += 1
      continue
    }
    answer[0][j] += selfGrowth[idx++]
  }
}

for (let i = 1; i < N; i++) {
  for (let j = 1; j < N; j++) {
    answer[i][j] = answer[0][j]
  }
}

for (let i = 0; i < answer.length; i++) {
  console.log(answer[i].toString().replaceAll(',', ' '))
}