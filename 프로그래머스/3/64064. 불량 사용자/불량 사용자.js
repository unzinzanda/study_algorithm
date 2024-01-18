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
                    // correctSet에 이미 있는 set이라면 true
                    let flag = false;
                    for(let set of correctSet) {
                        const newSet = new Set(order);
                        const preSize = newSet.size;
                        for(let element of set) newSet.add(element);

                        // 검사해야 하는 제재 아이디 목록으로 set을 만들고 correctSet의 set의 원소를 모두 add 하였을 때 길이가 그대로 -> 모든 원소가 겹침
                        // -> 이미 있는 set 따라서 flag = true 처리
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
