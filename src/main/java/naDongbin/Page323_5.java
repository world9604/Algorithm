package naDongbin;

public class Page323_5 {
    public static void main(String[] args) {
        String s = "aabbaccc";
        System.out.print(new Page323_3().solution(s));
    }

    public int solution(String s) {
        int answer = 0;

        for(int i=1; i<=(s.length()/2)+1; i++){
            int result = getSplitedLength(s, i, 1).length();
            answer = i==1 ? result : (answer>result?result:answer);
        }

        return answer;
    }

    public String getSplitedLength(String s, int n, int repeat){
        if(s.length() < n) return s;
        String preString = s.substring(0, n);
        String postString = s.substring(n);

        // 불일치 -> 현재까지 [반복횟수 + 반복문자] 조합
        if(!postString.startsWith(preString)){
            if(repeat == 1)
                return preString + getSplitedLength(postString, n, 1);
            else
                return repeat + preString + getSplitedLength(postString, n, 1);
        }

        return getSplitedLength(postString, n, repeat + 1);
    }
}
