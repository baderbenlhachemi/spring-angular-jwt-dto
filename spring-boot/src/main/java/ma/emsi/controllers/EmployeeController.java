package ma.emsi.controllers;
import java.util.List;

import ma.emsi.exception.AlreadyExistsException;
import ma.emsi.exception.NotFoundException;
import ma.emsi.payload.request.EmployeeRequestDTO;
import ma.emsi.payload.response.EmployeeResponseDTO;
import ma.emsi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class EmployeeController {

    private final EmployeeService employeeService; // dependency injection with constructor

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // get all employees
    @GetMapping("/employees")
    public List<EmployeeResponseDTO> getAllEmployeesDTO(){
        return employeeService.getAllEmployees();
    }

    // get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable long id) throws NotFoundException {
        EmployeeResponseDTO employeeResponseDTO = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

    // create employee
    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) throws AlreadyExistsException {
    return new ResponseEntity<EmployeeResponseDTO>(employeeService.createEmployee(employeeRequestDTO), HttpStatus.CREATED);
    }

    // update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable long id, @RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) throws AlreadyExistsException, NotFoundException {
        return new ResponseEntity<EmployeeResponseDTO>(employeeService.updateEmployee(employeeRequestDTO, id), HttpStatus.OK);
    }

    // delete employee
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable long id) throws NotFoundException {
        employeeService.deleteEmployee(id);
    }
}
