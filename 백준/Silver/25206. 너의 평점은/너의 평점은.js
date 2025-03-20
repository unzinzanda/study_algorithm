const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.trim().split(' '))

const grade = {
  'A+': 4.5,
  A0: 4.0,
  'B+': 3.5,
  B0: 3.0,
  'C+': 2.5,
  C0: 2.0,
  'D+': 1.5,
  D0: 1.0,
  F: 0.0,
}

let score = 0,
  gradeSum = 0

for (let i = 0; i < input.length; i++) {
  if (input[i][2] === 'P') continue

  score += Number(input[i][1]) * grade[input[i][2]]
  gradeSum += Number(input[i][1])
}

console.log((Math.round((score / gradeSum) * 1000000) / 1000000).toFixed(6))
