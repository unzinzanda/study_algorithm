/*
    대실 시작 시각이 빠르고 시작 시각이 같다면 대실 종료 시각이 빠른 순으로 정렬
    
    book_time으로 반복문을 돌면서 room 배열에서
    배열의 값 중 종료 시각 + 10 < book_time[i]의 시작 시각인 값이 있다면 book_time[i]로 대체
    없다면 새로 배열에 값 추가
*/
function solution(book_time) {
    const sortedArr = book_time.sort();
    const rooms = [];
    rooms.push(book_time[0]);
    
    for(let i = 1;i < book_time.length;i++) {
        let flag = true;
        for(let j = 0;j < rooms.length;j++) {
            const room = rooms[j][1].split(":");
            const book = book_time[i][0].split(":");
            if((Number(room[0]) * 60) + Number(room[1]) + 10 <= (Number(book[0]) * 60) + Number(book[1])) {
                rooms[j] = book_time[i];
                flag = false;
                break;
            }
        }
        
        if(flag) rooms.push(book_time[i]);
    }
    
    return rooms.length;
}