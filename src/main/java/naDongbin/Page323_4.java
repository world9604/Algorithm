package naDongbin;

public class Page323_4 {
    public static void main(String[] args) {
        String s = "aabbaccc";
        System.out.print(new Page323_4().solution(s));
    }

    public String solution(String str) {
        String totalCompStr = str;
        int halfLen = str.length() / 2;

        // 1글자 압축 부터 halfLen 까지 순차 압축
        for (int compSize = 1; compSize <= halfLen; compSize++) {
            String compStr = "";
            String prevStr = str.substring(0, compSize);
            int cnt = 1;

            for (int i = compSize; i < str.length(); i += compSize) {
                String subStr = "";

                // subStr = str.substring(i, i + compSize);
                // 주의 위와 같이 subStr 을 구하면, compSize 길이 만큼
                // 딱 안떨어지는 문자열들은 담을 수 없다.
                for (int k = i; k < i + compSize; k++) {
                    if (k < str.length())
                        subStr += str.charAt(k);
                }

                if (prevStr.equals(subStr)){
                    cnt++;
                } else {
                    compStr += compress(prevStr, cnt);
                    prevStr = subStr;
                    cnt = 1;
                }
            }
            compStr += compress(prevStr, cnt);

            if (totalCompStr.length() > compStr.length())
                totalCompStr = compStr;
        }
        return totalCompStr;
    }

    private String compress(String prevStr, int cnt) {
        String compStr;
        if (cnt > 1)
            compStr = cnt + prevStr;
        else
            compStr = prevStr;
        return compStr;
    }
}
