import java.util.List;

public class Main {
    public static void main(String[] args) {

        FileReader trainingData = new FileReader((args[0]));
        List<Instance> trainingSet = trainingData.getInstances();
        FileReader testData = new FileReader((args[1]));
        List<Instance> testSet = testData.getInstances();

        List<String> categoryNames = trainingData.getCategoryNames();
        List<String> attributeNames = trainingData.getAttNames();

        new DecisionTree(trainingSet, testSet, categoryNames, attributeNames);
    }
}
