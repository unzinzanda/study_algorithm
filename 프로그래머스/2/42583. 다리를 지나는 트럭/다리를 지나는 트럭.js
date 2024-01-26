/*
    다리를 건너는 트럭의 queue를 생성
    queue에 추가 가능한 트럭이면 트럭 push
    queue에 아직 추가 불가능한 트럭이라면 0 push
*/
function solution(bridge_length, weight, truck_weights) {
    var answer = 0;
    
    // 다리를 건너는 트럭을 담을 queue
    const bridge = new Array(bridge_length).fill(0);
    let curWeight = 0;
    
    while(truck_weights.length !== 0) {
        answer++;
        curWeight -= bridge.shift();
        
        if(curWeight + truck_weights[0] <= weight) {
            bridge.push(truck_weights.shift());
            curWeight += bridge[bridge.length - 1];
        } else bridge.push(0);
    }
    
    return answer + bridge_length;
}