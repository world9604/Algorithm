package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class BOJ11437 {
    /**
     * @문제 https://www.acmicpc.net/problem/11437
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int MAX = 50000;
        LinkedList<Integer>[] tree = Stream.of(new LinkedList[N+1]).map(value -> value = new LinkedList()).toArray(LinkedList[]::new);

        // 전체 트리 노드를 연결
        for (int i = 0; i < N-1; i++) {
            int node = sc.nextInt();
            int node2 = sc.nextInt();
            tree[node].add(node2);
            tree[node2].add(node);
        }

        // 최소 공통 조상을 알고 싶은 노드 쌍
        final int M = sc.nextInt();
        List<int[]> nodes = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int[] node = new int[2];
            node[0] = sc.nextInt();
            node[1] = sc.nextInt();
            nodes.add(node);
        }

        for (int[] node : nodes) {
            int result = new LCAFinder(N + 1, MAX, tree).lca(node[0], node[1]);
            System.out.printf("%d\n", result);
        }
    }

    static class LCAFinder {
        private final int N;
        private final int MAX;
        private int LOG;
        private boolean[] checked;
        private int[] depths;
        private int[][] parent;
        private LinkedList<Integer>[] tree;

        LCAFinder(int N, int MAX, LinkedList<Integer>[] tree) {
            this.N = N;
            this.MAX = MAX;
            for (int i = 0; i < MAX; i++) {
                if (MAX <= (1 << i)) {
                    this.LOG = i;
                    break;
                }
            }
            this.checked = new boolean[MAX];
            this.depths = new int[MAX];
            this.parent = new int[MAX][LOG];
            this.tree = tree;
            setParent();
        }

        void setParent() {
            // 루트를 1로 설정
            dfs(1, 0);
            // 모든 노드에 대한 2^i 번째 부모 노드를 구합니다.
            for (int i = 1; i < LOG; i++) {
                for (int j = 0; j < N; j++) {
                    parent[j][i] = parent[parent[j][i - 1]] [i - 1];
                }
            }
        }

        // 바로 윗 부모와 연결하는 함수 입니다.
        // 모든 노드의 깊이를 구합니다.
        void dfs(int x, int depth) {
            checked[x] = true;
            depths[x] = depth;
            for (int i = 0; i < tree[x].size(); i++) {
                int y = tree[x].get(i);
                if (checked[y]) continue;
                parent[y][0] = x;
                dfs(y, depth + 1);
            }
        }

        // 최소 공통 조상을 찾는 함수 입니다.
        int lca(int x, int y) {
            // y가 더 깊도록 설정 합니다.
            if (depths[x] > depths[y]) {
                int tmp = y;
                y = x;
                x = tmp;
            }

            // 두 노드의 깊이를 동일하도록 맞춥니다.
            for (int i = LOG - 1; i >= 0; i--) {
                if (depths[y] - depths[x] >= (1 << i)) {
                    y = parent[y][i];
                }
            }

            // 부모가 같은 경우 반환 합니다.
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
}
