package naDongbin;

public class Page368 {
    // 고정점 찾기
    public static void main(String[] args) {
        /*
        * 고정점(Fixed Point) 란
        * 수열의 원소 중에서 그 값이 인덱스와 동일한 원소를 의미 합니다.
        * 예를 들어 a = {-15, -4, 2 ,8, 13} 가 있을때 a[2] = 2 이므로
        * 고정점은 2가 됩니다.
        * N개의 서로 다른 원소를 포함하고 있으며, 모든 원소가 오름차순 정렬
        * 이때 수열에서 고정점을 출력 하시오. 만약 없으면 -1 출력
        * 시간 복잡도 O(log N) 로 설계 하지 않으면 '시간 초과' 판정을 받습니다.
        * 1 <= N <= 1,000,000
        * -1e9 <= 각 원소 <= 1e9
        * */
//        int N = 5;
//        int N = 7;
        int N = 7;
//        int[] array = new int[]{-15, -6, 1, 3, 7};
//        int[] array = new int[]{-15, -4, 2, 8, 9, 13, 15};
        int[] array = new int[]{-15, -4, 3, 8, 9, 13, 15};
        System.out.print(new Page368().solution(N, array));
    }

    public int solution(int n, int[] array) {
        int result = -1;
        int left = 0;
        int right = n - 1;

        while(left <= right) {
            int mid = (left + right) / 2;

            if (array[mid] > mid) {
                // array[mid] 이상은 찾을수 없다
                right = mid - 1;
            } else if (array[mid] < mid) {
                // array[mid] 이하는 찾을수 없다
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return result;
    }
}
