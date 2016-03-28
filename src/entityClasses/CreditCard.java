package entityClasses;

import java.util.Date;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class CreditCard {
    private float amount;
    private int payID;
    private String onDate;
    private int dID;
    private String credit_card_num;
    private int CSV;
    private String name;
    private String expiry_date;
    private String type;

    public CreditCard() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCSV() {
        return CSV;
    }

    public void setCSV(int CSV) {
        this.CSV = CSV;
    }

    public String getCredit_card_num() {
        return credit_card_num;
    }

    public void setCredit_card_num(String credit_card_num) {
        this.credit_card_num = credit_card_num;
    }

    public int getdID() {
        return dID;
    }

    public void setdID(int dID) {
        this.dID = dID;
    }

    public String getOnDate() {
        return onDate;
    }

    public void setOnDate(String onDate) {
        this.onDate = onDate;
    }

    public int getPayID() {
        return payID;
    }

    public void setPayID(int payID) {
        this.payID = payID;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}

