/**
 * i와 j 사이에 파이프를 건설하는 비용 === i와 j 사이의 거리
 * 최소 비용으로 파이프 건설하고 싶음
 * 단, 두 필드 사이의 거리가 C 이하라면 파이프를 건설할 수 없음
 * -> 가중치가 C 이상으로 이루어진 MST로 풀면 될 듯
 *
 * 만약 MST를 만들 수 없다면 -1 출력
 *
 * MST 알고리즘 중에서 edge 중점으로 하는 걸 써야할듯 -> 크루스칼 알고리즘
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? 'dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

function find(a) {
  if (parent[a] === a) return a
  else return (parent[a] = find(parent[a]))
}

function union(a, b) {
  a = find(a)
  b = find(b)

  if (a === b) return false

  if (a < b) parent[b] = a
  else parent[a] = b

  return true
}

const [N, C] = input.shift()

const pq = []
const parent = Array(N)

for (let i = 0; i < N; i++) parent[i] = i

for (let i = 0; i < N - 1; i++) {
  for (let j = i + 1; j < N; j++) {
    const distance =
      Math.pow(Math.abs(input[i][0] - input[j][0]), 2) +
      Math.pow(Math.abs(input[i][1] - input[j][1]), 2)

    if (distance >= C) {
      pq.push({ from: i, to: j, weight: distance })
    }
  }
}

pq.sort((a, b) => b.weight - a.weight)

let result = 0,
  cnt = 0

while (pq.length > 0) {
  const temp = pq.pop()

  if (union(temp.from, temp.to)) {
    result += temp.weight
    cnt += 1

    if (cnt === N - 1) break
  }
}

if (cnt === N - 1) console.log(result)
else console.log(-1)