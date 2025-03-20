const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs.readFileSync(filePath).toString().trim()

let answer = 1
for (let i = 0; i < input.length / 2; i++) {
  if (input[i] !== input[input.length - i - 1]) {
    answer = 0
    break
  }
}
console.log(answer)
