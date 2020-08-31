package naDongbin;

public class Page118 {
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
        System.out.print(new Page118().solution());
    }

    int solution(){
        int N = 4, M = 4;
        int[][] gameMap = {
                {1, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1}};

        CharactorProcessor processor = new CharactorProcessor(
                new Charactor(1, 1, CARDINAL_POINTS.NORTH), gameMap, N, M);

        processor.run();
        return processor.getMiniumDistance();
    }
}

class CharactorProcessor{
    private Charactor charactor;
    private final int[][] GAME_MAP;
    private int[][] visitedMap;
    private final int SEA;
    private final int VISITED;
    private final int N;
    private final int M;
    private int miniMumDistance;

    public CharactorProcessor(Charactor charactor, int[][] gameMap, int N, int M) {
        this.charactor = charactor;
        this.GAME_MAP = gameMap;
        this.visitedMap = gameMap;
        this.SEA = 1;
        this.VISITED = 1;
        this.N = N;
        this.M = M;
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
        for (int i = 0; i < CARDINAL_POINTS.length; i++) {
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
        // 이동할 방향(동/서/남/북)
        CARDINAL_POINTS toGoFace = getFacePositionToGo();

        // 이동할 위치(x, y)
        int[] postion = getMapPositionToGo(toGoFace);

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
        int[] mapPostionToGo = getMapPositionToGo(charactor.getFacePostion());
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
        // 맵 안 이면서,
        // 바다 이면 true
        return isInMap(mapPosition)
                && isASea(mapPosition);
    }

    // 모두 갈곳이 없다면, 멈춘다.
    public void stop(){
        System.out.print("stop");
        System.out.println();
    }

    private CARDINAL_POINTS getFacePositionToGo() {
        CARDINAL_POINTS toGoFace = null;
        if (CARDINAL_POINTS.NORTH == charactor.getFacePostion()) {
            toGoFace = CARDINAL_POINTS.WEST;
        } else if (CARDINAL_POINTS.WEST == charactor.getFacePostion()) {
            toGoFace = CARDINAL_POINTS.SOUTH;
        } else if (CARDINAL_POINTS.SOUTH == charactor.getFacePostion()) {
            toGoFace = CARDINAL_POINTS.EAST;
        } else if (CARDINAL_POINTS.EAST == charactor.getFacePostion()) {
            toGoFace = CARDINAL_POINTS.NORTH;
        }
        return toGoFace;
    }

    //동 으로 가려면 y + 1
    //북 로 가려면 x - 1
    //서 로 가려면 y - 1
    //남 으로 가려면 x + 1
    //가야할 방향을 파라미터로 주면 이동할 map 위치를 반환
    private int[] getMapPositionToGo(CARDINAL_POINTS face) {
        int x = charactor.getX();
        int y = charactor.getY();
        // 북쪽을 바라 보고 있다면, x - 1
        if(face == CARDINAL_POINTS.NORTH){
            x = charactor.getX() - 1;
        // 서쪽을 바라 보고 있다면, y - 1
        } else if(face == CARDINAL_POINTS.WEST){
            y = charactor.getY() - 1;
        // 동쪽을 바라 보고 있다면, y + 1
        } else if(face == CARDINAL_POINTS.EAST){
            y = charactor.getY() + 1;
        // 남쪽을 바라 보고 있다면, x + 1
        } else if(face == CARDINAL_POINTS.SOUTH){
            x = charactor.getX() + 1;
        }
        return new int[]{x, y};
    }

    private int[] getMapPositionToBack() {
        int x = charactor.getX();
        int y = charactor.getY();
        // 북쪽을 바라 보고 있다면, x + 1
        if (CARDINAL_POINTS.NORTH == charactor.getFacePostion()) {
            x = charactor.getX() + 1;
        // 서쪽을 바라 보고 있다면, y + 1
        } else if (CARDINAL_POINTS.WEST == charactor.getFacePostion()){
            y = charactor.getY() + 1;
        // 남쪽을 바라 보고 있다면, x - 1
        } else if (CARDINAL_POINTS.SOUTH == charactor.getFacePostion()){
            x = charactor.getX() - 1;
        // 동쪽을 바라 보고 있다면, y + 1
        } else if (CARDINAL_POINTS.EAST == charactor.getFacePostion()) {
            y = charactor.getY() + 1;
        }
        return new int[]{x, y};
    }

    private void checkVisitedPosition(int x, int y) {
        //현재 위치 방문 표시
        visitedMap[x][y] = 1;
        //이동 칸 증가
        miniMumDistance++;
    }
}

class Charactor{
    private int x;
    private int y;
    private CARDINAL_POINTS facePostion;

    public Charactor(int x, int y, CARDINAL_POINTS facePostion) {
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

    public CARDINAL_POINTS getFacePostion() {
        return facePostion;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFacePostion(CARDINAL_POINTS facePostion) {
        this.facePostion = facePostion;
    }
}

enum CARDINAL_POINTS {
    NORTH,
    WEST,
    SOUTH,
    EAST;
    public static final int length = CARDINAL_POINTS.values().length;
}