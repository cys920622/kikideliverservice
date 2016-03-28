package entityClasses;

import java.util.Date;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class Cash {
    private float amount;
    private int payID;
    private String onDate;
    private int dID;

    public Cash() {
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getPayID() {
        return payID;
    }

    public void setPayID(int payID) {
        this.payID = payID;
    }

    public String getOnDate() {
        return onDate;
    }

    public void setOnDate(String onDate) {
        this.onDate = onDate;
    }

    public int getdID() {
        return dID;
    }

    public void setdID(int dID) {
        this.dID = dID;
    }
}

