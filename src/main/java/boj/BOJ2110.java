package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {
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

        System.out.print(new BOJ2110().solution(N, C, position));
    }

    public int solution(int N, int C, int[] position){
        // 1.정렬
        // 시간 복잡도 O(N log N)
        Arrays.sort(position);
        this.houses = new House[N];

        // 2. House[] 인스턴스화
        // 시간 복잡도 O(N)
        int cnt = 0;
        for (int pos : position) {
            this.houses[cnt] = new House(cnt, pos);
            cnt++;
        }

        // 처음에 첫, 끝 2개를 설치 하고 시작 함
        C = C - 2;
        return getShortestDisAfterInstallRouter(0, N - 1, C);
    }

    House[] houses;

    public int getShortestDisAfterInstallRouter(int start, int end, int routerCnt) {
        // 재귀 탈출 조건 (공유기 갯수가 0이면 탈출)
        if (routerCnt == 0)
            return Math.abs(houses[end].position - houses[start].position);

        // 재귀 탈출 조건2 (끝 인덱스 < 첫번째 인덱스)
        if (start > end)
            return Integer.MAX_VALUE;

        // 첫번째 인덱스 설치, 끝 인덱스 설치, 중간 인덱스 설치
        int mid = (start + end) / 2;

        // 설치시 공유기 갯수 -1
        routerCnt--;

        return Math.min(getShortestDisAfterInstallRouter(start, mid, routerCnt),
                getShortestDisAfterInstallRouter(mid, end, routerCnt));
    }

    class House {
        // 인덱스, 집 위치값 을 담는 house 자료구조 만듬
        int id;
        int position;

        public House(int id, int position) {
            this.id = id;
            this.position = position;
        }
    }
}