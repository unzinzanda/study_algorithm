/**
 * 애벌레의 성장 정도 : 왼위, 왼, 위 중 가장 많이 성장한 만큼
 * 이 때, 주어진 가장자리 성장도는 오름차순으로 주어지기 때문에 항상 위가 선택되고
 * 결국 가장자리를 제외하고 나머지 모든 열은 가장 윗 행의 값을 가진다
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [M, N] = input.shift()

const answer = Array(2 * M - 1).fill(1)

for (let i = 0; i < N; i++) {
  let idx = input[i][0]

  for (let j = 0; j < input[i][1]; j++) answer[idx++] += 1
  for (let j = 0; j < input[i][2]; j++) answer[idx++] += 2
}

let result = Array(M)

result = answer.slice(M - 1, 2 * M)

console.log(result.toString().replaceAll(',', ' '))

let idx = M - 2

for (let i = 1; i < M; i++) {
  result[0] = answer[idx--]
  console.log(result.toString().replaceAll(',', ' '))
}