package naDongbin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Page316_3 {

    //https://programmers.co.kr/learn/courses/30/lessons/42891
    public static void main(String[] args) {
        int[] food_times = {3, 1, 2};
        long k = 5;
        System.out.print(new Page316_3().solution(food_times, k));
    }

    public int solution(int[] food_times, long k) {
        List<Food> foodList = new ArrayList<>();
        int id = 0;

        for (int food : food_times) {
            foodList.add(new Food(food, ++id));
        }

        // 먹는 시간별로 정렬
        foodList.sort(new CompTimes());

        long afterStopSec = k;
        int curLength = food_times.length;
        int curId = 0;
        int answer = -1;
        int eatenFoodTimes = 0;

        for (Food f : foodList) {
            // 현재 음식 - 이전 음식 시간
            int curFood = f.times - eatenFoodTimes;
            // 남은 음식 * 먹는 시간이 가장 적은 음식
            long tmp = (long) curLength * curFood;

            // afterStopSec >= tmp(현재 먹을 음식까지의 걸린 총 시간)
            if(afterStopSec >= tmp) {
                afterStopSec -= tmp;
                // curFood : 현째 까지 먹은 음식 시간이 쌓인다.
                eatenFoodTimes += curFood;
            } else {
                // 현재 먹을 음식(남은 음식 중 가장 적게 걸리는 음식)을
                // 남은시간(afterStopSec) 동안 다 못 먹움
                // 앞으로 afterStopSec 초 후의 방송이 멈춤
                List<Food> subList = foodList.subList(curId, food_times.length);
                subList.sort(new CompIdx());
                answer = subList.get((int) (afterStopSec % curLength)).id;
                break;
            }

            // 다 먹은 음식 제외
            curLength--;
            // 현재 음식의 인덱스를 알기 위해서
            curId++;
        }

        return answer;
    }

    private class Food {
        public int times;
        public int id;

        public Food(int times, int id) {
            this.times = times;
            this.id = id;
        }
    }

    private class CompTimes implements Comparator<Food> {
        @Override
        public int compare(Food a, Food b) {
            return Integer.compare(a.times, b.times);
        }
    }

    private class CompIdx implements Comparator<Food> {
        @Override
        public int compare(Food a, Food b) {
            return Integer.compare(a.id, b.id);
        }
    }
}