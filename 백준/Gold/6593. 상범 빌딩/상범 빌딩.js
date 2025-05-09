const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.trim())

const dr = [1, -1, 0, 0, 0, 0],
  dc = [0, 0, 1, -1, 0, 0],
  dl = [0, 0, 0, 0, 1, -1]

while (true) {
  const [L, R, C] = input.shift().split(' ').map(Number)
  let start = [0, 0, 0],
    end = [0, 0, 0]
  if (L === 0) break

  // [R][C][L]
  const building = Array.from(Array(R), () => Array.from(Array(C), () => Array(L)))
  const visited = Array.from(Array(R), () => Array.from(Array(C), () => Array(L)))

  // 빌딩 배열 초기화
  for (let l = 0; l < L; l++) {
    for (let r = 0; r < R; r++) {
      const temp = input.shift().split('')
      for (let c = 0; c < C; c++) {
        if (temp[c] === 'S') start = [r, c, l]
        if (temp[c] === 'E') end = [r, c, l]
        building[r][c][l] = temp[c]
      }
    }
    input.shift()
  }

  let isEscaped = false,
    time = 0
  const q = [[...start, 0]]
  visited[start[0]][start[1]][start[2]] = true
  // start부터 BFS 돌기
  while (q.length > 0) {
    const temp = q.shift()
    for (let i = 0; i < 6; i++) {
      const nr = temp[0] + dr[i]
      const nc = temp[1] + dc[i]
      const nl = temp[2] + dl[i]

      if (
        nr < 0 ||
        nr >= R ||
        nc < 0 ||
        nc >= C ||
        nl < 0 ||
        nl >= L ||
        building[nr][nc][nl] === '#' ||
        visited[nr][nc][nl]
      )
        continue

      if (nr === end[0] && nc === end[1] && nl === end[2]) {
        isEscaped = true
        time = temp[3] + 1
        break
      }

      q.push([nr, nc, nl, temp[3] + 1])
      visited[nr][nc][nl] = true
    }

    if (isEscaped) break
  }

  // 탈출 여부에 따른 출력
  if (isEscaped) console.log(`Escaped in ${time} minute(s).`)
  else console.log('Trapped!')
}
