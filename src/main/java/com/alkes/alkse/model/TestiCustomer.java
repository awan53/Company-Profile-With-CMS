package com.alkes.alkse.model;
import jakarta.persistence.*;

@Entity
@Table(name = "testiCustomer")
public class TestiCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String company;
    private String photoUrl;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    public TestiCustomer() {}

    public TestiCustomer(String customerName, String company, String photoUrl, String feedback) {
        this.customerName = customerName;
        this.company = company;
        this.photoUrl = photoUrl;
        this.feedback = feedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
