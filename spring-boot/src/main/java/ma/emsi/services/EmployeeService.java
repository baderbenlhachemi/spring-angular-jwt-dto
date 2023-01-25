package ma.emsi.services;

import ma.emsi.exception.AlreadyExistsException;
import ma.emsi.exception.NotFoundException;
import ma.emsi.payload.request.EmployeeRequestDTO;
import ma.emsi.payload.response.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployees();
    EmployeeResponseDTO getEmployeeById(long id) throws NotFoundException;
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employee) throws AlreadyExistsException;
    EmployeeResponseDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO, long id) throws AlreadyExistsException, NotFoundException;
    void deleteEmployee(long id) throws NotFoundException;
}
