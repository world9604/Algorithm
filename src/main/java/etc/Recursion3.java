package etc;

public class Recursion3 {
    /**
     * @제목 Designing Recursion
     *
     * Recursion (순환)의 조건
     * 1. 적어도 하나의 base case, 즉 순환되지 않고 종료되는 case 가 있어야함
     * 2. 모든 case 는 결국 base case 로 수렴해야 함
     *
     * ** 암시적 매개변수를 명시적 매개변수로 바꾸어라 **
     */
    public static void main(String[] args) {

    }

    // 순차 탐색1
    public static int search(int[] data, int n, int target) {
        for (int i = 0; i < n; i++) {
            if (data[i] == target)
                return i;
        }
        return -1;
    }

    // 매개변수의 명시화 : 순차 탐색
    // begin 에서 end 까지의 구간을 탐색 한다.
    // 검색구간을 명시화 한다.
    public static int search(int[] data, int begin, int end, int target) {
        if (begin > end)
            return -1;
        else if (target == data[begin])
            return begin;
        else
            return search(data, begin + 1, end, target);
    }

    // 순차 탐색2
    // 뒤에서 부터 탐색 한다.
    // 검색 구간을 명시화 한다.
    public static int search2(int[] data, int begin, int end, int target) {
        if (begin > end)
            return -1;
        else if (target == data[end])
            return end;
        else
            return search2(data, begin, end - 1, target);
    }

    // 순차 탐색3
    // 정렬된 조건이 없으므로 이진 탐색 아님
    public static int search3(int[] data, int begin, int end, int target) {
        if (begin > end) {
          return -1;
        } else {
            int middle = (begin + end) / 2;

            if (data[middle] == target)
                return middle;

            int index = search3(data, begin, middle - 1, target);

            if (index != -1)
                return index;
            else
                return search(data, middle + 1, end, target);
        }
    }

    // 최대값 찾기
    // 매개변수 명시화
    // begin <= end 라고 가정 한다.
    public static int findMax(int[] data, int begin, int end) {
        if (begin == end)
            return data[begin];
        else
            return Math.max(data[begin], findMax(data, begin + 1, end));
    }

    // 이진 탐색
    public static int binarySearch(String[] items, String target, int begin, int end) {
        if (begin > end) {
            return -1;
        } else {
            int middle = (begin + end) / 2;
            int compResult = target.compareTo(items[middle]);
            if (compResult == 0) {
                return middle;
            } else if (compResult < 0) {
                return binarySearch(items, target, begin, middle - 1);
            } else {
                return binarySearch(items, target, middle + 1, end);
            }
        }
    }
}
