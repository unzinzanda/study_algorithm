const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [n, w, L] = input.shift()

input = input.flat()

let weight = 0,
  result = 0
const bridge = []

while (input.length > 0) {
  result += 1

  if (bridge.length === w) {
    weight -= bridge.shift()
  }

  if (weight + input[0] <= L) {
    weight += input[0]
    bridge.push(input.shift())
  } else bridge.push(0)
}

console.log(result + w)