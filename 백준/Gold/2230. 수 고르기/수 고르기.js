const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, M] = input.shift()
const arr = []

input.forEach((el) => arr.push(el[0]))

arr.sort((a, b) => a - b)

let start = 0,
  end = 1,
  minSub = Infinity

while (end < N) {
  const sub = arr[end] - arr[start]

  if (sub >= M) {
    minSub = Math.min(minSub, sub)
    start += 1
  } else {
    end += 1
  }
}

console.log(minSub)
