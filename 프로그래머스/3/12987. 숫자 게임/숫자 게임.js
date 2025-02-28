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