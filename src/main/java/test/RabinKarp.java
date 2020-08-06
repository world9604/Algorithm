package test;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {

    public static void main(String[] args) {
        String parent = "abaaccdaabacaba";
        String pattern = "accdaa";
        List<Integer> list = new Solution().solve(parent, pattern);
        for (Integer value : list) {
            System.out.printf("%d번째에서 발견 했습니다.", value);
            System.out.println();
        }
    }
}

class Solution{
    public List<Integer> solve(String parent, String pattern){
        double parentHash = 0, patternHash = 0, power = 1;
        int parentLen = parent.length();
        int patternLen = pattern.length();

        List<Integer> findedList = new ArrayList<>();

        for (int i = 0; i < parentLen - patternLen; i++) {
            if (i == 0) {
                for(int j = 0; j < patternLen; j++){
                    parentHash = parentHash + parent.charAt(patternLen - 1 - j) * power;
                    patternHash = patternHash + pattern.charAt(patternLen - 1 - j) * power;
                    if(j < patternLen - 1) power = power * 2;
                }
            } else {
                parentHash = 2 * (parentHash - (parent.charAt(i - 1) * power)) + parent.charAt(patternLen - 1 + i);
            }

            // 충돌이 일어났을 경우 대비
            if(parentHash == patternHash){
                boolean finded = true;
                for(int j = 0; j < patternLen; j++){
                    if (parent.charAt(i + j) != pattern.charAt(j)) {
                        finded = false;
                        break;
                    }
                }

                if (finded) {
                    findedList.add(i + 1);
                }
            }
        }

        return findedList;
    }
}
