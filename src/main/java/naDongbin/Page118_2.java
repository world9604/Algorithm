package naDongbin;

import java.util.HashMap;
import java.util.Map;

public class Page118_2 {
    // 입력 예시
    // 4 4      => 4 x 4 맵 생성
    // 1 1 0    => (1, 1)에 북쪽(0)을 바라보고 서 있는 캐릭터
    // 1 1 1 1  => 바다/바다/바다/바다
    // 1 0 0 1  => 바다/육지/육지/바다
    // 1 1 0 1  => 바다/바다/육지/바다
    // 1 1 1 1  => 바다/바다/바다/바다
    // 출력 예시
    // 3
    public static void main(String[] args) {
        System.out.print(new Page118_2().solution());
    }

    int solution(){
        int N = 4, M = 4;
        int[][] gameMap = {
                {1, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1}};

        CharactorProcessor2 processor = new CharactorProcessor2(
                new Charactor2(1, 1, CARDINAL_POINTS2.NORTH), gameMap, N, M);

        processor.run();
        return processor.getMiniumDistance();
    }
}

class CharactorProcessor2 {
    private Charactor2 charactor;
    private final int[][] GAME_MAP;
    private int[][] visitedMap;
    private final Map<CARDINAL_POINTS2, Integer> dx;
    private final Map<CARDINAL_POINTS2, Integer> dy;
    private final int SEA;
    private final int VISITED;
    private final int N;
    private final int M;
    private int miniMumDistance;

    public CharactorProcessor2(Charactor2 charactor, int[][] gameMap, int N, int M) {
        this.charactor = charactor;
        this.GAME_MAP = gameMap;
        this.visitedMap = gameMap;
        this.SEA = 1;
        this.VISITED = 1;
        this.N = N;
        this.M = M;
        // 북-0/동-1/남-2/서-3
        // dx[] = {-1, 0, 1, 0};
        // dy[] = {0, 1, 0, -1};
        this.dx = new HashMap<>();
        this.dy = new HashMap<>();
        this.dx.put(CARDINAL_POINTS2.NORTH, 0);
        this.dx.put(CARDINAL_POINTS2.SOUTH, 0);
        this.dx.put(CARDINAL_POINTS2.EAST, -1);
        this.dx.put(CARDINAL_POINTS2.WEST, 1);

        this.dy.put(CARDINAL_POINTS2.NORTH, -1);
        this.dy.put(CARDINAL_POINTS2.SOUTH, 1);
        this.dy.put(CARDINAL_POINTS2.EAST, 0);
        this.dy.put(CARDINAL_POINTS2.WEST, 0);
        // 북, 동, 남, 서 방향 정의
        //public static int dx[] = {-1, 0, 1, 0};
        //public static int dy[] = {0, 1, 0, -1};
        this.miniMumDistance = 0;
        // 현재 위치 방문 체크
        checkVisitedPosition(charactor.getX(), charactor.getY());
    }

    // 최소 이동 칸 반환
    public int getMiniumDistance(){
        return miniMumDistance;
    }

    // 캐릭터 로직
    public void run(){
        // 동,서,남,북 이동(4)
        for (int i = 0; i < CARDINAL_POINTS2.length; i++) {
            // 갈방향이 바다면 false 반환
            // 이미 방문한 노드라면 false 반환
            // 맵이 아니라면 false 반환
            if(isGoLeft()) {
                rotateLeft();
                goStright();
                run();
                return;
            } else {
                rotateLeft();
            }
        }

        // 동,서,남,북 모두 이동 할 곳 없다면
        // 뒤로 가거나, 갈수 없다면 멈춘다
        if(isTheSeaBehind()) {
            stop();
        } else {
            goBack();
            run();
        }
    }

    // 왼쪽 방향의 갈곳이 있으면 true
    // 북-0/동-1/남-2/서-3
    public boolean isGoLeft(){
        // 이동할 위치(x, y)
        int[] postion = getMapPositionToGo();

        // 갈방향이 바다면 false 반환
        // 이미 방문한 노드라면 false 반환
        // 맵이 아니라면 false 반환
        return isInMap(postion)
                && !isASea(postion)
                && !isVisited(postion);
    }

    // 방문 했으면 true
    private boolean isVisited(int[] postion){
        return visitedMap[postion[0]][postion[1]] == VISITED;
    }

    // 바다면 true
    private boolean isASea(int[] postion) {
        return GAME_MAP[postion[0]][postion[1]] == SEA;
    }

    // 맵 안이면 true
    private boolean isInMap(int[] postion) {
        return postion[0] >= 0 && postion[0] < N
                && postion[1] >= 0 && postion[1] < M;
    }

    // 왼쪽으로 회전
    public void rotateLeft(){
        charactor.setFacePostion(getFacePositionToGo());
        System.out.print("rotateLeft");
        System.out.println();
    }

    // 앞으로 전진
    public void goStright(){
        int[] mapPostionToGo = getMapPositionToGo();
        charactor.setX(mapPostionToGo[0]);
        charactor.setY(mapPostionToGo[1]);

        //이동 한 위치 방문 표시
        checkVisitedPosition(mapPostionToGo[0], mapPostionToGo[1]);

        System.out.print("goStright");
        System.out.println();
    }

    // 갈곳이 없을 경우, 바라보는 곳을 유지하고
    // 한칸 뒤로 이동
    public void goBack(){
        int[] mapPosition = getMapPositionToBack();
        charactor.setX(mapPosition[0]);
        charactor.setY(mapPosition[1]);

        //이동 한 위치 방문 표시
        checkVisitedPosition(mapPosition[0], mapPosition[1]);

        System.out.print("goBack");
        System.out.println();
    }

    // 뒤칸이 바다 이면 true
    private boolean isTheSeaBehind(){
        int[] mapPosition = getMapPositionToBack();
        return GAME_MAP[mapPosition[0]][mapPosition[1]] == SEA;
    }

    // 모두 갈곳이 없다면, 멈춘다.
    public void stop(){
        System.out.print("stop");
        System.out.println();
    }

    private CARDINAL_POINTS2 getFacePositionToGo() {
        CARDINAL_POINTS2 toGoFace = null;
        if (CARDINAL_POINTS2.NORTH == charactor.getFacePosition()) {
            toGoFace = CARDINAL_POINTS2.WEST;
        } else if (CARDINAL_POINTS2.WEST == charactor.getFacePosition()) {
            toGoFace = CARDINAL_POINTS2.SOUTH;
        } else if (CARDINAL_POINTS2.SOUTH == charactor.getFacePosition()) {
            toGoFace = CARDINAL_POINTS2.EAST;
        } else if (CARDINAL_POINTS2.EAST == charactor.getFacePosition()) {
            toGoFace = CARDINAL_POINTS2.NORTH;
        }
        return toGoFace;
    }

    //동 으로 가려면 y + 1
    //북 로 가려면 x - 1
    //서 로 가려면 y - 1
    //남 으로 가려면 x + 1
    //가야할 방향을 파라미터로 주면 이동할 map 위치를 반환
    // 북-0/동-1/남-2/서-3
    private int[] getMapPositionToGo() {
        int nx = charactor.getX() + dx.get(charactor.getFacePosition());
        int ny = charactor.getY() + dy.get(charactor.getFacePosition());
        return new int[]{nx, ny};
    }

    private int[] getMapPositionToBack() {
        int nx = charactor.getX() - dx.get(charactor.getFacePosition());
        int ny = charactor.getY() - dy.get(charactor.getFacePosition());
        return new int[]{nx, ny};
    }

    private void checkVisitedPosition(int x, int y) {
        //현재 위치 방문 표시
        visitedMap[x][y] = 1;
        //이동 칸 증가
        miniMumDistance++;
    }
}

class Charactor2{
    private int x;
    private int y;
    private CARDINAL_POINTS2 facePostion;

    public Charactor2(int x, int y, CARDINAL_POINTS2 facePostion) {
        this.x = x;
        this.y = y;
        this.facePostion = facePostion;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CARDINAL_POINTS2 getFacePosition() {
        return facePostion;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFacePostion(CARDINAL_POINTS2 facePostion) {
        this.facePostion = facePostion;
    }
}

enum CARDINAL_POINTS2 {
    // 북-0/동-1/남-2/서-3
    NORTH,
    WEST,
    SOUTH,
    EAST;

    public static final int length = CARDINAL_POINTS2.values().length;
}