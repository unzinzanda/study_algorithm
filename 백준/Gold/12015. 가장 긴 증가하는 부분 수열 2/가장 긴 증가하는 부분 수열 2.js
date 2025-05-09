const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map((el) => el.split(' ').map(Number))

const [N] = input.shift()
const arr = input.flat()

// 가장 긴 증가하는 부분 수열 binary search version
// 가장 긴 증가하는 부분 수열 배열
const lis = [arr[0]]

for (let i = 1; i < N; i++) {
  // lis의 마지막 원소보다 크면 바로 추가
  if (lis[lis.length - 1] < arr[i]) lis.push(arr[i])
  else if (lis[lis.length - 1] > arr[i]) {
    // 작으면 대치할 원소 찾기 -> 이분 탐색
    let start = 0,
      end = lis.length - 1,
      target = -1,
      min = Infinity,
      isExist = false

    while (start <= end) {
      const mid = Math.floor((start + end) / 2)

      if (lis[mid] > arr[i]) {
        if (lis[mid] < min) {
          target = mid
          min = lis[mid]
        }
        end = mid - 1
      } else if (lis[mid] === arr[i]) {
        isExist = true
        break
      } else start = mid + 1
    }

    if (!isExist) lis.splice(target, 1, arr[i])
  }
}

console.log(lis.length)
