package entityClasses;

/**
 * Created by stellafang. on 2016-03-31.
 */
public class DeliveryParcel {
    private int dID;
    private String type;
    private String status;
    private int sender_ID;
    private int receiver_ID;

    private int pID;
    private float length;
    private float width;
    private float height;
    private float weight;
    private String cID;
    private String next_cID;


    private int filterdID;
    private int totaldID;
    private int totalpID;
    private String filtertype;
    private String totaltype;
    private String filterstatus;
    private String totalstatus;
    private int filtersender;
    private int totalsender;
    private int filterreceiver;
    private int totalreceiver;


    public DeliveryParcel() { }

    public int getdID() {
        return dID;
    }

    public void setdID(int dID) {
        this.dID = dID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSender_ID() {
        return sender_ID;
    }

    public void setSender_ID(int sender_ID) {
        this.sender_ID = sender_ID;
    }

    public int getReceiver_ID() {
        return receiver_ID;
    }

    public void setReceiver_ID(int receiver_ID) {
        this.receiver_ID = receiver_ID;
    }

    public int getpID() { return pID; }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public float getLength() { return length; }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() { return width; }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() { return height; }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getcID() {return cID;}

    public void setcID(String cID) {this.cID = cID;}

    public String getNextcID() {return next_cID;}

    public void setNextcID(String next_cID) {this.next_cID = next_cID;}

    public int getTotalreceiver() {
        return totalreceiver;
    }

    public void setTotalreceiver(int totalreceiver) {
        this.totalreceiver = totalreceiver;
    }

    public int getFilterreceiver() {
        return filterreceiver;
    }

    public void setFilterreceiver(int filterreceiver) {
        this.filterreceiver = filterreceiver;
    }

    public int getTotalsender() {
        return totalsender;
    }

    public void setTotalsender(int totalsender) {
        this.totalsender = totalsender;
    }

    public String getTotalstatus() {
        return totalstatus;
    }

    public void setTotalstatus(String totalstatus) {
        this.totalstatus = totalstatus;
    }

    public String getFiltertype() {
        return filtertype;
    }

    public void setFiltertype(String filtertype) {
        this.filtertype = filtertype;
    }

    public int getFilterdID() {
        return filterdID;
    }

    public void setFilterdID(int filterdID) {
        this.filterdID = filterdID;
    }

    public int getTotaldID() {
        return totaldID;
    }

    public void setTotaldID(int totaldID) {
        this.totaldID = totaldID;
    }

    public String getTotaltype() {
        return totaltype;
    }

    public void setTotaltype(String totaltype) {
        this.totaltype = totaltype;
    }

    public String getFilterstatus() {
        return filterstatus;
    }

    public void setFilterstatus(String filterstatus) {
        this.filterstatus = filterstatus;
    }

    public int getFiltersender() {
        return filtersender;
    }

    public void setFiltersender(int filtersender) {
        this.filtersender = filtersender;
    }

    public int getTotalpID() {
        return totalpID;
    }

    public void setTotalpID(int totalpID) {
        this.totalpID = totalpID;
    }

}
