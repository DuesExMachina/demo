package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api")
public class EmployeeController {
    List<EmployeeRequest> empList = new ArrayList<>();

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

    /*
     * @GetMapping("/getUser")
     * ResponseEntity<EmployeeResponse> getUser1(@RequestParam(name="id",
     * defaultvalue=1)Integer id,
     * 
     * @RequestParam(name="name", defaultvalue="sampleUser")String name){
     * 
     * 
     * }
     * 
     * @GetMapping("/getUser/{id}")
     * ResponseEntity<EmployeeResponse> getUser2(@PathVariable("id") Integer id){
     * 
     * }
     */
}
