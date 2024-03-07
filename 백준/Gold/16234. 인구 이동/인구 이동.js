/**
 * BFS
 *
 * 연합 : L <= 인구 차이 <= R 이고 아직 방문하지 않은 나라를 방문하며 만들 수 있음.
 */

const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, L, R] = input.shift()
const dx = [1, -1, 0, 0],
  dy = [0, 0, 1, -1]
let answer = 0

while (true) {
  let repeat = false
  const visited = Array.from(Array(N), () => Array(N).fill(false))
  for (let x = 0; x < N; x++) {
    for (let y = 0; y < N; y++) {
      if (!visited[x][y]) {
        const queue = []
        const union = []
        let population = input[x][y],
          country = 1
        queue.push([x, y]) // bfs를 위한 queue
        union.push([x, y]) // 연합 기록(인구 이동에 사용)
        visited[x][y] = true

        while (queue.length) {
          const temp = queue.shift()

          for (let i = 0; i < 4; i++) {
            const nx = temp[0] + dx[i]
            const ny = temp[1] + dy[i]

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
              continue

            const diff = Math.abs(input[temp[0]][temp[1]] - input[nx][ny])

            if (diff >= L && diff <= R) {
              visited[nx][ny] = true
              union.push([nx, ny])
              queue.push([nx, ny])
              population += input[nx][ny]
              country++
            }
          }
        }

        if (country === 1) continue

        repeat = true
        union.map((el) => {
          input[el[0]][el[1]] = Math.floor(population / country)
        })
      }
    }
  }

  if (!repeat) break
  else answer++
}

console.log(answer)