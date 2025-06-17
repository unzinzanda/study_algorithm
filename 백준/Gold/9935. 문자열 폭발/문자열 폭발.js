const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.trim())

/**
 * 문자열을 순회
 * 만약 폭발문자열에 포함된 문자라면 stack에 추가
 * stack의 길이 >= 폭발문자열의 길이 폭발 가능인지 확인
 * 가능하면 stack에서 제거, 아니라면 그대로 진행
 * 만약 stack 길이가 0이 아닌데 폭발문자열에 포함되지 않은 문자 마주치면
 * -> stack에 담긴 값을 answer의 마지막에 추가하고 stack 비우기
 *
 */

let stack = []
const boom = input[1].split('')
let answer = ''

for (let i = 0; i < input[0].length; i++) {
  // 폭발 문자 포함
  if (boom.includes(input[0][i])) {
    stack.push(input[0][i])
    if (stack.length >= input[1].length) {
      let isBoom = true
      for (let j = 0; j < input[1].length; j++) {
        if (stack[stack.length - input[1].length + j] !== input[1][j]) {
          isBoom = false
          break
        }
      }

      if (isBoom) stack.length = stack.length - input[1].length
    }
  } else {
    if (stack.length === 0) answer += input[0][i]
    else {
      // stack 비우기
      answer += stack.join('')
      answer += input[0][i]
      stack = []
    }
  }
}

if (stack.length > 0) answer += stack.join('')

console.log(answer.length > 0 ? answer : 'FRULA')
