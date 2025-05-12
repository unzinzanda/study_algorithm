const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N] = input.shift()
const arr = input.flat()

let start = 0,
  end = arr.length - 1,
  result = Infinity

// 0에 가깝다 -> 절댓값이 가장 작은 것

while (start < end) {
  const mix = arr[start] + arr[end]

  if (Math.abs(result) > Math.abs(mix)) result = mix

  if (mix === 0) break
  else if (mix < 0) start += 1
  else end -= 1
}

console.log(result)
