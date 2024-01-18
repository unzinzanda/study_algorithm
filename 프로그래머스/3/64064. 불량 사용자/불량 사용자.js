/*
    순열(r === banned_id.length)로 해서 각 인덱스에 맞는 불량 아이디로 검사해서 맞으면 answer++ 
*/
function solution(user_id, banned_id) {
    var answer = 0;
    const selected = new Array(5).fill(false);
    const order = [];
    const correctSet = new Set();
    
    function perm(cnt) {
        if(cnt === banned_id.length) {
            // 올바른 제재 아이디 목록인지 check
            let correct = true;
            for(let i = 0;i < order.length;i++) {
                const id = order[i];
                const ban = banned_id[i];
                if(id.length !== ban.length) {
                    correct = false;
                    break;
                }
                
                for(let j = 0;j < id.length;j++) {
                    if(ban[j] !== '*' && ban[j] !== id[j]) {
                        correct = false;
                        break;
                    }
                }
                
                if(!correct) break;
            }
            
            if(correct) {
                // 이미 카운트된 제재 아이디 목록인지 아닌지 체크 필요
                if(correctSet.size === 0) {
                    answer++;
                    correctSet.add(new Set(order));
                }
                else {
                    let flag = false;
                    for(let set of correctSet) {
                        const newSet = new Set(order);
                        const preSize = newSet.size;
                        for(let element of set) newSet.add(element);
                        
                        if(preSize === newSet.size) {
                            flag = true;
                        }
                    }
                    
                    if(!flag) {
                        answer++;
                        correctSet.add(new Set(order));
                    }
                }
            }
            
            return; // 제발...재귀에서 return 빼먹지마...
        }
        
        for(let i = 0;i < user_id.length;i++) {
            if(!selected[i]) {
                order[cnt] = user_id[i];
                selected[i] = true;
                perm(cnt + 1);
                selected[i] = false;
            }
        }
    }
    
    perm(0);
    
    return answer;
}