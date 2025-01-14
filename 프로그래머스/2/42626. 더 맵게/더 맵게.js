class MinHeap {
    constructor() {
        this.heap = [];
    }
    
    size() {
        return this.heap.length;
    }
    
    push(value) {
        this.heap.push(value);
        if(this.size() > 1) {
            let curIndex = this.size() - 1;
            while(curIndex > 0) {
                const parentIndex = Math.floor((curIndex - 1) / 2);
                if(this.heap[parentIndex] > this.heap[curIndex]) {
                    [this.heap[curIndex], this.heap[parentIndex]] = [this.heap[parentIndex], this.heap[curIndex]];
                    curIndex = parentIndex;
                } else break;
            }
        } 
    }
    
    pop() {
        // 힙에 값이 없을 경우, -1 리턴
        if(this.size() < 1) return -1;
        
        const minValue = this.heap[0];
        if(this.size() === 1) this.heap = [];
        else {
            this.heap[0] = this.heap[this.size() - 1];
            this.heap.pop();
            let curIndex = 0;
            while(curIndex < this.size()) {
                const leftChildIndex = 2 * curIndex + 1;
                const rightChildIndex = 2 * curIndex + 2;
                let targetChildIndex = leftChildIndex;
                // 왼오 중 더 작은 값 찾기
                if(this.heap[rightChildIndex] && this.heap[leftChildIndex] > this.heap[rightChildIndex]) targetChildIndex = rightChildIndex;
                
                if(this.heap[curIndex] > this.heap[targetChildIndex]) {
                    [this.heap[curIndex], this.heap[targetChildIndex]] = [this.heap[targetChildIndex], this.heap[curIndex]];
                    curIndex = targetChildIndex;
                } else break;
            }
        }
        
        return minValue;
    }
    
    peek() {
        if(this.size() < 1) return -1;
        else return this.heap[0];
    }
}

function solution(scoville, K) {
    let answer = 0;
    
    const minHeap = new MinHeap();
    
    scoville.forEach(value => minHeap.push(value));
    
    while(minHeap.size() >= 2 && minHeap.peek() < K) {
        const a = minHeap.pop();
        const b = minHeap.pop();
        
        minHeap.push(a + (2 * b));
        answer += 1;
    }
    
    if(minHeap.peek() < K) return -1;
    else return answer;
}