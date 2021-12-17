package entity;

public class Customer {
    //fields
    private int id;
    private String firstName;
    private String lastName;
    private String dob;
    private String phone;
    private String email;
    private boolean loyalty;
    private int favStore;
    
    //constructor
    public Customer(int id, String firstName, String lastName, String dob, String phone, String email, boolean loyalty, int favStore) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.loyalty = loyalty;
        this.favStore = favStore;
    }
    
    //getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLoyalty() {
        return loyalty;
    }

    public int getFavStore() {
        return favStore;
    }
    
    
}//enc class Customer
