const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, S] = input.shift()
input = input.flat()

for (let i = 1; i < N; i++) {
  input[i] += input[i - 1]
}
input.unshift(0)
let start = 1,
  end = 1,
  answer = Infinity

while (end <= N) {
  const sum = input[end] - input[start - 1]

  if (sum >= S) {
    answer = Math.min(answer, end - start + 1)
    start += 1
  } else end += 1
}

console.log(answer !== Infinity ? answer : 0)