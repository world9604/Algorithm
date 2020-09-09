package naDongbin;

import java.util.Arrays;

public class Page361 {
    /*
    * N     stages                      result
    * 5     [2, 1, 2, 6, 2, 4, 3, 3]    [3,4,2,1,5]
    * 4     [4,4,4,4,4]                 [4,1,2,3]
    * */
    public static void main(String[] args) {
        int N = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        System.out.println(new Page361().solution(N, stages));
    }

    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        FailRate[] failRates = new FailRate[N];

        // 실패율 = 클리어 못한 플레이어 수(m) / 스테이지에 도달한 플레이어 수(k)
        // 1 <= stages <= 200000
        // stages 의 원소를 돌면서,
        // 1번 ~ N번 스테이지 도전자를 구함(k = 현재 스테이지(인덱스) 보다 같거나 큰 값 갯수)
        // 1번 ~ N번 스테이지 중 실패한 도전자 수 구함(m = 현재 스테이지(인덱스)의 갯수)
        // 실패율 = m / k
        // 실패율 내림차순 정렬

        int n = stages.length;
        double rate;

        // 500 * 200000 = 100000000 (1초에 2000만 에서 1억 연산)
        for (int i = 0; i < N; i++) { // 현재 스테이지
            int k = 0; // 플레이어수
            int m = 0; // 실패한 플레이어수

            for (int j = 0; j < n; j++) {
                if (i+1 <= stages[j]) k++;
                if (i+1 == stages[j]) m++;
            }

            if (m == 0 || k == 0) rate = 0;
            else rate = (double)m / (double)k;

            failRates[i] = new FailRate(i+1, rate);
        }

        Arrays.sort(failRates);

        for (int i = 0; i < N; i++) {
            FailRate failRate = failRates[i];
            answer[i] = failRate.id;
        }
        return answer;
    }

    private class FailRate implements Comparable<FailRate>{
        int id;
        double rate;

        FailRate(int id, double rate) {
            this.id = id;
            this.rate = rate;
        }

        @Override
        public int compareTo(FailRate o) {
            // 내림 차순 정렬
            return Double.compare(o.rate, this.rate);
        }
    }
}

