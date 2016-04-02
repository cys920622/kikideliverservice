package entityClasses;

/**
 * Created by stellafang. on 2016-03-29.
 */
public class ClientAddress {
    private int clID;
    private String fname;
    private String lname;
    private String PC;
    private int house_num;
    private String phone_num;
    private String country;
    private String province;
    private String city;
    private String street_name;
    private int dID;


    public ClientAddress() { }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public int getdID() {
        return dID;
    }

    public void setdID(int dID) {
        this.dID = dID;
    }

}
