function solution(n) {
    const store = new Array(2001).fill(0);
    store[1] = 1;
    store[2] = 2;
    
    function dp(n) {
        if(store[n] === 0) {
            store[n] = Math.floor((dp(n - 1) + dp(n - 2)) % 1234567);
        }
        
        return store[n]
    }
    
    return Math.floor(dp(n) % 1234567);
}