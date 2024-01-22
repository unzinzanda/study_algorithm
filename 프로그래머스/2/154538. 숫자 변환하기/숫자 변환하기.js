function solution(x, y, n) {
    var answer = y;
    
    const dp = new Array(y + 1).fill(y + 1);
    
    const queue = [];
    
    queue.push([y, 0]);
    dp[y] = 0;
    
    while(queue.length !== 0) {
        const temp = queue.shift();
        
        if(temp[0] < x) continue;
        
        else {
            if(dp[temp[0] - n] > temp[1] + 1) {
                dp[temp[0] - n] = temp[1] + 1;
                queue.push([temp[0] - n, temp[1] + 1]);
            }
            if(temp[0] % 2 === 0 && dp[temp[0] / 2] > temp[1] + 1) {
                dp[temp[0] / 2] = temp[1] + 1;
                queue.push([temp[0] / 2, temp[1] + 1]);
            }
            if(temp[0] % 3 === 0 && dp[temp[0] / 3] > temp[1] + 1) {
                dp[temp[0] / 3] = temp[1] + 1;
                queue.push([temp[0] / 3, temp[1] + 1]);
            }
        }
    }
    
    if(dp[x] === y + 1) dp[x] = -1;
    else if(x === y) dp[x] = 0;
    
    return dp[x];
}