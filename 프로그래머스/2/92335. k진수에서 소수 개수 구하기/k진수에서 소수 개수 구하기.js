/*
    1. n을 k진수로 만들기
    2. 0로 split
    3. 소수인지 판별
*/
function solution(n, k) {
    let answer = 0;
    let result = '';
    
    // n을 k진수로 변환
    result = n.toString(k);
    
    // result를 0으로 split;
    const resArr = result.split("0");
    
    // 에라토스테네스의 체 -> 1번을 맞출만큼 소수판별을 하며 시간 초과
//     const isPrime = new Array(1000000).fill(true);
//     isPrime[0] = isPrime[1] = false;
    
//     for(let i = 2;i < 1000000;i++) {
//         if(isPrime[i]) {
//             for(let j = i * i;j < 1000000;j += i) {
//                 if(isPrime[j]) isPrime[j] = false;
//             }
//         }
//     }
    
//     resArr.map((res) => {
//         if(isPrime[Number(res)]) answer++;
//     })
    
    for(let i = 0;i < resArr.length;i++) {
        const res = Number(resArr[i]);
        if(res <= 2) {
            if(res === 2) answer++;
            continue;
        } 
        
        let flag = true;
        for(let j = 2;j <= Math.sqrt(res);j++) {
            if(res % j === 0) {
                flag = false;
                break;
            }
        }
        
        if(flag) answer++;
    }
    
    return answer;
}