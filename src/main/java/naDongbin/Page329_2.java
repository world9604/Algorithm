package naDongbin;

import java.util.*;


public class Page329_2 {

    // 기둥과 보 설치
    // https://programmers.co.kr/learn/courses/30/lessons/60061
    static final int PILLAR = 0;
    static final int BEAM = 1;
    static final int DESTRUCT = 0;
    static final int CONSTRUCT = 1;

    boolean[][] pillars, beams; // 기둥, 보

    public int[][] solution(int n, int[][] build_frame) {
        int structureCount = 0;

        // 기둥과 보를 따로 관리 한다.
        // 구현의 편의성
        pillars = new boolean[n + 3][n + 3];
        beams = new boolean[n + 3][n + 3];

        for (int[] frame : build_frame) {
            int x = frame[0] + 1;
            int y = frame[1] + 1;
            // 기둥인지 보인지
            int structureType = frame[2];
            // 설치 할지, 삭제 할지
            int commandType = frame[3];

            // 설치 한다면,
            if (commandType == CONSTRUCT) {
                if (structureType == PILLAR && canConstructPillar(x, y)) {
                    pillars[x][y] = true;
                    structureCount++;
                }
                if (structureType == BEAM && canConstructBeam(x, y)) {
                    beams[x][y] = true;
                    structureCount++;
                }
            } else if (commandType == DESTRUCT) {
                // 삭제
                if (structureType == PILLAR) {
                    pillars[x][y] = false;
                } else if (structureType == BEAM) {
                    beams[x][y] = false;
                }

                if (canDestruct(n)) {
                    structureCount--;
                    continue;
                }

                // 삭제 할수 없으므로, 되살린다.
                if (structureType == PILLAR) {
                    pillars[x][y] = true;
                } else if (structureType == BEAM) {
                    beams[x][y] = true;
                }
            }
        }

        int index = 0;
        int[][] answer = new int[structureCount][3];

        for (int i = 1; i <= n + 1; ++i) {
            for (int j = 1; j <= n + 1; ++j) {
                if (pillars[i][j]) answer[index++] = new int[]{i - 1, j - 1, PILLAR};
                if (beams[i][j]) answer[index++] = new int[]{i - 1, j - 1, BEAM};
            }
        }
        return answer;
    }

    private boolean canConstructPillar(int x, int y) {
        return y == 1 || pillars[x][y - 1] || beams[x][y] || beams[x - 1][y];
    }

    private boolean canConstructBeam(int x, int y) {
        return pillars[x][y - 1] || pillars[x + 1][y - 1] || (beams[x - 1][y] && beams[x + 1][y]);
    }

    private boolean canDestruct(int n) {
        for (int i = 1; i <= n + 1; ++i) {
            for (int j = 1; j <= n + 1; ++j) {
                if (pillars[i][j] && !canConstructPillar(i, j)) {
                    return false;
                }
                if (beams[i][j] && !canConstructBeam(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }
}