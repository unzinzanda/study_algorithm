const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.trim())

const vowel = ['a', 'e', 'i', 'o', 'u']

/**
 * flag : false에서 시작
 * 1. 모음을 포함하면 true로 변경
 * 2. 같은 알파벳 3개 연속 포함 or 'ee', 'oo' 제외 2개 연속 포함하면 false로 변경
 *
 * 1번 조건을 거쳐 최종적으로 true이면 acceptable
 */
for (i = 0; i < input.length - 1; i++) {
  let flag = false

  for (j = 0; j < 5; j++) {
    if (input[i].includes(vowel[j])) {
      flag = true
      break
    }
  }

  if (flag) {
    flag = doubbleCheck(input[i]) && tripleCheck(input[i])
  }

  if (flag) console.log(`<${input[i]}> is acceptable.`)
  else console.log(`<${input[i]}> is not acceptable.`)
}

function doubbleCheck(str) {
  for (let i = 1; i < str.length; i++) {
    if (str[i - 1] === str[i]) {
      if (str[i] !== 'e' && str[i] !== 'o') return false
    }
  }
  return true
}

function tripleCheck(str) {
  let cnt = 0,
    vowelFlag = false
  for (let i = 0; i < str.length; i++) {
    if (vowel.includes(str[i])) {
      if (!vowelFlag) {
        cnt = 0
        vowelFlag = true
      }
      cnt += 1
    } else {
      if (vowelFlag) {
        cnt = 0
        vowelFlag = false
      }
      cnt -= 1
    }

    if (cnt === -3 || cnt === 3) return false
  }

  return true
}