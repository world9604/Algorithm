package boj;

import java.util.Scanner;

public class BOJ1849 {
    /**
     * @문제 https://www.acmicpc.net/problem/1849
     *
     * 5,      0,      1,      2,      1,      2,      0,      0
     * arr[5], arr[0], arr[2], arr[4], arr[3], arr[7], arr[1], arr[6]
     * --> 0부터 순서대로 정렬
     * arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7]
     * 2, 7, 3, 5, 4, 1, 8, 6
     *
     *
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
//        new Solution().sovle();
    }


    static class Solution {

    }
}

