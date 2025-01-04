function solution(phone_book) {
    var answer = true;
    const map = new Map();
    
    phone_book.map(el => {
        map.set(el, 1);
    })
    
    for(let i = 0;i < phone_book.length;i++) {
        for(let j = 1;j < phone_book[i].length;j++) {
            const temp = phone_book[i].slice(0, j);
            if(map.has(temp)) {
                return false;
            }
        }
    }
    
    return answer;
}