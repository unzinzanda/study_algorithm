const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [C] = input.pop()
let [A, B] = input.pop()

B += C
A = (A + Math.floor(B / 60)) % 24
B = B % 60

console.log(A, B)
