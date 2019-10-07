package NhN;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class One {
    /**
     * 3
     * ant bee ant
     * antbeeant
     * ant가 패턴 문자열로 같은 것이 있는지 판단
     *
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); sc.nextLine();

        String[] insects = new String[N];
        for (int i = 0; i < N; i++) {
            insects[i] = sc.next();
        }

//        Stream.of(insects).map()
//        for (int i = 0; i < insects.length; i++) {
//            kmp(insects, insects[i]);
//        }
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
