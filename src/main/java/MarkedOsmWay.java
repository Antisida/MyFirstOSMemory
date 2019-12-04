import org.alex73.osmemory.OsmWay;

public class MarkedOsmWay {

    private OsmWay osmWay;

    private int parentNum;

    private boolean ready;

    public MarkedOsmWay(OsmWay osmWay) {
        this.osmWay = osmWay;
    }

    public OsmWay getOsmWay() {
        return osmWay;
    }

    public void setParentNum(int parentNum) {
        this.parentNum = parentNum;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getParentNum() {
        return parentNum;
    }

    public boolean isReady() {
        return ready;
    }
}
