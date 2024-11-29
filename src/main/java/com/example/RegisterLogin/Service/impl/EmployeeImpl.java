package com.example.RegisterLogin.Service.impl;

import com.example.RegisterLogin.DTO.EmployeeDTO;
import com.example.RegisterLogin.DTO.LoginDTO;
import com.example.RegisterLogin.Entity.Employee;
import com.example.RegisterLogin.Repo.EmployeeRepo;
import com.example.RegisterLogin.Service.EmployeeService;
import com.example.RegisterLogin.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getEmployeeId(),
        employeeDTO.getEmployeeName(),
        employeeDTO.getEmail(),
        this.passwordEncoder.encode(employeeDTO.getPassword())
        );
        employeeRepo.save(employee);
        return employee.getEmployeename();
    }
    @Override
    public LoginResponse loginEmployee(LoginDTO loginDTO){
        String msg="";
        Employee employee=employeeRepo.findByEmail(loginDTO.getEmail());
        if(employee!=null){
            String password= loginDTO.getPassword();
            String encodedPassword=employee.getPassword();
            Boolean isPwdRight=passwordEncoder.matches(password,encodedPassword);
            if(isPwdRight){
                Optional<Employee>employee1=employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if(employee1.isPresent()){
                    return new LoginResponse("login success",true);
                }else{
                    return new LoginResponse("login failed",false);
                }
            }else{
                return new LoginResponse("password not match",false);
            }
        }else{
            return new LoginResponse("email not exist",false);
        }
    }
}
