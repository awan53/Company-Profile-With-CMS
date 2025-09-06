package com.alkes.alkse.model;
import jakarta.persistence.*;

@Entity
@Table(name = "contact_info")
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String phone;
    private String email;
    private String NmUser;
    private String message;


    public ContactInfo() {}
    public ContactInfo(String address, String phone, String email, String NmUser, String message) {
        this.address = address;
        this.NmUser = NmUser;
        this.phone = phone;
        this.email = email;
        this.message = message;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNmUser() {
        return NmUser;
    }

    public void setNmUser(String nmUser) {
        NmUser = nmUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
