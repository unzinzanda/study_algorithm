const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const T = input.shift().pop()

for (let t = 0; t < T; t++) {
  const n = input.shift().pop()
  const store = []
  const start = input.shift()

  for (let i = 0; i < n; i++) {
    store.push(input.shift())
  }

  const end = input.shift()

  const visited = Array(n).fill(false)
  const q = [start]
  let answer = 'sad'
  while (q.length) {
    const temp = q.shift()

    // 현재 지점과 도착지가 1000미터 이하 거리만큼 떨어져있으면 도착 가능
    if (Math.abs(temp[0] - end[0]) + Math.abs(temp[1] - end[1]) <= 1000) {
      answer = 'happy'
      break
    }

    for (let i = 0; i < n; i++) {
      // 아직 방문 안 한 편의점이고 현재 지점에서 편의점까지가 1000미터 이하라면
      if (
        !visited[i] &&
        Math.abs(temp[0] - store[i][0]) + Math.abs(temp[1] - store[i][1]) <=
          1000
      ) {
        visited[i] = true
        q.push(store[i])
      }
    }
  }

  console.log(answer)
}