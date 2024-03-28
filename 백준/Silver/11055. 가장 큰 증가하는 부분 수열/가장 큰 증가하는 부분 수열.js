const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, ...numbers] = input.flat()

const sum = [...numbers]

for (let i = 1; i < numbers.length; i++) {
  for (let j = 0; j < i; j++) {
    if (numbers[j] < numbers[i]) {
      sum[i] = Math.max(sum[j] + numbers[i], sum[i])
    }
  }
}

console.log(Math.max(...sum))