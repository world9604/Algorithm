package boj;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ11728_2 {
    //https://www.acmicpc.net/problem/11728
    // 입력
    //2 2
    //3 5
    //2 9
    // 출력
    //2 3 5 9

    // 입력
    //2 1
    //4 7
    //1
    // 출력
    //1 4 7

    // 입력
    //4 3
    //2 3 5 9
    //1 4 7
    // 출력
    //1 2 3 4 5 7 9
    public static void main(String[] args) throws IOException {
        /*
        Scanner sc = new Scanner(System.in);
        // 1 <= N,M <= 1000000
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();

        int[] A = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int[] B = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        */

        /*
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] len = Arrays.stream(bf.readLine().split(" ")).parallel()
                .mapToInt(Integer::parseInt).toArray();
        int N = len[0];
        int M = len[1];

        int[] A = Arrays.stream((bf.readLine().split(" "))).parallel()
                .mapToInt(Integer::parseInt).toArray();
        int[] B = Arrays.stream(bf.readLine().split(" ")).parallel()
                .mapToInt(Integer::parseInt).toArray();
        */

        /*
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N, M;
        String[] len = bf.readLine().split(" ");
        N = Integer.parseInt(len[0]);
        M = Integer.parseInt(len[1]);

        int[] A = new int[N];
        String[] AStrs = bf.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(AStrs[i]);
        }

        int[] B = new int[M];
        String[] BStrs = bf.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(BStrs[i]);
        }
        */


        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] B = new int[M];
        for (int i = 0; i < M; i++)
            B[i] = Integer.parseInt(st.nextToken());
            */


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        int[] B = new int[M];
        for (int i = 0; i < M; i++)
            B[i] = Integer.parseInt(st.nextToken());

        BOJ11728_2 main = new BOJ11728_2();
        main.solution(N, M, A, B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N + M; i++) {
            sb.append(main.sorted[i]).append(" ");
        }
        out.write(sb.toString());
        out.close();
    }

    // 최종 배열 sorted
    int[] sorted;

    public int[] solution(int N, int M, int[] A, int[] B) {
        this.sorted = new int[N + M];
        merge(A, B, N, M);
        return sorted;
    }

    private void merge(int[] A, int[] B, int N, int M) {
        // A 배열 시작, 끝
        int i = 0;
        int n = N;

        // B 배열 시작, 끝
        int j = 0;
        int m = M;

        // sorted 배열 인덱스
        int k = 0;

        // 앞배열(인덱스 i), 뒷배열(인덱스 j)가 각각 정렬 되어 있음
        while(i < n && j < m) {
            if (A[i] < B[j])
                sorted[k++] = A[i++];
            else
                sorted[k++] = B[j++];
        }

        // A 배열이 다 담겼다면, B 배열 나머지 넣어주기
        while (i < n) sorted[k++] = A[i++];
        while (j < m) sorted[k++] = B[j++];
    }
}