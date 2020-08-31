package naDongbin;

import java.io.IOException;
import java.util.Scanner;

public class Page201_2 {
    // 첫째줄에 떡의 갯수 : N, 요청한 떡의 길이 M
    // (1<= N <= 1000000 || 1<= M <= 2000000000)
    // 둘째줄에는 떡의 개별 높이가 주어진다. 떡 높이의 총합은 항상 M 이상
    // 떡의 높이는 10억 보다 작거나 같은 양의 정수, 또는 0
    // 출력 조건 : 적어도 M 만큼의 떡을 집에 가져가기 위해 절단기에 설정할 수있는 높이의 최댓값을 출력
    // 입력 예시 : 4 6
    //           19 15 10 17
    // 출력 예시 : 15
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // 떡의 개수(N)와 요청한 떡의 길이(M)
        int N = sc.nextInt();
        int M = sc.nextInt();

        // 각 떡의 개별 높이 정보
        int[] riceCakeHeights = new int[N];
        for (int i = 0; i < N; i++) {
            riceCakeHeights[i] = sc.nextInt();
        }

        int answer = new Page201_2().solution(M, riceCakeHeights);
        System.out.print(answer);
    }

    public int solution(int M, int[] riceCakeHeights){
        // 이진 탐색 안에서 잘린 떡의 길이를 구하고
        // 요청한 떡의 길이와 맞는지 크기 비교
        // head = 0, tail = 10억(떡의 높이 == 절단기 최대 길이)
        // 요청한 떡의 길이가 더 크다면 head = mid + 1
        // 요청한 떡의 길이가 더 작다면 tail = mid - 1
        int head = 0;
        int tail = (int)1e9;
        int target = M;
        int answer = 0;

        // 시간 복잡도 : O(logN) => 30 (N = 1e9)
        while (head <= tail) {
            // mid 는 이진 탐색에서 중앙값이자, 절단기의 길이
            int mid = (head + tail) / 2;

            // 시간 복잡도 : 1000000
            int riceCakeLenForSale = getRiceCakeLenForSale(riceCakeHeights, mid);

            if(riceCakeLenForSale < target){ // 팔기 위해 자른 떡의 길이가 모자르면, 못팜
                tail = mid - 1;
            }else{
                head = mid + 1;
                answer = mid; // 팔기 위해 자른 떡의 길이가 충분하면, 팔수 있음
            }
        }
        return answer;
    }

    private int getRiceCakeLenForSale(int[] riceCakeHeights, int lengthForCutOff) {
        int cuttedHeight = 0;

        // 시간 복잡도 : 1000000
        for (int riceCakeHeight : riceCakeHeights) {
            if (riceCakeHeight - lengthForCutOff > 0) //떡의 길이가 절단기 보다 큰 경우
                cuttedHeight = cuttedHeight + (riceCakeHeight - lengthForCutOff);
        }
        return cuttedHeight;
    }
}