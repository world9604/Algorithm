package naDongbin;

import java.util.Arrays;

public class Page311 {
    // 모험가 길드
    // page 311
    public static void main(String[] args) {
        int N = 5;
        int[] group = new int[]{2, 3, 1, 2, 2};
        System.out.print(new Page311().solution(N, group));
    }

    // N <= 100,000
    private int solution(int n, int[] group) {
        Arrays.sort(group);

        int groupCnt = 0, cnt = 0;

        for (int i = 0; i < n; i++) {
            cnt++;
            if (group[i] <= cnt) {
                groupCnt++;
                cnt = 0;
            }
        }

        return groupCnt;
    }
}
