/*
    이미 확인해본 조합을 거르고 진행한다.
*/
var answer = []
let candidate = []
let checkedComb = []
function solution(orders, course) {
    orders.sort((a,b) => a.length - b.length)
    
    for(let i = 0;i < course.length;i++) {
        const r = course[i]
        candidate = []
        
        for(let j = 0;j < orders.length;j++) {
            const order = orders[j]
            if(order.length < r) continue
            comb(0, 0, r, order.length, order, [], orders)
            
        }
        
        if(candidate.length === 0) continue
        candidate.sort((a, b) => b[1] - a[1])
        let max = candidate[0][1]
        for(let i = 0;i < candidate.length;i++) {
            if(max !== candidate[i][1]) break
            answer.push(candidate[i][0])
        }
    }
    
    return answer.sort();
}

function comb(idx, cnt, r, n, order, result, orders) {
    if(cnt === r) {
        const set = [...result].sort().toString().replaceAll(',', '')
        if(checkedComb.includes(set)) return
        checkedComb.push(set)
        const cnt = check(set, orders)
        if(cnt !== false) candidate.push([set, cnt])
        return
    }
        
    for(let i = idx;i < n;i++) {
        result.push(order[i])
        comb(i + 1, cnt + 1, r, n, order, result, orders)
        result.pop()
    }
}

function check(set, orders) {
    let cnt = 0
    const charSet = set.split('')

    for(let i = 0;i < orders.length;i++) {
        let flag = true
        for(let j = 0;j < charSet.length;j++) {
            if(!orders[i].includes(charSet[j])) {
                flag = false
                break
            }
        }
        if(flag) cnt += 1
    }
    
    if(cnt >= 2) return cnt
    else return false
}