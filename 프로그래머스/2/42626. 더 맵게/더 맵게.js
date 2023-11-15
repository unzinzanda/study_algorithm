class Heap {
    constructor() {
        this.heap = [null]; // 첫 원소는 사용 X
    }
    
    size() {
        return this.heap.length - 1;
    }
    
    getMin() {
        return this.heap[1] ? this.heap[1] : null;
    }
    
    swap(a, b) {
        return [this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]];
    }
    
    push(value) {
        this.heap.push(value); // 일단 끝에 삽입
        let curIdx = this.heap.length - 1;
        let parIdx = (curIdx / 2) >> 0; // 부모 노드
        
        // 부모 노드의 값이 자녀 노드보다 작아질 때까지 반복
        while(curIdx > 1 && this.heap[parIdx] > this.heap[curIdx]) {
            // 구조 분해 할당을 이용한 swap
            this.swap(parIdx, curIdx)
            
            curIdx = parIdx;
            parIdx = (curIdx / 2) >> 0;
        }
    }
    
    pop() {
        const min = this.heap[1];
        if(this.heap.length <= 2) this.heap = [null];
        // 배열의 가장 마지막 원소를 top에 두고 top-down
        else this.heap[1] = this.heap.pop();
        
        let curIdx = 1;
        let leftIdx = curIdx * 2;
        let rightIdx = (curIdx * 2) + 1;
        
        if(!this.heap[leftIdx]) return min;
        if(!this.heap[rightIdx]) {
            if(this.heap[leftIdx] < this.heap[curIdx]) {
                this.swap(leftIdx, curIdx);
            }
            return min;
        }
        
        // 양쪽 자식이 모두 있는 경우
        while(this.heap[leftIdx] < this.heap[curIdx] || this.heap[rightIdx] < this.heap[curIdx]) {
            // 오 왼 중 더 작은 값의 인덱스를 저장
            const minIdx = this.heap[leftIdx] > this.heap[rightIdx] ? rightIdx : leftIdx;
            this.swap(minIdx, curIdx);
            curIdx = minIdx;
            leftIdx = curIdx * 2;
            rightIdx = curIdx * 2 + 1;
        }
        
        return min;
    }
}

function solution(scoville, K) {
    var answer = 0;
    
    const heap = new Heap();
    
    for(let i = 0;i < scoville.length;i++) {
        heap.push(scoville[i] + 1);
    }
    
    while(heap.getMin() < K + 1) {
        if(heap.size() < 2) break;
        answer++;
        const first = heap.pop() - 1;
        const second = heap.pop() - 1;
        
        heap.push(first + (second * 2) + 1);
    }
    
    if(heap.size() < 2 && heap.getMin() < K + 1) return -1;
    else return answer;
}