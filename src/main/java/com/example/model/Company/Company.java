package com.example.model.Company;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "company_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String localization;

    @Size(max = 1024)
    private String description;

    private Integer areaRange;
    private String phoneNumber;
    private String avatar;
    private Float rating;
    private int numberJobs;
    private int numberOpinions;

    public Company() {
    }

    public Company(String name, String email, String password, String localization, Integer areaRange) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.localization = localization;
        this.areaRange = areaRange;
    }

    public Company(String name, String email, String password, String localization, Integer areaRange, String phoneNumber, String descript, String avatar, Float avgRating, int numberJobs, int numberOpinions) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.localization = localization;
        this.areaRange = areaRange;
        this.phoneNumber = phoneNumber;
        this.description = descript;
        this.avatar = avatar;
        this.rating = avgRating;
        this.numberJobs = numberJobs;
        this.numberOpinions = numberOpinions;
    }

    public void copy(Company company) {
        //this.id = company.getId();
        this.name = company.getName();
        //this.email = company.getEmail();
        this.password = company.getPassword();
        this.localization = company.getLocalization();
        this.areaRange = company.getAreaRange();
        this.phoneNumber = company.getPhoneNumber();
        this.description = company.getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public Integer getAreaRange() {
        return areaRange;
    }

    public void setAreaRange(Integer areaRange) {
        this.areaRange = areaRange;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public int getNumberJobs() {
        return numberJobs;
    }

    public void setNumberJobs(int numberJobs) {
        this.numberJobs = numberJobs;
    }

    public int getNumberOpinions() {
        return numberOpinions;
    }

    public void setNumberOpinions(int numberOpinions) {
        this.numberOpinions = numberOpinions;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", rating=" + rating +
                ", numberJobs=" + numberJobs +
                ", numberOpinions=" + numberOpinions +
                '}';
    }
}
