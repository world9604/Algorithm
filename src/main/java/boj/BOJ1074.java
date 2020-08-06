package boj;

import java.util.Scanner;

public class BOJ1074 {
    /**
     * @문제 acmicpc.net/problem/1074
     * @참고 https://jaimemin.tistory.com/718
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        new SplitConquester(r, c).solve((int) Math.pow(2, N), 0, 0);
    }
}

class SplitConquester {
    int R, C, result;

    SplitConquester(int R, int C) {
        this.R = R;
        this.C = C;
        this.result = 0;
    }

    void solve(int n, int r, int c) {
        // 크기가 제일 작은 단위인 2 * 2
        if (n == 2) {
            if (r == R && c == C){
                System.out.printf("%d", result);
                return;
            }
            result++;

            if (r == R && c + 1 == C){
                System.out.printf("%d", result);
                return;
            }
            result++;

            if (r + 1 == R && c == C){
                System.out.printf("%d", result);
                return;
            }
            result++;

            if (r + 1 == R && c + 1 == C){
                System.out.printf("%d", result);
                return;
            }
            result++;
            return;
        }

        // 원본을 4 등분 한다. 따라서 n = n / 2 가 된다.
        // 1 사분면
        solve(n / 2, r, c);
        // 2 사분면
        solve(n / 2, r, c + n / 2);
        // 3 사분면
        solve(n / 2, r + n / 2, c);
        // 4 사분면
        solve(n / 2, r + n / 2, c + n / 2);
    }
}
