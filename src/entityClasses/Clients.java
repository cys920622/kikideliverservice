package entityClasses;

import javax.print.DocFlavor;

/**
 * Created by chuchutrainn on 2016-03-26.
 */
public class Clients {
    private int clID;
    private String fname;
    private String lname;
    private String PC;
    private int house_num;
    private String phone_num;

    public Clients() { }

    public int getClID() {
        return clID;
    }

    public void setClID(int clID) {
        this.clID = clID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPC() {
        return PC;
    }

    public void setPC(String PC) {
        this.PC = PC;
    }

    public int getHouse_num() {
        return house_num;
    }

    public void setHouse_num(int house_num) {
        this.house_num = house_num;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }
}