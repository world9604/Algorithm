package boj;

import java.util.Scanner;
import java.util.stream.Stream;

public class BOJ1978 {
    /**
     * @문제 https://www.acmicpc.net/problem/1978
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        int[] values = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int result = new Eratosthenes().solve(values);
        System.out.printf("%d", result);
    }
}

class Eratosthenes {
    int solve(int[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == 0) continue;
            if (values[i] == 1) {
                values[i] = 0;
                continue;
            }
            for (int k = values[i] + values[i]; k < values.length; k += values[i]) {
                values[k] = 0;
            }
        }

        int count = 0;
        for (int value : values) {
            if (value != 0) count++;
        }
        return count;
    }
}