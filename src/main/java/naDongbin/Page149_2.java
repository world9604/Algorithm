package naDongbin;


import java.util.Scanner;

public class Page149_2 {

    public static void main(String[] args) {
        /*int[][] array =
                {{0, 0, 1, 1, 0},
                        {0, 0, 0, 1, 1},
                        {1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0}};*/
        /*int[][] array =
                {{0, 0, 1},
                 {0, 1, 0},
                 {1, 0, 1}};*/
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        // 버퍼 비우기
        sc.nextLine();

        int[][] array = new int[1000][1000];
        for (int i = 0; i < N; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < M; j++) {
                // char - '0' 으로 char to int 형 변환
                array[i][j] = str.charAt(j)-'0';
            }
        }

        int answer = new Page149_2().solution(N, M, array);
        System.out.print(answer);
    }

    // (입력 조건)
    // 첫 번째 줄에 얼음틀의 세로 길이 N, 가로 길이 M이 주어진다
    // (1 <= (N, M) <= 1000)
    // 두 번째 줄부터 N + 1번째 줄까지 얼음 틀의 형태가 주어진다.
    // 이때 구멍이 뚫려있는 부분은 0, 그렇지 않은 부분은 1이다.
    // (출력 조건)
    // 한 번에 만들 수 있는 아이스크림의 개수를 출력 한다.
    public int solution(int N, int M, int[][] array){
        DFS dfs = new DFS();
        int count = 0;

        // 총 시간 복잡도 : 1,000,000,000,000
        // 시간 복잡도 : 1000
        for (int i = 0; i < array.length; i++) {
            // 시간 복잡도 : 1000
            for (int j = 0; j < array[i].length; j++) {
                if (dfs.search(array, i, j, N, M)) {
                    // 시간 복잡도 : 1000000(0의 개수)
                    count++;
                }
            }
        }
        return count;
    }
}

class DFS{

    // 방문 노드의 값과 인덱스 i, j를 받는다.
    public boolean search(int[][] array, int i, int j, int N, int M){
        if (i < 0 || i >= N ||
                j < 0 || j >= M)
            return false;

        // 시간 복잡도 : 1000000(0의 개수)
        if (array[i][j] == 0) {
            // 1로 방문 처리
            array[i][j] = 1;
            // 현재 노드의 상하좌우 노드를 방문 처리 해야 함.
            search(array, i+1, j, N, M);
            search(array, i-1, j, N, M);
            search(array, i, j+1, N, M);
            search(array, i, j-1, N, M);
            return true;
        }
        return false;
    }
}