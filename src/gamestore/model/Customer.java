
package gamestore.model;

public class Customer {
    
    private int customerID;
    private String userName;
    private String email;
    private String password;
    private String ccNumber;
    private double balance;
    
    // Default constructor
    public Customer(){}

    // Constructor
    public Customer(int customerID, String userName, String email, String password, String ccNumber, double balance) {
        setCustomerID(customerID);
        setUserName(userName);
        setEmail(email);
        setPassword(password);
        setCCNumber(ccNumber);
        setBalance(balance);
    }    
    
    // Set/Get methods
    public int getCustomerID() {
        return customerID;
    }
    
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getCCNumber() {
        return ccNumber;
    }
    
    public void setCCNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }
    
    public Double getBalance() {
        return balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
