package idea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BipartiteMatching {
    /**
     * @참고 https://blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221240613074&categoryNo=128&parentCategoryNo=0&viewDate=&currentPage=4&postListTopCurrentPage=1&from=postList&userTopListOpen=true&userTopListCount=5&userTopListManageOpen=false&userTopListCurrentPage=4
     * @내용 네트워크 플로우 알고리즘 중 이분 매칭 알고리즘
     */
    static final int MAX = 101;
    static List<Integer>[] arr = new ArrayList[MAX];
    static int[] result = new int[MAX];
    static boolean[] checked = new boolean[MAX];
    static int num = 3;

    public static void main(String[] args) {
        arr = Stream.of(arr).map(value -> value = new ArrayList<>()).toArray(ArrayList[]::new);
        arr[1].add(1);
        arr[1].add(2);
        arr[1].add(3);
        arr[2].add(1);
        arr[3].add(2);
        int count = 0;

        for (int i = 0; i <= num; i++) {
            Arrays.fill(checked, false);
            if (dfs(i)) count++;
        }
        System.out.printf("%d개의 매칭이 이루어졌습니다. \n", count);
        for (int i = 1; i < MAX; i++) {
            if (result[i] != 0) {
                System.out.printf("%d -> %d\n", result[i], i);
            }
        }
    }

    // 매칭에 성공한 경우 true, 실패한경우 false
    static boolean dfs(int x) {
        for (int i = 0; i < arr[x].size(); i++) {
            int y = arr[x].get(i);
            // 이미 처리한 노드는 더이상 볼필요가 없음
            if (checked[y]) continue;
            checked[y] = true;
            // 비어있거나 점유 노드에 더 들어갈 공간이 있는 경우
            if (result[y] == 0 || dfs(result[y])) {
                result[y] = x;
                return true;
            }
        }
        return false;
    }
}
