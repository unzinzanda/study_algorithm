const fs = require('fs')
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt'
const input = fs.readFileSync(filePath).toString().trim().split('\n').map(Number)

input.pop()

input.forEach((num) => {
  const visited = Array(num + 1).fill(false)
  const q = [[1, '1']]
  visited[1] = true

  while (q.length > 0) {
    const temp = q.shift()

    if (temp[0] === 0) {
      console.log(temp[1])
      break
    }

    let remain = (temp[0] * 10) % num
    if (!visited[remain]) {
      visited[remain] = true
      q.push([remain, temp[1] + '0'])
    }
    remain = (remain + 1) % num
    if (!visited[remain]) {
      visited[remain] = true
      q.push([remain, temp[1] + '1'])
    }
  }
})
