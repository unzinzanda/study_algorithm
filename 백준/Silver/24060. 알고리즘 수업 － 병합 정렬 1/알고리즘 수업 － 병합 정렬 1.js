const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

let [N, K] = input.shift()
input = input.flat()
let answer = -1

// 원소가 하나인 배열이 될 때까지 분할
const mergeSort = (p, r) => {
  if (p < r) {
    const q = Math.floor((p + r) / 2)
    mergeSort(p, q)
    mergeSort(q + 1, r)
    merge(p, q, r)
  }
}

const merge = (p, q, r) => {
  const temp = []
  let t = 0,
    i = p,
    j = q + 1
  while (i <= q && j <= r) {
    if (input[i] <= input[j]) temp[t++] = input[i++]
    else temp[t++] = input[j++]
  }

  while (i <= q) temp[t++] = input[i++]
  while (j <= r) temp[t++] = input[j++]

  t = 0
  for (i = p; i <= r; i++) {
    if (--K === 0) answer = temp[t]
    input[i] = temp[t++]
  }
}

mergeSort(0, input.length - 1)

console.log(answer)
