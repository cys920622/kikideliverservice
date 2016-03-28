package entityClasses;

/**
 * Created by chuchutrainn on 2016-03-27.
 */
public class Center {
    private String cID;
    private String center_addr;

    public Center() { }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public String getCenter_addr() {
        return center_addr;
    }

    public void setCenter_addr(String center_addr) {
        this.center_addr = center_addr;
    }
}
