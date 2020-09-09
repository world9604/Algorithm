package naDongbin;

public class Page316 {

    //https://programmers.co.kr/learn/courses/30/lessons/42891
    // food_times 배열을 완전 탐색으로 돌면서
    // -1 씩 감소, 0 이면 건너뜀
    // k 번 움직였을때,
    // 해당 인덱스 출력
    // 전체 길이 : 3 / k : 5
    // 5 / 3 = 1...2
    // 전체 1바퀴 돌고(전체 -1) 2번째 인덱스
    /////////////???????????
    // 4 3 2 1 5 2 1 1
    // 3 2 1 0 4 1 0 0 (한 바퀴 - n초 지남)
    // 2 1 0 0 3 0 0 0 (두 바퀴 - 5초 지남)
    // 전체 n 번 돌면서 0의 갯수를 구한다
    // 인덱스 = k % (n - (0의 갯수)) -> 하지만 매번, 0의 갯수가 바뀐다
    // (n - (0의 갯수)) -> 이 갯수가 매번 바뀐다.
    public static void main(String[] args) {
        int[] food_times = {3, 1, 2};
        long k = 5;
        System.out.print(new Page316().solution(food_times, k));
    }

    public int solution(int[] food_times, long k) {
        int answer = 0;
        int lastEatenFoodIdx = -1;
        int n = food_times.length;
        int eatenFoodCnt = 0;

        // 0초 ~ k초 까지
        for (int i = 0; i < k; i++) {
            lastEatenFoodIdx++;

            // 한번 순회 다함.
            if (lastEatenFoodIdx >= n)
                lastEatenFoodIdx = 0;

            // 0 이면 건너뜀
            if (food_times[lastEatenFoodIdx] == 0) {
                lastEatenFoodIdx++;
                eatenFoodCnt++;
                // 한번 순회 다함.
                if (lastEatenFoodIdx >= n)
                    lastEatenFoodIdx = 0;
                // 음식을 다먹으면 return -1
                if (eatenFoodCnt == n)
                    return -1;
            }

            // 먹을 음식이 있으면 -1
            food_times[lastEatenFoodIdx] -= 1;
        }

        // 인덱스 i, 다음 인덱스 중,
        // 값이 0 이 아닌 인덱스(+1/인덱스는 0 부터 시작하기 때문) 반환
        // 시간 복잡도 : 2000
        for (int i = 1; i < n + 1; i++) {
            if (lastEatenFoodIdx + i >= n)
                lastEatenFoodIdx = -1;
            if (food_times[lastEatenFoodIdx + i] != 0)
                return lastEatenFoodIdx + i + 1;
        }

        return answer;
    }
}