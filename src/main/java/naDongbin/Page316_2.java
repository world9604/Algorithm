package naDongbin;

import java.util.PriorityQueue;
import java.util.Queue;

public class Page316_2 {

    //https://programmers.co.kr/learn/courses/30/lessons/42891
    public static void main(String[] args) {
        int[] food_times = {3, 1, 2, 2, 1};        long k = 5;
        System.out.print(new Page316_2().solution(food_times, k));
    }

    public int solution(int[] food_times, long k) {
        //(음식 먹는 시간, 음식의 번호) 큐의 넣음
        //음식 먹는 시간이 짧은 것 부터 빼냄
        //k -= (남은 음식의 갯수 x 현재 음식을 먹는 시간) 반복
        //(남은 음식의 갯수 x 현재 음식을 먹는 시간) > k 면,
        //현재 음식 부터 k초 뒤의 음식을 출력

        int n = food_times.length;
        Queue<Node> queue = new PriorityQueue<>();

        // 전체 음식을 큐에 넣음
        for (int i = 0; i < n; i++) {
            queue.offer(new Node(i + 1, food_times[i]));
        }

        // 큐에서 음식 먹는 시간이 적은것 부터 빼낸다
        for (int i = 0; i < n; i++) {
            Node node = queue.poll();
            if (queue.size() == 0) {
                return (k > node.timeToEatFood) ? -1 : node.foodIdx;
            }
            // k - (먹는 시간 * 남은 음식)
            long timeToEat = node.timeToEatFood * queue.size();
            if (k < timeToEat) {
                break;
            } else {
                k -= timeToEat;
            }
        }

        // 남은 음식들 중 k초 후의 먹을 음식 출력
        for (int i = 0; i < k; i++) {
            Node node = queue.peek();
            node.timeToEatFood -= 1;
            if (node.timeToEatFood == 0) {
                queue.remove();
            }
        }

        // k초 후 남아있는 음식 출력
        Node node = queue.poll();
        if (node == null)
            return -1;
        else
            return node.foodIdx;
    }

    class Node implements Comparable<Node>{
        public int foodIdx;
        public long timeToEatFood;

        public Node(int foodIdx, long timeToEatFood) {
            this.foodIdx = foodIdx;
            this.timeToEatFood = timeToEatFood;
        }

        @Override
        public int compareTo(Node node) {
            return (int) (timeToEatFood - node.timeToEatFood);
        }
    }
}
