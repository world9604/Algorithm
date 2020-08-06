package idea;


public class StringMatching {

    public static void main(String[] args) {
        String parent = "ababacabacaabacaaba";
        String pattern = "abacaaba";
        StringMatching StringMatching = new StringMatching();
        StringMatching.kmp(parent, pattern);
    }

    public void kmp(String parent, String pattern) {
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

    private int[] makeTable(String pattern) {
        char[] patterns = pattern.toCharArray();
        int[] table = new int[patterns.length];
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
