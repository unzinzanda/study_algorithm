function solution(arr) {
    var answer = 0;
    return (arr.reduce((a, sum) => sum + a)) / arr.length;
}