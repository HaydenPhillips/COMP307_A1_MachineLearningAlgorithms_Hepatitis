public class Node {
        private Node left;
        private Node right;
        private String attribute;
        private int attIndex;

    public Node(Node left, Node right, String attribute, int attIndex) {
            this.left = left;
            this.right = right;
            this.attribute = attribute;
            this.attIndex = attIndex;
        }


    public void report(String indent){
        System.out.format("%s%s = True:\n",	indent, attribute);
        left.report(indent+"   ");
        System.out.format("%s%s = False:\n", indent, attribute);
        right.report(indent+" ");
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public String getAttribute() {
        return attribute;
    }

    public int getAttIndex() {
        return attIndex;
    }
}
