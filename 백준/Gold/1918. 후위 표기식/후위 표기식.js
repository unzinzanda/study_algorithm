const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim();

let answer = '';
const opeStack = [];

[...input].map((str) => {
    if(/[A-Z]/i.test(str)) {
        answer += str;
    }
    else if(str === "(") {
        opeStack.push(str);
    } else if(str === ")") {
        while(opeStack[opeStack.length - 1] !== "(") {
            answer += opeStack.pop();
        }
        opeStack.pop();
    }
    else if(str === "+" || str === "-") {
        while(opeStack.length && opeStack[opeStack.length - 1] !== "(") {
            answer += opeStack.pop();
        }
        opeStack.push(str);
    }
    else if(str === "*" || str === "/") {
        while(opeStack.length && (opeStack[opeStack.length - 1] === "*" || opeStack[opeStack.length - 1] === "/")) {
            answer += opeStack.pop();
        }
        opeStack.push(str);
    }
})

while(opeStack.length) answer += opeStack.pop();

console.log(answer);