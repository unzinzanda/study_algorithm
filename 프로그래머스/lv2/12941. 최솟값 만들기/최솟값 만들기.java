import java.util.*;
class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        
        Arrays.sort(A);
        Integer temp[] = new Integer[B.length];
        for(int i = 0;i < B.length;i++) temp[i] = B[i];
        
        Arrays.sort(temp, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        
        for(int i = 0 ;i < A.length;i++) {
            answer += A[i] * temp[i];
        }

        return answer;
    }
}