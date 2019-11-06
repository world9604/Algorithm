package etc;

public class Nqueens {
    /**
     * Nqueens 문제
     * N * N 체스판에서
     * N개의 퀸을 어디에 넣을 수 있는지?
     *
     * sudo 코드
     * int[] cols = new int[N + 1];
     * return-type queens( level ) {
     *     if non-promising (infeasible 한 경우)
     *          report failure and return;
     *     else if success
     *          report answer and return;
     *     else
     *          visit children recursively;
     * }
     *
     * sudo 코드
     * 매개변수 level 은 현재 노드의 행을 표현하고,
     * cols[i] = j, i번 말이 (i행, j열)에 놓였음을 의미한다.
     */
    public static void main(String[] args) {
        queens(0);
    }

    static int N = 8;
    static int[] cols = new int[N + 1];

    // tracking 가능한 경우
    static boolean promising(int level) {
        for (int i = 1; i < level; i++) {
            if (cols[level - i] == cols[level]) { // 같은 열인지 검사
                return false;
            } else if(level - i == Math.abs(cols[level] - cols[i])) { // 대각선인지 검사
                return false;
            }
        }
        return true;
    }

    static boolean queens(int level) {
        if (!promising(level)) { // tracking 이 가능 하지 않은 경우
            return false;
        } else if (level == N) {
            for (int i = 1; i <= N; i++) {
                System.out.printf("(%d, %d)\n", i, cols[i]);
            }
            return true;
        } else {
            for (int i = 1; i <= N; i++) {
                cols[level + 1] = i;
                if (queens(level + 1))
                    return true;
            }
            return false;
        }
    }

}
