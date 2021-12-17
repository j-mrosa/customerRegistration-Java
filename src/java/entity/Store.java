package entity;

public class Store {
    //fields
    private int code;
    private String address;
    private String phone;

    //constructor
    public Store(int code, String address, String phone) {
        this.code = code;
        this.address = address;
        this.phone = phone;
    }

    //getters
    public int getCode() {
        return code;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
       
}//end class Store
