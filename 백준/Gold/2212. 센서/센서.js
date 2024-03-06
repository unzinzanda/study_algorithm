/**
 * 오름차순 정렬하고 각 센서 사이의 거리 배열 구하기
 * K개의 집중국을 설치할 수 있다 -> 거리 배열에서 K - 1개를 제외시킬 수 있다.
 * -> 최소를 구해야 하므로 거리 배열을 내림차순 정렬하고 앞에서 부터 K - 1개를 제거한 후 합을 구한다.
 */

const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N] = input.shift()
const [K] = input.shift()

// 센서보다 집중국이 많거나 같으면 0 리턴
if (K >= N) {
  console.log(0)
  return
}

input = input.flat().sort((a, b) => a - b)

const distance = []

for (let i = 0; i < N - 1; i++) {
  distance.push(input[i + 1] - input[i])
}

distance.sort((a, b) => b - a)

console.log(
  distance.splice(K - 1, distance.length).reduce((val, sum) => sum + val, 0),
)