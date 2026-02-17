package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api")
public class EmployeeController {
    List<EmployeeRequest> empList = new ArrayList<>(); // simulate database

    @GetMapping("/welcome")
    ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to Employee Management System");
    }

    @GetMapping("/getAllUsers")
    ResponseEntity<List<EmployeeRequest>> getAllUsers() {
        return (ResponseEntity.ok(empList));
    }

    @PutMapping("/addUser")
    ResponseEntity<EmployeeResponse> addUser(@Validated @RequestBody EmployeeRequest empReqObj) {
        empList.add(empReqObj);

        EmployeeResponse empRespObj = new EmployeeResponse(empReqObj.getId(), empReqObj.getName());
        return (ResponseEntity.ok(empRespObj));
    }

    @GetMapping("/getUser") // get employee name and id from query parameter
    ResponseEntity<EmployeeResponse> getUser1(@RequestParam(name = "id", required = true) Integer id,
            @RequestParam(name = "name", defaultValue = "sampleUser") String name) {
        // search the list for the employee with the given id and name
        if (empList.stream().noneMatch(emp -> emp.getId() == id && emp.getName().equals(name))) {
            return ResponseEntity.notFound().build();
        } else {
            EmployeeResponse empRespObj = new EmployeeResponse(id, name);
            return (ResponseEntity.ok(empRespObj));
        }
    }

    @GetMapping("/getUser/{id}") // get employee id from path variable
    ResponseEntity<EmployeeResponse> getUser2(@PathVariable("id") Integer id) {

        // search the list for the employee with the given id
        if (empList.stream().noneMatch(emp -> emp.getId() == id)) {
            return ResponseEntity.notFound().build();
        } else {
            EmployeeRequest empReqObj = empList.stream().filter(emp -> emp.getId() == id).findFirst().get();
            EmployeeResponse empRespObj = new EmployeeResponse(empReqObj.getId(), empReqObj.getName());
            return (ResponseEntity.ok(empRespObj));
        }
    }

}
