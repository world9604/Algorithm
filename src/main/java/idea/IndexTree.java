package idea;

public class IndexTree {
    /**
     * @참고 http://blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221312822103&categoryNo=128&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=&from=postList&userTopListOpen=true&userTopListCount=5&userTopListManageOpen=false&userTopListCurrentPage=1
     */
    public static void main(String[] args) {
        final int num = 6;
        IndexTreeSolution indexTree = new IndexTreeSolution(num);

        indexTree.update(1, 1);
        indexTree.update(2, 2);
        indexTree.update(3, 3);
        indexTree.update(4, 4);
        indexTree.update(5, 5);

        // 2 ~ 5의 구간 합을 구합니다.
        System.out.printf("%d\n", indexTree.getSection(2, 5));
        indexTree.update(3, -2);

        // 2 ~ 5의 구간 합을 구합니다.
        System.out.printf("%d\n", indexTree.getSection(2, 5));
        indexTree.update(5, 2);

        // 2 ~ 5의 구간 합을 구합니다.
        System.out.printf("%d\n", indexTree.getSection(2, 5));

        // 1 ~ 5의 구간 합을 구합니다.
        System.out.printf("%d\n", indexTree.getSection(1, 5));
    }

    static class IndexTreeSolution {
        private int num;
        private int[] tree;

        IndexTreeSolution(int num) {
            this.num = num;
            this.tree = new int[num];
        }

        int[] getTree() {
            return tree;
        }

        int sum(int i) {
            int result = 0;
            while(i > 0) {
                result += tree[i];
                i -= (i & -i);
            }
            return result;
        }

        void update(int i, int dif) {
            while(i < num) {
                tree[i] += dif;
                i += (i & -i);
            }
        }

        int getSection(int start, int end) {
            return sum(end) - sum(start - 1);
        }
    }
}
