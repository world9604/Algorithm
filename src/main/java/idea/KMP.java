package idea;

import java.util.Arrays;

public class KMP {
    /**
     * @참고 https://blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221240660061&categoryNo=128&parentCategoryNo=0&viewDate=&currentPage=4&postListTopCurrentPage=1&from=postList&userTopListOpen=true&userTopListCount=5&userTopListManageOpen=false&userTopListCurrentPage=4
     * @내용 KMP (Knuth-Morris-Pratt) 알고리즘 : 문자열 매칭 알고리즘
     */
    public static void main(String[] args) {
        String parent = "ababacabacaabacaaba";
        String pattern = "abacaaba";
        kmp(parent, pattern);
    }

    static void kmp(String parent, String pattern) {
        int[] table = makeTable(pattern);
        char[] parents = parent.toCharArray();
        char[] patterns = pattern.toCharArray();
        int k = 0;
        for (int i = 0; i < parents.length; i++) {
            while (k > 0 && parents[i] != patterns[k]) {
                k = table[k - 1];
            }
            if (parents[i] == patterns[k]) {
                if (k == patterns.length - 1) {
                    System.out.printf("%d번째에서 찾았습니다.\n", i - patterns.length + 2);
                    k = table[k];
                } else {
                    k++;
                }
            }
        }
    }

    static int[] makeTable(String pattern) {
        char[] patterns = pattern.toCharArray();
        int[] table = new int[patterns.length];
        Arrays.fill(table, 0);
        int k = 0;
        for (int i = 1; i < patterns.length; i++) {
            while (k > 0 && patterns[i] != patterns[k]) {
                k = table[k - 1];
            }
            if (patterns[i] == patterns[k]) {
                table[i] = ++k;
            }
        }
        return table;
    }
}
