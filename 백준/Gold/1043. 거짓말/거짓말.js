const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

/**
 * 1. 모든 파티를 돌며 어떻게든 진실을 듣게 되는 사람 찾는다.
 * 2. 모든 파티를 돌며 진실을 듣지 못하는 사람으로만 구성되는 파티를 찾는다.
 */

const [N, M] = input.shift()
const truthPerson = input.shift()
const truthCnt = truthPerson.shift()
const known = Array(N + 1).fill(false)

// 진실을 아는 사람 true 처리
for (let i = 0; i < truthCnt; i++) known[truthPerson[i]] = true

// 파티에서 진실을 알 수도 있는 사람 찾기
for (let t = 0; t < M; t++) {
  for (let i = 0; i < M; i++) {
    let flag = false
    for (let j = 1; j <= input[i][0]; j++) {
      if (known[input[i][j]]) {
        flag = true
        break
      }
    }

    // 진실을 아는 사람이 있다면
    if (flag) {
      for (let j = 1; j <= input[i][0]; j++) {
        known[input[i][j]] = true
      }
    }
  }
}

// 과장할 수 있는 파티 세기
let answer = 0
input.map((party) => {
  party.shift()

  let flag = false
  party.map((person) => (flag |= known[person]))

  if (!flag) answer++
})

console.log(answer)