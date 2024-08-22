/**
 * store[i][j] = store[i - 1][j - 1] + store[i - 1][j + 1]
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs.readFileSync(filePath).toString().trim()
input = parseInt(input)

const store = Array.from(Array(input), () => Array(10).fill(0))

for (let i = 1; i < 10; i++) store[0][i] = 1
for (let i = 1; i < input; i++) {
  for (let j = 0; j < 10; j++) {
    if (j - 1 < 0) {
      store[i][j] = store[i - 1][j + 1] % 1000000000
    } else if (j + 1 >= 10) {
      store[i][j] = store[i - 1][j - 1] % 1000000000
    } else {
      store[i][j] = (store[i - 1][j - 1] + store[i - 1][j + 1]) % 1000000000
    }
  }
}

console.log(store[input - 1].reduce((acc, cur) => (acc += cur), 0) % 1000000000)
