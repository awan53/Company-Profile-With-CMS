package com.alkes.alkse.model;
import jakarta.persistence.*;

@Entity
@Table(name = "aboutus")
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "companyname", nullable = false, length = 255)
    private String companyname;

    @Column(name = "mission", columnDefinition = "VARCHAR(MAX)", nullable = false)
    private String mission;

    @Column(name = "vision", columnDefinition = "VARCHAR(MAX)", nullable = false)
    private String vision;

    @Column(name = "description", columnDefinition = "VARCHAR(MAX)")
    private String description;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Column(name = "contact_email", length = 255)
    private String contactEmail;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "slogan", length = 255)
    private String slogan;

    public About (){}

    public About(String companyname, String mission, String vision, String description, String logoUrl, String contactEmail, String address, String phone, String slogan) {
        this.companyname = companyname;
        this.mission = mission;
        this.vision = vision;
        this.description = description;
        this.logoUrl = logoUrl;
        this.contactEmail = contactEmail;
        this.address = address;
        this.phone = phone;
        this.slogan = slogan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
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

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
