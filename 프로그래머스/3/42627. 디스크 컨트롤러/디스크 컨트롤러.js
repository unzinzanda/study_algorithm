class PriorityQueue {
    constructor() {
        this.heap = [null];
    }
    
    size() {
        return this.heap.length - 1;
    }
    
    // [번호, 요청 시각, 소요 시간] 배열을 value로 push
    // 우선순위 : 소요시간 짧 > 요청 시각 빠름 > 번호 작음
    push(value) {
        this.heap.push(value);
        if(this.size() > 1) {
            let curIndex = this.size();
            
            // bottom-up으로 자리 찾기
            while(curIndex > 1) {
                const parentIndex = Math.floor(curIndex / 2);
                
                if((this.heap[curIndex][2] < this.heap[parentIndex][2]) || 
                   (this.heap[curIndex][2] === this.heap[parentIndex][2] && this.heap[curIndex][1] < this.heap[parentIndex][1]) ||
                  (this.heap[curIndex][2] === this.heap[parentIndex][2] && this.heap[curIndex][1] === this.heap[parentIndex][1] && this.heap[curIndex][0] < this.heap[parentIndex][0])) {
                    [this.heap[curIndex], this.heap[parentIndex]] = [this.heap[parentIndex], this.heap[curIndex]];
                    curIndex = parentIndex;
                } else break;
            }
        }
    }
    
    // 가장 높은 우선순위(index 1)인 [번호, 요청 시각, 소요 시간] 형태의 value를 pop
    // 우선순위 : 소요시간 짧 > 요청 시각 빠름 > 번호 작음
    pop() {
        // pq가 비어있으면 null 리턴
        if(this.size() === 0) return null;
        
        const value = this.heap[1];
        
        // pop 후, pq가 비게 된다면 그냥 null 재할당
        if(this.size() === 1) this.heap = [null];
        else {
            this.heap[1] = this.heap.pop();
            // top-down으로 자리 찾기
            let curIndex = 1;
            while(curIndex < this.size()) {
                const leftChildIndex = curIndex * 2;
                const rightChildIndex = curIndex * 2 + 1;
                let targetChildIndex = leftChildIndex;
                if(this.heap[rightChildIndex]) {
                    if((this.heap[rightChildIndex][2] < this.heap[leftChildIndex][2]) || 
                   (this.heap[rightChildIndex][2] === this.heap[leftChildIndex][2] && this.heap[rightChildIndex][1] < this.heap[leftChildIndex][1]) ||
                  (this.heap[rightChildIndex][2] === this.heap[leftChildIndex][2] && this.heap[rightChildIndex][1] === this.heap[leftChildIndex][1] && this.heap[rightChildIndex][0] < this.heap[leftChildIndex][0])) targetChildIndex = rightChildIndex;
                }
                
                if(this.heap[targetChildIndex] && ((this.heap[targetChildIndex][2] < this.heap[curIndex][2]) || 
                   (this.heap[targetChildIndex][2] === this.heap[curIndex][2] && this.heap[targetChildIndex][1] < this.heap[curIndex][1]) ||
                  (this.heap[targetChildIndex][2] === this.heap[curIndex][2] && this.heap[targetChildIndex][1] === this.heap[curIndex][1] && this.heap[targetChildIndex][0] < this.heap[curIndex][0]))) {
                    [this.heap[targetChildIndex], this.heap[curIndex]] = [this.heap[curIndex], this.heap[targetChildIndex]];
                    curIndex = targetChildIndex;
                } else break;
            }
        }
        
        return value;
    }
    
    peek() {
        return this.heap[1];
    }
}

function solution(jobs) {
    let answer = 0;
    const size = jobs.length;
    
    for(let i = 0;i < jobs.length;i++) jobs[i].unshift(i);
    jobs.sort((a, b) => a[1] - b[1]);
    
    const pq = new PriorityQueue();
    let curTime = 0;
    while(jobs.length) {
        while(jobs.length && curTime >= jobs[0][1]) pq.push(jobs.shift());
        if(pq.size() > 0) {
             const task = pq.pop();
            curTime += task[2];
            answer += curTime - task[1];
        } else curTime += 1;
    }
    
    while(pq.size()) {
        const task = pq.pop();
        curTime += task[2];
        answer += curTime - task[1];
    }
    
    return Math.floor(answer / size);
}