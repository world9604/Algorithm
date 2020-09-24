package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18405 {
    // https://www.acmicpc.net/problem/18405
    // 경쟁적 전염
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        System.out.print(new BOJ18405().solution(N, map, S, X, Y));
    }

    public int solution(int n, int[][] map, int s, int x, int y) {
        // 바이러스의 타입 중 가장 작은 타입 부터 전염(PriorQueue 특성)
        Queue<Node> queue = new PriorityQueue<Node>();

        // 이미 감염 되어있는 노드들을 queue 에 넣어줌
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) {
                    queue.offer(new Node(i, j, map[i][j], 0));
                }
            }
        }

        return bfs(queue, map, s, x - 1, y - 1, n);
    }

    private int bfs(Queue<Node> queue, int[][] map, int s, int desX, int desY, int n) {
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int virusType = cur.virusType;
            int time = cur.time;
            if (time == s) break;

            // 바이러스 전파
            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                int nextTime = time + 1;

                if (isInBox(nextX, n) && isInBox(nextY, n) && !isVirus(map, nextX, nextY)) {
                    // 바이러스 전염
                    queue.offer(new Node(nextX, nextY, virusType, nextTime));
                    map[nextX][nextY] = virusType;
                }
            }
        }

        return map[desX][desY];
    }

    private boolean isVirus(int[][] map, int nextX, int nextY) {
        return map[nextX][nextY] != 0;
    }

    private boolean isInBox(int x, int n) {
        return x >= 0 && x < n;
    }
}

class Node implements Comparable<Node> {
    int x;
    int y;
    int virusType;
    int time;

    public Node(int x, int y, int virusType, int time) {
        this.x = x;
        this.y = y;
        this.virusType = virusType;
        this.time = time;
    }

    @Override
    public int compareTo(Node other) {
        if (time != other.time) {
            return time - other.time;
        } else {
            return virusType - other.virusType;
        }
    }
}
