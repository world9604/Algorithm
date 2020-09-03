package naDongbin;

public class Page323_6 {
    public static void main(String[] args) {
        String s = "aabbaccc";
        System.out.print(new Page323_6().solution(s));
    }

    public int solution(String s){
        int halfLen = s.length() / 2;
        int answer = Integer.MAX_VALUE;

        // i는 압축할 문자열 길이
        for (int i = 1; i <= halfLen; i++) {
            String str = compressStr(s, i, 1);
            answer = answer > str.length() ? str.length() : answer;
        }
        return answer;
    }

    private String compressStr(String s, int compSize, int repeat) {
        // 압축 사이즈 보다 작으면, 문자열 그대로 리턴
        if (s.length() < compSize) return s;
        String prevStr = s.substring(0, compSize);
        String postStr = s.substring(compSize);

        // 같지 않다면, 현재까지 문자열 + 반복 횟수 + 압축 문자 + 나머지 문자열
        if (!postStr.startsWith(prevStr)) {
            if (repeat == 1)
                return prevStr + compressStr(postStr, compSize, 1);
            else
                return repeat + prevStr + compressStr(postStr, compSize, 1);
        }

        return compressStr(postStr, compSize, repeat + 1);
    }
}
