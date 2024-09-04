/*
    장르별 2곡씩 수록
    장르 Map: key: 장르문자열, value: [수록곡 재생수, [재생수1, 고유번호1],...]
*/
function solution(genres, plays) {
    var answer = [];
    let map = new Map()
    
    for(let i = 0;i < genres.length;i++) {
        if(!map.has(genres[i])) map.set(genres[i], [plays[i], [plays[i], i]])
        else {
            const temp = [...map.get(genres[i])]
            temp[0] += plays[i]
            temp.push([plays[i], i])
            map.set(genres[i], temp)
        }
    }
    
    let arr = [...map].map(el => el.splice(1).flat())
    
    arr.sort((a, b) => b[0] - a[0])
    
    for(let i = 0;i < arr.length;i++) {
        const list = arr[i].slice(1)
        if(list.length === 1) {
            answer.push(list[0][1])
        } else {
            list.sort((a, b) => {
                if(a[0] === b[0]) return a[1] - b[1]
                return b[0] - a[0]
            })
            console.log(list)
            answer.push(list[0][1])
            answer.push(list[1][1])
        }
    }
    
    return answer;
}