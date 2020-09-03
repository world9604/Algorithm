package naDongbin;

public class Page223 {
    //N = 1일때 1개 (2*1)
    //N = 2일때 3개 (2*1, 2*1) (1*2, 1*2) (2*2)
    //N = 3일때 5개 (2*1, 2*1, 2*1) (1*2, 2*1, 2*1) (2*1, 2*1, 1*2) (2*2, 2*1) (2*1, 2*2)
    // dp[i] = dp[i-1] + (2 * dp[i-2])
    public static void main(String[] args) {
        System.out.print(new Page223().solution(3));
    }

    int solution(int N){
        int[] dp = new int[N+1];
        dp[1] = 1;
        dp[2] = 3;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i-1] + (2 * dp[i-2]) % 796796;
        }
        return dp[N];
    }
}

