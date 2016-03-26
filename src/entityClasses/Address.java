package entityClasses;

/**
 * Created by danielchoi on 2016-03-25.
 */
public class Address {
    private String country;
    private String province;
    private String city;
    private String street_name;
    private int house_num;
    private String PC;

    public Address(String country, String province, String city, String street_name, int house_num, String pc) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.street_name = street_name;
        this.house_num = house_num;
        PC = pc;
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

    public int getHouse_num() {
        return house_num;
    }

    public void setHouse_num(int house_num) {
        this.house_num = house_num;
    }

    public String getPC() {
        return PC;
    }

    public void setPC(String PC) {
        this.PC = PC;
    }
}
