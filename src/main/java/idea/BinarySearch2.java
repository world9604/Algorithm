package idea;

public class BinarySearch2 {

    public static final int ERROR_NUM = -999999;

    /*
     * 이진탐색 구현
     * 전체 배열이 주어지고
     * 타겟 값이 주어졌을때
     * 데이터는 정렬되어 있다고 가정
     */
    // 재귀함수를 이용한 이진 탐색
    public int searchAsRecursion(int[] array, int target, int head, int tail) {
        // 시작 인덱스가 끝 인덱스 보다 크면 마이너스 값 리턴
        if (head > tail) return ERROR_NUM;

        // 중간값을 구함
        int mid = (head + tail) / 2;

        if (target == array[mid])
            return mid;
        else if (target < array[mid])
            return searchAsRecursion(array, target, head, mid - 1);
        else
            return searchAsRecursion(array, target, mid + 1, tail);
    }

    // 반복문을 이용한 이진 탐색
    public int searchAsIteration(int[] array, int target, int head, int tail) {
        while(head <= tail){
            int mid = (head + tail) / 2;
            if (target == array[mid])
                return mid;
            else if(target < array[mid])
                tail = mid - 1;
            else
                head = mid + 1;
        }
        return ERROR_NUM;
    }
}