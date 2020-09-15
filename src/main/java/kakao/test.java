package kakao;

public class test {
    public static void main(String[] args) {
        String a = "abcdefghijklmn.p";
        test test = new test();
        System.out.print(test.solution(a));
    }

    String answer;

    public String solution(String new_id) {
        this.answer = new_id;

        // 1.대문자 -> 소문자
        // 2.소문자, 숫자, 빼기('-'), 밑줄('_'), 마침표('.') 제외한 문자 제거
        // 3.마침표 2개 이상이면 1개로
        // 4.마침표 처음과 끝 제거
        // 5. 빈문자열이면 'a' 삽입
        // 6. 길이가 16이상이면 15길이 외의 제거
        // 7. 길이가 2이하면 마지막 문자를 길이가 3이 될때까지 반복

        // 1.대문자 -> 소문자
        getLowerCase();
        // 2.소문자, 숫자, 빼기('-'), 밑줄('_'), 마침표('.') 제외한 문자 제거
        removeUnnecessaryChar();
        // 3.마침표 2개 이상이면 1개로
        removeOneMoreClose();
        // 4.마침표 처음과 끝 제거
        removeStartEndClose();
        // 5. 빈문자열이면 'a' 삽입
        insertA();
        // 6. 길이가 16이상이면 15길이 외의 제거
        remove16Chars();
        // 7. 길이가 2이하면 마지막 문자를 길이가 3이 될때까지 반복
        shortLength();

        return answer;
    }

    // 1.대문자 -> 소문자
    void getLowerCase() {
        answer = answer.toLowerCase();
    }

    // 2.소문자, 숫자, 빼기('-'), 밑줄('_'), 마침표('.') 제외한 문자 제거
    void removeUnnecessaryChar() {
        int n = answer.length();
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (45 == answer.charAt(i) || 46 == answer.charAt(i) ||
                    95 == answer.charAt(i)) {
                tmp.append(answer.charAt(i));
                continue;
            }

            if (33 <= answer.charAt(i) && 47 >= answer.charAt(i)) {
                continue;
            }
            if (58 <= answer.charAt(i) && 96 >= answer.charAt(i)) {
                continue;
            }
            if (123 <= answer.charAt(i) && 126 >= answer.charAt(i)) {
                continue;
            }
            tmp.append(answer.charAt(i));
        }
        answer = tmp.toString();
    }

    // 3.마침표 2개 이상이면 1개로
    void removeOneMoreClose() {
        int n = answer.length();
        String tmp = "";
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (start <= i && end > i) continue;

            tmp += answer.charAt(i);

            if ('.' == answer.charAt(i)) {
                start = i;
                for (int j = i + 1; j < n; j++) {
                    if ('.' != answer.charAt(j)) {
                        end = j;
                        break;
                    }
                    end = n;
                }
            }
        }

        answer = tmp;
    }

    // 4.마침표 처음과 끝 제거
    void removeStartEndClose() {
        if ('.' == answer.charAt(0))
            answer = answer.substring(1);
        if (answer.length() > 0 && '.' == answer.charAt(answer.length() - 1))
            answer = answer.substring(0, answer.length() - 1);
    }

    // 5. 빈문자열이면 'a' 삽입
    void insertA() {
        if ("".equals(answer))
            answer += "a";
    }

    // 6. 길이가 16이상이면 15길이 외의 제거
    void remove16Chars() {
        if (answer.length() >= 15) {
            answer = answer.substring(0, 15);
            if (answer.length() > 0 && '.' == answer.charAt(answer.length() - 1))
                answer = answer.substring(0, answer.length() - 1);
        }
    }

    // 7. 길이가 2이하면 마지막 문자를 길이가 3이 될때까지 반복
    void shortLength() {
        if (answer.length() <= 2) {
            char last = answer.charAt(answer.length() - 1);
            while (answer.length() < 3) {
                answer += last;
            }
        }
    }
}