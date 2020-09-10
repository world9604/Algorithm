package naDongbin;

import java.util.*;

public class Page355_2 {

    // https://programmers.co.kr/learn/courses/30/lessons/60063
    // 블록 이동하기
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1},
                        {0, 0, 0, 1, 0},
                        {0, 1, 0, 1, 1},
                        {1, 1, 0, 0, 1},
                        {0, 0, 0, 0, 0}};
        System.out.print(new Page355_2().solution(board));
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
        List<RobotPos> visited = new ArrayList<>();
        visited.add(robot);

        while (!queue.isEmpty()) {
            RobotPos cur = queue.poll();

            //왼쪽 날개나 오른쪽 날개가 (N, N)에 닿아있으면 탈출
            if (haveUArrived(cur)) return cur.time;

            // 이동
            Set<RobotPos> robots = new HashSet<>();
            PositionGetter positionGetter = new PositionGetter(cur);
            robots.addAll(positionGetter.goTo());

            for(RobotPos nextRobot : robots) {
                // 방문한 position 제거
                boolean check = false;
                for (RobotPos robotPos : visited) {
                    if (robotPos == nextRobot) {
                        check = true;
                    }
                }
                if (check) continue;

                // 큐에 삽입
                queue.offer(nextRobot);
                visited.add(nextRobot);
            }
        }
        return ERR_TIME;
    }

    private boolean haveUArrived(RobotPos robot) {
        return (robot.leftWing.x == n && robot.leftWing.y == n) ||
                (robot.rightWing.x == n && robot.rightWing.y == n);
    }

    class PositionGetter {
        BodyState bodyState;
        RobotPos robot;

        public PositionGetter(RobotPos robot) {
            this.robot = robot;

            if (robot.leftWing.x == robot.rightWing.x) {
                this.bodyState = new HorizontalBodyState();
            }

            if (robot.leftWing.y == robot.rightWing.y) {
                this.bodyState = new VerticalBodyState();
            }
        }

        Set<RobotPos> goTo() {
            Set<RobotPos> set = new HashSet();

            if (!isOutBoard(robot) &&
                    !isWall(robot.leftWing.x + 1, robot.leftWing.y) &&
                    !isWall(robot.rightWing.x + 1, robot.rightWing.y)) {
                set.addAll(bodyState.goToBottom(robot));
            }
            if (!isOutBoard(robot) &&
                    !isWall(robot.leftWing.x - 1, robot.leftWing.y) &&
                    !isWall(robot.rightWing.x - 1, robot.rightWing.y)) {
                set.addAll(bodyState.goToTop(robot));
            }
            if (!isOutBoard(robot) &&
                    !isWall(robot.leftWing.x, robot.leftWing.y + 1) &&
                    !isWall(robot.rightWing.x, robot.rightWing.y + 1)) {
                set.addAll(bodyState.goToRight(robot));
            }
            if (!isOutBoard(robot) &&
                    !isWall(robot.leftWing.x, robot.leftWing.y - 1) &&
                    !isWall(robot.rightWing.x, robot.rightWing.y - 1)) {
                set.addAll(bodyState.goToLeft(robot));
            }
            return set;
        }

        private boolean isOutBoard(RobotPos robot) {
            return (robot.leftWing.x > n || robot.leftWing.y > n) ||
                    (robot.leftWing.x < 0 || robot.leftWing.y < 0) ||
                    (robot.rightWing.x > n || robot.rightWing.y > n) ||
                    (robot.rightWing.x < 0 || robot.rightWing.y < 0);
        }

        private boolean isWall(int x, int y) {
            boolean check = true;
            if (x <= n && y <= n && x >= 0 && y >= 0)
                check = board[x][y] == 1;
            return check;
        }
    }

    interface BodyState {
        Set<RobotPos> goToBottom(RobotPos robotPos);
        Set<RobotPos> goToTop(RobotPos robotPos);
        Set<RobotPos> goToRight(RobotPos robotPos);
        Set<RobotPos> goToLeft(RobotPos robotPos);
    }

    class HorizontalBodyState implements BodyState {

        @Override
        public Set<RobotPos> goToBottom(RobotPos robot) {
            Set<RobotPos> set = new HashSet<>();

            set.add(new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y),
                    new Pos(robot.rightWing.x + 1, robot.rightWing.y - 1),
                    robot.time + 1));

            set.add(new RobotPos(new Pos(robot.leftWing.x + 1, robot.leftWing.y + 1),
                    new Pos(robot.rightWing.x, robot.rightWing.y),
                    robot.time + 1));

            set.add(new RobotPos(new Pos(robot.leftWing.x + 1, robot.leftWing.y),
                    new Pos(robot.rightWing.x + 1, robot.rightWing.y),
                    robot.time + 1));
            return set;
        }

        @Override
        public Set<RobotPos> goToTop(RobotPos robot) {
            Set<RobotPos> set = new HashSet<>();
            set.add(new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y),
                    new Pos(robot.rightWing.x - 1, robot.rightWing.y - 1),
                    robot.time + 1));

            set.add(new RobotPos(new Pos(robot.leftWing.x - 1, robot.leftWing.y + 1),
                    new Pos(robot.rightWing.x, robot.rightWing.y),
                    robot.time + 1));

            set.add(new RobotPos(new Pos(robot.leftWing.x - 1, robot.leftWing.y - 1),
                    new Pos(robot.rightWing.x, robot.rightWing.y),
                    robot.time + 1));
            return set;
        }

        @Override
        public Set<RobotPos> goToRight(RobotPos robot) {
            Set<RobotPos> set = new HashSet<>();

            set.add(new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y + 1),
                    new Pos(robot.rightWing.x, robot.rightWing.y + 1),
                    robot.time + 1));
            return set;
        }

        @Override
        public Set<RobotPos> goToLeft(RobotPos robot) {
            Set<RobotPos> set = new HashSet<>();

            set.add(new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y - 1),
                    new Pos(robot.rightWing.x, robot.rightWing.y - 1),
                    robot.time + 1));
            return set;
        }
    }

    class VerticalBodyState implements BodyState {

        @Override
        public Set<RobotPos> goToBottom(RobotPos robot) {
            Set<RobotPos> set = new HashSet<>();
            set.add(new RobotPos(new Pos(robot.leftWing.x + 1, robot.leftWing.y),
                    new Pos(robot.rightWing.x + 1, robot.rightWing.y),
                    robot.time + 1));
            return set;
        }

        @Override
        public Set<RobotPos> goToTop(RobotPos robot) {
            Set<RobotPos> set = new HashSet<>();
            set.add(new RobotPos(new Pos(robot.leftWing.x - 1, robot.leftWing.y),
                    new Pos(robot.rightWing.x - 1, robot.rightWing.y),
                    robot.time + 1));
            return set;
        }

        @Override
        public Set<RobotPos> goToRight(RobotPos robot) {
            Set<RobotPos> set = new HashSet<>();
            set.add(new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y),
                    new Pos(robot.rightWing.x - 1, robot.rightWing.y + 1),
                    robot.time + 1));

            set.add(new RobotPos(new Pos(robot.leftWing.x + 1, robot.leftWing.y + 1),
                    new Pos(robot.rightWing.x, robot.rightWing.y),
                    robot.time + 1));

            set.add(new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y + 1),
                    new Pos(robot.rightWing.x, robot.rightWing.y + 1),
                    robot.time + 1));
            return set;
        }

        @Override
        public Set<RobotPos> goToLeft(RobotPos robot) {
            Set<RobotPos> set = new HashSet<>();
            set.add(new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y),
                    new Pos(robot.rightWing.x - 1, robot.rightWing.y - 1),
                    robot.time + 1));

            set.add(new RobotPos(new Pos(robot.leftWing.x + 1, robot.leftWing.y - 1),
                    new Pos(robot.rightWing.x, robot.rightWing.y),
                    robot.time + 1));

            set.add(new RobotPos(new Pos(robot.leftWing.x, robot.leftWing.y - 1),
                    new Pos(robot.rightWing.x, robot.rightWing.y - 1),
                    robot.time + 1));
            return set;
        }
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
    }

    class Pos{
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
