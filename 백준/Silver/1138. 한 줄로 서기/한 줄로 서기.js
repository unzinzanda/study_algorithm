const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N] = input.shift()
input = input.flat().reverse()
const arr = []

arr.push(N)

for (let i = 1; i < N; i++) {
  if (input[i] === 0) arr.unshift(N - i)
  else {
    let idx = 0,
      count = 0
    for (let j = 0; j < arr.length; j++) {
      if (arr[j] > N - i) count += 1
      if (count === input[i]) {
        idx = j
        break
      }
    }
    arr.splice(idx + 1, 0, N - i)
  }
}
console.log(arr.join(' '))