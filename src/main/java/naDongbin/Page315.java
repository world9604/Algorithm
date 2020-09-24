package naDongbin;


public class Page315 {
    //볼링공 고르기
    // N개의 볼링고이 주어지고
    // M까지의 볼링공 무게가 존재 할때
    public static void main(String[] args) {
        int N = 5;
        int M = 3;
        int[] weights = new int[]{1, 3, 2, 3, 2};
        System.out.print(new Page315().solution(N, M, weights));
    }

    public int solution(int N, int M, int[] weights) {
        // 볼링공의 최대 무게는 10 입니다.
        int[] total = new int[11];
        int result = 0;

        // 각 무게에 해당하는 볼링공의 개수 카운트
        for (int i = 0; i < N; i++) {
            total[weights[i]] += 1;
        }
        // Arrays.stream(weights).forEach(weight -> total[weight] += 1);

        // 1부터 M 까지의 각 무게에 대해서 처리
        for (int i = 1; i < M + 1; i++) {
            N -= total[i];
            result += total[i] * N;
        }

        return result;
    }
}
