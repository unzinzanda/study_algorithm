/*
    if stack의 size === 0, answer[cur] = -1
    if stack의 top <= numbers[cur], stack에서 pop하고 다음 값과 비교
    if stack의 top > numbers[cur], answer[cur] = stack의 top
    
    비교를 마친 값은 stack에 push
*/
function solution(numbers) {
    const answer = new Array(numbers.length).fill(-1);
    
    const stack = [];
    
    for(let i = numbers.length - 1;i >= 0;i--) {
        if(stack.length === 0) answer[i] = -1;
        else {
            while(stack.length !== 0) {
                if(stack[stack.length - 1] <= numbers[i]) stack.pop();
                else {
                    answer[i] = stack[stack.length - 1];
                    break;
                }
            }
            
            if(stack.length === 0) answer[i] = -1;
        }
        stack.push(numbers[i]);
    }
    
    return answer;
}