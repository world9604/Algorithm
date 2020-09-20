package Woahan;

public class Task2020_1 {
    public static void main(String[] args) {
        int U = 3, L = 2;
        int[] C = new int[]{2, 1, 1, 0, 1};
        System.out.print(new Task2020_1().solution(U, L, C));
    }
    /*
    1. C[i] == 0, C[i] == 2인 경우
    1. result 의 채워 넣음,
    2. 채워진 result[0][i]의 값을 U에서 뻄
    3. 채워진 result[1][i]의 값을 L에서 뻄
    4. 채워지지 않은 result[0][i] = 1을 대입해서 U-k를 만듬
    5. 채워지지 않은 result[1][i] = 0을 대입해서 L-i를 만듬
    6. result[][] 하나의 문자열로 만들어 반환
    * */
    final String ERR = "IMPOSSIBLE";

    public String solution(int U, int L, int[] C) {
        // 1. C[i] == 0, C[i] == 2인 경우, result 의 채워 넣음,
        int N = C.length;
        int[][] result = new int[2][N];

        // 시간 복잡도 100,000
        for (int i = 0; i < N; i++) {
            int sum = C[i];

            if (sum == 0) {
                result[0][i] = 0;
                result[1][i] = 0;
            }

            if(sum == 2) {
                result[0][i] = 1;
                result[1][i] = 1;
                // 결정된 원소 값 지움
                U--;
                L--;
            }

            if (sum == 1) {
                result[0][i] = -1;
                result[1][i] = -1;
            }
        }

        // 2. 채워진 result[0][i]의 값을 U에서 뻄
        // 3. 채워진 result[1][i]의 값을 L에서 뻄
        // 시간 복잡도 100,000
        for (int i = 0; i < N; i++) {
            // 값이 결정되지 않은 경우
            if (U > 0 && result[0][i] == -1) {
                // 4. 채워지지 않은 result[0][i] = 1을 대입해서 U-k를 만듬
                // 5. 채워지지 않은 result[1][i] = 0을 대입해서 L-i를 만듬
                result[0][i] = 1;
                result[1][i] = 0;
                U--;
            } else if (L > 0 && result[1][i] == -1) {
                result[0][i] = 0;
                result[1][i] = 1;
                L--;
            } else if(U == 0 && L == 0) {
                break;
            } else if (result[0][i] == -1) {
                return ERR;
            }
        }

        if(U > 0 || L > 0)
            return ERR;

        // 시간 복잡도 100,000
        StringBuilder zeroRowSb = new StringBuilder();
        StringBuilder oneRowSb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            zeroRowSb.append(result[0][i]);
            oneRowSb.append(result[1][i]);
        }

        // 6. result[][] 하나의 문자열로 만들어 반환
        return zeroRowSb.append(",").append(oneRowSb).toString();
    }
}
