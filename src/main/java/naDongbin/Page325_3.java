package naDongbin;


public class Page325_3 {

    // https://programmers.co.kr/learn/courses/30/lessons/60059?language=java
    // 자물쇠와 열쇠
    /*
    * 먼저 2차원 리스트를 90도 회전하는 함수에서 result[j][n - i - 1] = a[i][j]는 배열 a를 시계 방향으로 90도 회전시킨 결과를 result에 담도록 만들어 줍니다.
    * 더 자세히 설명 드리자면, 원래 배열의 열 정보에 해당하는 j가 결과 배열에서는 행으로 들어가도록 만듭니다. 또한 원래 배열의 행 정보에 해당하는 i가 결과 배열에서는 열에 해당해야 합니다. 단, 순서가 반전되어야 하기 때문에 n - i - 1을 넣습니다.
    * 이 함수는 직접 종이에 2차원 배열을 그려 보신 뒤에, 종이를 시계 방향으로 돌려 보시면 빠르게 이해하실 수 있을 거라고 생각합니다.
    * 이어서 반복문에서 n * 2가 사용된 이유에 대해서 말씀드리겠습니다.
    * 일단 우리는 크기가 3배로 변환된 자물쇠에 대하여 왼쪽 위부터 오른쪽 아래까지의 모든 위치에 대하여 차례대로 열쇠를 끼워 넣어야 합니다. 반복문에서 x와 y가 0부터 n * 2 - 1까지 증가하도록 만들면 가능한 모든 경우의 수를 고려할 수 있습니다.
    * 자물쇠에 열쇠를 끼워 넣고자 하는 상황에서, [x][y]는 열쇠의 가장 왼쪽 위 위치를 의미하고, [x + i][y + j]는 열쇠의 가장 오른쪽 아래 위치를 의미한다고 보시면 됩니다. 그렇기 때문에 열쇠가 맵을 벗어나지 않도록 보장하기 위해(인덱스가 벗어나지 않도록 하기 위해) n * 3이 아닌 n * 2가 들어가야 합니다.
    * 감사합니다.
    * 나동빈 드림
    * */
    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.print(new Page325_3().solution(key, lock));
    }

    public boolean solution(int[][] key, int[][] lock){
        // 4방향의 회전을 통해서 확인
        for (int rotate = 0; rotate < 4; rotate++) {
            key = rotateMatrixBy90Degree(key);
            if(isOpen(key, lock)) return true;
        }
        return false;
    }

    public boolean isOpen(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;

        // 자물쇠의 3배 크기의 2차원 배열을 만듬
        int[][] newLock = new int[n * 3][n * 3];

        // 2차원 배열 중앙에 lock 배열을 넣음
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newLock[i + n][j + n] = lock[i][j];
            }
        }

        // 자물쇠 완전 탐색
        for (int x = 0; x < n * 2; x++) {
            for (int y = 0; y < n * 2; y++) {
                // 자물쇠의 키 삽입
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        newLock[x + i][y + j] += key[i][j];
                    }
                }

                if (check(newLock)) return true;

                // 자물쇠의 키 해제
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        newLock[x + i][y + j] -= key[i][j];
                    }
                }
            }
        }
        return false;
    }

    // 자물쇠의 중간 부분이 모두 1인지 확인
    public boolean check(int[][] newLock){
        int n = newLock.length / 3;
        for (int i = n; i < n * 2; i++) {
            for (int j = n; j < n * 2; j++) {
                if (newLock[i][j] != 1)
                    return false;
            }
        }
        return true;
    }

    // 2차원 리스트 90도 회전하기
    public int[][] rotateMatrixBy90Degree(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        int[][] newArr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newArr[j][n - 1 - i] = a[i][j];
            }
        }
        return newArr;
    }
}
