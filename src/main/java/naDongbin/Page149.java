package naDongbin;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Page149 {

    public static void main(String[] args) {
        int answer = new Page149().solution();
        System.out.print(answer);
    }

    // (입력 조건)
    // 첫 번째 줄에 얼음틀의 세로 길이 N, 가로 길이 M이 주어진다
    // (1 <= (N, M) <= 1000)
    // 두 번째 줄부터 N + 1번째 줄까지 얼음 틀의 형태가 주어진다.
    // 이때 구멍이 뚫려있는 부분은 0, 그렇지 않은 부분은 1이다.
    // (출력 조건)
    // 한 번에 만들 수 있는 아이스크림의 개수를 출력 한다.
    public int solution(){
        // BFS를 통해서 방문 노드를 체크하고
        // count 를 센다.
        // 총 count 를 반환 한다.
        int N = 3;
        int M = 3;
        //int[][] array = new int[N][M];
        /*int[][] array =
                {{0, 0, 1, 1, 0},
                 {0, 0, 0, 1, 1},
                 {1, 1, 1, 1, 1},
                 {0, 0, 0, 0, 0}};*/
        int[][] array =
                {{0, 0, 1},
                 {0, 1, 0},
                 {1, 0, 1}};

        //모두 false 으로 초기화 됨
        boolean[][] visited = new boolean[N][M];
        BFS bfs = new BFS();
        int count = 0;

        // 총 시간 복잡도 : 1,000,000,000,000
        // 시간 복잡도 : 1000
        for (int i = 0; i < array.length; i++) {
            // 시간 복잡도 : 1000
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0 && !visited[i][j]) {
                    // 시간 복잡도 : 1000000(0의 개수)
                    bfs.search(array, visited, i, j, N, M);
                    count++;
                }
            }
        }
        return count;
    }
}

class BFS{
    Queue<Integer> queue;

    BFS(){
        this.queue = new LinkedList<>();
    }

    // 방문 노드의 값과 인덱스 i, j를 받는다.
    public void search(int[][] array, boolean[][] visited,
                       int i, int j, int N, int M){
        if (i < 0 || i >= N ||
                j < 0 || j >= M ||
                visited[i][j] || array[i][j] == 1)
            return;

        queue.add(array[i][j]);

        // 시간 복잡도 : 1000000(0의 개수)
        while (!queue.isEmpty()) {
            queue.poll();
            visited[i][j] = true;

            // 현재 노드의 상하좌우 노드를 queue 의 넣어야 함.
            search(array, visited, i+1, j, N, M);
            search(array, visited, i-1, j, N, M);
            search(array, visited, i, j+1, N, M);
            search(array, visited, i, j-1, N, M);
        }
    }
}