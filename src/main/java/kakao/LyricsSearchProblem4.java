package kakao;

import java.util.HashMap;
import java.util.Map;

public class LyricsSearchProblem4 {

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] answers = {3, 2, 4, 1, 0};

        LyricsSearchProblem4 problem = new LyricsSearchProblem4();
        int[] expectedAnswers = problem.solution(words, queries);
        for (int i = 0; i < answers.length; i++) {
            System.out.printf("%d ", expectedAnswers[i]);
        }
    }

    // 문제 : https://programmers.co.kr/learn/courses/30/lessons/60060
    // words : ["frodo", "front", "frost", "frozen", "frame", "kakao"]
    // queries : ["fro??", "????o", "fr???", "fro???", "pro?"]
    // answer : [3, 2, 4, 1, 0]
    // https://leveloper.tistory.com/99 참고
    public int[] solution(String[] words, String[] queries) {
        // word 의 길이 따라 Trie 를 분류 해서 담음
        Trie2[] tries = new Trie2[100001];

        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            if (tries[len] == null)
                tries[len] = new Trie2();
            tries[len].insert(words[i]);
        }

        int[] answers = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int len = queries[i].length();
            if (tries[len] == null)
                answers[i] = 0;
            else
                answers[i] = tries[len].getCount(queries[i]);
        }
        return answers;
    }
}

class Trie2 {
    Node2 frontNode; //접미사 부터 '?'가 시작되는 경우
    Node2 backNode;  //접두사 부터 '?'가 시작되는 경우

    Trie2(){
        this.frontNode = new Node2();
        this.backNode = new Node2();
    }

    public void insert(String word){
        insertFront(word);
        insertBack(word);
    }

    private void insertFront(String word) {
        Node2 node = this.frontNode;
        for (int i = 0; i < word.length(); i++) {
            node.count++;
            // computeIfAbsent 는 key(word.charAt(i)) 가 존재 하지 않을 경우에만
            // value 의 new Node2()를 실행하여 put 해줌.
            // put()은 무조건 덮어씌우지만, computeIfAbsent()는 덮어씌우지 않음
            node = node.children.computeIfAbsent(word.charAt(i), c -> new Node2());
        }
    }

    private void insertBack(String word) {
        Node2 node = this.backNode;
        for (int i = word.length() - 1; i >= 0; i--) {
            node.count++;
            // computeIfAbsent 는 key(word.charAt(i)) 가 존재 하지 않을 경우에만
            // value 의 new Node2()를 실행하여 put 해줌.
            node.children.computeIfAbsent(word.charAt(i), c -> new Node2());
            node = node.children.get(word.charAt(i));
        }
    }

    public int getCount(String query) {
        if (query.charAt(0) == '?')
            return getCountFromBack(query);
        else
            return getCountFromFront(query);
    }

    private int getCountFromFront(String query) {
        Node2 node = this.frontNode;
        for (int i = 0; i < query.length(); i++) {
            // 전체가 물음표("????")인 경우
            if (query.charAt(i) == '?')
                break; //continue / break 헷갈림
            if(!node.children.containsKey(query.charAt(i)))
                return 0;
            node = node.children.get(query.charAt(i));
        }
        return node.count;
    }

    private int getCountFromBack(String query) {
        Node2 node = this.backNode;
        for (int i = query.length()-1; i >= 0; i--) {
            // 전체가 물음표("????")인 경우
            if (query.charAt(i) == '?')
                break; //continue / break 헷갈림
            if(!node.children.containsKey(query.charAt(i)))
                return 0;
            node = node.children.get(query.charAt(i));
        }
        return node.count;
    }
}
class Node2 {
    Map<Character, Node2> children;
    int count;

    Node2(){
        this.children = new HashMap<>();
        this.count = 0;
    }
}


