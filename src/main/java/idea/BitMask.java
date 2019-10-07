package idea;

public class BitMask {
    /**
     * @참고 https://blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221312837477&categoryNo=128&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView&userTopListOpen=true&userTopListCount=5&userTopListManageOpen=false&userTopListCurrentPage=1
     */
    public static void main(String[] args) {
        BitMaskSolution bitmask = new BitMaskSolution(0);

        bitmask.init();
        System.out.print("모든 원소 삭제: ");
        bitmask.show();
        System.out.println();

        bitmask.full();
        System.out.print("모든 원소 포함: ");
        bitmask.show();
        System.out.println();

        bitmask.drop(5);
        System.out.print("5번째 인덱스 삭제: ");
        bitmask.show();
        System.out.println();

        bitmask.set(5);
        System.out.print("5번째 인덱스 포함: ");
        bitmask.show();
        System.out.println();

        System.out.printf("5번째 인덱스 포함 여부: %b", bitmask.isSet(5));
        System.out.println();

        bitmask.toggle(5);
        System.out.print("5번째 인덱스 토글: ");
        bitmask.show();
        System.out.println();

        System.out.printf("5번째 인덱스 포함 여부: %b", bitmask.isSet(5));
        System.out.println();

        bitmask.dropLast();
        bitmask.dropLast();
        bitmask.dropLast();

        System.out.print("마지막 원소 3개 삭제: ");
        bitmask.show();
        System.out.println();

        System.out.printf("마지막 원소 구하기: %d", bitmask.getLast());
        System.out.println();
    }

    static class BitMaskSolution {
        private int num;

        BitMaskSolution(int num) {
            this.num = num;
        }

        // 2진수 형태로 출력하기
        void show() {
            for (int i = 32; i > 0; i--) {
                if ((num & (1 << i - 1)) >= 1)
                    System.out.print("1");
                else
                    System.out.print("0");
            }
        }

        // 모든 원소를 삭제하기
        void init() {
            num = 0;
        }

        // 모든 원소를 포함시키기
        void full() {
            num = -1;
        }

        // 특정 원소를 삭제하기
        void drop(int i) {
            num &= ~(1<<i);
        }

        // 특정 원소를 포함시키기
        void set(int i) {
            num |= (1<<i);
        }

        // 특정 원소 토글하기
        void toggle(int i) {
            num ^= (1<<i);
        }

        // 특정 원소의 포함 여부를 확인하기
        boolean isSet(int i) {
            if ((num & (1<<i)) > 1)
                return true;
            else
                return false;
        }

        // 마지막 원소 구하기
        int getLast() {
            return num & -num;
        }

        // 마지막 원소 삭제하기
        void dropLast() {
            num &= (num - 1);
        }
    }
}
