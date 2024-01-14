function solution(brown, yellow) {
    var answer = [];
    
    let h = 3;
    const total = brown + yellow;
    
    while(true) {
        let flag = false;
        for(let w = 3;w <= Math.floor(total / h);w++) {
            if(h * w === total) {
                if((h - 2) * (w - 2) === yellow) {
                    flag = true;
                    answer[0] = w;
                    answer[1] = h;
                    break;
                }
            }
        }
        if(flag) break;
        else h++;
    }
    
    return answer;
}