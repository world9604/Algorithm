package boj;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BOJ1021_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int result = 0;

        Deque<Integer> deque = new ArrayDeque<>(N);
        for (int i = 0; i < N; i++) {
            //값은 상관 없으니, 1로 deque를 모두 채운다.
            deque.add(1);
        }

        int[] indices = new int[M];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = sc.nextInt();
        }

        for (int i = 0; i < indices.length; i++) {
            int index = indices[i];
            int head = deque.getFirst();
            int tail;

            //해당 index가 head가 될 때 까지
            //f2, f3 연산을 수행하여 덱을 회전한다.
            while(index != head) {
                head = deque.getFirst();
                tail = deque.getLast();

                //인덱스와 head 까지의 거리 : |head - index|
                //인덱스와 tail 까지의 거리 : |tail - index|
                //거리가 가까운쪽으로 더 가까워지도록 회전시킨다.
                if (Math.abs(head - index) > Math.abs(tail - index)) {
                    //f3 연산 수행
                    int tmp = deque.removeLast();
                    deque.addFirst(tmp);
                    result += 1;
                } else {
                    //f2 연산 수행
                    int tmp = deque.removeFirst();
                    deque.addLast(tmp);
                    result += 1;
                }
            }
            deque.removeFirst();
        }
        System.out.println(result);
    }
}