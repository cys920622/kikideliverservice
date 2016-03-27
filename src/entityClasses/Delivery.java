package entityClasses;

/**
 * Created by danielchoi on 2016-03-26.
 */
public class Delivery {
    private int dID;
    private String type;
    private String status;
    private int sender_ID;
    private int receiver_ID;

    public Delivery() { }

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
}
