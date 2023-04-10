import java.io.*;
import java.util.*;

//백준 11000 강의실 배정
/*
 * 수업 시작 시간과 끝나는 시간을 저장할 객체 생성
 * -> 입력을 우선순위 큐(pq)에 저장하여 시작 시간, 끝나는 시간으로 오름차순으로 정렬
 * 
 * 하나의 우선순위 큐(last)를 추가로 만듬
 * -> 끝나는 시간을 오름차순으로 저장
 * -> 즉, 각 교실별로 수업시작이 가능한 시간이 저장되며 그것이 오름차순으로 정렬되어 있는 것
 * -> last의 peek()보다 수업 시작 시간이 작다는 것은 모든 교실이 그 시간에 수업 중이라는 것임
 * 
 * pq에서 하나의 객체를 뽑아서 시작 시간이 last의 peek()보다 크거나 같으면 교실 추가로 할당하지 않고 수업가능
 * 시작 시간이 last의 peek()보다 작으면 수업 가능한 교실이 없음 -> 교실 배정
 * */
public class Main {
	static class Time implements Comparable<Time> {
		int start;
		int end;
		public Time(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Time o) {
			if(this.start == o.start) return this.end - o.end;
			return this.start - o.start;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Time> pq = new PriorityQueue<>();
		PriorityQueue<Integer> last = new PriorityQueue<>();
		
		String str[];
		int a, b;
		for(int i = 0;i < N;i++) {
			str = br.readLine().split(" ");
			a = Integer.parseInt(str[0]);
			b = Integer.parseInt(str[1]);
			pq.add(new Time(a, b));
		}
		
		int res = 1;
		last.add(pq.poll().end);
		
		while(!pq.isEmpty()) {
			Time cur = pq.poll();
			
			if(cur.start < last.peek()) 
				res++;
			else
				last.poll();
			
			last.add(cur.end);
		}
		
		System.out.println(res);
	}
}