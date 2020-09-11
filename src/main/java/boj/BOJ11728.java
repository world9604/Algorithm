package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ11728 {
    //https://www.acmicpc.net/problem/11728
    // 입력
    //2 2
    //3 5
    //2 9
    // 출력
    //2 3 5 9

    // 입력
    //2 1
    //4 7
    //1
    // 출력
    //1 4 7

    // 입력
    //4 3
    //2 3 5 9
    //1 4 7
    // 출력
    //1 2 3 4 5 7 9
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1 <= N,M <= 1000000
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();

        int[] A = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int[] B = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        BOJ11728 main = new BOJ11728();
        main.solution(N, M, A, B);

        for (int i = 0; i < main.sorted.length; i++) {
            System.out.printf("%d ", main.sorted[i]);
        }
    }

    public int[] solution(int N, int M, int[] A, int[] B) {
        this.A = A;
        this.B = B;
        this.sorted = new int[N + M];

        int[] a = new int[N + M];
        for (int i = 0; i < a.length; i++) {
            if (i <= N - 1) {
                a[i] = A[i];
            } else {
                a[i] = B[i - N];
            }
        }
        mergeSort(a,0, N + M - 1);
        return sorted;
    }

    int[] A;
    int[] B;
    // 최종 배열 sorted
    int[] sorted;

    private void mergeSort(int[] a, int startIdx, int endIdx) {
        if (startIdx < endIdx) {
            int midIdx = (startIdx + endIdx) / 2;
            mergeSort(a, startIdx, midIdx);
            mergeSort(a, midIdx + 1, endIdx);
            merge(a, startIdx, midIdx, endIdx);
        }
    }

    private void merge(int[] a, int startIdx, int midIdx, int endIdx) {
        int i = startIdx;
        int j = midIdx + 1;
        int k = startIdx;
        // A, B가 각각 정렬 되어 있음
        while(i <= midIdx && j <= endIdx) {
            if (a[i] < a[j]) {
                sorted[k] = a[i];
                i++;
            } else {
                sorted[k] = a[j];
                j++;
            }
            k++;
        }

        // 앞배열(i 인덱스)이 다 담겼다면, 뒷배열(j 인덱스) 나머지 넣어주기
        if (i > midIdx) {
            for (int m = j; m <= endIdx; m++) {
                sorted[k] = a[m];
                k++;
            }
        } else {
        // 뒷배열(j 인덱스)이 다 담겼다면, 앞배열(i 인덱스) 나머지 넣어주기
            for (int m = i; m <= midIdx; m++) {
                sorted[k] = a[m];
                k++;
            }
        }

        for (int n = startIdx; n < endIdx; n++) {
            a[n] = sorted[n];
        }
    }
}