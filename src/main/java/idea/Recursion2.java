package idea;

import java.util.Scanner;

public class Recursion2 {
    public static void main(String[] args) {
        System.out.printf("abc의 길이 : %d", length("abc"));

        System.out.print("abc 출력 : ");
        printChars("abc");
        System.out.println();

        System.out.print("abc 역출력 : ");
        printCharReverse("abc");
        System.out.println();

        System.out.print("3의 2진수 출력 : ");
        printBinary(3);
        System.out.println();

        int[] data = {1, 2, 3};
        System.out.printf("{1, 2, 3} 배열 합 출력 : %d", sum(3, data));
        System.out.println();

//        readFrom(3, data, new Scanner(System.in));
    }

    // Recursion VS Iteration
    // 모든 순환함수는 반복문으로 변경 가능
    // 그역도 성립함. 즉 모든 반복문은 recursion 으로 표현 가능함
    // 순환함수는 복잡한 알고리즘을 단순하고 알기쉽게 표현하는 것을 가능하게 함
    // 하지만 함수 호출에 따른 오버헤드가 있음 (메개변수 전달, 액티베이션 프레임 생성(jvm 스택) 등)

    // 문자열 길이 계산
    public static int length(String str) {
        if ("".equals(str)) {
            return 0;
        } else {
            return 1 + length(str.substring(1));
        }
    }

    // 문자열의 프린트
    public static void printChars(String str) {
        if (str.length() == 0) {
            return;
        } else {
            System.out.print(str.charAt(0));
            printChars(str.substring(1));
        }
    }

    // 문자열을 뒤집어 프린트
    public static void printCharReverse(String str) {
        if (str.length() == 0) {
            return;
        } else {
            printCharReverse(str.substring(1));
            System.out.print(str.charAt(0));
        }
    }

    // 음이 아닌 정수 n을 이진수로 변환하여 인쇄 한다.
    public static void printBinary(int n) {
        if (n < 2) {
            System.out.print(n);
        } else {
            printBinary(n / 2); // n을 2로 나눈 몫을 먼저 2진수로 변환하여 인쇄한 후
            System.out.print(n % 2); // n을 2로 나눈 나머지를 인쇄한다.
        }
    }

    // 배열 합 구하기
    // data[0]에서 data[n - 1]까지의 합을 구하여 반환 한다.
    public static int sum(int n, int[] data) {
        if (n <= 0) {
            return 0;
        } else {
            return sum(n - 1, data) + data[n - 1];
        }
    }

    // 데이터 파일로 부터 n개의 정수 읽어오기
    // Scanner in이 참조하는 파일로 부터 n개의 정수를 입력받아
    // 배열 data 에 저장한다.
    public static void readFrom(int n, int[] data, Scanner in) {
        if (n == 0) {
            return;
        } else {
            readFrom(n - 1, data, in);
            data[n - 1] = in.nextInt();
        }
    }
}
