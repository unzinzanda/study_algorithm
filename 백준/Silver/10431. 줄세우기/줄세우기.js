/**
 * 맨뒤부터 자신의 앞이 자신보다 크면 swap 호출
 */

const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [T] = input.shift()

for (let t = 0; t < T; t++) {
  input[t].shift()
  let result = 0
  let line = [input[t][0]]
  for (let i = 1; i < 20; i++) {
    let insertIdx = -1
    for (let j = 0; j < line.length; j++) {
      if (line[j] > input[t][i]) {
        insertIdx = j
        break
      }
    }

    if (insertIdx === -1) {
      line.push(input[t][i])
    } else {
      line.splice(insertIdx, 0, input[t][i])
      result += line.length - insertIdx - 1
    }
  }

  console.log(t + 1, result)
}