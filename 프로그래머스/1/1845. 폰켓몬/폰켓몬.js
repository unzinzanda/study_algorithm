function solution(nums) {
    var answer = 0;
    
    const map = new Map();
    
    for(let i = 0;i < nums.length;i++) {
        if(!map.has(nums[i])) map.set(nums[i], 1);
        else map.set(nums[i], map.get(nums[i]) + 1);
    }
    
    const r = nums.length / 2;
    
    if(map.size > r) answer = r;
    else {
        answer = map.size;
    }
    
    return answer;
}