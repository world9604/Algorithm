package idea;

public class Maze {
    /**
     * @제목 미로찾기
     * 현재 위치에서 출구까지 가는 경로가 있으려면
     * 1. 현재 위치가 출구이거나 혹은
     * 2. 이웃한 셀들 중 하나에서 현재 위치를 지나지 않고 출구까지 가는 경로 있거나
     */

    /*
    미로찾기 1 (sudo 코드)
    (x`, y`)에서 (x, y)로 다시 간다면 이것은 무한 루프에 빠질 수 있다.
    public static boolean findPath(x, y) {
        if (x, y) is the exit
            return true;
        else
            for each neighbouring cell (x`, y`) of (x, y) do
                if (x`, y`) is on the pathway
                    if findPath(x`, y`)
                        return true;
            return false;
    }

    미로찾기 2 (sudo 코드)
    (x`, y`)에서 (x, y)로 다시 간다면 이것은 무한 루프에 빠질 수 있으므로
    (x, y)를 방문 했다고 처리한 후 방문한 곳은 방문하지 않도록 구현
    public static boolean findPath(x, y) {
        if (x, y) is the exit
            return true;
        else
            mark (x, y) as a visited cell;
            for each neighbouring cell (x`, y`) of (x, y) do
                if (x`, y`) is on the pathway and not visited
                    if findPath(x`, y`)
                        return true;
            return false;
    }
    */

    private static int N = 8;
    private static int[][] maze = {
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 0, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 0, 0, 1},
            {0, 1, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 0, 0, 1, 1},
            {0, 1, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 1},
            {0, 1, 1, 1, 0, 1, 0, 0}
    };

    private static final int PATHWAY_COLOUR = 0; // 길목 칼라
    private static final int WALL_COLOUR = 1;    // 벽 칼라
    private static final int BLOCKED_COLOUR = 2; // 가는길이 막혔다면 BLOCKED_COLOUR 로 칠한다.
    private static final int PATH_COLOUR = 3;    // 출구까지 가는 길이 있다면 PATH_COLOUR 로 칠하고 출구가 없다면 BLOCKED_COLOUR 로 칠한다

    public static void main(String[] args) {
        int x = 2, y = 4;
        System.out.printf("%d, %d에 길을 찾을 수 있나? %b", x, y, findPath(x, y));
    }

    public static boolean findPath(int x, int y) {
        if (x < 0 || y < 0 || x > N-1 || y > N-1) {
            return false;
        } else if (maze[x][y] != PATHWAY_COLOUR) {
            return false;
        } else if (x == N-1 && y == N-1) {
            maze[x][y] = PATH_COLOUR;
            return true;
        } else {
            maze[x][y] = PATH_COLOUR;
            if (findPath(x-1, y) || findPath(x, y + 1)
                || findPath(x + 1, y) || findPath(x, y - 1)) {
                return true;
            }
            maze[x][y] = BLOCKED_COLOUR; // dead end
            return false;
        }
    }
}
