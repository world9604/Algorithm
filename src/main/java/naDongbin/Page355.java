package naDongbin;

import java.util.*;

public class Page355 {

    // https://programmers.co.kr/learn/courses/30/lessons/60063
    // 블록 이동하기
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1},
                        {0, 0, 0, 1, 0},
                        {0, 1, 0, 1, 1},
                        {1, 1, 0, 0, 1},
                        {0, 0, 0, 0, 0}};
        System.out.print(new Page355().solution(board));
    }

    int[][] board;
    int n;
    public int solution(int[][] board) {
        this.n = board.length - 1;
        this.board = board;

        // 시간을 계산하는 변수 정의
        Pos leftWing = new Pos(0, 0);
        Pos rightWing = new Pos(0, 1);
        RobotPos robotPos = new RobotPos(leftWing, rightWing, 0);
        return bfs(robotPos);
    }

    // BFS 탐색 함수
    private int bfs(RobotPos robot) {
        final int ERR_TIME = -1;
        Queue<RobotPos> queue = new LinkedList();
        queue.offer(robot);

        while (!queue.isEmpty()) {
            RobotPos cur = queue.poll();
            Set<RobotPos> robots = new HashSet<>();

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                robots.add(goTo(i, cur));
            }

            // 회전 탐색
            robots.addAll(verticalStateRotateToLeft(cur));
            robots.addAll(verticalStateRotateToRight(cur));
            robots.addAll(horizontalStateRotateToBottom(cur));
            robots.addAll(horizontalStateRotateToTop(cur));

            for(RobotPos nextRobot : robots) {
                if (canGoTo(nextRobot)) {
                    //왼쪽 날개나 오른쪽 날개가 (N, N)에 닿아있으면 탈출
                    if (haveUArrived(nextRobot)) return nextRobot.time;
                    queue.offer(nextRobot);
                }
            }
        }
        return ERR_TIME;
    }

    private boolean haveUArrived(RobotPos robot) {
        return (robot.leftWing.x == n && robot.leftWing.y == n) ||
                (robot.rightWing.x == n && robot.rightWing.y == n);
    }

    // 로봇이 상,하,좌,우 로 이동시 좌표값 반환 하는 함수
    private RobotPos goTo(int direction, RobotPos robot) {
        int[] x = {1, -1, 0, 0};
        int[] y = {0, 0, 1, -1};

        Pos left = new Pos(robot.leftWing.x, robot.leftWing.y);
        Pos right = new Pos(robot.rightWing.x, robot.rightWing.y);
        RobotPos pos = new RobotPos(left, right, robot.time + 1);

        // 왼쪽 날개 이동
        pos.leftWing.x += x[direction];
        pos.leftWing.y += y[direction];
        // 오른쪽 날개 이동
        pos.rightWing.x += x[direction];
        pos.rightWing.y += y[direction];

        return pos;
    }

    // 로봇이 시계 방향으로 회전 가능한지 확인하는 함수
    private boolean canGoTo(RobotPos robot){
        if (isOutBoard(robot))
            return false;

        if (isWall(robot))
            return false;

        return true;
    }

    private boolean isOutBoard(RobotPos robot) {
        return (robot.leftWing.x > n || robot.leftWing.y > n) ||
                (robot.leftWing.x < 0 || robot.leftWing.y < 0) ||
                (robot.rightWing.x > n || robot.rightWing.y > n) ||
                (robot.rightWing.x < 0 || robot.rightWing.y < 0);
    }

    private boolean isWall(RobotPos robot) {
        return  board[robot.leftWing.x][robot.leftWing.y] == 1 ||
                board[robot.rightWing.x][robot.rightWing.y] == 1;
    }

    // 왼쪽 날개 축으로 시계 방향 90도 회전하기
    // 따라서 왼쪽 날개의 좌표는 그대로
    public RobotPos rotateByLeftAxis90(RobotPos robot) {
        int[][] a = {{robot.leftWing.x, robot.leftWing.y}, {robot.rightWing.x, robot.rightWing.y}};
        int[][] result = new int[2][2]; // 결과 리스트
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result[j][2 - i - 1] = a[i][j];
            }
        }

        return new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y),
                new Pos(result[1][0], result[1][1]), robot.time + 1);
    }

    // 오른쪽 날개 축으로 시계 방향 90도 회전하기
    // 따라서 오른쪽 날개의 좌표는 그대로
    public RobotPos rotateByRightAxis90(RobotPos robot) {
        int[][] a = {{robot.leftWing.x, robot.leftWing.y}, {robot.rightWing.x, robot.rightWing.y}};
        int[][] result = new int[2][2]; // 결과 리스트
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result[j][2 - i - 1] = a[i][j];
            }
        }

        return new RobotPos(new Pos(result[0][0], result[0][1]),
                new Pos(robot.rightWing.x, robot.rightWing.y), robot.time + 1);
    }

    //1.로봇이 가로로 놓인 상태에서 아래쪽으로 회전하는 경우
    public Set<RobotPos> horizontalStateRotateToBottom(RobotPos robot) {
        Set<RobotPos> set = new HashSet();

        if ((board[robot.leftWing.x + 1][robot.leftWing.y] == 1) ||
            (board[robot.rightWing.x + 1][robot.rightWing.y] == 1))
            return set;

        // 왼쪽 날개 기준 축
        RobotPos robotPos = new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y),
                new Pos(robot.rightWing.x + 1, robot.rightWing.y - 1), robot.time + 1);

        // 오른쪽 날개 기준 축
        RobotPos robotPos2 = new RobotPos(new Pos(robot.leftWing.x + 1, robot.leftWing.y + 1),
                new Pos(robot.rightWing.x, robot.rightWing.y), robot.time + 1);

        set.add(robotPos);
        set.add(robotPos2);
        return set;
    }

    //2.로봇이 가로로 놓인 상태에서 위쪽으로 회전하는 경우
    public Set<RobotPos> horizontalStateRotateToTop(RobotPos robot) {
        Set<RobotPos> set = new HashSet();
        if (robot.leftWing.x == 0 || robot.rightWing.x == 0) return set;

        if ((board[robot.leftWing.x - 1][robot.leftWing.y] == 1) ||
                (board[robot.rightWing.x - 1][robot.rightWing.y] == 1))
            return set;

        // 왼쪽 날개 기준 축
        RobotPos robotPos = new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y),
                new Pos(robot.rightWing.x - 1, robot.rightWing.y - 1), robot.time + 1);

        // 오른쪽 날개 기준 축
        RobotPos robotPos2 = new RobotPos(new Pos(robot.leftWing.x - 1, robot.leftWing.y + 1),
                new Pos(robot.rightWing.x, robot.rightWing.y), robot.time + 1);

        set.add(robotPos);
        set.add(robotPos2);
        return set;
    }

    //3.로봇이 세로로 놓인 상태에서 오른쪽으로 회전하는 경우
    public Set<RobotPos> verticalStateRotateToRight(RobotPos robot) {
        Set<RobotPos> set = new HashSet();

        if ((board[robot.leftWing.x][robot.leftWing.y + 1] == 1) ||
                (board[robot.rightWing.x][robot.rightWing.y + 1] == 1))
            return set;

        // 왼쪽 날개 기준 축
        RobotPos robotPos = new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y),
                new Pos(robot.rightWing.x - 1, robot.rightWing.y + 1), robot.time + 1);

        // 오른쪽 날개 기준 축
        RobotPos robotPos2 = new RobotPos(new Pos(robot.leftWing.x + 1, robot.leftWing.y + 1),
                new Pos(robot.rightWing.x, robot.rightWing.y), robot.time + 1);

        set.add(robotPos);
        set.add(robotPos2);
        return set;
    }

    //4.로봇이 세로로 놓인 상태에서 왼쪽으로 회전하는 경우
    public Set<RobotPos> verticalStateRotateToLeft(RobotPos robot) {
        Set<RobotPos> set = new HashSet();
        if (robot.leftWing.y == 0 || robot.rightWing.y == 0) return set;

        if ((board[robot.leftWing.x][robot.leftWing.y - 1] == 1) ||
                (board[robot.rightWing.x][robot.rightWing.y - 1] == 1))
            return set;

        // 왼쪽 날개 기준 축
        RobotPos robotPos = new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y),
                new Pos(robot.rightWing.x - 1, robot.rightWing.y - 1), robot.time + 1);

        // 오른쪽 날개 기준 축
        RobotPos robotPos2 = new RobotPos(new Pos(robot.leftWing.x + 1, robot.leftWing.y - 1),
                new Pos(robot.rightWing.x, robot.rightWing.y), robot.time + 1);

        set.add(robotPos);
        set.add(robotPos2);
        return set;
    }

    // 로봇의 위치 Class 정의 (x, y), (x, y)
    class RobotPos {
        Pos leftWing;
        Pos rightWing;
        int time;

        RobotPos(Pos leftWing, Pos rightWing, int time) {
            this.leftWing = leftWing;
            this.rightWing = rightWing;
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RobotPos robotPos = (RobotPos) o;
            return time == robotPos.time &&
                    Objects.equals(leftWing, robotPos.leftWing) &&
                    Objects.equals(rightWing, robotPos.rightWing);
        }

        @Override
        public int hashCode() {
            return Objects.hash(leftWing, rightWing, time);
        }
    }

    class Pos{
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x &&
                    y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
