package idea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {

    public static void main(String[] args) {
        int n = 7;

        List<Edge> list = new ArrayList<>();
        list.add(new Edge(1, 2, 44));
        list.add(new Edge(1, 4, 12));
        list.add(new Edge(1, 5, 27));
        list.add(new Edge(1, 7, 56));
        list.add(new Edge(2, 4, 34));
        list.add(new Edge(2, 5, 11));
        list.add(new Edge(3, 5, 60));
        list.add(new Edge(3, 6, 25));
        list.add(new Edge(4, 7, 58));
        list.add(new Edge(5, 6, 42));
        list.add(new Edge(5, 7, 31));

        // 간선의 비용을 오름차순 정렬
        Collections.sort(list);

        // 각 정점이 포함된 그래프가 어디인지 저장
        int rootParents[] = new int[n];
        for (int i = 0; i < n; i++) {
            rootParents[i] = i;
        }

        // 거리의 합을 0으로 초기화
        int sum = 0;
        UnionFind uf = new UnionFind();

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("i : %d", i);
            System.out.println();

            for (int j = 0; j < rootParents.length; j++) {
                System.out.printf("rootParents[%d] : %d", j, rootParents[j]);
                System.out.println();
            }
            // 동일한 부모를 가르키지 않는 경우,
            // 즉 사이클이 발생하지 않을 때만 선택
            if (!uf.findSet(rootParents, list.get(i).node[0] - 1, list.get(i).node[1] - 1)) {
                sum += list.get(i).distance;
                uf.unionSet(rootParents, list.get(i).node[0] - 1, list.get(i).node[1] - 1);
            }
        }

        System.out.printf("간선의 총합 : %d", sum);
        System.out.println();
    }
}

class Edge implements Comparable<Edge> {
    int[] node;
    int distance;

    Edge(int a, int b, int distance) {
        this.node = new int[2];
        this.node[0] = a;
        this.node[1] = b;
        this.distance = distance;
    }

    @Override
    public int compareTo(Edge edge) {
        return (this.distance - edge.distance);
    }
}