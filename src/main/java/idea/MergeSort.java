package idea;

public class MergeSort {
    public static void main(String[] args) {

    }

    void merge(int[] data, int p, int q, int r) {
        int[] tmp = new int[data.length];
        int i = p, j = q + 1, k =p;

        // 전반부, 후반부에 원소들을 하나씩 비교해 가며 tmp 배열에 담는다.
        while (i <= q && j <= r) {
            if (data[i] <= data[j]) {
                tmp[k++] = data[i++];
            } else {
                tmp[k++] = data[j++];
            }
        }

        // 전반부에 남았 있는 데이터가 있으면 전부 담는다.
        while (i <= q)
            tmp[k++] = data[i++];

        // 후반부에 남았 있는 데이터가 있으면 전부 담는다.
        while (j <= r)
            tmp[k++] = data[j++];

        // data 에 tmp 값을 전부 담는다.
        for (int a = p; a <= r; a++)
            data[a] = tmp[a];
    }
}
