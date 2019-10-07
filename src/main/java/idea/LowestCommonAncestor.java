package idea;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LowestCommonAncestor {
    /**
     * @참고 https://blog.naver.com/ndb796/221282478466
     */
    static final int MAX = 1001;
    static final int LOG = 11; // 전체 노드의 갯수는 2^10개 이하 입니다.
    static int n;
    static int[][] parent = new int[MAX][LOG];
    static int[] d = new int[MAX];
    static boolean[] c = new boolean[MAX];
    static List<Integer>[] a = new ArrayList[MAX];

    public static void main(String[] args) {
        n = 21;
        a = Stream.of(a).map(value -> value = new ArrayList<>()).toArray(ArrayList[]::new);
        // 0과 1을 연결 합니다.
        a[0].add(1);
        a[1].add(0);
        // 0과 2를 연결 합니다.
        a[0].add(2);
        a[2].add(0);
        // 1과 3를 연결 합니다.
        a[1].add(3);
        a[3].add(1);
        // 1과 4를 연결 합니다.
        a[1].add(4);
        a[4].add(1);
        // 2과 5를 연결 합니다.
        a[2].add(5);
        a[5].add(2);
        // 2과 6를 연결 합니다.
        a[2].add(6);
        a[6].add(2);
        // 3과 7를 연결 합니다.
        a[3].add(7);
        a[7].add(3);
        // 3과 8를 연결 합니다.
        a[3].add(8);
        a[8].add(3);
        // 4과 9를 연결 합니다.
        a[4].add(9);
        a[9].add(4);
        // 4과10를 연결 합니다.
        a[4].add(10);
        a[10].add(4);
        // 4과 11를 연결 합니다.
        a[4].add(11);
        a[11].add(4);
        // 8과 12를 연결 합니다.
        a[8].add(12);
        a[12].add(8);
        // 8과 13를 연결 합니다.
        a[8].add(13);
        a[13].add(8);
        // 9과 14를 연결 합니다.
        a[9].add(14);
        a[14].add(9);
        // 10과 15를 연결 합니다.
        a[10].add(15);
        a[15].add(10);
        // 13과 16를 연결 합니다.
        a[13].add(16);
        a[16].add(13);
        // 13과 17를 연결 합니다.
        a[13].add(17);
        a[17].add(13);
        // 14과 18를 연결 합니다.
        a[14].add(18);
        a[18].add(14);
        // 15과 19를 연결 합니다.
        a[15].add(19);
        a[19].add(15);
        // 17과 20를 연결 합니다.
        a[17].add(20);
        a[20].add(17);
        setParent();
//        System.out.printf("5와 7의 LCA: %d \n", LCA(5, 7));
        System.out.printf("15와 20의 LCA: %d \n", LCA(15, 20));
//        System.out.printf("16와 17의 LCA: %d \n", LCA(16, 17));
//        System.out.printf("10와 9의 LCA: %d \n", LCA(10, 9));
//        System.out.printf("3와 17의 LCA: %d \n", LCA(3, 17));
    }

    // 전체 부모 관계를 설정하는 함수 입니다.
    static void setParent() {
        // 루트를 0으로 설정 합니다.
        dfs(0, 0);
        // 모든 노드에 대한 2^i 번째 부모 노드를 구합니다.
        for (int i = 1; i < LOG; i++) {
            for (int j = 0; j < n; j++) {
                parent[j][i] = parent[parent[j][i - 1]] [i - 1];
            }
        }
    }

    // 바로 윗 부모와 연결하는 함수 입니다.
    // 모든 노드의 깊이를 구합니다.
    static void dfs(int x, int depth) {
        c[x] = true;
        d[x] = depth;
        for (int i = 0; i < a[x].size(); i++) {
            int y = a[x].get(i);
            if (c[y]) continue;
            parent[y][0] = x;
            dfs(y, depth + 1);
        }
    }

    // 최소 공통 조상을 찾는 함수 입니다.
    static int LCA(int x, int y) {
        // y가 더 깊도록 설정 합니다.
        if (d[x] > d[y]) {
            int tmp = y;
            y = x;
            x = tmp;
        }

        // 두 노드의 깊이를 동일하도록 맞춥니다.
        for (int i = LOG - 1; i >= 0; i--) {
            if (d[y] - d[x] >= (1 << i)) {
                y = parent[y][i];
            }
        }

        // 부모가 같은 경우 반환합니다.
        if (x == y) return x;

        // 최상단 노드부터 내려오는 방식으로 두 노드의 공통 부모를 찾습니다.
        for (int i = LOG - 1; i >= 0; i--) {
            // 조상을 향해 거슬러 올라 갑니다.
            if (parent[x][i] != parent[y][i]) {
                x = parent[x][i];
                y = parent[y][i];
            }
        }
        // 바로 부모가 찾고자 하는 조상 입니다.
        return parent[x][0];
    }
}
