/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asconvertmvn.model;

/**
 *
 * @author tranm
 */
public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String gender;
   
    public Customer() {
    }

    public Customer(int customerId, String firstName, String lastName, String email, String phone, String address, String gender) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }
    public Customer(String firstName, String lastName, String email, String phone, String address, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", address=" + address + ", gender=" + gender + '}';
    }
   
    
    
   
    
    
     
}
