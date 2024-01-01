function solution(s) {
    const arr = s.split(" ").map((a) => parseInt(a));
    
    
    return Math.min(...arr) + " " + Math.max(...arr);
}