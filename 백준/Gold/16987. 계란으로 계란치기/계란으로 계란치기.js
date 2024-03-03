const input = require('fs').readFileSync('./dev/stdin').toString().split('\n').map(v => v.split(' ').map(Number));
const N = input.shift()[0];
let answer = 0;

let eggs = input;

function dfs(now) {
	if (now == N) {
		let broken = 0;
		for (let i = 0; i < N; i++) {
			if (eggs[i][0] <= 0) broken++;
		}
		answer = Math.max(broken, answer);
		return;
	}
	let flag = true;
	for (let next = 0; next < N; next++) {
		if (next == now) continue;
		if (eggs[now][0] <= 0 || eggs[next][0] <= 0) continue;
		flag = false;
		eggs[now][0] = eggs[now][0] - eggs[next][1];
		eggs[next][0] = eggs[next][0] - eggs[now][1];
		dfs(now + 1);
		eggs[now][0] = eggs[now][0] + eggs[next][1];
		eggs[next][0] = eggs[next][0] + eggs[now][1];
	}
	if (flag) dfs(now + 1)
}

dfs(0)
console.log(answer)