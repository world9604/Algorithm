package idea;

public class SegmentTree {

    /**
     * @참고 https://blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221282210534&parentCategoryNo=&categoryNo=128&viewDate=&isShowPopularPosts=false&from=postList
     */
    static final int NUMBER = 12;
    static int[] a = {1, 9, 3, 8, 4, 5, 5, 9, 10, 3, 4, 5};
    static int[] tree = new int[4 * NUMBER]; // 데이터의 갯수 * 4 만큼의 공간을 미리 할당.

    public static void main(String[] args) {
        // 구간 합 트리의 인덱스를 제외하고는 모두 인덱스 0부터 시작합니다.
        // 구간 합 트리 생성하기
        // 구간 합 트리는 데이터 트리와 다르게 인덱스 1 부터 시작하는게 더 편하다. 따라서 node 값 : 1
        init(0 , NUMBER - 1, 1);
        // 구간 합 구하기
        System.out.printf("0부터 12까지의 구간 합: %d", sum(0, NUMBER - 1, 1, 0, 12));
        System.out.println();
        // 구간 합 구하기
        System.out.printf("4부터 8까지의 구간 합: %d", sum(0, NUMBER - 1, 1, 4, 8));
        System.out.println();
        // 구간 합 갱신하기
        System.out.printf("인덱스 5의 원소를 -5만큼 수정");
        System.out.println();
        update(0, NUMBER - 1, 1, 5, -5);
        // 구간 합 다시 구하기
//        System.out.printf("3부터 8까지의 구간 합: %d", sum(0, NUMBER - 1, 1, 0, 12));
        System.out.println();
    }

    // start : 시작 인덱스, end : 끝 인덱스
    static int init(int start, int end, int node) {
        if (start == end) return tree[node] = a[start];
        int mid = (start + end) / 2;
        // 재귀적으로 두 부분으로 나눈 뒤에 그 합을 자기 자신으로 합니다.
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    // start : 시작 인덱스, end : 끝 인덱스
    // left, right : 구간 합을 구하고자 하는 범위
    static int sum(int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (left > end || right < start) return 0;
        // 범위 안에 있는 경우
        if (left <= start && end <= right) return tree[node];
        // 그렇지 않다면 두 부분으로 나누어 합을 구하기
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    // start: 시작 인덱스, end: 끝 인덱스
    // index: 구간 합을 수정하고자 하는 노드
    // dif: 수정할 값
    static void update(int start, int end, int node, int index, int dif) {
        // 범위 밖에 있는 경우
        if(index < start || index > end) return;
        // 범위 안에 있으면 내려가며 다른 원소도 갱신
        tree[node] += dif;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
    }
}
