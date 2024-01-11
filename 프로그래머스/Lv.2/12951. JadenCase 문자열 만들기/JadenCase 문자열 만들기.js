function solution(s) {
    var answer = '';
    
    const str = s.split(" ");
    
    for(let i = 0;i < str.length;i++){
        const word = str[i];
        answer += word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
        
        if(i !== str.length - 1) answer += " ";
    }
    
    return answer;
}