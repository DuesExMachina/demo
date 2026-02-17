package com.example.demo;

public class EmployeeResponse { // DTO clas for response
    Integer id;
    String name;
    // String mail; //We will enable this later and fetch it from DB

    // constructor cum setter
    EmployeeResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
