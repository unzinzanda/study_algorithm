function solution(queue1, queue2) {
    var answer = -1;
    
    let count = 0, maxCount = (queue1.length + queue2.length) * 2;
    let sum1 = queue1.reduce((sum, cur) => {return sum += cur}, 0);
    let sum2 = queue2.reduce((sum, cur) => {return sum += cur}, 0);
    let start1 = 0, start2 = 0;
    while(count < maxCount) {
        // queue1에서 빼서 queue2에 추가
        if(sum1 > sum2) {
            count++;
            sum1 -= queue1[start1];
            sum2 += queue1[start1];
            queue2.push(queue1[start1++]);
        }
        else if(sum2 > sum1) {
            count++;
            sum1 += queue2[start2];
            sum2 -= queue2[start2];
            queue1.push(queue2[start2++]);
        }
        else {
            answer = count;
            break;
        }
    }
    
    return answer;
}