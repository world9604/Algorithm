package idea;

public class SimpleComparisonString {
    /**
     * 단순 문자열 찾기 알고리즘
     * KMP 라는 더 효율적인 알고리즘을 사용 하기 전에 구현
     * @참고 https://blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221240660061&categoryNo=128&parentCategoryNo=0&viewDate=&currentPage=4&postListTopCurrentPage=1&from=postList&userTopListOpen=true&userTopListCount=5&userTopListManageOpen=false&userTopListCurrentPage=4
     */
    public static void main(String[] args) {
        String parent = "Hello world";
        String pattern = "llo k";
        int result = findString(parent, pattern);
        if (result == -1)
            System.out.printf("찾을수 없습니다.");
        else
            System.out.printf("%d번째에서 찾았습니다.", result + 1);
    }

    static int findString(String parent, String pattern) {
        char[] parents = parent.toCharArray();
        char[] patterns = pattern.toCharArray();
        int index = -1;

        for (int i = 0; i < parents.length - patterns.length; i++) {
            boolean finded = true;
            for (int k = 0; k < patterns.length; k++) {
                if (parents[i + k] != patterns[k]) {
                    finded = false;
                    break;
                }
            }
            if (finded) {
                index = i;
                break;
            }
        }

        return index;
    }
}
