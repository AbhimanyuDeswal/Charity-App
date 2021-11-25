package com.abhimanyu.charity;

public class NGO
{
    String name, cause, phone, email, location;


    public NGO() {}


    public NGO(String name, String cause, String phone, String email, String location) {
        this.name = name;
        this.cause = cause;
        this.phone = phone;
        this.email = email;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
