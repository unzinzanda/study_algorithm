const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n").map(el => el.split(" ").map(Number));

/**
 * 필요한 함수
 * 1. 빙산 녹이는 작업을 할 완전탐색 함수
 * 2. 빙산의 분리를 체크할 dfs 함수
 */

const N = input[0][0], M = input[0][1];
const dx = [1, -1, 0, 0], dy = [0, 0, -1, 1];
let visited = Array.from(new Array(N + 1), () => new Array(M).fill(false));
function melting() {
    for(let i = 1; i <= N;i++) {
        for(let j = 0;j < M;j++) {
            if(input[i][j] > 0) {
                // 빙산이 녹아서 0 이하가 된 칸에 대한 예외 처리를 하기 위함
                visited[i][j] = true;
                for(let k = 0;k < 4;k++) {
                    const nx = i + dx[k];
                    const ny = j + dy[k];

                    if(nx < 1 || ny < 0 || nx > N || ny >= M || input[nx][ny] > 0 || visited[nx][ny]) continue;
                    
                    input[i][j] -= 1;
                }
            }
        }
    }
}

function dfs(x, y) {
    visited[x][y] = true;
    
    for(let k = 0;k < 4;k++) {
        const nx = x + dx[k];
        const ny = y + dy[k];

        if(nx < 1 || ny < 0 || nx > N || ny >= M || input[nx][ny] <= 0 || visited[nx][ny]) continue;
        
        dfs(nx, ny);
    }
}

let answer = 0;

while(true) {
    // 년도 체크
    answer++;

    // 빙산 녹이기
    melting();

    // 나눠졌는지 체크
    let count = 0;
    visited = Array.from(new Array(N + 1), () => new Array(M).fill(false));
    for(let i = 1;i <= N;i++) {
        for(let j = 0;j < M;j++) {
            if(!visited[i][j] && input[i][j] > 0) {
                dfs(i, j);
                count++;
            }
        }
    }

    if(count > 1 || count === 0) {
        // 분리되기 전에 모두 녹으면 0 출력
        if(count === 0) answer = 0;
        break;
    }
}

console.log(answer);