package boj;

import java.util.*;
import java.util.stream.Stream;

public class BOJ2150 {
    /**
     * @문제 https://www.acmicpc.net/problem/2150
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertexCount = sc.nextInt();
        int edgeCount = sc.nextInt();
        List<Integer>[] edgeInfos = new ArrayList[vertexCount + 1];
        edgeInfos = Stream.of(edgeInfos).map(value -> value = new ArrayList<>()).toArray(ArrayList[]::new);

        for (int i = 0; i < edgeCount; i++) {
            int vertexSrc = sc.nextInt();
            int vertexDes = sc.nextInt();
            edgeInfos[vertexSrc].add(vertexDes);
        }

        List<List<Integer>> SCC = new SccClassifier(vertexCount, edgeInfos).solve();
        System.out.printf("%d", SCC.size());
        System.out.println();
        for (List<Integer> scc : SCC) {
            for (Integer value : scc) {
                System.out.printf("%d ", value);
            }
            System.out.printf("-1");
            System.out.println();
        }
    }
}

class SccClassifier {
    int vertexCount;
    List<Integer>[] edgeInfos;
    Stack<Integer> stack;
    int[] parents;
    boolean[] finished;
    List<List<Integer>> SCC;
    int id;

    SccClassifier(int vertexCount, List<Integer>[] edgeInfos) {
        this.vertexCount = vertexCount;
        this.edgeInfos = edgeInfos;
        this.stack = new Stack<>();
        this.parents = new int[vertexCount + 1];
        this.finished = new boolean[vertexCount + 1];
        this.SCC = new ArrayList<>();
        this.id = 0;
    }

    List<List<Integer>> solve() {
        for (int i = 1; i < edgeInfos.length; i++) {
            if (parents[i] == 0) dfs(i);
        }
        Collections.sort(SCC, (src, des) -> src.get(0) - des.get(0));
        return SCC;
    }

    // 항상 처음 방문하는 노드만 실행 됩니다. 즉, N번 수행
    private int dfs(int x) {
        parents[x] = ++id; // 노드마다 고유한 번호를 할당
        stack.add(x); // 스택에 자기 자신을 삽입

        int parent = parents[x];
        for (int k = 0; k < edgeInfos[x].size(); k++) {
            int y = edgeInfos[x].get(k);
            // 방문하지 않은 이웃
            if (parents[y] == 0) parent = Math.min(parent, dfs(y));
            // 방문은 했지만 아직 처리 중인 노드인 경우
            else if (!finished[y]) parent = Math.min(parent, parents[y]);
        }

        // 부모노드가 자기 자신인 경우 SCC를 형성 합니다.
        if (parent == parents[x]) {
            List<Integer> scc = new ArrayList<>();
            while (true) {
                int top = stack.pop();
                scc.add(top);
                finished[top] = true;
                if (top == x) break;
            }
            Collections.sort(scc);
            SCC.add(scc);
        }

        // 자신의 부모 값을 반환 합니다.
        return parent;
    }
}