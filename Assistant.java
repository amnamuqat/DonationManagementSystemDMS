/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

/**
 *
 * @author Amna
 */
public class Assistant {
    // 1. Private fields to achieve Encapsulation
    private String name;
    private String password;
    private String email;
    private String contactNo;

    // 2. Default Constructor
    public Assistant() {
    }

    // 3. Parameterized Constructor
    public Assistant(String name, String password, String email, String contactNo) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.contactNo = contactNo;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}