package boj;

import java.util.Scanner;

public class BOJ4811 {
    /**
     * @문제 https://www.acmicpc.net/problem/4811
     * @입력
     * 6
     * 1
     * 4
     * 2
     * 3
     * 30
     * 0
     * @출력
     * 132
     * 1
     * 14
     * 2
     * 5
     * 3814986502092304
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int num = Integer.parseInt(sc.nextLine());
            if (num == 0) break;

            System.out.printf("%d", dfs(num, 0));
            System.out.println();
        }
    }

    static long[][] dp = new long[31][31];

    static long dfs(int W, int H){
        if (dp[W][H] > 0)
            return dp[W][H];
        if (W == 0 && H == 0)
            return 1;
        if (W > 0)
            dp[W][H] += dfs(W - 1, H + 1);
        if (H > 0)
            dp[W][H] += dfs(W, H - 1);

        return dp[W][H];
    }
}
