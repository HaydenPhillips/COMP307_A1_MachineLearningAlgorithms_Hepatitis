import java.util.List;

class catData {
    public String liveOrDie;
    public double liveCount;
    public double dieCount;

    public catData(String liveOrDie, double liveCount, double dieCount) {
        this.liveCount = liveCount;
        this.dieCount = dieCount;
        this.liveOrDie = liveOrDie;
    }

    public String getLiveOrDie() {
        return liveOrDie;
    }


    public double getLiveCount() {
        return liveCount;
    }

    public double getDieCount() {
        return dieCount;
    }
}