package naDongbin;


public class Page325_3 {

    // https://programmers.co.kr/learn/courses/30/lessons/60059?language=java
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
