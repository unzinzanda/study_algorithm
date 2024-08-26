const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, score, P] = input.shift()

input = input.flat()

input.sort((a, b) => b - a)

const ranks = input.splice(0, P)

// 랭킹에 들지 못하는 경우
if (N >= P && ranks[P - 1] >= score) console.log(-1)
else {
  let result = 1
  for (let i = 0; i < ranks.length; i++) {
    if (ranks[i] > score) result++
    else break
  }
  console.log(result)
}
