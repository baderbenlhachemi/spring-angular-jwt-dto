package ma.emsi.services.impl;

import ma.emsi.exception.AlreadyExistsException;
import ma.emsi.exception.NotFoundException;
import ma.emsi.models.Employee;
import ma.emsi.payload.mapper.EmployeeMapper;
import ma.emsi.payload.request.EmployeeRequestDTO;
import ma.emsi.payload.response.EmployeeResponseDTO;
import ma.emsi.repository.EmployeeRepository;
import ma.emsi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return EmployeeMapper.MAPPER.fromEntityListToResponse(employees);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(long employeeId) throws NotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not exist with id :" + employeeId));
        return EmployeeMapper.MAPPER.fromEntityToResponse(employee);
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) throws AlreadyExistsException {
        Employee employee = EmployeeMapper.MAPPER.fromRequestToEntity(employeeRequestDTO);
        if(employeeRepository.findByEmailId(employee.getEmailId())!=null) {
            throw new AlreadyExistsException("Employee with email " + employee.getEmailId() + " already exists");
        }
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MAPPER.fromEntityToResponse(savedEmployee);
    }

    @Override
        public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO, long employeeId) throws NotFoundException, AlreadyExistsException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not exist with id :" + employeeId));
        if(employeeRepository.findByEmailId(employeeRequestDTO.getEmailId())!=null) {
            throw new AlreadyExistsException("Employee with email " + employeeRequestDTO.getEmailId() + " already exists");
        }
        employee.setFirstName(employeeRequestDTO.getFirstName());
        employee.setLastName(employeeRequestDTO.getLastName());
        employee.setEmailId(employeeRequestDTO.getEmailId());
        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MAPPER.fromEntityToResponse(updatedEmployee);
    }

    @Override
    public void deleteEmployee(long employeeId) throws NotFoundException {
        if (employeeRepository.findById(employeeId).isPresent()) {
            employeeRepository.deleteById(employeeId);
        } else {
            throw new NotFoundException("Employee not exist with id :" + employeeId);
        }
    }
}
