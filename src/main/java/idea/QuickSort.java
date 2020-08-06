package idea;

public class QuickSort {
    public static void main(String[] args) {

    }

    /*quickSort(A[], p, r) { // A[p....r]을 정렬한다.
        if (p < r) then {
            q = partition(A, p, r); // 분할
            quickSort(A, p, q-1);   // 왼쪽 부분배열 정렬
            quickSort(A, q+1, r);   // 오른쪽 부분배열 정렬
        }
    }*/

    /*partition(A[], p, r) {
        배열 A[p....r]의 원소들을 A[r]을 기준으로 양쪽으로 재배치하고
        A[r]이 자리한 위치를 return 한다;
    }*/

    /*int partition(A[], p, r) {
        int i = 0, j = 0;

        while(j < r) {
            if (A[j] >= x) {
                j = j +1;
            } else {
                i = i + 1;
                exchange A[i] and A[j];
                j = j + 1;
            }
        }

        int tmp = A[i+1];
        A[i+1] = A[r];
        A[r] = tmp;

        return i;
    }*/
}
