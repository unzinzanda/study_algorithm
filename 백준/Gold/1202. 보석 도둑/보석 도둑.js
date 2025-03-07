/**
 * 우선순위 큐 + 그리디
 * 가격으로 내림차순 정렬(minHeap)
 * 보석 오름차순, 가방 오름차순 정렬
 * 현재 가방에 넣을 수 있는 보석을 pq에 넣음
 * 더이상넣을 수 있는 보석이 없다면 pq의 top에 있는 보석을 해당 가방에 넣음
 * 그리고 다음 가방으로 넘어가서 반복
 */
const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

let [N, K] = input.shift()
const jewel = input.splice(0, N)
const bags = input.flat()

class PriorityQueue {
  constructor() {
    this.queue = [null]
  }

  // value = [무게, 가격], 무게가 무겁고 가격이 높은 순 정렬
  push(value) {
    this.queue.push(value)
    let cur = this.queue.length - 1

    while (cur !== 1) {
      const parent = Math.floor(cur / 2)

      if (this.queue[parent][1] < this.queue[cur][1]) {
        ;[this.queue[parent], this.queue[cur]] = [this.queue[cur], this.queue[parent]]
        cur = parent
      } else break
    }
  }

  pop() {
    const ret = this.queue[1]

    if (this.queue.length === 2) this.queue = [null]
    else {
      this.queue[1] = this.queue.pop()
      let cur = 1

      while (cur < this.queue.length - 1) {
        const leftChild = 2 * cur
        const rightChild = 2 * cur + 1
        let targetChild = leftChild
        if (this.queue[rightChild] && this.queue[leftChild][1] < this.queue[rightChild][1]) {
          targetChild = rightChild
        }

        if (this.queue[targetChild] && this.queue[cur][1] < this.queue[targetChild][1]) {
          ;[this.queue[cur], this.queue[targetChild]] = [this.queue[targetChild], this.queue[cur]]
          cur = targetChild
        } else break
      }
    }

    return ret
  }
}
const pq = new PriorityQueue()

jewel.sort((a, b) => a[0] - b[0])
bags.sort((a, b) => a - b)

let answer = 0,
  jewelIdx = 0
for (let i = 0; i < bags.length; i++) {
  while (jewelIdx < N) {
    if (bags[i] >= jewel[jewelIdx][0]) {
      pq.push(jewel[jewelIdx])
      jewelIdx += 1
    } else {
      break
    }
  }

  if (pq.queue.length > 1) {
    const temp = pq.pop()
    answer += temp[1]
  }
}

console.log(answer)
