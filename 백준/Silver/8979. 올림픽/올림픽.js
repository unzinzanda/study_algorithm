const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, K] = input.shift()

let target

for (let i = 0; i < N; i++) {
  if (input[i][0] === K) {
    target = input.splice(i, 1).flat()
    break
  }
}

input.sort((a, b) => {
  if (a[1] === b[1]) {
    if (a[2] === b[2]) {
      return b[3] - a[3]
    }
    return b[2] - a[2]
  }
  return b[1] - a[1]
})

let rank = 1

for (let i = 0; i < input.length; i++) {
  if (input[i][1] > target[1]) {
    rank += 1
    continue
  } else if (input[i][1] === target[1]) {
    if (input[i][2] > target[2]) {
      rank += 1
      continue
    } else if (input[i][2] === target[2]) {
      if (input[i][3] > target[3]) {
        rank += 1
        continue
      }
    } else {
      break
    }
  } else {
    break
  }
}

console.log(rank)