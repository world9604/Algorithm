package kakao;

public class LyricsSearchProblem2 {

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] answers = {3, 2, 4, 1, 0};

        LyricsSearchProblem2 problem = new LyricsSearchProblem2();
        int[] expectedAnswers = problem.solution(words, queries);
        for (int i = 0; i < answers.length; i++) {
            System.out.printf("%d ", expectedAnswers[i]);
        }
    }

    // 문제 : https://programmers.co.kr/learn/courses/30/lessons/60060
    // words : ["frodo", "front", "frost", "frozen", "frame", "kakao"]
    // queries : ["fro??", "????o", "fr???", "fro???", "pro?"]
    // answer : [3, 2, 4, 1, 0]
    public int[] solution(String[] words, String[] queries) {
        /* case1 : 전체길이 비교 + 순차 탐색
        for i in queries
            queries 의 전체 문자열 길이를 구함(String word / int)
                for i in words
                    queries 의 전체 문자열 길이와 words 의 전체 문자열 길이 비교 후 같은 것만 선별(String word, String compWord  / boolean)
                    queries 의 첫 문자 부터 words 의 첫 문자와 비교, '?'는 건너뜀 (순차 탐색)(String word, String compWord / boolean)
                    queries 단어의 해당 되는 카운트 변수 +1
        카운트 변수를 answer 배열의 추가
        */

        //최악 시간 복잡도 : 100000(queries의 개수) * 100000(words.length) * 10000(query 의 길이 1이상)
        // = 100,000,000,000,000
        int answer[] = new int[queries.length];

        //for i in queries
        //시간 복잡도 : queries의 개수는 2 이상 100000 이하입니다.
        for (int i = 0; i < queries.length; i++) {
            //queries 의 전체 문자열 길이를 구함
            int queryLen = getLength(queries[i]);
            int equalCount = 0;

            //for i in words
            //시간 복잡도 : words.length 는 2이상 100000이하
            for (int j = 0; j < words.length; j++) {
                //queries 의 전체 문자열 길이와
                //words 의 전체 문자열 길이 비교
                int wordLen = getLength(words[j]);
                if (queryLen == wordLen) {
                    //queries 의 첫 문자 부터 words 의 첫 문자와 비교,
                    //'?'는 건너뜀 (순차 탐색)
                    //시간 복잡도 : query 의 길이 1이상 10000이하
                    if(isEqual(queries[i], words[j])){
                        //queries 단어의 해당 되는 카운트 변수 +1
                        equalCount++;
                    }
                }
            }
            //카운트 변수를 answer 배열의 추가
            answer[i] = equalCount;
        }
        return answer;
    }

    /*
     * queries, words 각 문자 순차 비교
     * '?'는 건너뜀
     */
    private boolean isEqual(String queryStr, String wordStr) {
        char[] queryChars = queryStr.toCharArray();
        char[] wordChars = wordStr.toCharArray();

        for (int i = 0; i < queryChars.length; i++) {
            if (queryChars[i] == '?') continue;
            if (queryChars[i] != wordChars[i]) return false;
        }
        return true;
    }

    //문자열 길이 반환
    private int getLength(String str) {
        return str.length();
    }
}
