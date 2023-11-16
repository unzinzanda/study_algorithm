function timeToMinute(time) {
    const str = time.split(":");
    return Number(str[0]) * 60 + Number(str[1]);
}

function solution(fees, records) {
    var answer = [];
    
    const garage = new Map();
    const cost = new Map();
    
    for(let i = 0;i < records.length;i++) {
        const str = records[i].split(" ");
        
        if(str[2] === 'IN') {
            garage.set(str[1], timeToMinute(str[0]));
        }
        else if(str[2] === 'OUT') {
            const outTime = timeToMinute(str[0]);
            const inTime = garage.get(str[1]);
            garage.delete(str[1]);
            
            if(!cost.has(str[1])) cost.set(str[1], outTime - inTime);
            else cost.set(str[1], cost.get(str[1]) + outTime - inTime);
        }
    }
    
    if(garage.size !== 0) {
        for (let [key, value] of garage) {
            if(!cost.has(key)) cost.set(key, timeToMinute("23:59") - value);
            else cost.set(key, cost.get(key) + timeToMinute("23:59") - value);
        }
    }
    
    const mapToArray = [...cost];
    mapToArray.sort((a, b) => a[0] - b[0]);
    
    for(let i = 0;i < mapToArray.length;i++) {
        if(mapToArray[i][1] > fees[0]) answer.push(fees[1] + Math.ceil((mapToArray[i][1] - fees[0]) / fees[2]) * fees[3]);
        else answer.push(fees[1]);
    }
    
    return answer;
}