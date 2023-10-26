import java.util.*;
class Solution {
    public int solution(int[] arr) {
        int answer = 1;
        List<Integer> primeList = new ArrayList<>();
        
        // false이면 소수
        boolean prime[] = new boolean[120];
        
        prime[0] = prime[1] = true;
        
        for(int i=2; i*i<120; i++){
        	// prime[i]가 소수라면
            if(!prime[i]){
            	// prime[j] 소수가 아닌 표시
            	for(int j=i*i; j<120; j+=i) prime[j] = true;                
            }        
        }
        
        for(int i = 0;i < 120;i++) {
            if(!prime[i]) primeList.add(i);
        }
        
        whole:
        while(true) {
            for(int p : primeList) {
                int divCnt = 0;
                for(int i = 0;i < arr.length;i++) {
                    if(arr[i] % p == 0) divCnt++;
                }

                if(divCnt >= 2) {
                    for(int i = 0;i < arr.length;i++) {
                        if(arr[i] % p == 0) arr[i] /= p;
                    }

                    answer *= p;
                    break;
                }
                
                if(divCnt < 2 && p == primeList.get(primeList.size() - 1)) break whole;
            }
        }
        
        for(int i = 0;i < arr.length;i++) answer *= arr[i];
        
        return answer;
    }
}