const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs.readFileSync(filePath).toString().trim()

const arr = ['c=', 'c-', 'dz=', 'd-', 'lj', 'nj', 's=', 'z=']
let answer = 0
for (let i = 0; i < arr.length; i++) {
  if (input.includes(arr[i])) {
    answer += input.split(arr[i]).length - 1
    input = input.replaceAll(arr[i], '@')
  }
}

answer += input.split('').filter((value) => value !== '@').length

console.log(answer)
