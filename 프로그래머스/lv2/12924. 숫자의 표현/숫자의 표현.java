class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int arr[] = new int[n + 1];
        arr[0] = 0;
        for(int i = 1;i <= n;i++) {
            arr[i] = arr[i - 1] + i;
        }
        
        int start = 0;
        int end = 1;
        
        int sum = 0;
        while(end <= n) {
            sum = arr[end] - arr[start];
            if(sum == n) {
                answer++;
                end++;
            }
            else if(sum < n) end++;
            else start++;
        }
        
        return answer;
    }
}