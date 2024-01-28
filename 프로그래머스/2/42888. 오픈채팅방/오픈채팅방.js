/*
    채팅방에 들어온 적있는 유저 아이디 기록 -> Map(key = 유저아이디, value = 닉네임)
    처음 들어오는 유저라면 그냥 추가
    이미 들어온 적있는 유저 아이디가 다시 들어온다면 닉네임 업데이트
    
    들어온건지 나간건지 + 유저아이디 기록 -> 배열
    (들어온거 = 1, 나가는거 -1)
*/
function solution(record) {
    var answer = [];
    const userMap = new Map();
    const msgs = [];
    
    record.map((rec) => {
        const str = rec.split(" ");
        
        if(str[0] === "Enter") {
            userMap.set(str[1], str[2]);
            msgs.push([1, str[1]]);
        }
        else if(str[0] === "Leave") {
            msgs.push([-1, str[1]]);
        }
        else {
            userMap.set(str[1], str[2]);
        }
    })
    
    msgs.map((msg) => {
        if(msg[0] === 1) answer.push(userMap.get(msg[1]) + "님이 들어왔습니다.");
        else answer.push(userMap.get(msg[1]) + "님이 나갔습니다.");
    })
    
    return answer;
}