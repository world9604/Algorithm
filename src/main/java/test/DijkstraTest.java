package test;


public class DijkstraTest {
    static final int INF = Integer.MAX_VALUE;
    static final int num = 5;

    public static void main(String[] args) {
        // 방문한 노드 입니다.
        boolean[] completed = new boolean[num];
        // 결과 최소 거리 배열 입니다.
        int[] shortestDistance = new int[num];
        // 전체 그래프를 초기화 합니다.
        int[][] edge = {
                {0, 3, 1, 3, INF},
                {3, 0, 1, 2, INF},
                {1, 1, 0, INF, 3},
                {3, 2, INF, 0, 2},
                {INF, INF, 3, 2, 0},
        };

        new ShortestPathFinder(completed, shortestDistance, edge, num, INF).dijkstra(0);

        for (int i = 0; i < num; i++) {
            System.out.printf("%d ", shortestDistance[i]);
        }
    }
}

class ShortestPathFinder{
    private int min;
    private int num;
    private boolean[] completed;
    private int[] shortestDistance;
    private int[][] edge;

    ShortestPathFinder(boolean[] completed, int[] shortestDistance, int[][] edge, int num, int min){
        this.completed = completed;
        this.shortestDistance = shortestDistance;
        this.edge = edge;
        this.num = num;
        this.min = min;
    }

    // 가장 최소 거리를 가지는 정점을 반환 합니다.
    private int getSmallIndex() {
        int index = 0;
        for (int i = 0; i < num; i++) {
            if (shortestDistance[i] < min && !completed[i]) {
                min = shortestDistance[i];
                index = i;
            }
        }
        return index;
    }

    public void dijkstra(int start) {
        for (int i = 0; i < num; i++)
            shortestDistance[i] = edge[start][i];

        completed[start] = true;
        for (int i = 0; i < num - 2; i++) {
            int current = getSmallIndex();
            completed[current] = true;
            for (int j = 0; j < num; j++) {
                if (!completed[j]) {
                    if (shortestDistance[current] + edge[current][j] < shortestDistance[j]) {
                        shortestDistance[j] = shortestDistance[current] + edge[current][j];
                    }
                }
            }
        }
    }
}