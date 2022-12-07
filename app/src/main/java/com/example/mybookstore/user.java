package com.example.mybookstore;

public class user {

    String Name,User_Name,Phone_No;

    public user(String name, String user_Name, String phone_No) {
        Name = name;
        User_Name = user_Name;
        Phone_No = phone_No;
    }

    public user() {

    }

    public String getName() {
        return Name;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public String getPhone_No() {
        return Phone_No;
    }


    public void setName(String name) {
        Name = name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public void setPhone_No(String phone_No) {
        Phone_No = phone_No;
    }
}
