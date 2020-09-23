package idea;

import java.util.Arrays;

public class Eratosthenes {
    // 에라토네스의 체 (주어진 범위 내에서 소수 판별)
    public static void main(String[] args) {
        int n = 17;
        boolean[] array = new boolean[n + 1];
        Arrays.fill(array, true);

        Eratosthenes eratos = new Eratosthenes();
        boolean[] answers = eratos.isPrimeNumber(array);

        for (int i = 2; i < answers.length; i++) {
            if (answers[i]) {
                System.out.print(i);
                System.out.println();
            }
        }
    }

    public boolean[] isPrimeNumber(boolean[] array) {
        int n = array.length;
        int sqrtNum = (int) Math.sqrt(n);

        // 약수의 대칭 성질을 이용해서
        // 제곱근 까지만 탐색
        for (int i = 2; i <= sqrtNum; i++) {
            // 소수인 경우
            if (array[i]) {
                // i를 제외한 i의 모든 배수 제거
                for (int j = 2; i * j < n; j++) {
                    array[i * j] = false;
                }
            }
        }
        return array;
    }
}
