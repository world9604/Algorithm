package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class BOJ2188 {
    /**
     * @문제 https://www.acmicpc.net/problem/2188
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cowCount = sc.nextInt();
        int barnCount = sc.nextInt();
        sc.nextLine();

        int[] result = new int[cowCount + 1];
        ArrayList<Integer>[] arr = new ArrayList[cowCount + 1];
        arr = Stream.of(arr).map(value -> value = new ArrayList<>()).toArray(ArrayList[]::new);

        for (int i = 1; i <= cowCount; i++) {
            int num = sc.nextInt();
            for (int k = 1; k <= num; k++) {
                int barnNum = sc.nextInt();
                arr[i].add(barnNum);
            }
        }

        int count = new BipartiteMatching(arr, result, barnCount).solve();
        System.out.printf("%d", count);
    }
}

class BipartiteMatching {
    int totalCount = 0;
    List<Integer>[] arr;
    int[] result;
    boolean[] checked;

    BipartiteMatching(List[] arr, int[] result, int barnCount) {
        this.arr = arr;
        this.result = result;
        this.checked = new boolean[barnCount + 1];
    }

    // 짝이 결정이 났으면 true, 결정 나지 않았으면 false
    private boolean dfs(int x) {
        for (int i = 0; i < arr[x].size(); i++) {
            int y = arr[x].get(i);
            // 결정 났으므로 건너 뛴다.
            if (checked[y]) continue;
            checked[y] = true;
            // 비어있거나 점유 노드에 들어갈 공간이 있는 경우
            // 아직 결정이 나지 않았고, 결정이 났지만 확실하지 몰라 dfs 를 통해 결정 짓는다.
            if (result[y] == 0 || dfs(result[y])) {
                result[y] = x;
                return true;
            }
        }
        return false;
    }

    int solve() {
        for (int i = 1; i < arr.length; i++) {
            Arrays.fill(checked, false);
            if (dfs(i)) totalCount++;
        }
        return totalCount;
    }
}
