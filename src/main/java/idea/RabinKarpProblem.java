package idea;


import java.util.ArrayList;
import java.util.List;

public class RabinKarpProblem {
    /**
     * @참고 https://blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221240679247&parentCategoryNo=&categoryNo=128&viewDate=&isShowPopularPosts=false&from=postList
     * @내용 해시를 이용한 문자열 매칭 알고리즘
     */
    public static void main(String[] args) {
        String parent = "abaaccdaabacaba";
        String pattern = "accdaa";
        List<Integer> list = new RabinKarp().solve(parent, pattern);
        for (Integer value : list) {
            System.out.printf("%d번째에서 발견했습니다.", value);
            System.out.println();
        }
    }
}

class RabinKarp {
    List<Integer> solve(String parent, String pattern) {
        double parentHash = 0, patternHash = 0, power = 1;
        int parentLen = parent.length();
        int patternLen = pattern.length();
        // 패턴이 일치하는 위치를 저장할 리스트
        List<Integer> findedList = new ArrayList<>();

        for (int i = 0; i < parentLen - patternLen; i++) {
            if (i == 0) {
                // 전체 문자열에 첫 파트의 hash 코드 구하기
                // 패턴 문자열 hash 코드 구하기
                for (int j = 0; j < patternLen; j++) {
                    parentHash = parentHash + parent.charAt(patternLen - 1 - j) * power;
                    patternHash = patternHash + pattern.charAt(patternLen - 1 - j) * power;
                    if (j < patternLen - 1) power = power * 2;
                }
            } else {
                // 긴글 해시 값 = 2 * (첫파트 해시 값 - 가장 앞에 있는 문자) + 새롭게 들어온 문자
                parentHash = 2 * (parentHash - (parent.charAt(i - 1) * power)) + parent.charAt(patternLen - 1 + i);
            }

            // 충돌이 일어났을 경우 대비
            if (parentHash == patternHash) {
                // hash 코드가 같아도 문자열이 다를 수 있기 때문에 (충돌)
                // 다시 한번 문자열이 맞는지 검사 한다.
                boolean finded = true;
                for (int j = 0; j < patternLen; j++) {
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


