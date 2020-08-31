package naDongbin;

public class Page220_2 {
    //식량 창고
    // N (3 <= N <= 100)
    // K (0 <= K <= 1000)
    //입력 예시
    // 4
    // 1 3 1 5
    //출력 예시
    // 8
    public static void main(String[] args) {
        int N = 4;
        int[] array = {1, 3, 1, 5};
        System.out.print(new Page220_2().solution(N, array));
    }

    int solution(int N, int[] array){
        //1. 짝수 열씩의 총합
        //2. 홀수 열씩의 총합
        // 둘 총합 중의 더 큰 값 출력
        // 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
        int[] dp = new int[100];

        // 다이나믹 프로그래밍(DP 보텀업)
        dp[0] = array[0];
        dp[1] = Math.max(array[0], array[1]);
        for (int i = 2; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + array[i]);
        }
        return dp[N - 1];
    }
}
