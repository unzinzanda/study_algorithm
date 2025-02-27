function solution(n, s) {
    const sum = Math.floor(s / n)
    let answer = Array(n).fill(sum)
    const remainder = s % n
    
    for(let i = 1;i <= remainder;i++) {
        answer[n - i] += 1
    }
    
    if(answer[0] === 0) answer = [-1]
    
    return answer;
}