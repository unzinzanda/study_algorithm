function solution(number, k) {
    var answer = [];
    
    const arr = number.toString().split("");
    
    let idx = 0;
    while(true) {
        if(idx >= arr.length) break;
        if(answer.length === 0) answer.push(arr[idx++]);
        else {
            if(k !== 0 && answer[answer.length - 1] < arr[idx]) {
                answer.pop();
                k -= 1;
            } 
            else answer.push(arr[idx++]);
        }
    }
    
    while(k-- > 0) answer.pop();
    
    return answer.toString().replaceAll(",", "");
}