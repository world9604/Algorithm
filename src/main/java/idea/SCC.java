package idea;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class SCC {
    /**
     * SCC(Strongly Connected Component) 강한 결합 요소
     */
    static final int MAX = 1001;
    static ArrayList<Integer>[] arr = new ArrayList[MAX];
    static int[] visited = new int[MAX];
    static List<List<Integer>> SCC = new ArrayList<>();
    static Stack<Integer> stack = new Stack<>();
    static int id = 0;
    static boolean[] finished = new boolean[MAX];

    public static void main(String[] args) {
        final int num = 11;
        arr = Stream.of(arr).map(value -> value = new ArrayList<>()).toArray(ArrayList[]::new);
        arr[1].add(2);
        arr[2].add(3);
        arr[3].add(1);
        arr[4].add(2);
        arr[4].add(5);
        arr[5].add(7);
        arr[6].add(5);
        arr[7].add(6);
        arr[8].add(5);
        arr[8].add(9);
        arr[9].add(10);
        arr[10].add(11);
        arr[11].add(3);
        arr[11].add(8);
        for (int i = 1; i <= num; i++) {
            if (visited[i] == 0) dfs(i);
        }
        System.out.printf("SCC의 갯수 : %d\n", SCC.size());
        for (int i = 0; i < SCC.size(); i++) {
            System.out.printf("%d번째 SCC : ", i + 1);
            for (int k = 0; k < SCC.get(i).size(); k++) {
                System.out.printf("%d ", SCC.get(i).get(k));
            }
            System.out.println();
        }
    }

    static int dfs(int x) {
        visited[x] = ++id; // 노드마다 고유한 번호를 할당
        stack.add(x); // 스택에 자기 자신을 삽입

        int parent = visited[x];
        for (int i = 0; i < arr[x].size(); i++) {
            int y = arr[x].get(i);
            // 방문하지 않은 이웃
            if (visited[y] == 0) parent = Math.min(parent, dfs(y));
            // 처리중인 이웃
            else if (!finished[y]) parent = Math.min(parent, visited[y]);
        }

        // 부모노드가 자기 자신인 경우
        if (parent == visited[x]) {
            List<Integer> scc = new ArrayList<>();
            while (true) {
                int top = stack.pop();
                scc.add(top);
                finished[top] = true;
                if (top == x) break;
            }
            SCC.add(scc);
        }

        // 자신의 부모 값을 반환 합니다.
        return parent;
    }
}
