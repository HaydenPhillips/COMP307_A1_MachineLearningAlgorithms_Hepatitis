import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    List<String> categoryNames;
    List<String> attNames;
    List<Instance> allInstances;

    public FileReader(String fname) {
        /* format of names file:
         * names of categoryNames, separated by spaces
         * names of attNames
         * category followed by true's and false's for each instance
         */
        System.out.println("Reading data from file " + fname);
        try {
            Scanner scan = new Scanner(new File(fname));
            categoryNames = new ArrayList<>();
            attNames = new ArrayList<>();

            Scanner sCat = new Scanner(scan.nextLine());
            while (sCat.hasNext()) {
                categoryNames.add(sCat.next());
            }
            System.out.println(categoryNames.size() + " categories");

            Scanner sAtt = new Scanner(scan.nextLine());
            while (sAtt.hasNext()) {
                attNames.add(sAtt.next());
            }
            System.out.println(attNames.size() + " attributes");

            allInstances = readInstances(scan);
            scan.close();
        } catch (IOException e) {
            throw new RuntimeException("Data File caused IO exception");
        }
    }


    private List<Instance> readInstances(Scanner scan) {
        /* instance = classname and space separated attribute values */
        List<Instance> instances = new ArrayList<>();
        while (scan.hasNext()) {
            Scanner line = new Scanner(scan.nextLine());

            String category = categoryNames.get(categoryNames.indexOf(line.next()));
            List<Boolean> attValues = new ArrayList<>();
            while (line.hasNextBoolean()) {
                attValues.add(line.nextBoolean());
            }
            instances.add(new Instance(category, attValues));
        }
        System.out.println("Read " + instances.size() + " instances");
        return instances;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public List<String> getAttNames() {
        return attNames;
    }

    public List<Instance> getInstances() {
        return allInstances;
    }

}

