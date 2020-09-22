package naDongbin;

public class Page312 {
    // 곱하기 혹은 더하기
    // page 312
    public static void main(String[] args) {
        String value = "02984";
        System.out.print(new Page312().solution(value));
    }

    public int solution(String value) {
        // value의 첫번째 char를 int형으로 변환하기
        int total = Integer.parseInt(String.valueOf(value.charAt(0)));
        // 아래와 같은 방법으로도 char to int 변환 가능
        // int total = value.charAt(0) - '0';

        for (int i = 1; i < value.length(); i++) {
            int digi = Integer.parseInt(String.valueOf(value.charAt(i)));

            if (digi == 0 || digi == 1)
                total += digi;
            else if (total == 0 || total == 1)
                total += digi;
            else
                total *= digi;
        }

        return total;
    }
}
