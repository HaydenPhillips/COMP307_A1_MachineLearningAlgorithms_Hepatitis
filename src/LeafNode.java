public class LeafNode extends Node{

    private String attribute;
    private double probability;

    public LeafNode(String attribute, double probability) {
        super(null, null, attribute, 0);
        this.attribute = attribute;
        this.probability = probability;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public void report(String indent){
        System.out.format("%sClass %s, prob=%4.2f\n",
                indent, attribute, probability);
    }
}
