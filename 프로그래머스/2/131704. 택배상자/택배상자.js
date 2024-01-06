/*
    컨테이너 : 1부터 순서대로 상자 전달
    보조 컨테이너 : stack
    order : 순서에 맞는 상자 오면 queue처럼 빼기
*/
function solution(order) {
    var answer = 0;
    
    order.reverse();
    const subCont = [];
    const orderSize = order.length;
    for(let i = 1; i <= orderSize;i++) {
        if(order[order.length - 1] === i) {
            answer++;
            order.pop();
        }
        else if(subCont.length > 0 && subCont[subCont.length - 1] === order[order.length - 1]) {
            answer++;
            order.pop();
            subCont.pop();
            i--;
        }
        else subCont.push(i);
    }
    
    while(order.length > 0) {
        if(order[order.length - 1] === subCont[subCont.length - 1]) {
            answer++;
            order.pop();
            subCont.pop();
        }
        else break;
    }
    
    return answer;
}