package idea;

public class Dijkstra {
    static final int INF = 100000000;
    static final int num = 6;
    // 방문한 노드 입니다.
    static boolean[] visited = new boolean[6];
    // 결과 최소 거리 배열 입니다.
    static int[] d = new int[6];
    // 전체 그래프를 초기화 합니다.
    static int[][] a = {
            {0, 2, 5, 1, INF, INF},
            {2, 0, 3, 2, INF, INF},
            {5, 3, 0, 3, 1, 5},
            {1, 2, 3, 0, 1, INF},
            {INF, INF, 1, 1, 0, 2},
            {INF, INF, 5, INF, 2, 0}
    };

    public static void main(String[] args) {
        dijkstra(0);
        for (int i = 0; i < num; i++) {
            System.out.printf("%d ", d[i]);
        }
    }

    // 가장 최소 거리를 가지는 정점을 반환 합니다.
    static int getSmallIndex() {
        int min = INF;
        int index = 0;
        for (int i = 0; i < num; i++) {
            if (d[i] < min && !visited[i]) {
                min = d[i];
                index = i;
            }
        }
        return index;
    }

    static void dijkstra(int start) {
        for (int i = 0; i < num; i++) {
            d[i] = a[start][i];
        }
        visited[start] = true;
        for (int i = 0; i < num - 2; i++) {
            int current = getSmallIndex();
            visited[current] = true;
            for (int j = 0; j < 6; j++) {
                if (!visited[j]) {
                    if (d[current] + a[current][j] < d[j]) {
                        d[j] = d[current] + a[current][j];
                    }
                }
            }
        }
    }
}
