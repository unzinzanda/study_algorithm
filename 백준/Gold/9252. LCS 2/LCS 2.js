const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const [a, b] = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.trim().split(''))

a.unshift('-')
b.unshift('-')
const arr = Array.from(Array(a.length), () => Array(b.length).fill(0))

for (let i = 1; i < a.length; i++) {
  for (let j = 1; j < b.length; j++) {
    if (a[i] === b[j]) arr[i][j] = arr[i - 1][j - 1] + 1
    else arr[i][j] = Math.max(arr[i][j - 1], arr[i - 1][j])
  }
}

const answer = []
print(a.length - 1, b.length - 1)

function print(i, j) {
  if (arr[i][j] === 0) return

  if (a[i] === b[j]) {
    print(i - 1, j - 1)
    answer.push(a[i])
  } else {
    arr[i - 1][j] > arr[i][j - 1] ? print(i - 1, j) : print(i, j - 1)
  }
}

console.log(answer.length, answer.toString().replaceAll(',', ''))