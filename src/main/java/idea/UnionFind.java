package idea;

public class UnionFind {

    int getParent(int parenSet[], int x){
        if(parenSet[x] == x) return x;
        return parenSet[x] = getParent(parenSet, parenSet[x]);
    }

    void unionSet(int parentSet[], int a, int b){
        a = getParent(parentSet, a);
        b = getParent(parentSet, b);
        if(a < b)
            parentSet[b] = a;
        else
            parentSet[a] = b;
    }

    boolean findSet(int[] parenSet, int a, int b){
        a = getParent(parenSet, a);
        b = getParent(parenSet, b);
        if(a == b)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        int parent[] = new int[11];
        for(int i = 1; i <= 10; i++){
            parent[i] = i;
        }

        UnionFind unionFind = new UnionFind();
        unionFind.unionSet(parent, 1, 2);
        unionFind.unionSet(parent, 2, 3);
        unionFind.unionSet(parent, 3, 4);

        unionFind.unionSet(parent, 5, 6);
        unionFind.unionSet(parent, 6, 7);
        unionFind.unionSet(parent, 7, 8);

        System.out.printf("1과 5는 연결되어 있나요? %b", unionFind.findSet(parent, 1, 5));
        System.out.println();

        unionFind.unionSet(parent, 1, 5);
        System.out.printf("1과 5는 연결되어 있나요? %b", unionFind.findSet(parent, 1, 5));
        System.out.println();
    }
}
