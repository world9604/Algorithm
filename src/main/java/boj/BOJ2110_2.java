package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110_2 {
    // https://www.acmicpc.net/problem/2110
    // 공유기 설치
    public static void main(String[] args) throws IOException {
        // 집의 개수 N (2 ≤ N ≤ 200,000)
        // 공유기의 개수 C (2 ≤ C ≤ N)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] position = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            position[i] = Integer.parseInt(st.nextToken());
        }

        System.out.print(new BOJ2110_2().solution(N, C, position));
    }

    /*
    1. 집의 범위가 1 ~ 1,000,000,000 크다, 따라서 파라메트릭 서치
    2. 여기서 가장 인접한 공유기의 최대 거리를 구해야 하므로,
    3. 가장 인접한 공유기의 거리의 범위를 구한다.
    4. 집의 좌표를 정렬후,
    5. [1] - [0]가 최소 범위가 된다,
    6. [마지막 인덱스] - [0]이 최대 범위가 된다.
    7. mid = (최소 범위 + 최대 범위) / 2 로 mid 를 구한다.
    8. 여기서 mid가 가장 인접한 공유기의 거리가 된다.
    9. mid 거리로 첫번째 집부터 설치를 한다.
    10. 설치후 제공된 공유기의 갯수 C 보다 작으면
    11. mid 거리를 좁혀 다시 설치(right = mid - 1)
    12. 설치후 제공된 공유기의 갯수가 C 보다 크면
    13. mid 거리를 늘려 다시 설치(left = mid + 1)
    14. 공유기의 갯수 C와 같을 때까지 설치 하는게 아니고
    15. mid의 최소 범위가 최대 범위 보다 크거나 같을때 까지 설치를 진행
    16. 최적의 mid 값 반환
    */
    public int solution(int N, int C, int[] position){
        // 집의 좌표를 정렬후,
        // 시간 복잡도 : nlogN
        Arrays.sort(position);

        // [1] - [0]가 최소 범위가 된다,
        // [마지막 인덱스] - [0]이 최대 범위가 된다.
        int left = position[1] - position[0];
        int right = position[N - 1] - position[0];
        int result = 0;

        // mid의 최소 범위가 최대 범위 보다 크거나 같을때 까지 설치를 진행
        // 총 시간 복잡도 : 200,000 x log(200,000)
        // 시간 복잡도 : log 200,000
        while(left <= right) {
            // mid = (최소 범위 + 최대 범위) / 2 로 mid 를 구한다.
            int mid = (left + right) / 2;
            int cnt = 1;
            int prev = position[0];

            // mid 거리로 첫번째 집부터 설치를 한다.
            // 시간 복잡도 : 200,000
            for (int i = 1; i < N; i++) {
                if (position[i] - prev >= mid) {
                    prev = position[i];
                    cnt++;
                }
            }

            if (cnt >= C) { // mid 거리를 좁혀 다시 설치
                left = mid + 1;
                result = mid;
            } else {        // mid 거리를 늘려 다시 설치
                right = mid - 1;
            }
        }
        return result;
    }
}