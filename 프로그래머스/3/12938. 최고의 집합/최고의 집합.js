/*
    각 원소의 곱이 최대인 경우
    ->  s / n 으로 구성된 집합
        만약, 나머지가 있다면 나머지만큼의 원소에 +1 해야 곱이 최대가 됨
*/
function solution(n, s) {
    const sum = Math.floor(s / n)
    
    if(sum === 0) return [-1]
    else {
        const answer = Array(n).fill(sum)
        const remainder = s % n
        for(let i = 1;i <= remainder;i++) {
            answer[n - i] += 1
        }
        return answer;
    }
}