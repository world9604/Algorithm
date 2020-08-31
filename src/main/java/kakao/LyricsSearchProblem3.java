package kakao;

import java.util.HashMap;
import java.util.Map;

public class LyricsSearchProblem3 {

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] answers = {3, 2, 4, 1, 0};

        LyricsSearchProblem3 problem = new LyricsSearchProblem3();
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
        // words 의 단어들의 갯수는 100000
        Trie[] tries = new Trie[100001];
        for (String word : words) {
            int len = word.length();
            if (tries[len] == null)
                tries[len] = new Trie();
            tries[len].insert(word);
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int len = queries[i].length();
            if (tries[len] == null)
                answer[i] = 0;
            else
                answer[i] = tries[len].getCount(queries[i]);
        }
        return answer;
    }
}

class Trie{
    Node front; //query 의 접미사의 '?'가 시작되는 경우
    Node back;  //query 의 접두사의 '?'가 시작되는 경우
    // 두 경우를 분리 하는 이유는 접두사의 '?'가 있을 경우 node를 전부 탐색해야 하므로
    // 분리 하여 시간 복잡도를 줄일 수 있다.

    Trie(){
        this.front = new Node();
        this.back = new Node();
    }

    public void insert(String word){
        insertFront(word);
        insertBack(word);
    }

    private void insertFront(String word){
        Node node = this.front;
        for (int i = 0; i < word.length(); i++) {
            node.count++; // 한글자 단위 씩 count 증가(예: 대(2)한(2)민(1)국(1) | 대(2)한(2)이(1))
            node = node.children.computeIfAbsent(word.charAt(i), c -> new Node());
        }
    }

    private void insertBack(String word){
        Node node = this.back;
        for (int i = word.length() - 1; i >= 0;  i--) {
            node.count++;
            node = node.children.computeIfAbsent(word.charAt(i), c -> new Node());
        }
    }

    public int getCount(String query){
        if (query.charAt(0) == '?')
            return getCountFromBack(query);
        else
            return getCountFromFront(query);
    }

    private int getCountFromFront(String query){
        Node node = this.front;
        for (int i = 0; i < query.length(); i++) {
            if (query.charAt(i) == '?')
                break; //Trie 트리를 만들때 길이별로 만들기 때문에 나머지는 ("???"의 연속), 따라서 해당 node 의 count 만 반환하면 됨.
            if(!node.children.containsKey(query.charAt(i)))
                return 0;
            node = node.children.get(query.charAt(i));
        }
        return node.count;
    }

    private int getCountFromBack(String query){
        Node node = this.back;
        for (int i = query.length()-1; i >= 0; i--) {
            // 전체가 물음표("?????")인 경우
            if (query.charAt(i) == '?')
                break;
            if (!node.children.containsKey(query.charAt(i)))
                return 0;
            node = node.children.get(query.charAt(i));
        }
        return node.count;
    }
}

// 글자 하나를 표현
class Node{
    Map<Character, Node> children;
    int count; // 자신의 children 의 갯수

    Node(){
        this.children = new HashMap<>();
        this.count = 0;
    }
}


