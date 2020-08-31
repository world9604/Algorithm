package naDongbin;

import java.util.LinkedList;
import java.util.Queue;

public class Page152_2 {
    // 입력 조건
    // 첫째줄에 두 정수 N, M
    // (4 <= N, M <= 200)
    // 다음 N x M의 미로의 정보
    // 시작칸과 마지막 카은 항상 1이다.
    // 0은 괴물 존재, 1은 괴물 없음
    // 출력 조건
    // 첫째 줄에 최소 이동 칸의 개수 출력
    // 입력 예시
    // 5 6
    // 101010
    // 111111
    // 000001
    // 111111
    // 111111
    // 출력 예시
    // 10
    public static void main(String[] args) {
        int N = 5, M = 6;
        int[][] array = {
                {1, 0, 1, 0, 1, 0},
                {1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1}
        };
        int answer = new Page152_2().solution(N, M, array);
        System.out.print(answer);
    }

    public int solution(int N, int M, int[][] graph){
        int distance = new BFS2(N, M, graph).search(0, 0);
        for (int[] x : graph) {
            for (int y : x) {
                System.out.printf("%3d ", y);
            }
            System.out.println();
        }

        System.out.println();
        return distance;
    }
}

class BFS2{
    private int N;
    private int M;
    private int[][] graph;

    // (0, 0)인 자기 자신을 제외한 주변 노드 방문을 위해
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};

    public BFS2(int N, int M, int[][] graph) {
        this.N = N;
        this.M = M;
        this.graph = graph;
    }

    public int search(int n, int m){
        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(n, m));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.getX();
            int y = cur.getY();

            // 주변 노드들을 bfs 한다.
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 넘기지 않는다.
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                // 괴물이 존재 하는 경우 가지 않는다.
                if (graph[nx][ny] == 0) continue;

                // 처음 방문 하는 노드만 거리 기록
                if (graph[nx][ny] == 1){
                    // 최단 거리를 구하기 위해서는 이전 노드 + 1 (predecessor 이용)
                    // count 변수를 만들어 +1 하는 방법은
                    // bfs 는 모든 노드를 탐방하기 때문에
                    // 1이 들어 있는 모든 노드의 갯수를 반환
                    graph[nx][ny] = graph[x][y] + 1;
                    queue.offer(new Node(nx, ny));
                }
            }
        }
        // 도착지점 값 반환
        return graph[N-1][M-1];
    }
}

class Node{
    private int x;
    private int y;

    Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
