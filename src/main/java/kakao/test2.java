package kakao;


import java.util.ArrayList;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
        test2 test2 = new test2();
        String[] orders = new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = new int[]{2, 3, 4};
        test2.solution(orders, course);
    }

    public String[] solution(String[] orders, int[] course) {
        int n = orders.length;
        orderPers = new ArrayList();

        // orders 반복문
        for (int i = 0; i < n; i++) {
            String order = orders[i];
            // 예 : "ABCFG" -> "A", "B", "C", "F", "G"
            String[] orderArr = new String[order.length()];
            for (int k = 0; k < order.length(); k++) {
                String str = String.valueOf(order.charAt(k));
                orderArr[k] = str;
            }

            // course 값의 맞는 조합들을 만들어 담음.
            for (int j = course.length - 1; j >= 0; j--) {
                permutation(orderArr, course[j], course[j]);
            }
        }

        List<String> answerList = new ArrayList<>();
        boolean[] skips = new boolean[orderPers.size()];
        // orderPers 중 2개 이상 카운트 되는 것만 추출
        for (int i = 0; i < orderPers.size(); i++) {

            if (skips[i]) continue;
            String orderPer = orderPers.get(i);
            int cnt = 0;

            for (int j = 0; j < orderPers.size(); j++) {
                if (orderPer.equals(orderPers.get(j))) {
                    cnt++;
                    if (cnt >= 2) {
                        answerList.add(orderPer);
                        skips[j] = true; // i 인덱스 검사시 스킵
                        break;
                    }
                }
            }
        }

        String[] answer = new String[answerList.size()];
        answer = answerList.toArray(answer);
        return answer;
    }

    List<String> orderPers;

    // 순열 구하기
    void permutation(String[] arr, int depth, int r) {
        if (depth == r) {
            String tmp = "";

            for (int i = 0; i < r; i++)
                tmp += arr[i];

            orderPers.add(tmp);
        }

        /*for (int i = depth; i < arr.length; i++) {
            //swap(arr, depth, i);
            permutation(arr, depth + 1, r);
            //swap(arr, depth, i);
        }*/
    }

    void swap(String[] arr, int a, int b) {
        String tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}