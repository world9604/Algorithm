package kakao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PillarAndBeamProblem {
    // 기둥과 보 설치
    // https://programmers.co.kr/learn/courses/30/lessons/60061

    public static void main(String[] args) {
        int n = 5;
        ///int[][] build_frame = new int[][]{{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
        int[][] build_frame = new int[][]{{0,0,0,1},
                                        {2,0,0,1},
                                        {4,0,0,1},
                                        {0,1,1,1},
                                        {1,1,1,1},
                                        {2,1,1,1},
                                        {3,1,1,1},
                                        {2,0,0,0},
                                        {1,1,1,0},
                                        {2,2,0,1}};
        new PillarAndBeamProblem().solution(n, build_frame);
    }

    List<int[]> result;
    int n;

    public int[][] solution(int n, int[][] build_frame) {
        // 임시 결과 List
        this.result = new ArrayList<int[]>();
        this.n = n;

        // build_frame 을 반복문을 돌면서 구현
        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int pillar = build_frame[i][2];
            int install = build_frame[i][3];

            if (install == INSTALL) install(x, y, pillar);
            else unInstall(x, y, pillar);
        }

        // result x, y, 기둥 -> 보 순으로 정렬
        result.sort(sortPillarAndBeam());

        int[][] answer = new int[result.size()][3];
        answer = result.toArray(answer);
        return answer;
    }

    private Comparator<int[]> sortPillarAndBeam() {
        return (o1, o2) -> {
            if (o1[0] == o2[0]) {
                if (o1[1] == o2[1]) {
                    return o1[2] == PILLAR ? 1 : -1;
                } else {
                    return o1[1] - o2[1];
                }
            } else {
                return o1[0] - o2[0];
            }
        };
    }

    private void install(int x, int y, int pillar) {
        if (isInstallPillar(x, y, pillar) ||
                isInstallBeam(x, y, pillar)) {
            int[] a = new int[3];
            a[0] = x;
            a[1] = y;
            a[2] = pillar;
            result.add(a);
        }
    }

    private void unInstall(int x, int y, int pillar) {
        // 삭제하기
        for (int i = 0; i < result.size(); i++) {
            // 삭제
            if (result.get(i)[0] == x && result.get(i)[1] == y && result.get(i)[2] == pillar) {
                result.remove(i);

                // 삭제 후 남아 있는 기둥과 보는 아래 조건을 만족해야 한다.
                for (int j = 0; j < result.size(); j++) {
                    int x1 = result.get(j)[0];
                    int y1 = result.get(j)[1];
                    int pillar1 = result.get(j)[2];

                    // 아래 조건을 한번 이라도 만족 한다면
                    // 삭제 했던 배열 다시 추가
                    if (!isInstallPillar(x1, y1, pillar1) && !isInstallBeam(x1, y1, pillar1)) {
                        // 삭제 했던 배열 다시 추가
                        result.add(new int[]{x, y, pillar});
                        break;
                    }
                }
                break;
            }
        }
    }

    private final int INSTALL = 1;
    private final int PILLAR = 0;
    private final int BEAM = 1;
    private final int FLOOR = 0;

    private boolean isInstallPillar(int x, int y, int pillar) {
        // 기둥이 아니라면 false
        if (pillar != PILLAR)
            return false;

        // 기둥은 바닥 위에 있거나
        // 보의 한쪽 끝 부분 위에 있거나,
        // 또는 다른 기둥 위에 있어야 합니다.
        if (!isOutOfMapForPillar(x, y) &&
                (isOnTheFloor(y) ||
                isAboveOneEndOfTheBeam(x, y) ||
                isOnThePillar(x, y)))
            return true;

        return false;
    }

    private boolean isInstallBeam(int x, int y, int pillar){
        // 보가 아니면 false
        if (pillar != BEAM)
            return false;

        // 보는 한쪽 끝 부분이 기둥 위에 있거나,
        // 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
        if (!isOutOfMapForBeam(x, y) &&
                (isAboveThePillar(x, y) ||
                isConnectedWithOtherBeam(x, y)))
            return true;

        return false;
    }

    private boolean isOutOfMapForPillar(int x, int y) {
        return n < x || (n - 1) < y || 0 > x || 0 > y;
    }

    private boolean isOnTheFloor(int y){
        return (y == FLOOR);
    }

    private boolean isAboveOneEndOfTheBeam(int x, int y) {
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i)[0] == x && result.get(i)[1] == y && result.get(i)[2] == BEAM)
                return true;
            if (result.get(i)[0] == (x - 1) && result.get(i)[1] == y && result.get(i)[2] == BEAM)
                return true;
        }
        return false;
    }

    private boolean isOnThePillar(int x, int y) {
        for (int i = 0; i < result.size(); i++)
            if (result.get(i)[0] == x && result.get(i)[1] == (y - 1) && result.get(i)[2] == PILLAR)
                return true;
        return false;
    }

    private boolean isOutOfMapForBeam(int x, int y) {
        return (n - 1) < x || n < y || 0 > x || 0 > y;
    }

    private boolean isAboveThePillar(int x, int y) {
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i)[0] == x && result.get(i)[1] == (y - 1) && result.get(i)[2] == PILLAR)
                return true;
            if (result.get(i)[0] == (x + 1) && result.get(i)[1] == (y - 1) && result.get(i)[2] == PILLAR)
                return true;
        }
        return false;
    }

    private boolean isConnectedWithOtherBeam(int x, int y) {
        boolean left = false;
        boolean right = false;

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i)[0] == (x - 1) && result.get(i)[1] == y && result.get(i)[2] == BEAM)
                left = true;
            if (result.get(i)[0] == (x + 1) && result.get(i)[1] == y && result.get(i)[2] == BEAM)
                right = true;
            if (left && right)
                return true;
        }

        return false;
    }
}
