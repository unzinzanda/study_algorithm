const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

let answer = null

/**
 * 모든 칸의 수를 정한 후, check
 */

// 빈 칸을 찾고 빈 칸에 들어갈 수 있는 숫자 추리기
const emptyBlock = []
for (let i = 0; i < 9; i++) {
  for (let j = 0; j < 9; j++) {
    if (input[i][j] === 0) {
      const isExist = Array(10).fill(false)
      for (let k = 0; k < 9; k++) {
        // 가로
        isExist[input[i][k]] = true
        // 세로
        isExist[input[k][j]] = true
      }
      // 사각형
      for (let k = 3 * Math.floor(i / 3); k < 3 * Math.floor(i / 3) + 3; k++) {
        for (let l = 3 * Math.floor(j / 3); l < 3 * Math.floor(j / 3) + 3; l++)
          isExist[input[k][l]] = true
      }
      const arr = []
      for (let k = 1; k <= 9; k++) if (!isExist[k]) arr.push(k)
      emptyBlock.push([i, j, arr])
    }
  }
}

const pushNumber = (idx) => {
  if (idx === emptyBlock.length) {
    // 정답 저장
    answer = Array.from(Array(9), () => Array(9))
    for (let i = 0; i < 9; i++) {
      for (let j = 0; j < 9; j++) answer[i][j] = input[i][j]
    }

    answer.map((el) => console.log(el.join(' ')))

    process.exit(0)
  }

  for (let i = 1; i < 10; i++) {
    if (check(emptyBlock[idx][0], emptyBlock[idx][1], i)) {
      input[emptyBlock[idx][0]][emptyBlock[idx][1]] = i
      pushNumber(idx + 1)
      input[emptyBlock[idx][0]][emptyBlock[idx][1]] = 0
    }
  }
}

const check = (x, y, value) => {
  for (let i = 0; i < 9; i++) {
    // 가로
    if (input[x][i] === value) return false
    // 세로
    if (input[i][y] === value) return false
  }

  let blockX = Math.floor(x / 3) * 3
  let blockY = Math.floor(y / 3) * 3
  for (let i = blockX; i < blockX + 3; i++) {
    for (let j = blockY; j < blockY + 3; j++) {
      if (input[i][j] === value) return false
    }
  }

  return true
}

pushNumber(0)
