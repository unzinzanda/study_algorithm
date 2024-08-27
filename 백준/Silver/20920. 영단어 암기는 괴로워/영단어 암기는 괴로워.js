const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs.readFileSync(filePath).toString().trim().split('\n')

const [N, M] = input.shift().split(' ').map(Number)

const words = new Map()

for (let i = 0; i < N; i++) {
  input[i] = input[i].trim()
  if (input[i].length < M) continue

  if (!words.has(input[i])) words.set(input[i], 1)
  else words.set(input[i], words.get(input[i]) + 1)
}

let wordsArr = [...words]

wordsArr = wordsArr
  .sort((a, b) => {
    if (a[1] === b[1]) {
      if (a[0].length === b[0].length) {
        return a[0] > b[0] ? 1 : -1
      }
      return b[0].length - a[0].length
    }
    return b[1] - a[1]
  })
  .map((el) => el[0])
  .join('\n')

console.log(wordsArr)