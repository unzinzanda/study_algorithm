/**
 * DFS
 * 시작점으로 다시 돌아올 수 있다면 팀을 이룸!
 */

const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

let [t] = input.shift()
// done : 하나의 노드가 dfs 순회를 마쳤음을 의미
// visited : dfs에 사용할 방문 배열
let visited, done, board, count

while (t-- > 0) {
  const [n] = input.shift()
  board = input.shift()
  board.unshift(0)

  visited = Array(n + 1).fill(false)
  done = Array(n + 1).fill(false)
  count = 0

  for (let i = 1; i <= n; i++) {
    if (visited[i]) continue

    dfs(i)
  }

  console.log(n - count)
}

function dfs(node) {
  visited[node] = true
  const next = board[node]

  if (!visited[next]) dfs(next)
  else if (!done[next]) {
    for (let i = next; i !== node; i = board[i]) count += 1
    count += 1
  }

  done[node] = true
}