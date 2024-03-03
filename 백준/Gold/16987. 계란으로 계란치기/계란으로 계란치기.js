const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n").map(el => el.split(" ").map(Number));

/**
 * 1. 가장 왼쪽의 계란을 든다.
 * 2. 나머지 계란 중 아무거나 하나 친다. -> 계란이 깨지거나 내구도가 달라진다.
 * 3. 계란을 내려놓고 오른쪽 계란을 든 후, 2번 과정을 다시 진행한다.
 * 4. 내려놓은 계란이 가장 오른쪽 계란이거나 모든 계란이 깨졌다면 종료된다.
 * 
 * 완탐이니?!?!?!
 */

const totalEgg = input.shift()[0];
let answer = 0;
let eggs = input;

function hitEgg(index) {
    if(index == totalEgg) {
        let brokenEggs = 0;
        for(let i = 0;i < totalEgg;i++) {
            if(eggs[i][0] <= 0) brokenEggs++;
        }
        answer = Math.max(answer, brokenEggs);
        return;
    }

    let flag = true;
    for(let i = 0;i < totalEgg;i++) {
        if(i == index) continue;
        if(eggs[index][0] <= 0 || eggs[i][0] <= 0) continue;
        
        flag = false;
        eggs[index][0] -= eggs[i][1];
        eggs[i][0] -= eggs[index][1];
        hitEgg(index + 1);
        eggs[index][0] += eggs[i][1];
        eggs[i][0] += eggs[index][1];
    }
    if(flag) hitEgg(index + 1);
}

hitEgg(0);

console.log(answer);