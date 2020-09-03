package naDongbin;

public class Page323 {
    //입력 예시
    //"aabbaccc"
    //출력 예시
    //7 (2a2ba3c)
    //입력 예시
    //"ababcdcd ababcdcd"
    //출력 예시
    //9 (2ababcdcd)
    //s는 1000이하 길이
    public static void main(String[] args) {
        String s = "aabbaccc";
        System.out.print(new Page323().solution(s));
    }

    private int solution(String s) {
        //최대 1000의 반 500까지 압축 할수 있습니다.
        //문자열 s의 반 까지 압축 할수 있다.
        //끝부터 압축을 시작하여
        //압축이 되면 바로 리턴
        String compressedString = compressString(s, 0);
        System.out.printf("compressedString : %s", compressedString);
        System.out.println();
        return compressedString.length();
    }

    private String compressString(String compressedString, int beginIdx){
        final String frontS = compressedString.substring(0, beginIdx);
        final String s = compressedString.substring(beginIdx);
        final int halfLen = s.length() / 2;

        if(halfLen == 1) {
            return compressedString;
        }

        //문자 압축 길이(compLen)을 점점 줄여가며 압축
        for (int compLen=halfLen; compLen >= 1; compLen--) {
            String preSubS = "";
            int iterNum = 1;

            // 압축
            for (int j = 0; j < s.length(); j+=compLen) {
                // 압축 할 끝 인덱스가 넘어 버리면, 압축 못함
                if ((j+compLen) > s.length())
                    break;

                // 압축을 하기 위한 시작 인덱스, 끝 인덱스
                String subS = s.substring(j, j+compLen);

                if(preSubS.equals(subS)){
                    iterNum++;
                    if(j == s.length()-1){
                        String compS = getCompressedStr(s, preSubS, iterNum, j-(iterNum*compLen), j-(iterNum*compLen) + (iterNum*compLen));
                        // 전체 s의 길이가 달라졌으므로 압축된 문자열로 compressString()
                        return frontS + compressString(compS, iterNum*compLen);
                    }
                }else{
                    if(iterNum != 1){
                        String compS = getCompressedStr(s, preSubS, iterNum, j-(iterNum*compLen), j-(iterNum*compLen) + compLen);
                        // 전체 s의 길이가 달라졌으므로 압축된 문자열로 compressString()
                        return frontS + compressString(compS, iterNum*compLen);
                    }
                }

                preSubS = subS;
            }
        }

        return s;
    }

    private String getCompressedStr(String s, String preSubS, int iterNum, int startIdx, int endIdx) {
        // 압축 전 앞 문자열 + 압축 숫자 + 압축 문자열 + 압축 뒤의 문자열
        return s.substring(0, startIdx) + iterNum + preSubS + s.substring(endIdx + 1);
    }
}


