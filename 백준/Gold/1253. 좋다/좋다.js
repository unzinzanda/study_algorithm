/**
 * 다익스트라
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N] = input.shift()
input = input.flat()
input.sort((a, b) => a - b)
let count = 0

for (let i = 0; i < N; i++) {
  const temp = [...input]
  temp.splice(i, 1)

  let start = 0,
    end = temp.length - 1

  while (start < end) {
    const sum = temp[start] + temp[end]

    if (sum === input[i]) {
      count += 1
      break
    } else if (sum > input[i]) end -= 1
    else start += 1
  }
}

console.log(count)