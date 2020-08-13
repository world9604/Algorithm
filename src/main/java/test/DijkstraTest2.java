package test;


import java.util.*;

public class DijkstraTest2 {
    public static void main(String[] args) {
        int nodeNum = 5;
        List<int[]>[] edge = new ArrayList[nodeNum + 1];

        // 전체 그래프를 초기화 합니다.
        edge[1] = new ArrayList<>();
        edge[1].add(new int[]{2, 3});
        edge[1].add(new int[]{3, 1});
        edge[1].add(new int[]{4, 3});

        edge[2] = new ArrayList<>();
        edge[2].add(new int[]{1, 3});
        edge[2].add(new int[]{3, 1});
        edge[2].add(new int[]{4, 2});

        edge[3] = new ArrayList<>();
        edge[3].add(new int[]{1, 1});
        edge[3].add(new int[]{2, 1});
        edge[3].add(new int[]{5, 3});

        edge[4] = new ArrayList<>();
        edge[4].add(new int[]{1, 3});
        edge[4].add(new int[]{2, 2});
        edge[4].add(new int[]{5, 2});

        edge[5] = new ArrayList<>();
        edge[5].add(new int[]{3, 3});
        edge[5].add(new int[]{4, 2});
        edge[5].add(new int[]{5, 0});

        new DijkstraByHeap2(edge, nodeNum).start(1);
    }
}

class DijkstraByHeap2 {
    // 엣지 정보 입니다.
    private List<int[]>[] edge;
    // 결과로 출력할 최소 거리 배열 입니다.
    private int[] shortestDistance;
    // 노드의 갯수 입니다.
    private int nodeNum;
    // 정수형 최대값으로 무한값을 대체 합니다.
    private final int INF = Integer.MAX_VALUE;

    DijkstraByHeap2(List<int[]>[] edge, int nodeNum) {
        this.edge = edge;
        this.nodeNum = nodeNum;
        this.shortestDistance = new int[nodeNum + 1];
        for (int i = 1; i <= nodeNum; i++) {
            this.shortestDistance[i] = INF;
        }
    }

    void start(int start) {
        shortestDistance[start] = 0;

        // PriorityQueue 는 최소 힙 구조 입니다.
        Queue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        priorityQueue.add(new int[]{start, 0});

        // 가까운 순서대로 처리하므로 큐를 사용 합니다.
        while (!priorityQueue.isEmpty()) {
            int[] top = priorityQueue.poll();

            final int current = top[0];
            final int distance = top[1];

            // 최단 거리가 아닌 경우 스킵 합니다.
            if (shortestDistance[current] < distance) continue;

            for (int i = 0; i < edge[current].size(); i++) {
                // 선택된 노드의 인접 노드
                int next = edge[current].get(i)[0];
                // 선택된 노드를 인접 노드로 거쳐서 가는 비용
                // 시작노드 부터 선택된노드(current) 까지의 최단 거리 : distance
                // 선택된노드(current) 부터 인접노드(i) 까지 거리 : edge[current].get(i)[1
                int nextDistance = distance + edge[current].get(i)[1];
                // 기존의 최소 비용보다 더 저렴하다면 교체 합니다.
                if (nextDistance < shortestDistance[next]) {
                    shortestDistance[next] = nextDistance;
                    priorityQueue.add(new int[]{next, nextDistance});
                }
            }
        }

        for (int i = 1; i <= nodeNum; i++) {
            System.out.printf("%d ", shortestDistance[i]);
        }
    }
}