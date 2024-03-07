/**
 * 다익스트라(weight가 소의 수)
 */

class PriorityQueue {
  constructor() {
    this.values = []
  }

  getLeftChildIndex = (parentIndex) => parentIndex * 2 + 1
  getRightChildIndex = (parentIndex) => parentIndex * 2 + 2
  getParentIndex = (childIndex) => Math.floor((childIndex - 1) / 2)

  peek = () => this.values[0]

  enqueue = (node) => {
    this.values.push(node)

    let index = this.values.length - 1
    const curNode = node

    while (index > 0) {
      const parentIndex = this.getParentIndex(index)

      if (this.values[parentIndex].cow > curNode.cow) {
        this.values[index] = this.values[parentIndex]
        index = parentIndex
      } else break
    }

    this.values[index] = curNode
  }

  heapifyDown = () => {
    let index = 0
    const count = this.values.length
    const rootNode = this.values[index]

    while (this.getLeftChildIndex(index) < count) {
      const left = this.getLeftChildIndex(index)
      const right = this.getRightChildIndex(index)

      const smallerChildIndex =
        right < count && this.values[right].cow < this.values[left].cow
          ? right
          : left

      if (this.values[smallerChildIndex].cow <= rootNode.cow) {
        this.values[index] = this.values[smallerChildIndex]
        index = smallerChildIndex
      } else break
    }

    this.values[index] = rootNode
  }

  dequeue = () => {
    const count = this.values.length
    const rootNode = this.values[0]

    if (count <= 0) return undefined
    if (count === 1) this.values = []
    else {
      this.values[0] = this.values.pop()
      this.heapifyDown()
    }

    return rootNode
  }
}

const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
let input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N, M] = input.shift()

const graph = Array.from(Array(N + 1), () => [])

for (let i = 0; i < M; i++) {
  graph[input[i][0]].push({ node: input[i][1], cow: input[i][2] })
  graph[input[i][1]].push({ node: input[i][0], cow: input[i][2] })
}

const pq = new PriorityQueue()
const cowCnt = Array(N + 1).fill(Infinity)

// 데이크스트라
cowCnt[1] = 0
pq.enqueue({ node: 1, cow: 0 })

while (pq.values.length > 0) {
  const temp = pq.dequeue()

  if (cowCnt[temp.node] < temp.cow) continue

  for (let i = 0; i < graph[temp.node].length; i++) {
    const next = graph[temp.node][i]
    const newCowCnt = next.cow + cowCnt[temp.node]

    if (newCowCnt < cowCnt[next.node]) {
      cowCnt[next.node] = newCowCnt
      pq.enqueue(next)
    }
  }
}

console.log(cowCnt[N])