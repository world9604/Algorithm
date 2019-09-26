package boj;

import java.util.Scanner;

public class BOJ1305 {
    /**
     * @문제 https://www.acmicpc.net/problem/1305
     * aabaaa -> aa는 prefix, suffix가 같은 최대 길이 패턴
     * aabaaa 에서 앞에 aa를 빼주면 baaa 가 원래 광고 문구
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt(); sc.nextLine();
        String string = sc.nextLine();

        int result = new Solution().solve(length, string);
        System.out.printf("%d", result);
    }
}

class Solution {
    int solve(int length, String string) {
        int[] table = getKmpTable(length, string.toCharArray());

        // 전체 전광판 길이 - 최대일치 길이 = 최소 길이의 원래 광고 문구
        return length - table[length - 1];
    }

    private int[] getKmpTable(int length, char[] strings) {
        int k = 0;
        int[] result = new int[length];

        for (int i = 1; i < length; i++) {
            while (k > 0 && strings[i] != strings[k])
                k = result[k - 1];
            if (strings[k] == strings[i])
                result[i] = ++k;
        }

        return result;
    }
}