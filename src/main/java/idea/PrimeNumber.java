package idea;

public class PrimeNumber {
    public static void main(String[] args) {
        PrimeNumber pn = new PrimeNumber();
        System.out.println(pn.isPrimeNumber2(6));
        System.out.println(pn.isPrimeNumber2(7));
    }

    // 소수 판별
    public boolean isPrimeNumber(int x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0)
                return true;
        }
        return false;
    }

    // 효율적인 소수 판별
    public boolean isPrimeNumber2(int x) {
        final int sqrtNum = (int) Math.sqrt(x);

        for (int i = 2; i <= sqrtNum; i++) {
            if (x % i == 0)
                return false;
        }
        return true;
    }
}
