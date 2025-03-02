function solution(cacheSize, cities) {
    let answer = 0;
    
    const queue = []
    
    cities.forEach(city => {
        city = city.toLowerCase()
        // cache miss
        if(!queue.includes(city)) {
            answer += 5
            queue.push(city)
            if(queue.length > cacheSize) queue.shift()
        } else {
            answer += 1
            const targetIdx = queue.indexOf(city)
            queue.splice(targetIdx, 1)
            queue.push(city)
        }
    })
    
    return answer;
}