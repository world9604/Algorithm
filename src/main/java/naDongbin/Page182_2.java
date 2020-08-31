package naDongbin;

import java.util.Arrays;

public class Page182_2 {
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
        System.out.print(new Page182_2().solution(N, K, A, B));
    }

    int solution(int N, int K, int[] A, int[] B){
        // A에서 가장 작은 값 뽑음
        // B에서 가장 큰 값 뽑음
        // 두 값 바꿈
        // K번 반복
        // A의 원소 총합 반환
        int lastIndex = N - 1;
        int firstIndex = 0;

        // 시간 복잡도 : O(1000000 log(1000000))
        Arrays.sort(A);
        // 시간 복잡도 : O(1000000 log(1000000))
        Arrays.sort(B);

        // 시간 복잡도 : 1000000
        for (int i = 0; i < K; i++) {
            // A의 가장 작은 값
            int smallestWithA = A[firstIndex];
            // B의 가장 큰 값
            int biggestWithB = B[lastIndex];

            if (smallestWithA < biggestWithB) break;

            // 바꿔치기
            B[lastIndex] = smallestWithA;
            A[firstIndex] = biggestWithB;

            firstIndex = firstIndex + 1;
            lastIndex = lastIndex - 1;
        }
        return Arrays.stream(A).sum();
    }
}
