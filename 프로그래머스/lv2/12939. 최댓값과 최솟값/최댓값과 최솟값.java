class Solution {
    public String solution(String s) {
        int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		String str[] = s.split(" ");
		
		int num;
		for(int i = 0;i < str.length;i++) {
			num = Integer.parseInt(str[i]);
			min = Math.min(min, num);
			max = Math.max(max, num);
		}
		
		return min + " " + max;
    }
}