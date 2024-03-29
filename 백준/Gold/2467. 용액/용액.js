const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, ...solution] = input.flat()

let res1 = 0,
  res2 = 0,
  curMin = Infinity
let start = 0,
  end = solution.length - 1

while (start < end) {
  const temp = Math.abs(solution[start] + solution[end])

  if (temp <= curMin) {
    curMin = temp
    res1 = solution[start]
    res2 = solution[end]
    if (temp === 0) break
  }

  if (solution[start] < 0 && solution[end] > 0) {
    if (Math.abs(solution[start]) > solution[end]) start += 1
    else end -= 1
  } else {
    if (solution[end] < 0) start += 1
    else if (solution[start] > 0) end -= 1
  }
}

console.log(res1, res2)