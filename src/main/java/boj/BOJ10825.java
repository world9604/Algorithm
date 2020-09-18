package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ10825 {
    // https://www.acmicpc.net/problem/10825
    // 국영수
    // 1 <= N <= 100000
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<GradeCard> gradeCards = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());

            GradeCard gradeCard = new BOJ10825().new GradeCard(name, korean, english, math);
            gradeCards.add(gradeCard);
        }

        System.out.print(new BOJ10825().solution(N, gradeCards));
    }

    public String solution(int N, List<GradeCard> gradeCards) {
        // 1. 국어 감소
        // 2. 영어 증가
        // 3. 수학 감소
        // 4. 이름이 사전 순
        // 위 순서대로 정렬 기준을 정해서 Arrays.sort()
        // 한번 만 정렬
        // 시간 복잡도 : O(n log n)
        Collections.sort(gradeCards);
        StringBuilder sb = new StringBuilder();
        for (GradeCard gradeCard : gradeCards) {
            sb.append(gradeCard.name).append(System.lineSeparator());
        }
        return sb.toString();
    }

    class GradeCard implements Comparable<GradeCard>{
        String name;
        int korean;
        int english;
        int math;

        public GradeCard(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(GradeCard other) {
            final int POSITIVE_NUM = 1;
            final int NEGATIVE_NUM = -1;

            if (korean < other.korean)
                return POSITIVE_NUM;
            else if (korean > other.korean)
                return NEGATIVE_NUM;

            if (english > other.english)
                return POSITIVE_NUM;
            else if (english < other.english)
                return NEGATIVE_NUM;

            if (math < other.math)
                return POSITIVE_NUM;
            else if (math > other.math)
                return NEGATIVE_NUM;

            // 영어 이름 알파베티컬 하게 비교
            return name.compareTo(other.name);
        }
    }
}
