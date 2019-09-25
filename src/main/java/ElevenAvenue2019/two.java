package ElevenAvenue2019;

public class two {
    public static void main(String[] args) {
        String message = "Why not";
        int K = 100;
        String result = new Solution().solution(message, K);
        System.out.printf("%s", result);
    }
}

class Solution {
    public String solution(String message, int K) {
        final char whiteSpace = " ".toCharArray()[0];
        char[] messages = message.toCharArray();
        int index = -1;

        if (K >= messages.length) return message;
        // messages에 14번째 자리는 K이다. 배열은 0 부터 시작 하므로
        if (messages[K] != whiteSpace) {
            for (int i = K - 1; i >= 0; i--) {
                if (messages[i] == whiteSpace) {
                    index = i;
                    break;
                }
            }
        } else {
            index = K;
        }
        return getResultMessage(messages, index);
    }

    private String getResultMessage(char[] messages, int index) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= index - 1; i++) {
            sb.append(messages[i]);
        }
        return sb.toString();
    }
}
