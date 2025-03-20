const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs.readFileSync(filePath).toString().trim().split('\n')

const [N] = input.shift()
input = input.map((el) => el.trim())
let answer = 0

for (let i = 0; i < input.length; i++) {
  const alpha = Array(26).fill(false)
  let flag = true
  for (let j = 0; j < input[i].length; j++) {
    if (alpha[input[i].charAt(j).charCodeAt() - 'A'.charCodeAt()]) {
      flag = false
      break
    }
    if (input[i][j] !== input[i][j + 1])
      alpha[input[i].charAt(j).charCodeAt() - 'A'.charCodeAt()] = true
  }

  if (flag) answer += 1
}

console.log(answer)
