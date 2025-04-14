package entity;

public class Customer {
    private int customerId;
    private String name;
    private String email;
    private String phoneNumber;

    public Customer(int customerId, String name, String email, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer [ID=" + customerId + ", Name=" + name + ", Email=" + email + ", Phone=" + phoneNumber + "]";
    }
}