package etc;

public class Blob {
    /**
     * @입력
     * N * N 크기의 2차원 그리드
     * 하나의 좌표 (x, y)
     * @출력
     * 픽셀 (x, y)가 포함된 blob 의 크기
     * (x, y)가 어떤 blob 에도 속하지 않는 경우에는 0
     *
     * 현재 픽셀이 속한 blob 의 크기를 카운트 하려면
     * 현재 픽셀이 image color 가 아니라면
     *      0을 반환 한다.
     * 현재 픽셀이 image color 라면
     *      먼저 현재 픽셀을 카운트 한다
     *      현재 픽셀이 중복 카운트 되는 것을 방지하기 위해 다른 색으로 칠 한다.
     *      현재 픽셀에 이웃한 모든 픽셀들에 대해서
     *            그 픽셀이 속한 blob 의 크기를 카운트 하여 카운터에 더해준다.
     *      카운터를 반환 한다.
     *
     * sudo 코드
     * int countCells(x, y) {
     *     if the pixel (x, y) is outside the grid
     *           the result is 0;
     *     else if pixel (x, y) is not an image pixel or already counted
     *           the result is 0;
     *     else
     *           set the colour of the pixel (x, y) to a red colour;
     *           the result is 1 plus the number of cells in each piece of
     *           the blob that includes a nearest neighbour;
     * }
     */
    static int N = 8;
    static final int NOT_BLOB_BLOCK = 0;
    static final int BLOB_BLOCK = 1;
    static int[][] blob = {
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 0, 0, 1, 1},
            {0, 1, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 1},
            {0, 1, 1, 1, 0, 1, 0, 0}
    };

    public static void main(String[] args) {
        int x = 1, y = 1;
        System.out.printf("result : %d", getBlobCount(x, y));
    }

    static int getBlobCount(int x, int y) {
        if (x < 0 || y < 0 || x > N - 1 || y > N - 1) {
            return 0;
        } else if (blob[x][y] != BLOB_BLOCK) {
            return 0;
        } else {
            blob[x][y] = NOT_BLOB_BLOCK;
            return 1 + getBlobCount(x + 1, y) + getBlobCount(x - 1, y)
                    + getBlobCount(x, y + 1) + getBlobCount(x, y - 1)
                    + getBlobCount(x + 1, y + 1)
                    + getBlobCount(x + 1, y - 1)
                    + getBlobCount(x - 1, y + 1)
                    + getBlobCount(x - 1, y - 1);
        }
    }
}
