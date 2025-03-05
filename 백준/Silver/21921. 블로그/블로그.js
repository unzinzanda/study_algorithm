const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, X] = input.shift()
input = input.flat()

for (let i = 1; i < input.length; i++) input[i] += input[i - 1]

input.unshift(0)

let start = 0,
  end = start + X,
  maxVisitor = 0,
  count = 0

while (end < input.length) {
  const sum = input[end] - input[start]
  if (sum > maxVisitor) {
    count = 1
    maxVisitor = sum
  } else if (sum === maxVisitor) count += 1

  start += 1
  end += 1
}

if (maxVisitor === 0) console.log('SAD')
else {
  console.log(maxVisitor)
  console.log(count)
}
