function solution(nums) {
    var answer = 0;
    
    const set = new Set([...nums]);
    
    const r = nums.length / 2;
    
    return set.size > r ? r : set.size;
}