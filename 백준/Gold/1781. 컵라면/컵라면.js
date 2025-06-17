const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N] = input.shift()

class MinHeap {
  constructor() {
    this.heap = [null]
  }

  push(value) {
    this.heap.push(value)
    let cur = this.heap.length - 1

    while (cur > 1) {
      const parent = Math.floor(cur / 2)
      if (this.heap[parent][1] > this.heap[cur][1]) {
        ;[this.heap[parent], this.heap[cur]] = [this.heap[cur], this.heap[parent]]
        cur = parent
      } else break
    }
  }

  pop() {
    const ret = this.heap[1]
    this.heap[1] = this.heap[this.heap.length - 1]
    this.heap.pop()

    let cur = 1

    while (cur < this.heap.length) {
      const leftChildIndex = cur * 2
      const rightChildIndex = cur * 2 + 1
      let target = leftChildIndex
      if (
        this.heap[rightChildIndex] &&
        this.heap[leftChildIndex][1] > this.heap[rightChildIndex][1]
      ) {
        target = rightChildIndex
      }

      if (this.heap[target] && this.heap[cur][1] > this.heap[target][1]) {
        ;[this.heap[cur], this.heap[target]] = [this.heap[target], this.heap[cur]]
        cur = target
      } else break
    }

    return ret
  }

  top() {
    return this.heap[1]
  }
}

let cnt = 0
const question = new MinHeap()

input.sort((a, b) => {
  if (a[0] === b[0]) return b[1] - a[1]
  return a[0] - b[0]
})

for (let i = 0; i < input.length; i++) {
  if (cnt < input[i][0]) {
    cnt++
    question.push(input[i])
  } else {
    if (question.top()[1] >= input[i][1]) continue
    question.pop()
    question.push(input[i])
  }
}
question.heap.shift()
console.log(question.heap.reduce((pre, cur) => pre + cur[1], 0))
