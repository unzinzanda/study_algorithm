class MaxHeap {
    constructor() {
        this.heap = [null]
    }
    
    size() {
        return this.heap.length - 1
    }
    
    push(value) {
        this.heap.push(value)
        let current = this.size()
        
        // value가 꼭대기에 올라오게 되면 종료
        while(current !== 1) {
            const parent = Math.floor(current / 2)
            if(this.heap[parent] < this.heap[current]) {
                [this.heap[parent], this.heap[current]] = [this.heap[current], this.heap[parent]]
                current = parent
            } else break
        }
    }
    
    pop() {
        const value = this.heap[1]
        
        if(this.heap.length === 2) this.heap = [null]
        else {
            // 가장 끝 값을 맨 위로(top-down)
            this.heap[1] = this.heap.pop()
            let current = 1
            while(current < this.size()) {
                const leftChild = 2 * current
                const rightChild = 2 * current + 1
                
                let targetChild = leftChild
                if(this.heap[rightChild] && this.heap[rightChild] > this.heap[leftChild])   targetChild = rightChild
                
                if(this.heap[current] < this.heap[targetChild]) {
                    [this.heap[current], this.heap[targetChild]] = [this.heap[targetChild], this.heap[current]]
                    
                    current = targetChild
                } else break
            }
        }
        
        return value
    }
}

function solution(n, works) {
    const maxHeap = new MaxHeap()
    works.forEach(el => maxHeap.push(el))
    
    while(n > 0 && maxHeap.size() > 0) {
        const maxValue = maxHeap.pop()
        if(maxValue !== 0) {
            maxHeap.push(maxValue - 1)
        }
        n -= 1
    }
    
    let answer = 0
    
    maxHeap.heap.forEach(el => {
        answer += el * el
    })
    
    return answer;
}