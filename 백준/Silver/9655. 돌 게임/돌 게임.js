const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs.readFileSync(filePath).toString().trim()

input = parseInt(input)

let result = Math.floor(input / 3)
result += Math.floor(input % 3)

console.log(result % 2 === 0 ? 'CY' : 'SK')