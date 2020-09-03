package naDongbin;


public class Page313_2 {
    // 첫째 줄에 0과 1로만 이루어진 문자열 S
    // S < 1000000
    // 최소 연속적으로 바꿔치기 할수 있는 행동의 횟수 출력
    // 입력
    // 0001100
    // 출력
    // 1
    public static void main(String[] args) {
        System.out.print(new Page313_2().soultion("0001100"));
    }

    int soultion(String S){
        // 문자열이 연속적인 패턴을 찾음
        // 예로 000, 11, 00
        // 0인 경우 : 2개
        // 1인 경우 : 1개
        // 최소 값 : 1개
        // 적은 패턴의 갯수를 카운트 후 반환
        // 문자가 연속된지 알아야함
        // 현재 문자와 이전 문자가 같으면 같은 그룹
        char[] chars = S.toCharArray();
        int zeroPattern = 0;
        int onePattern = 0;

        if('1' == chars[0]) onePattern++;
        else zeroPattern++;

        // 시간 복잡도 : 1000000
        for (int i = 1; i < chars.length; i++) {
            if ('1' != chars[i-1] && '1' == chars[i]) {
                onePattern++;
            } else if ('0' != chars[i-1] && '0' == chars[i]) {
                zeroPattern++;
            }
        }
        return Math.min(zeroPattern, onePattern);
    }
}