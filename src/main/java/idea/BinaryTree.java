package idea;

public class BinaryTree {

    public static void main(String[] args) {
        final int NUM = 15;
        Node[] nodes = new Node[NUM+1];
        for (int i = 1; i <= NUM; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 2; i <= NUM; i++) {
            if(i % 2 == 0)
                nodes[i/2].setLeftNode(nodes[i]); // 짝수인 경우 부모의 왼쪽 노드
            else
                nodes[i/2].setRightNode(nodes[i]); // 홀수인 경우 부모의 오른쪽 노드
        }

        BinaryTreeTraversaler traversaler = new BinaryTreeTraversaler();

        System.out.print("preOrder : ");
        traversaler.preOrder(nodes[1]);
        System.out.println();

        System.out.print("inOrder : ");
        traversaler.inOrder(nodes[1]);
        System.out.println();

        System.out.print("postOrder : ");
        traversaler.postOrder(nodes[1]);
    }
}

class BinaryTreeTraversaler{

    public void preOrder(Node node){
        System.out.printf("%s -> ", node.getData());
        if(node.getLeftNode() != null)
            preOrder(node.getLeftNode());
        if(node.getRightNode() != null)
            preOrder(node.getRightNode());
    }
    public void inOrder(Node node){
        if(node.getLeftNode() != null)
            inOrder(node.getLeftNode());
        System.out.printf("%s -> ", node.getData());
        if(node.getRightNode() != null)
            inOrder(node.getRightNode());
    }

    public void postOrder(Node node){
        if(node.getLeftNode() != null)
            postOrder(node.getLeftNode());
        if(node.getRightNode() != null)
            postOrder(node.getRightNode());
        System.out.printf("%s -> ", node.getData());
    }
}

class Node {
    private int data;
    private Node leftNode;
    private Node rightNode;

    public Node(int data){
        this.data = data;
    }

    public Node(int data, Node leftNode, Node rightNode){
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int getData() {
        return data;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}
