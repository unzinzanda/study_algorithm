function solution(n, k) {
    let answer = []
    let arr = Array(n).fill(1)
    for(let i = 1;i < n;i++) arr[i] = arr[i - 1] + 1
    
    let nth = k - 1
    
    while(arr.length) {
        if(nth === 0) {
            answer.push(...arr)
            break
        }
        
        const count = factorial(arr.length - 1)
        const index = Math.floor(nth / count)
        nth = nth % count
        
        answer.push(arr[index])
        arr.splice(index, 1)
    }
    
    return answer;
}

function factorial(num) {
    let mul = 1
    for(let i = 2;i <= num;i++) mul *= i
    
    return mul
}