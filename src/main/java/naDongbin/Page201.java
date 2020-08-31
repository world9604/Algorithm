package naDongbin;

import idea.BinarySearch2;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class Page201 {
    // 첫째줄에 떡의 갯수 : N, 요청한 떡의 길이 M
    // (1<= N <= 1000000 || 1<= M <= 2000000000)
    // 둘째줄에는 떡의 개별 높이가 주어진다. 떡 높이의 총합은 항상 M 이상
    // 떡의 높이는 10억 보다 작거나 같은 양의 정수, 또는 0
    // 출력 조건 : 적어도 M 만큼의 떡을 집에 가져가기 위해 절단기에 설정할 수있는 높이의 최댓값을 출력
    // 입력 예시 : 4 6
    //           19 15 10 17
    // 출력 예시 : 15
    //////////////////////////////
    // 입력 예시 : 5 10
    //           19 15 10 17 200
    // 출력 예시 : 190
    // 책에서 제시한 파라메트릭 서치 방법으로 다시 구현
    // 나는 이미 결과 배열을 구하고 이진 탐색을 했지만,
    // 책은 이진 탐색 과정 중 요청한 떡의 길이와 비교해 가며
    // 정답을 돌출 하기 때문에,
    // 내가 구현한 소스 코드에서 결과 배열을 만드는 시간 복잡도 만큼 줄일 수 있다.
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        System.out.printf("test N: %d", N);
        System.out.println();
        System.out.printf("test M: %d", M);
        System.out.println();

        int[] riceCakeHeights = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        for (int riceCakeHeight : riceCakeHeights) {
            System.out.printf("test : %d", riceCakeHeight);
            System.out.println();
        }

        int answer = new Page201().solution(N, M, riceCakeHeights);
        System.out.print(answer);
    }

    public int solution(int N, int M, int[] riceCakeHeights){
        // 가장 긴 떡의 길이 ~ 0까지
        // 점점 작아지면서 떡을 절단 했을때의 총합 배열을 만든다.
        // 총합 배열중 손님이 요청한 떡의 길이를 이진 탐색으로 찾고
        // 그 떡의 인덱스 값이 절단기의 길이

        // 가장 긴 떡의 길이 ~ 0까지(큰것부터 썰어야 총합의 길이가 정렬됨)
        int maxRiceCake = getRiceCakeMaximumLength(riceCakeHeights);
        System.out.printf("가장 긴떡 길이 : %d", maxRiceCake);
        System.out.println();

        int[] riceCakesForSale = getRiceCakesForSale(riceCakeHeights,
                maxRiceCake);

        BinarySearch3 binarySearch = new BinarySearch3();
        return binarySearch.search(riceCakesForSale, 0, maxRiceCake-1, M);
    }

    private int getRiceCakeMaximumLength(int[] riceCakeHeights) {
        int riceCakeMaximumLength = 0;

        // 시간 복잡도 : 1000000
        for (int i = 0; i < riceCakeHeights.length; i++) {
            if (riceCakeMaximumLength < riceCakeHeights[i])
                riceCakeMaximumLength = riceCakeHeights[i];
        }
        return riceCakeMaximumLength;
    }

    private int[] getRiceCakesForSale(int[] riceCakeHeights, int maxRiceCake) {
        // 0 ~ 가장 긴 떡 까지의 길이로 자른 떡의 총 길이 합 배열
        int[] riceCakesForSale = new int[maxRiceCake];

        // 시간 복잡도 : 10억
        // 0 ~ 가장 긴 떡 까지 증가하며, 잘린 떡의 길이를 모두 더한다.
        for (int i = 0; i < maxRiceCake; i++) {
            int cuttedHeight = 0;

            // 시간 복잡도 : 1000000
            for (int riceCakeHeight : riceCakeHeights) {
                if (riceCakeHeight - i < 0) continue; //떡의 길이가 절단기 보다 작은 경우
                cuttedHeight = cuttedHeight + (riceCakeHeight - i); //떡의 길이가 절단기 보다 큰 경우
            }

            System.out.printf("%d ", cuttedHeight);
            riceCakesForSale[i] = cuttedHeight;
        }
        System.out.println();
        //Collections.reverse(Arrays.asList(riceCakesForSale));
        return riceCakesForSale;
    }
}

class BinarySearch3{

    public static final int ERROR_NUM = -9999;

    // 시간 복잡도 : O(logN)
    int search(int[] array, int head, int tail, int target){
        while (head <= tail) {
            int mid = (head + tail) / 2;
            if (array[mid] == target)
                return mid;
            else if(array[mid] < target)
                tail = mid - 1;
            else
                head = mid + 1;
        }
        return ERROR_NUM;
    }
}
