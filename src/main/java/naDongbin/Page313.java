package naDongbin;

import java.util.ArrayList;
import java.util.List;

public class Page313 {
    // 첫째 줄에 0과 1로만 이루어진 문자열 S
    // S < 1000000
    // 최소 연속적으로 바꿔치기 할수 있는 행동의 횟수 출력
    // 입력
    // 0001100
    // 출력
    // 1
    public static void main(String[] args) {
        System.out.print(new Page313().soultion("0001100"));
    }

    int soultion(String S){
        // 문자열이 연속적인 패턴을 찾음
        // 예로 000, 11, 00
        // 0인 경우 : 2개
        // 1인 경우 : 1개
        // 최소 값 : 1개
        // 적은 패턴의 갯수를 카운트 후 반환
        // S를 패턴으로 쪼갬
        // 문자가 연속된지 알아야함
        // 현재 문자와 이전 문자가 같으면 같은 그룹
        char[] chars = S.toCharArray();
        PatternInfo zeroPattern = new PatternInfo(0,0);
        PatternInfo onePattern = new PatternInfo(0,0);
        List<PatternInfo> zeroPatterns = new ArrayList<>();
        List<PatternInfo> onePatterns = new ArrayList<>();

        // 시간 복잡도 : 1000000
        for (int i = 1; i < chars.length; i++) {
            if ('1' == chars[i - 1] && '1' == chars[i]) {
                onePattern.setEndIndex(onePattern.getEndIndex() + 1);
            } else if ('0' == (int) chars[i - 1] && '0' == (int) chars[i]) {
                zeroPattern.setEndIndex(zeroPattern.getEndIndex() + 1);
            } else if ('1' != (int)chars[i-1] && '1' == (int)chars[i]) {
                onePatterns.add(onePattern);
                onePattern = new PatternInfo(i, i);
            } else if ('0' != (int)chars[i-1] && '0' == (int)chars[i]) {
                zeroPatterns.add(zeroPattern);
                zeroPattern = new PatternInfo(i, i);
            }

            if(i == chars.length-1){
                if('1' == chars[i])
                    onePatterns.add(onePattern);
                else if('0' == chars[i])
                    zeroPatterns.add(zeroPattern);
            }

        }

        System.out.printf("0 size : %d", zeroPatterns.size());
        System.out.println();
        System.out.printf("1 size : %d", onePatterns.size());
        System.out.println();
        if (zeroPatterns.size() > onePatterns.size())
            return onePatterns.size();
        else
            return zeroPatterns.size();
    }
}

class PatternInfo{
    int startIndex;
    int endIndex;

    public PatternInfo(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}