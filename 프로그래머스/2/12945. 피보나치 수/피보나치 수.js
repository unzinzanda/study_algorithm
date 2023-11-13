function solution(n) {
    var fibo = [];
    fibo[0] = 0;
    fibo[1] = 1;
    
    for(var i = 2;i <= n;i++) {
        fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1234567;
    }
    
    return fibo[n] % 1234567;
}