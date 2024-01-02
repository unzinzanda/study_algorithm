function solution(s){
    var answer = true;
    
    const input = s.split("");
    const stack = [];
    
    input.map((i) => {
        if(i === '(') stack.push(i);
        else {
            if(stack.length === 0) {
                answer = false;
                return;
            }
            else stack.pop();
        }
    })
    
    if(stack.length !== 0) answer = false;

    return answer;
}