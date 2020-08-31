package kakao;

public class LyricsSearchProblem {

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        LyricsSearchProblem problem = new LyricsSearchProblem();
        int[] answers = problem.solution(words, queries);

        for (int answer : answers) {
            System.out.printf("%d ", answer);
        }
    }

    // 문제 : https://programmers.co.kr/learn/courses/30/lessons/60060
    // words : ["frodo", "front", "frost", "frozen", "frame", "kakao"]
    // queries : ["fro??", "????o", "fr???", "fro???", "pro?"]
    // answer : [3, 2, 4, 1, 0]
    public int[] solution(String[] words, String[] queries) {
        /* case2 : kmp
        words 의 전체 문자열을 모두 합친다(String[] / String totalString)

        for query in queries
            query의 단어들의 일치 테이블을 만든다(String, / int[])
            totalString, int[]을 통해 kmp 알고리즘 수행(String, String, int[] / int count)
            count를 answers[]의 담는다

        return answers[];

        int kmp(String parent, String pattern, int[] equalTable){
            count = 0

            for i  in parent
                for k in pattern
                    if(i == k)
        }
        */

        int[] answer = new int[queries.length];
        StringMatching stringMatching = new StringMatching();

        // words 의 전체 문자열을 모두 합친다(String[] / String totalString)
        String totalString = concat(words);

        // for query in queries
        for (int i = 0; i < queries.length; i++) {
            // totalString, int[]을 통해 kmp 알고리즘 수행(String, String, int[] / int count)
            int count = stringMatching.kmp(totalString, queries[i]);
            // count를 answers[]의 담는다
            answer[i] = count;
        }

        return answer;
    }

    private String concat(String[] words) {
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            sb.append(word);
        }

        return sb.toString();
    }
}

class StringMatching {

    public int kmp(String parent, String pattern) {
        int[] table = makeTable(pattern);
        char[] parents = parent.toCharArray();
        char[] patterns = pattern.toCharArray();
        int k = 0;
        int count = 0;

        for (int i = 0; i < parents.length; i++) {
            // 문자가 같지 않다면 일치 테이블 길이 만큼 건너 뜀
            while (k > 0 && !isEqual(parents[i], patterns[k])) {
                k = table[k - 1];
            }
            if (isEqual(parents[i], patterns[k])) {
                if (k == patterns.length - 1) {
                    System.out.printf("%d번째에서 찾았습니다.\n", i - patterns.length + 2);
                    k = table[k];
                    count++;
                } else {
                    k++;
                }
            }
        }
        System.out.println();
        return count;
    }

    private int[] makeTable(String pattern) {
        char[] patterns = pattern.toCharArray();
        int[] table = new int[patterns.length];
        int k = 0;
        for (int i = 1; i < patterns.length; i++) {
            while (k > 0 && !isEqual(patterns[i], patterns[k])) {
                k = table[k - 1];
            }
            if (isEqual(patterns[i], patterns[k])) {
                table[i] = ++k;
            }
        }
        return table;
    }

    // 와일드 카드('?')는 모두 같다고 한다.
    private boolean isEqual(char parentChar, char patternChar){
        if (patternChar == '?')
            return true;
        else
            return parentChar == patternChar;
    }
}
