const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const [S, T] = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.trim())

let answer = 0

const makeS = (str) => {
  if (str === S) {
    answer = 1
    return
  }

  if (str.length === 1) return

  if (str[str.length - 1] === 'A') makeS(str.slice(0, str.length - 1))
  const reversedStr = str.split('').reduce((reversed, char) => char + reversed, '')
  if (reversedStr[str.length - 1] === 'B') makeS(reversedStr.slice(0, str.length - 1))
}

makeS(T)

console.log(answer)