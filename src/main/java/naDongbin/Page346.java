package naDongbin;

public class Page346 {
    /*
    * p             result
    * "(()())()"    "(()())()"
    * ")("          "()"
    * "()))((()"    "()(())()"
    */
    public static void main(String[] args) {
        String p = "()))((()";
        System.out.print(new Page346().solution(p));
    }

    public String solution(String p) {
        return getCorrectStr(p);
    }

    private String getCorrectStr(String p) {
        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환 합니다.
        if (p.equals("")) return p;

        // p가 '올바른 문자열' 이면 그냥 반환
        if (isCorrectStr(p)) return p;

        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
        String[] uAndV = getBalancedStrs(p);
        String u = uAndV[0];
        String v = uAndV[1];

        // 3. 문자열 u가 '올바른 괄호 문자열' 이라면 문자열 v에 대해 1단계부터 다시 수행.
        if (isCorrectStr(u)) {
            return u + getCorrectStr(v);
        } else {
            // 4. 문자열 u가 '올바른 괄호 문자열'이 아니라면 아래 과정을 수행
            // 4-1. 빈 문자열에 첫번쨰 문자로 '('를 붙입니다.
            String str = "(";
            // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
            str += getCorrectStr(v);
            // 4-3. ')'를 다시 붙입니다.
            str += ')';
            // 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
            char[] chars = u.toCharArray();
            String resultStr = "";
            // 4-4. 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
            for (int i = 1; i < chars.length-1; i++) {
                if (chars[i] == '(') {
                    resultStr += ')';
                } else if (chars[i] == ')') {
                    resultStr += '(';
                }
            }
            str += resultStr;

            // 4-5. 생성된 문자열을 반환합니다.
            return str;
        }
    }

    private boolean isCorrectStr(String p) {
        char[] chars = p.toCharArray();
        int openBracketCnt = 0;
        int closeBracketCnt = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(')
                openBracketCnt++;
            else
                closeBracketCnt++;

            if (closeBracketCnt > openBracketCnt) return false;
        }
        return true;
    }

    private String[] getBalancedStrs(String p) {
        char[] chars = p.toCharArray();
        int openBracketCnt = 0;
        int closeBracketCnt = 0;
        int boundaryIdx = p.length() - 1;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(')
                openBracketCnt++;
            else
                closeBracketCnt++;

            if (openBracketCnt == closeBracketCnt) {
                boundaryIdx = i;
                break;
            }
        }

        String u = p.substring(0, boundaryIdx + 1);
        String v = p.substring(boundaryIdx + 1);
        return new String[]{u, v};
    }
}
