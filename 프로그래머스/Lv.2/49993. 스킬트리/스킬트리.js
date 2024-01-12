/*
    skill_trees에서 skill에 포함되지 않는 스킬은 제거하고 비교
    (아이디어 얻어옴 ㅎㅎ..)

    주의!
    선행 스킬이 있기 때문에 무조건 skill[0]으로 시작하는 result만 skill과 같은지 비교해도 됨
    만약 skill에 포함된 skill을 하나도 배우지 않는 스킬트리라면 그것도 가능한 경우가 됨!
*/

function solution(skill, skill_trees) {
    var answer = 0;
    
    skill_trees.map((step) => {
        for(let i = 0;i < step.length;i++) {
            let flag = true;
            for(let j = 0;j < skill.length;j++) {
                if(step[i] === skill[j]) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                step = step.replace(step[i], ' ');
            }
        }
        const result = step.replaceAll(' ', '');
        if(result[0] === skill[0] && skill.includes(result)) answer++;
        else if(!result) answer++;
    })
    
    return answer;
}
