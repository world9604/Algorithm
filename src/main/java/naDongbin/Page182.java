package naDongbin;

import java.util.Arrays;

public class Page182 {
    //입력 예시
    //5 3
    //1 2 5 4 3
    //5 5 6 6 5
    //출력 예시
    //26
    // 1 <= N <= 1000000
    // 0 <= K <= N
    // 원소 <= 10000000
    public static void main(String[] args) {
        int N = 5, K = 3;
        int[] A = {1, 2, 5, 4, 3};
        int[] B = {5, 5, 6, 6, 5};
        System.out.print(new Page182().solution(N, K, A, B));
    }

    int solution(int N, int K, int[] A, int[] B){
        // A에서 가장 작은 값 뽑음
        // B에서 가장 큰 값 뽑음
        // 두 값 바꿈
        // K번 반복
        // A의 원소 총합 반환
        final int LAST_INDEX = N - 1;
        final int FIRST_INDEX = 0;

        // 총시간 복잡도 : 2000000*(1000000 log(1000000))
        // 시간 복잡도 : 1000000
        for (int i = 0; i < K; i++) {
            // 시간 복잡도 : O(1000000 log(1000000))
            Arrays.sort(A);
            // 시간 복잡도 : O(1000000 log(1000000))
            Arrays.sort(B);

            // A의 가장 작은 값
            int smallestWithA = A[FIRST_INDEX];
            // B의 가장 큰 값
            int biggestWithB = B[LAST_INDEX];

            if (smallestWithA > biggestWithB)
                continue;

            // 바꿔치기
            B[LAST_INDEX] = smallestWithA;
            A[FIRST_INDEX] = biggestWithB;
        }
        return Arrays.stream(A).sum();
    }
}
