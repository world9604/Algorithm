package naDongbin;

import java.util.Scanner;

public class Page341_3 {

    public static int n, m, result = 0;
    public static int[][] arr = new int[8][8]; // 초기 맵 배열
    public static int[][] temp = new int[8][8]; // 벽을 설치한 뒤의 맵 배열

    // 4가지 이동 방향에 대한 배열
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    // 4 6
    // 0 0 0 0 0 0
    // 1 0 0 0 0 2
    // 1 1 1 0 0 2
    // 0 0 0 0 0 2
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        System.out.println(new Page341_3().solution(n, m, arr));
    }

    public int solution(int n, int m, int[][] arr){
        // 경우의 수 만큼 벽 3개를 설치 한다.

        // temp 초기화
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        // 벽 설치
        // 바이러스 전파
        // result 의 안정지역 최대값 업데이트
        installWall(temp, 0);
        return result;
    }

    private void installWall(int[][] temp, int count) {
        if (count == 3) {
            transmissionVirsu(temp);
            result = Math.max(result, getScore(temp));
        } else {
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[i].length; j++) {
                    if (temp[i][j] == 0) {
                        temp[i][j] = 1;
                        count++;
                        installWall(temp, count);
                        count--;
                        temp[i][j] = 0;
                    }
                }
            }
        }
    }

    private void transmissionVirsu(int[][] temp) {
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                dfs(temp, i, j);
            }
        }
    }

    private void dfs(int[][] temp, int i, int j) {
        //바이러스 라면
        if (2 == temp[i][j]) {
            //좌우상하 바이러스 전파
            for (int k = 0; k < 4; k++) {
                int nx = i + dx[k];
                int ny = j + dy[k];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    //안전 지역 이라면
                    if(0 == temp[nx][ny]){
                        temp[nx][ny] = 2;
                        dfs(temp, nx, ny);
                    }
                }
            }
        }
    }

    // 현재 맵에서 안전 영역의 크기 계산하는 메서드
    public static int getScore(int[][] temp) {
        int score = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                if (temp[i][j] == 0)
                    score++;
            }
        }
        return score;
    }
}