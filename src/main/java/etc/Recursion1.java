package etc;

public class Recursion1 {
    public static void main(String[] args) {
//        System.out.printf("%d", fibonacci2(10000, 1, 0));
        System.out.printf("%d", fibonacci(10000));
    }

    public static void func(int k) {
        if (k <= 0) { // Base Case : 수렴 조건
            return;
        } else {
            System.out.println("Hello World");
            func(k - 1); // Recurusive Case : 순환 조건
        }
    }

    // 팩토리얼 계산
    public static int factorial(int n) {
        if (n == 0)
            return 1;
        else
            return n * factorial(n-1);
    }

    // 피보나치 수열 계산
    public static int fibonacci(int n) {
        if (n < 2)
            return n;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 피보나치 수열 계산2
    public static int fibonacci2(int n, int previousNum, int previousPreviousNum) {
        if (n < 2)  return n * previousNum;
        return fibonacci2(n - 1,
                previousNum + previousPreviousNum,
                previousNum);
    }

    // 최대공약수 (유클리드 메소드)
    /*public static int gcd(int m, int n) {
        if (m < n){
            int tmp = m; m = n; n = tmp; // swap
        }

        if (m % n == 0)
            return n;
        else
            return gcd(n, m%n);
    }*/

    public static int gcd(int m, int n) {
        if (n == 0)
            return m;
        else
            return gcd(n, m % n);
    }
}
