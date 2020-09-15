package kakao;

import java.util.*;

public class test3 {
    public static void main(String[] args) {
        String[] info = new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        test3 test3 = new test3();
        int[] result = test3.solution(info, query);
        for (int i : result) {
            System.out.print(i);
            System.out.println();
        }
    }

    public int[] solution(String[] info, String[] query) {
        Trie[] tries = new Trie[100001];

        for (String pInfo : info) {
            String[] pInfos = pInfo.split(" ");
            // 언어(java, cpp, 파이썬) 길이 별로 분리
            int n = pInfos[0].length();
            if (tries[n] == null)
                tries[n] = new Trie();
            tries[n].insert(pInfo);
        }

        int[] answer = new int[query.length];

        for (int i = 0; i < query.length; i++) {
            String[] pQuery = query[i].split(" and ");
            // 언어(java, cpp, 파이썬) 길이 별로 분리
            int len = pQuery[0].length();
            if (tries[len] == null)
                answer[i] = 0;
            else
                answer[i] = tries[len].getCount(query[i]);
        }

        return answer;
    }

    class Trie{
        // query 의 접미사의 '-'가 시작되는 경우
        Node front;
        // query 의 접두사의 '-'가 시작되는 경우
        Node back;

        // 두 경우를 분리 하는 이유는 접두사의 '-'가 있을 경우 node를 전부 탐색해야 하므로
        // 분리 하여 시간 복잡도를 줄일 수 있다.
        Trie(){
            this.front = new Node();
            this.back = new Node();
        }

        public void insert(String word){
            insertFront(word);
            insertBack(word);
        }

        private void insertFront(String pInfo){
            Node node = this.front;
            String[] pInfos = pInfo.split(" ");
            for (int i = 0; i < pInfos.length; i++) {
                if (i == pInfos.length - 1)
                    node.score = Integer.parseInt(pInfos[i]);
                else
                    node = node.children.computeIfAbsent(pInfos[i], c -> new Node());
            }
        }

        private void insertBack(String pInfo){
            Node node = this.back;
            String[] pInfos = pInfo.split(" ");
            for (int i = pInfos.length - 1; i >= 0; i--) {
                if (i == pInfos.length - 1)
                    node.score = Integer.parseInt(pInfos[i]);
                else
                    node = node.children.computeIfAbsent(pInfos[i], c -> new Node());
            }
        }

        public int getCount(String query){
            if (query.charAt(0) == '-')
                return getCountFromBack(query);
            else
                return getCountFromFront(query);
        }

        private int getCountFromFront(String query){
            Node node = this.front;
            String[] pQuery = query.split(" and ");
            String[] k = pQuery[pQuery.length - 1].split(" ");
            pQuery[pQuery.length - 1] = k[0];

            for (int i = 0; i < pQuery.length; i++) {
                if (pQuery[i].equals("-"))
                    break;
                //Trie 트리를 만들때 길이별로 만들기 때문에 나머지는 ("???"의 연속),
                // 따라서 해당 node 의 count 만 반환하면 됨.
                if(!node.children.containsKey(pQuery[i]))
                    return 0;

                node = node.children.get(pQuery[i]);

                // 마지막 노드 직전
                if (i == pQuery.length - 1) {
                    Set<String> list = node.children.keySet();
                    int cnt = 0;
                    // 점수들 중 더 큰값 반환
                    /*for (Node food : list) {
                        Set<String> score = food.children.keySet();
                        for (String s : score) {
                            if (Integer.parseInt(s) >= Integer.parseInt(pQuery[i])) {
                                cnt++;
                            }
                        }
                    }*/
                    return cnt;
                }
            }
            return -1;
        }

        private int getCountFromBack(String query){
            Node node = this.back;
            String[] pQuery = query.split(" and ");
            String[] k = pQuery[pQuery.length - 1].split(" ");
            pQuery[pQuery.length - 1] = k[0];

            for (int i = pQuery.length-1; i >= 0; i--) {
                // 전체가 물음표("?????")인 경우
                if (pQuery[i].equals("-"))
                    break;
                if (!node.children.containsKey(pQuery[i]))
                    return 0;

                node = node.children.get(pQuery[i]);

                // 마지막 노드 직전
                if (i == pQuery.length - 2) {
                    Collection<Node> list = node.children.values();
                    int cnt = 0;
                    // 점수들 중 더 큰값 반환
                    for (Node food : list) {
                        Set<String> score = food.children.keySet();
                        for (String s : score) {
                            if (Integer.parseInt(s) >= Integer.parseInt(pQuery[i])) {
                                cnt++;
                            }
                        }
                    }
                    return cnt;
                }
            }
            return -1;
        }
    }

    // 각 종목 별 노드
    class Node{
        Map<String, Node> children;
        int score;

        Node(){
            this.children = new HashMap<>();
            this.score = 0;
        }
    }

}
