package naDongbin;

import java.util.Arrays;

public class Page360 {
    //입력 예시
    //4       (집의 개수)(1 <= N <= 200000)
    //5 1 7 9 (집의 위치)(1 <= M <= 100000)
    //출력 예시
    //5       (안테나 부터 집까지의 합이 최소가 되는 안테나 설치 위치)
    public static void main(String[] args) {
        int N = 4;
        int[] arr = {5, 1, 7, 9};
        System.out.print(new Page360().solution(N, arr));
    }

    int solution(int N, int[] arr){
        //(배열의 갯수 / 2) 의 인덱스의 해당하는 집 위치 반환
        Arrays.sort(arr);
        return arr[(N-1)/2];
    }
}
