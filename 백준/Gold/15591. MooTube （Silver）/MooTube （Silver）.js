const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, Q] = input.shift()

const graph = Array.from(Array(N + 1), () => Array())

for (let i = 0; i < N - 1; i++) {
  const temp = input.shift()

  graph[temp[0]].push({ node: temp[1], usado: temp[2] })
  graph[temp[1]].push({ node: temp[0], usado: temp[2] })
}

for (let i = 0; i < Q; i++) {
  const [k, v] = input.shift()
  const visited = Array(N + 1).fill(false)
  const result = Array(N + 1).fill(Infinity)

  const queue = [{ node: v, usado: Infinity }]
  visited[v] = true

  while (queue.length > 0) {
    const temp = queue.shift()
    for (let j = 0; j < graph[temp.node].length; j++) {
      const next = graph[temp.node][j]
      if (!visited[next.node]) {
        visited[next.node] = true
        if (temp.usado > next.usado) {
          result[next.node] = next.usado
          queue.push({ node: next.node, usado: next.usado })
        } else {
          result[next.node] = temp.usado
          queue.push({ node: next.node, usado: temp.usado })
        }
      }
    }
  }
  let cnt = 0
  for (let j = 1; j <= N; j++) {
    if (result[j] !== Infinity && result[j] >= k) cnt++
  }

  console.log(cnt)
}