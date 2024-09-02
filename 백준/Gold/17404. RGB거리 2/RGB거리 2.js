const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N] = input.shift()
let store

let result = Infinity

for (let i = 0; i < 3; i++) {
  store = Array.from(Array(N), () => Array(3).fill(Infinity))
  store[0][0] = input[0][0]
  store[0][1] = input[0][1]
  store[0][2] = input[0][2]
  result = Math.min(result, dp(N - 1, i, i))
}

console.log(result)

function dp(row, color, nthColor) {
  if (row === 0) {
    if (color === 0) {
      if (nthColor === 0) return Infinity
      else return input[0][0]
    } else if (color === 1) {
      if (nthColor === 1) return Infinity
      else return input[0][1]
    } else {
      if (nthColor === 2) return Infinity
      else return input[0][2]
    }
  }

  if (store[row][color] === Infinity) {
    if (color === 0) {
      store[row][color] =
        Math.min(dp(row - 1, 1, nthColor), dp(row - 1, 2, nthColor)) + input[row][color]
    } else if (color === 1) {
      store[row][color] =
        Math.min(dp(row - 1, 0, nthColor), dp(row - 1, 2, nthColor)) + input[row][color]
    } else if (color === 2) {
      store[row][color] =
        Math.min(dp(row - 1, 1, nthColor), dp(row - 1, 0, nthColor)) + input[row][color]
    }
  }

  return store[row][color]
}