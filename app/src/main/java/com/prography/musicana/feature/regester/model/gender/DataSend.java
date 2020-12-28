package com.prography.musicana.feature.regester.model.gender;

public class DataSend {
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private String country;
    private String gender;

    public DataSend(String firstname, String lastname, String phone, String email, String password, String country, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.country = country;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        gender = gender;
    }
}
