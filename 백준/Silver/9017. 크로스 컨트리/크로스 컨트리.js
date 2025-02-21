const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [T] = input.shift()

for (let t = 0; t < T; t++) {
  const [N] = input.shift()
  const arr = input.shift()
  let map = new Map()

  for (let i = 0; i < N; i++) {
    if (map.has(arr[i])) map.set(arr[i], map.get(arr[i]) + 1)
    else map.set(arr[i], 1)
  }
  const flag = [false]
  map.forEach((value, key) => {
    if (value === 6) flag[key] = true
    else flag[key] = false
  })

  map = new Map()
  let score = 1

  for (let i = 0; i < N; i++) {
    if (!flag[arr[i]]) continue

    if (map.has(arr[i])) map.set(arr[i], [...map.get(arr[i]), score])
    else map.set(arr[i], [score])

    score += 1
  }

  const result = [...map]
  let win = -1,
    winScore = Infinity

  for (let i = 0; i < result.length; i++) {
    let sum = result[i][1].slice(0, 4).reduce((acc, cur) => acc + cur)

    if (winScore > sum) {
      win = i
      winScore = sum
    } else if (winScore === sum) {
      if (result[win][1][4] > result[i][1][4]) {
        win = i
        winScore = sum
      }
    }
  }

  console.log(result[win][0])
}