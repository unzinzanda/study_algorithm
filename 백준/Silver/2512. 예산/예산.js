/**
 * 매개 변수 탐색
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N] = input.shift()
const [budget] = input.pop()
input = input.flat()
let answer = 0,
  start = 1,
  end = Math.max(...input),
  maxCost = 0

if (input.reduce((sum, cur) => sum + cur) <= budget) answer = end
else {
  while (start <= end) {
    const mid = Math.floor((start + end) / 2)

    const temp = input.reduce((sum, cur) => sum + (cur >= mid ? mid : cur), 0)

    if (temp > budget) end = mid - 1
    else {
      if (maxCost < temp) {
        maxCost = temp
        answer = mid
      }
      start = mid + 1
    }
  }
}
console.log(answer)