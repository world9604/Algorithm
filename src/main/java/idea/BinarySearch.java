package idea;

public class BinarySearch {
    /**
     * @참고 https://blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221275413971&categoryNo=128&parentCategoryNo=0&viewDate=&currentPage=2&postListTopCurrentPage=1&from=postView&userTopListOpen=true&userTopListCount=5&userTopListManageOpen=false&userTopListCurrentPage=2
     */
    static int[] arr = {1, 3, 5, 7, 9, 11, 14, 15, 18, 19, 25, 28};

    public static void main(String[] args) {
        int data = 7;
        final int NUMBER = 12;

        int result = search(0, NUMBER - 1, data);
        if (result != -1) System.out.printf("%d번째에서 찾았습니다.", result + 1);
    }

    static int search(int start, int end, int target) {
        if (start > end) return -1;
        int mid = (start + end) / 2;
        if (arr[mid] == target)
            return mid;
        else if (arr[mid] > target)
            return search(start, mid - 1, target);
        else
            return search(mid + 1, end, target);
    }
}
