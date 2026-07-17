/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

/**
 *
 * @author Amna
 */
public class Donation {
    // 1. Private fields to achieve Encapsulation
    private String donationName;
    private String donationType;
    private double amount;       
    private int quantity;        
    private double total;        
    private String targetGroup;
    private String description;
    private String status;

    // 2. Default Constructor
    public Donation() {}

    // 3. Parameterized Constructor
    public Donation(String donationName, String donationType, double amount, int quantity,
                    String targetGroup, String description, String status) {
        this.donationName = donationName;
        this.donationType = donationType;
        this.amount = amount;
        this.quantity = quantity;
        this.targetGroup = targetGroup;
        this.description = description;
        this.status = status;
        this.total = amount * quantity; 
    }

    // 4. Getters and Setters
    public String getDonationName() {
        return donationName;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
        this.total = this.amount * this.quantity; 
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.total = this.amount * this.quantity; 
    }

    public double getTotal() {
        return total;
    }

    public String getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(String targetGroup) {
        this.targetGroup = targetGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}