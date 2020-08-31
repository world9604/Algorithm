package naDongbin;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;

public class Page96 {
    //입력 조건
    //첫째줄에 행 : N, 열 : M
    //(1 <= N, M <= 100)
    //각 숫자 카드 N*M 이 주어진다
    //각 숫자는 1<= x <= 10000 값
    // 입력
    // 3 3
    // 3 1 2
    // 4 1 4
    // 2 2 2
    // 출력
    // 2
    /////////
    // 입력
    // 2 4
    // 7 3 1 8
    // 3 3 3 4
    // 출력
    // 3
    public static void main(String[] args) {
        int N = 3, M = 3;
        int[][] array = {
                {3, 1, 2},
                {4, 1, 4},
                {2, 2, 2}
        };
        /*int[][] array = {
                {7, 3, 1, 8},
                {3, 3, 3, 4}
        };*/
        System.out.print(new Page96().solution(N, M, array));
    }

    int solution(int N, int M, int[][] array){
        int maxValue = 0;

        // 시간 복잡도 : 100
        for (int[] row : array) {
            // 행에서 가장 작은 수를 찾는다.
            OptionalInt minValue = Arrays.stream(row).min();
            // 최소값
            maxValue = Math.max(maxValue, minValue.getAsInt());
        }

        return maxValue;
    }
}
