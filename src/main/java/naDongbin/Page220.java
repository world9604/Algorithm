package naDongbin;

public class Page220 {
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
        System.out.print(new Page220().solution(N, array));
    }

    int solution(int N, int[] array){
        //1. 짝수 열씩의 총합
        //2. 홀수 열씩의 총합
        // 둘 총합 중의 더 큰 값 출력
        int evenSum = 0;
        int oddSum = 0;

        for (int i = 0; i < N; i++) {
            // 짝수 인 경우
            if(i % 2 == 0)
                evenSum = evenSum + array[i];
            // 홀수 인 경우
            if(i % 2 == 1)
                oddSum = oddSum + array[i];
        }

        if(evenSum < oddSum)
            return oddSum;
        else
            return evenSum;
    }
}
