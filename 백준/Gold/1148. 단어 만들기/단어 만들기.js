const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.trim())

input.pop() // #제거

const words = input.splice(0, input.indexOf('-'))
input.shift() // - 제거

for (let i = 0; i < input.length; i++) {
  const board = input[i].split('')
  const alphabet = new Map()
  for (let j = 0; j < board.length; j++) {
    if (alphabet.has(board[j])) alphabet.set(board[j], alphabet.get(board[j]) + 1)
    else alphabet.set(board[j], 1)
  }

  let minCnt = Infinity,
    maxCnt = -1
  let minAlpha = [],
    maxAlpha = []

  // 중앙에 둘 알파벳 선정하고 그 때 만들어지는 단어 몇 개인지 확인하기
  alphabet.forEach((value, key) => {
    const temp = words.filter((el) => el.includes(key))

    let cnt = 0
    // 만들 수 있는 단어가 몇 개인지 확인
    temp.forEach((word) => {
      let flag = true
      const arr = word.split('')
      const tempCnt = Array(26).fill(0)

      for (let k = 0; k < word.length; k++) {
        // 퍼즐판에 존재하지 않는 알파벳을 지닌 경우
        if (!alphabet.has(arr[k])) {
          flag = false
          break
        }
        tempCnt[arr[k].charCodeAt() - 'A'.charCodeAt()] += 1

        // 사용할 수 있는 알파벳의 수를 넘은 경우
        if (tempCnt[arr[k].charCodeAt() - 'A'.charCodeAt()] > alphabet.get(arr[k])) {
          flag = false
          break
        }
      }

      // 퍼즐판으로 만들 수 있는 단어라면
      if (flag) cnt += 1
    })

    if (cnt < minCnt) {
      minCnt = cnt
      minAlpha = [key]
    } else if (cnt === minCnt) {
      minAlpha.push(key)
    }
    if (cnt > maxCnt) {
      maxCnt = cnt
      maxAlpha = [key]
    } else if (cnt === maxCnt) {
      maxAlpha.push(key)
    }
  })

  console.log(
    minAlpha.sort().toString().replaceAll(',', ''),
    minCnt,
    maxAlpha.sort().toString().replaceAll(',', ''),
    maxCnt,
  )
}