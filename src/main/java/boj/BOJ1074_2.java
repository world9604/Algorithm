package boj;

import java.util.Scanner;

public class BOJ1074_2 {
    static int r, c;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int MAX = (int) Math.pow(2, N);
        dp = new int[MAX + 1][MAX + 1];
        r = sc.nextInt();
        c = sc.nextInt();
        System.out.printf("%d", dfs(0, 0));
    }

    static int dfs(int row, int col) {
        dp[row][col]++;

        if (row == r && col == c) {
            return dp[row][col];
        } else if (row % 2 == 0 && col % 2 == 0) {
            return dfs(row, col + 1);
        } else if (row % 2 == 0 && col % 2 == 1) {
            return dfs(row + 1, col - 1);
        } else if (row % 2 == 1 && col % 2 == 0) {
            return dfs(row, col + 1);
        } else {
            return dfs(row - 1, col + 1);
        }


    }
}
