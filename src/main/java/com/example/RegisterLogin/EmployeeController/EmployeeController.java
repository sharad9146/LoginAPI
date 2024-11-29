package com.example.RegisterLogin.EmployeeController;

import com.example.RegisterLogin.DTO.EmployeeDTO;
import com.example.RegisterLogin.DTO.LoginDTO;
import com.example.RegisterLogin.Service.EmployeeService;
import com.example.RegisterLogin.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@CrossOrigin
@RestController("/api/v1/employee")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;
    @PostMapping(path="/save")
    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        String id=employeeService.addEmployee(employeeDTO);
        return id;
    }
    @PostMapping(path="/login")
    public ResponseEntity<?>loginEmployee(@RequestBody LoginDTO loginDTO){
        LoginResponse loginResponse=employeeService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
}
