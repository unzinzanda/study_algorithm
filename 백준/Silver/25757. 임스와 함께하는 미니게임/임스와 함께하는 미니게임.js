const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs.readFileSync(filePath).toString().trim().split('\n')

const [N, type] = input.shift().split(' ')
input = input.map((el) => el.trim())

let game = 0

if (type.trim() === 'Y') game = 1
else if (type.trim() === 'F') game = 2
else game = 3

const set = new Set(input)

console.log(Math.floor(set.size / game))