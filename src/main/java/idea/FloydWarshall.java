package idea;

public class FloydWarshall {
    // 10억으로 무한 설정
    static final int INF = (int) 1e9;

    public static void main(String[] args) {
        int[][] inputData = {
                {0, INF, 2},
                {3, 0, INF},
                {INF, 1, 0}
        };
        new All2AllShortestPathSolver(3).start(inputData);
    }
}

class All2AllShortestPathSolver {
    private int dimension;

    All2AllShortestPathSolver(int dimension) {
        this.dimension = dimension;
    }

    void start(int[][] inputDistances) {
        int[][] shortestDistances = new int[dimension][dimension];

        // 결과 그래프(shortestDistances)를 초기화
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                shortestDistances[i][j] = inputDistances[i][j];
            }
        }

        // 3중 반복문 이므로, 시간 복잡도 O(n^3)
        // k = 거쳐가는 노드
        for (int k = 0; k < dimension; k++) {
            // i = 출발 노드
            for (int i = 0; i < dimension; i++) {
                // j = 도착 노드
                for (int j = 0; j < dimension; j++) {
                    shortestDistances[i][j] = Math.min(shortestDistances[i][j],
                            shortestDistances[i][k] + shortestDistances[k][j]);
                }
            }
        }

        // 결과를 출력
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if(shortestDistances[i][j] == FloydWarshall.INF)
                    System.out.print("Infinity");
                else
                    System.out.printf("%4d", shortestDistances[i][j]);
            }
            System.out.println();
        }
    }
}
