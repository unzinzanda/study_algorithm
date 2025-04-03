const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs.readFileSync(filePath).toString().trim().split('\n').map(Number)
let arr = []
const make = (p, r) => {
  if (p < r) {
    const q = Math.floor((r - p) / 3)
    for (let i = p + q + 1; i <= r - q - 1; i++) arr[i] = ' '
    make(p, p + q)
    make(r - q, r)
  }
}

input.forEach((i) => {
  arr = Array(Math.pow(3, i)).fill('-')
  make(0, arr.length - 1)
  console.log(arr.join(''))
})
