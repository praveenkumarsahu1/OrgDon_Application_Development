package com.example.orgdon;


// This class contains the different data that we are going to be working with
// in our project.
public class model {
    // These are the data that we require for our project.
    String name, address, donated, age, blood, phone, hospital;

    // Rest of this class initializes different values and functions for us,
    // so that we can later call them in our program.
    model() {

    }

    public model(String name, String address, String donated, String age, String blood, String phone, String hospital) {
        this.name = name;
        this.address = address;
        this.donated = donated;
        this.age = age;
        this.blood = blood;
        this.phone = phone;
        this.hospital = hospital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDonated() {
        return donated;
    }

    public void setDonated(String donated) {
        this.donated = donated;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
