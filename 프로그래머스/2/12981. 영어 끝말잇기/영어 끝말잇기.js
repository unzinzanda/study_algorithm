function solution(n, words) {
    let flag = true;
    let round = 0, position = 0;
    
    const wordSet = new Set();
    wordSet.add(words[0]);
    
    let pre = words[0];
    for(let i = 1;i < words.length;i++) {
        if(wordSet.has(words[i]) || pre[pre.length - 1] !== words[i][0]) {
            flag = false;
            round = Math.floor(i / n) + 1;
            position = Math.floor(i % n) + 1;
            break;
        }
        else {
            wordSet.add(words[i]);
            pre = words[i];
        }
    }
    
    if(flag) {
        round = 0;
        position = 0;
    }

    return [position, round];
}