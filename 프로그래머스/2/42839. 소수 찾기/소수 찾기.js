function solution(numbers) {
    var answer = 0;
    
    const isPrime = new Array(10000000).fill(true);
    const isChecked = new Array(10000000).fill(false);
    
    // 에라토스테네스의 체
    isPrime[0] = isPrime[1] = false;
    // 지울 때 자기자신은 지우지 않고, 이미 지워진 수는 건너뛴다.
    for(let i = 2;i < 10000000; i++) {
        if(!isPrime[i]) continue; // 이미 지워진 수 건너 뛰기
        
        for(let j = 2 * i;j < 10000000;j += i) {
            if(!isPrime[i]) continue;
            isPrime[j] = false;
        }
    }
    
    const selected = new Array(numbers.length).fill(false);
    const order = [];
    const numArr = numbers.split("");
    
    function comb(idx, cnt, r) {
        if(cnt === r) {
            let str = "";
            order.map((a) => str += a);
            
            const value = Number(str);
            
            if(isPrime[value]) {
                if(!isChecked[value]) {
                    isChecked[value] = true;
                    answer++;
                }
            }
            
            return;
        }
        
        for(let i = 0;i < numbers.length;i++) {
            if(!selected[i]) {
                selected[i] = true;
                order[idx] = numArr[i];
                comb(idx + 1, cnt + 1, r);
                selected[i] = false;
            }
        }
    }
    
    for(let i = 1;i <= numbers.length;i++) {
        comb(0, 0, i);
    }
    
    return answer;
}