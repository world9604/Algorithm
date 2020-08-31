package naDongbin;

import java.util.LinkedList;
import java.util.Queue;

public class Page152 {
    // 입력 조건
    // 첫째줄에 두 정수 N, M
    // (4 <= N, M <= 200)
    // 다음 N x M의 미로의 정보
    // 시작칸과 마지막 카은 항상 1이다.
    // 0은 괴물 존재, 1은 괴물 없음
    // 출력 조건
    // 첫째 줄에 최소 이동 칸의 개수 출력
    // 입력 예시
    // 5 6
    // 101010
    // 111111
    // 000001
    // 111111
    // 111111
    // 출력 예시
    // 10
    public static void main(String[] args) {
        int N = 5, M = 6;
        int[][] array = {
                {1, 0, 1, 0, 1, 0},
                {1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1}
        };
        int answer = new Page152().solution(N, M, array);
        System.out.print(answer);
    }

    public int solution(int N, int M, int[][] array){
        int count = 0;
        Queue<Integer> queue = new LinkedList();

        int[] indexArray = new int[N * M];
        int indexCount = 0;
        for (int i = 0; i < N*M; i++) {
            indexArray[i] = indexCount;
            indexCount++;
        }

        int nodeIndex = indexArray[0];

        queue.offer(nodeIndex);
        while(!queue.isEmpty()){
            int index = queue.poll();

            // 방문한 노드를 기준으로 주변 노드 방문
            for (int k = 0; k < array[index].length; k++) {
                int indexNode = indexArray[index];
                if (indexNode != 0 && array[index][k] != 0) { // 방문 하지 않았다면
                    queue.offer(indexNode);
                    // 0으로 방문 처리
                    indexArray[index] = 0;
                    count++;
                }
            }
        }

        return count;
    }
}
