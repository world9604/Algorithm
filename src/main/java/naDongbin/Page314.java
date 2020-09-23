package naDongbin;

import java.util.Arrays;

public class Page314 {
    // 만들 수 없는 금액
    public static void main(String[] args) {
        int N = 5;
        int[] moneys = new int[]{3, 2, 1, 1, 9};
        System.out.print(new Page314().solution(N, moneys));
    }

    /*
    * 동네 편의점의 주인인 동빈이는 N개의 동전을 가지고 있습니다.
    * 이때 N개의 동전을 이용하여 만들수 없는 양의 정수 금액 중 최솟값을 구하는 프로그램을 작성 하시오
    * 예를 들어 N = 5이고, 각 동전이 각각 3원, 2원, 1원, 1원 9원짜리(화폐 단위) 동전이라고 가정 합니다.
    * 이때 동빈이가 만들 수 없는 양의 정수 금액 중 최솟값은 8원 입니다.
    * 또 다른 예시로, N = 3이고, 각 동전이 각각 3원, 5원, 7원 짜리 동전이라고 가정 합시다.
    * 이때 동빈이가 만들 수 없는 양의 정수 금액 중 최솟값은 1원 입니다.
    * 첫째 줄에는 동전의 개수를 나타내는 양의 정수 N이 주어집니다. (1 <= N <= 1000)
    * 둘째 줄에는 각 동전의 화폐 단위를 나타내는 N개의 자연수가 주어짐. 각 화폐 단위는 1,000,000 이하의 자연수
    * 출력 조건 : 주어진 동전들로 만들 수 없는 양의 정수 금액 중 최솟값을 출력 합니다.
    * */
    public int solution(int N, int[] moneys) {
        // 정렬 후
        // moneys[0] 이하의 자연수가 있으면, 반환
        // moneys[0 ~ N-1] 의 값 중 앞에서 부터 더해서 만들수 없는 금액이 있으면, 반환
        // 그렇지 않으면, moneys[N-1] + 1 반환
        Arrays.sort(moneys);

        int target = 1;
        for (int i = 0; i < N; i++) {
            // 만들 수 없는 금액을 찾았을 때 반복 종료
            if (target < moneys[i]) break;
            target += moneys[i];
        }
        return target;
    }
}
