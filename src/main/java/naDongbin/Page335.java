package naDongbin;

import java.util.ArrayList;


public class Page335 {

    /*
    * n     weak                dist            result
    * 12    [1, 5, 6, 10]       [1, 2, 3, 4]    2
    * 12    [1, 3, 4, 9, 10]    [3, 5, 7]       1
    * */
    public static void main(String[] args) {
        int n = 12;
        int[] weak = new int[]{1, 5, 6, 10};
        int[] dist = new int[]{1, 2, 3, 4};
        System.out.print(new Page335().solution(n, weak, dist));
    }

    ArrayList<int[]> friends;

    public int solution(int n, int[] weak, int[] dist) {
        int answer = -1;
        int[] expand_weak = new int[weak.length * 2 - 1];

        //1.취약점 배열 확장
        // 2배로 weak 확장
        for(int i=0; i<expand_weak.length; i++){
            expand_weak[i] = (i<weak.length) ? weak[i] : weak[i%weak.length] + n;
        }

        for(int i=1; i<=dist.length; i++){
            //2.출전 친구 구하기
            friends = new ArrayList<>();

            // 배열중 i명을 뽑았을때 조합
            // i = 1 이면 친구 한명 조합
            permutation(dist, 0, i);

            //3.해당 친구로 취약점 검사
            for(int j=0; j<friends.size(); j++){
                if(check(expand_weak, friends.get(j))){
                    answer = friends.get(j).length;
                    break;
                }
            }

            if(answer != -1) break;
        }

        return answer;
    }


    boolean check(int[] weak, int[] friend) {
        int len = weak.length / 2 + 1;

        // 출발 지점 갱신
        for(int i=0; i<len; i++) {
            // 친구 인덱스
            int idx = 0;
            // 출발 지점 갱신
            int startPoint = weak[i];
            boolean flag = true;

            // 출발 지점(i) 부터 ~ 취약 지점 끝(i+len) 까지
            for(int j=i; j<i+len; j++){
                //탐색 실패
                if(weak[j] - startPoint > friend[idx]){
                    startPoint = weak[j];
                    // 다른 친구가 이어서 검사
                    idx++;

                    // 0 ~ 친구 수 까지 다 돌면, 출발 지점 갱신
                    if(idx == friend.length){
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) return true;
        }
        return false;
    }

    // 순열 구하기
    void permutation(int[] arr, int depth, int r) {
        if (depth == r) {
            int[] tmp = new int[r];

            for(int i=0; i<r; i++)
                tmp[i] = arr[i];

            friends.add(tmp);
        }

        for (int i = depth; i < arr.length; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1, r);
            swap(arr, depth, i);
        }
    }

    void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}