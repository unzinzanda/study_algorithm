// 단어가 한 자리만 다른지 비교
function compareWords(a, b) {
    let count = 0;
    for(let i = 0;i < a.length;i++) {
        if(a.charAt(i) !== b.charAt(i)) count++;
    }
    
    if(count > 1) return false;
    else return true;
}

function solution(begin, target, words) {
    var answer = 0;
    
    // words에 target이 없으면 바로 0 리턴
    let isEnd = true;
    for(const word of words) {
        if(word === target) isEnd = false;
    }
    if(isEnd) return 0;
    
    const q = [];
    const visited = new Array(words.length).fill(false);
    
    q.push([begin, 0]);
    
    while(q.length !== 0) {
        const [cur, depth] = q.shift();
        
        if(cur === target) {
            answer = depth;
            break;
        }
        
        for(const word of words) {
            if(!visited[words.indexOf(word)] && compareWords(word, cur)) {
                q.push([word, depth + 1]);
                visited[words.indexOf(word)] = true;
            }
        }
    }
    
    return answer;
}