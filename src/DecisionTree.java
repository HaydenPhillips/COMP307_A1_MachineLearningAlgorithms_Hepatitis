import java.util.ArrayList;
import java.util.List;

public class DecisionTree {
    static List<String> categories;
    List<String> attributeNames;
    List<Instance> trainingInstances;
    List<Instance> testInstances;
    double baseLineAccuracy;
    String baseLineCat;
    Node tree;

    public DecisionTree(List<Instance> trainingSet, List<Instance> testSet,
                        List<String> categories, List<String> attributeNames) {
        this.trainingInstances = trainingSet;
        this.testInstances = testSet;
        this.categories = categories;
        this.attributeNames = attributeNames;

        baseLineCat = catCounter(trainingInstances).getLiveOrDie();
        catData cd = catCounter(trainingInstances);
        baseLineAccuracy = (Math.max(cd.getLiveCount(), cd.getDieCount()) / trainingInstances.size());

        tree = buildTree(trainingInstances, attributeNames);
        System.out.println("\nDecision Tree:\n");
        tree.report("");
        System.out.println("BaseLine Accuracy: " + baseLineAccuracy);
        results();
    }


    public Node buildTree(List<Instance> instances, List<String> attributes) {

        double lowestImpurity = 1;
        int bestAttIndex = 0;
        String bestAtt = "";
        List<Instance> bestInstTrue = null;
        List<Instance> bestInstFalse = null;

        if (instances == null || instances.isEmpty()) {
            return new LeafNode(baseLineCat, baseLineAccuracy);     //return baseline predictor leafNode
        }

        if ((int) (Math.max(catCounter(instances).getLiveCount(), catCounter(instances).getDieCount()) / instances.size()) == 1) {   //if pure
            return new LeafNode(instances.get(0).getLIVEorDIE(), 1);
        }

        if (attributeNames.isEmpty()) {
            double probability = (Math.max(catCounter(instances).getLiveCount(), catCounter(instances).getDieCount()) / instances.size());
            String majorityCategory = catCounter(instances).getLiveOrDie();
            return new LeafNode(majorityCategory, probability);
        }
        else {      //find best att
            for (int i = 0; i < attributes.size(); i++) {
                List<Instance> listTrue = new ArrayList<>();
                List<Instance> listFalse = new ArrayList<>();

                for (Instance instance : instances) {
                    if (instance.getAttVal(i)) {
                        listTrue.add(instance);
                    } else {
                        listFalse.add(instance);
                    }
                }
                //get purity of sets
                catData cdT = catCounter(listTrue);
                catData cdF = catCounter(listFalse);
                double truePurity = (cdT.getLiveCount() * cdT.getDieCount()) / Math.pow((cdT.getLiveCount() + cdT.getDieCount()), 2);
                double falsePurity = (cdF.getLiveCount() * cdF.getDieCount()) / Math.pow((cdF.getLiveCount() + cdF.getDieCount()), 2);
                double aveTrue = ((listTrue.size() / ((double) instances.size())) * truePurity);
                double aveFalse = ((listFalse.size() / ((double) instances.size())) * falsePurity);
                double weightedImpurity = aveTrue + aveFalse;

                if (weightedImpurity < lowestImpurity) {            // if weighted average purity of these sets is best so far
                    bestAtt = attributes.get(i);                    // bestAtt = this attribute
                    lowestImpurity = weightedImpurity;
                    bestAttIndex = i;
                    bestInstTrue = listTrue;                        // bestInstsTrue = set of true instances
                    bestInstFalse = listFalse;                      // bestInstsFalse = set of true instances
                }
            }
            attributeNames.remove(bestAttIndex);                    //build subtrees using the remaining attributes
            Node left = buildTree(bestInstTrue, attributeNames);        //- best att
            Node right = buildTree(bestInstFalse, attributeNames);      //- best att
            return new Node(left, right, bestAtt, bestAttIndex);
        }
    }

    public catData catCounter(List<Instance> instances) {
        String live = DecisionTree.categories.get(0);
        String die = DecisionTree.categories.get(1);
        double liveCount = 0.0;
        double dieCount = 0.0;
        for (Instance i : instances) {
            if (i.getLIVEorDIE().equals(live)) {
                liveCount++;
            } else if (i.getLIVEorDIE().equals(die)) {
                dieCount++;
            }
        }
        if (liveCount > dieCount) {
            String liveS = "live";
            catData cd = new catData(liveS, liveCount, dieCount);
            return cd;
        } else {
            String dieS = "die";
            catData cd = new catData(dieS, liveCount, dieCount);
            return cd;
        }
    }

    private String predictResult(Node node, Instance testInstance) {
        if (node instanceof LeafNode) {
            return node.getAttribute();
        } else {
            Node nonLeafNode = node;
            if (testInstance.getAttVal(nonLeafNode.getAttIndex())) {
                return predictResult(nonLeafNode.getLeft(), testInstance);
            } else {
                return predictResult(nonLeafNode.getRight(), testInstance);
            }
        }
    }

    private void results() {
        int correct = 0;
        for (Instance instance : testInstances) {
            if (predictResult(tree, instance).equals(instance.getLIVEorDIE())) {
                correct++;
            }
        }
        System.out.format("Correct Predictions: %d\n", correct);
        System.out.format("Total Tested: %d\n", testInstances.size());
        System.out.format("Accuracy: %f\n", (double)correct / testInstances.size() * 100);
    }
}