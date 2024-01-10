class MaxHeap {
    constructor() {
        this.heap = [null];
    }
    swap(a, b) {
        [this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]];
    }
    heap_push(value) {
        this.heap.push(value);
        let currentIndex = this.heap.length - 1;
        let parentIndex = Math.floor(currentIndex / 2);
        while(parentIndex !== 0 && this.heap[parentIndex] < value) {
            this.swap(parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = Math.floor(currentIndex / 2);
        }
    }
    
    heap_pop() {
        if(this.heap.length === 2) return this.heap.pop();
        
        let returnValue = this.heap[1];
        this.heap[1] = this.heap.pop();
        let currentIndex = 1;
        let leftIndex = 2;
        let rightIndex = 3;
        while(this.heap[currentIndex] < this.heap[leftIndex] || this.heap[currentIndex] < this.heap[rightIndex]) {
            if(this.heap[leftIndex] < this.heap[rightIndex]) {
                this.swap(currentIndex, rightIndex);
                currentIndex = rightIndex;
            } else {
                this.swap(currentIndex, leftIndex);
                currentIndex = leftIndex;
            }
            leftIndex = currentIndex * 2;
            rightIndex = leftIndex + 1;
        }
        return returnValue;
    }
    
    heap_return() {
        return this.heap;
    }
    
    heap_size() {
        return this.heap.length - 1;
    }
}

function solution(n, works) {
    var answer = 0;
    
    const pq = new MaxHeap();
    
    works.map((work) => {
        pq.heap_push(work);
    })
    
    for(let i = 0;i < n;i++) {
        if(pq.heap_size() === 0) break;
        
        let temp = pq.heap_pop();
        if(temp !== 1) pq.heap_push(temp - 1);
    }
    
    const size = pq.heap_size();
    for(let i = 1;i <= size;i++) {
        answer += Math.pow(pq.heap_pop(), 2);
    }
    
    return answer;
}