function solution(n) {
    var answer = 0;
    
    const store = new Array(n + 1).fill(0);
    
    store[1] = 1;
    store[2] = 2;
    
    function dp(num) {
        for(let i = 3;i <= n;i++) {
            store[i] = (store[i - 1] + store[i - 2]) % 1000000007;
        }
        
        return store[num];
    }
    
    dp(n);
    
    return store[n];
}