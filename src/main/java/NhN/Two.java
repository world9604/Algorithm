package NhN;

import java.util.*;

public class Two {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String en = "enqueue";
        String de = "dequeue";

        for (int i = 0; i < N; i++) {
            String command = sc.next();
            if (en.equals(command)) {
                int x = sc.nextInt();

            } else if (de.equals(command)) {

            }
        }
    }

/*    static class FQ {
        Queue<Integer> queue = new LinkedList<>();

        void enqueue(int x) {
            queue.add(x);
        }

        int dequeue() {
            for (int i = 0; i < queue.size(); i++) {
                queue.peek();
            }
            return ;
        }
    }*/
}

