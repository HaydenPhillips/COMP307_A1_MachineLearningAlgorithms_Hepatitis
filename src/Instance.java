import java.util.List;

public class Instance {

    private String LIVEorDIE;
    private List<Boolean> boolVals;

    public Instance(String LIVEorDIE, List<Boolean> instanceData){
        this.LIVEorDIE = LIVEorDIE;
        this.boolVals = instanceData;
    }

    public boolean getAttVal(int index){
        return boolVals.get(index);
    }

    public String getLIVEorDIE(){
        return LIVEorDIE;
    }

//    public String toString(){
//        StringBuilder ans = new StringBuilder(FileReader.categoryNames.get(FileReader.categorySize));
//        ans.append(" ");
//        for (Boolean val : boolVals)
//            ans.append(val?"true  ":"false ");
//        return ans.toString();
//    }
}
