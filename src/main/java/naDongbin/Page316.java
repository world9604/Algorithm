package naDongbin;

public class Page316 {
    // food_times 배열을 완전 탐색으로 돌면서
    // -1 씩 감소, 0 이면 건너뜀
    // k 번 움직였을때,
    // 해당 인덱스 출력
    public int solution(int[] food_times, long k) {
        int answer = 0;
        int lastEatenFoodIdx = 0;
        int n = food_times.length;

        for (int i = 0; i < k; i++) {
            // 한번 순회 다함.
            if (i >= n)
                i = 0;
            // 0 이면 건너뜀
            if (food_times[i] == 0) continue;
            // k초 마지막 음식 섭취
            if (i == (k - 1)) {
                lastEatenFoodIdx = i;
            }
            // 음식을 다먹으면 return -1

            // 먹을 음식이 있으면 -1
            food_times[i] -= 1;
        }

        // 인덱스 i, 다음 인덱스 중,
        // 값이 0 이 아닌 인덱스(+1/인덱스는 0 부터 시작하기 때문) 반환
        for (int i = 1; i < n + 1; i++) {
            if (lastEatenFoodIdx + i >= n)
                lastEatenFoodIdx = 0;
            if (food_times[lastEatenFoodIdx + i] != 0)
                return lastEatenFoodIdx + i + 1;
        }

        return answer;
    }
}