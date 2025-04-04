const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' '))

let [str] = input.shift()
str = str.trim()
const [N] = input.shift()

// 구간합 배열 만들기
const arr = Array.from(Array(26), () => Array(str.length).fill(0))

for (let i = 0; i < 26; i++) {
  const alpha = String.fromCharCode(i + 'a'.charCodeAt())

  if (str[0] === alpha) arr[i][0] = 1

  for (let j = 1; j < str.length; j++) {
    arr[i][j] = str[j] === alpha ? arr[i][j - 1] + 1 : arr[i][j - 1]
  }
}

let answer = ''
input.forEach((el) => {
  const ascii = el[0].charCodeAt() - 'a'.charCodeAt()
  if (Number(el[1]) === 0) answer += arr[ascii][Number(el[2])] + '\n'
  else answer += arr[ascii][Number(el[2])] - arr[ascii][Number(el[1]) - 1] + '\n'
  // console.log(
  //   Number(el[1]) === 0
  //     ? arr[ascii][Number(el[2])]
  //     : arr[ascii][Number(el[2])] - arr[ascii][Number(el[1]) - 1],
  // )
})

console.log(answer)
