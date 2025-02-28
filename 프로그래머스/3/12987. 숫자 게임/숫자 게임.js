/*
    그리디
    어차피 B의 순서는 내맘대로 할 수 있으니까 A배열을 정렬하여 문제 풀어도 괜찮
    A, B를 내림차순 정렬
    만약, B의 가장 큰 수가 A를 이길 수 없다면 B의 가장 작은 값을 소비
    이길 수 있다면 answer++ 하고 사용한 값 제거
*/
function solution(A, B) {
    let answer = 0;
    
    A.sort((a, b) => b - a)
    B.sort((a, b) => b - a)
    
    for(let i = 0;i < A.length;i++) {
        if(A[i] >= B[0]) B.pop()
        else {
            answer++
            B.shift()
        }
    }
    
    return answer;
}

/*
// 이분 탐색
// A[i]와 가장 근접한 큰 수 찾기
// splice 때문에 효율성 0점


function solution(A, B) {
    var answer = 0;
    
    B.sort((a, b) => a - b)
    
    for(let i = 0;i < A.length;i++) {
        let index = B.length - 1, start = 0, end = B.length - 1
        
        while(start <= end) {
            const mid = Math.floor((start + end) / 2)
            
            if(A[i] < B[mid]) {
                if(B[mid] < B[index]) index = mid
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        
        if(A[i] < B[index]) {
            answer += 1
            B.splice(index, 1)
        }
    }
    
    return answer;
}
*/