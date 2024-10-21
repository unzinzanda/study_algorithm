// 침들의 각도 계산
function solution(h1, m1, s1, h2, m2, s2) {
    var answer = 0;
    
    let startTime = h1 * 3600 + m1 * 60 + s1;
    let endTime = h2 * 3600 + m2 * 60 + s2;
    
    if(startTime === 0 || startTime == 12 * 3600) answer++;
    
    while(startTime < endTime) {
        let hCurAngle = startTime / 120 % 360;
        let mCurAngle = startTime / 10 % 360;
        let sCurAngle = startTime * 6 % 360;
        
        let hNextAngle = (startTime + 1) / 120 % 360 === 0 ? 360 : (startTime + 1) / 120 % 360;
        let mNextAngle = (startTime + 1) / 10 % 360 === 0 ? 360 : (startTime + 1) / 10 % 360;
        let sNextAngle = (startTime + 1) * 6 % 360 === 0 ? 360 : (startTime + 1) * 6 % 360;
        
        if(sCurAngle < hCurAngle && sNextAngle >= hNextAngle) answer += 1;
        if(sCurAngle < mCurAngle && sNextAngle >= mNextAngle) answer += 1;
        if(sNextAngle === hNextAngle && hNextAngle === mNextAngle) answer -= 1;
        
        startTime += 1;
    }
    
    
    return answer;
}