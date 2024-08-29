/**
 * 풀이 참고
 * 1. 문자열에 포함된 알파벳을 [문자의 개수, index1, index2, ...]를 value로 하는 Map으로 저장
 * 2. 문자의 개수가 K개인 알파벳만 추출
 * 3. 추출된 알파벳으로 문자열의 길이 구해서 가장 짧은거, 가장 긴 거 출력
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
let input = fs.readFileSync(filePath).toString().trim().split('\n')

const T = parseInt(input.shift())

for (let t = 0; t < T; t++) {
  const word = input.shift().trim()
  const K = parseInt(input.shift())

  const map = new Map()
  for (let i = 0; i < word.length; i++) {
    if (!map.has(word[i])) map.set(word[i], [1, i])
    else {
      const temp = [...map.get(word[i]), i]
      temp[0] += 1
      map.set(word[i], temp)
    }
  }

  const arr = []

  map.forEach((value) => {
    if (value[0] >= K) {
      for (i = value.length - 1; i >= K; i--) {
        arr.push(value[i] - value[i - K + 1] + 1)
      }
    }
  })

  if (arr.length === 0) console.log(-1)
  else {
    arr.sort((a, b) => a - b)
    console.log(arr[0], arr[arr.length - 1])
  }
}