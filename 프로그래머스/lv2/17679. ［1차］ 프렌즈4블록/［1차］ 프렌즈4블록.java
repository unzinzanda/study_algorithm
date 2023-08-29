import java.util.*;
import java.io.*;

/*
1. 블록 제거하기
    - 4개의 블록 중 2번 블록부터 탐색, 1 -> 3 -> 4 탐색하며 같은 블록 갯수 count
    - 단, 이미 visited == true인 블록은 갯수 count하지 않음.
    - 4개의 블록을 돌면서 서로 다른 블록이 아닌 경우에 count한 블록을 result에 더함
    - 위 과정을 모든 블록에 반복
    - 반복한 후, visited == true인 블록은 - 로 셋팅
    
2. 블록 아래로 내리기
    - 블록을 모두 제거한 후, 아래에서 부터 위로 탐색하며 블록 내리기
    
3. 지워지는 블록이 없을 때까지 반복

필요 로직
 1) 4블록의 동일함을 체크 후 visited 처리할 로직
 2) 블록을 아래로 내리는 로직
*/

class Solution {
    static char kakaoBoard[][];
    static int dx[] = {0, 1, 1};
    static int dy[] = {-1, -1, 0};
    static boolean visited[][];
    public int solution(int m, int n, String[] board) throws IOException {
        int answer = 0;
        kakaoBoard = new char[m][n];
        
        for(int i = 0;i < m;i++) {
            kakaoBoard[i] = board[i].toCharArray();
        }
        
        boolean endFlag = true;
        
        do {
            endFlag = true;
            visited = new boolean[m][n];
            for(int i = 0;i < m - 1;i++) {
                for(int j = 1;j < n;j++) {
                    
                    if(kakaoBoard[i][j] == '-') continue;
                    
                    int blockCnt = 0;
                    boolean flag = true;
                    for(int k = 0;k < 3;k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(kakaoBoard[i][j] != kakaoBoard[nx][ny]) {
                            flag = false;
                            break;
                        }
                    }

                    if(flag) {
                        endFlag = false;
                        if(!visited[i][j]) {
                            visited[i][j] = true;
                            blockCnt++;
                        }
                        for(int k = 0;k < 3;k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if(!visited[nx][ny]) {
                                visited[nx][ny] = true;
                                blockCnt++;
                            }
                        }

                        answer += blockCnt;
                    }
                }
            }
            
            for(int i = 0;i < m;i++) {
                for(int j = 0;j < n;j++) {
                    if(visited[i][j]) kakaoBoard[i][j] = '-';
                }
            }
            
            for(int i = m - 1;i >= 0;i--) {
                for(int j = 0;j < n;j++) {
                    if(kakaoBoard[i][j] != '-') {
                        int nx = i;
                        while(true) {
                            if(nx + 1 < m && kakaoBoard[nx + 1][j] == '-') {
                                kakaoBoard[nx + 1][j] = kakaoBoard[nx][j];
                                kakaoBoard[nx][j] = '-';
                                nx++;
                            } else break;
                        }
                    }
                }
            }
        } while(!endFlag);
        
        return answer;
    }
}