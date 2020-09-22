package naDongbin;

import java.util.Arrays;

public class Page311_2 {
    // 모험가 길드
    // page 311
    // 파라메트릭 서치로 풀어보기
    public static void main(String[] args) {
        int N = 5;
        int[] group = new int[]{2, 3, 1, 2, 2};
        System.out.print(new Page311_2().solution(N, group));
    }

    // N <= 100,000
    private int solution(int n, int[] group) {
        Arrays.sort(group);
        // 그룹의 갯수 범위 구해주기
        int left = 0;
        int right = n;

        while(left <= right) {
            // 그룹의 갯수 mid
            int mid = (left + right) / 2;
            int cnt = 0;

            for (int i = 0; i < n; i++) {
                group[i]
            }
        }


        return groupCnt;
    }
}
