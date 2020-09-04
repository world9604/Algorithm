package naDongbin;


public class Page325 {

    // https://programmers.co.kr/learn/courses/30/lessons/60059?language=java
    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.print(new Page325().solution(key, lock));
    }

    public boolean solution(int[][] key, int[][] lock){
        // 자물쇠의 3배 크기의 2차원 배열을 만듬
        int n = lock.length;
        int[][] newLock = new int[n*3][n*3];

        // 2차원 배열 중앙에 lock 배열을 넣음
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newLock[i + n][j + n] = lock[i][j];
            }
        }

        // 4방향의 회전을 통해서 확인
        for (int rotation = 0; rotation < 4; rotation++) {
            key = rotateMatrixBy90Degree(key); // 열쇠 회전
            // key 배열이 array 배열을 1칸씩 이동하며
            // lock 위치의 합이 모두 lock.length * lock.length 인지 확인
            for (int i = 0; i < n * 2; i++) {
                for (int j = 0; j < n * 2; j++) {
                    // 자물쇠에 열쇠 끼워 넣기
                    for (int k = 0; k < key.length; k++) {
                        for (int m = 0; m < key[k].length; m++) {
                            newLock[i+k][j+m] += key[k][m];
                        }
                    }
                    // 새로운 자물쇠 열쇠가 정확히 들어가는지 검사
                    // newLock[][]의 중앙 n*n 부분의 총합이 n*n이 되는지 검사
                    if (check(newLock)) return true;

                    // 자물쇠에서 열쇠를 다시 빼기
                    // 2차원 배열 중앙에 lock 배열을 넣음
                    for (int x = 0; x < key.length; x++) {
                        for (int y = 0; y < key[x].length; y++) {
                            newLock[i + x][j + y] -= key[x][y];
                        }
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
                if (newLock[i][j] != 1) return false;
            }
        }
        return true;
    }

    // 2차원 리스트 90도 회전하기
    public int[][] rotateMatrixBy90Degree(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        int[][] result = new int[n][m]; // 결과 리스트
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[j][n - i - 1] = a[i][j];
            }
        }
        return result;
    }
}
