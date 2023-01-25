package ma.emsi.payload.mapper;

import ma.emsi.models.Employee;
import ma.emsi.payload.request.EmployeeRequestDTO;
import ma.emsi.payload.response.EmployeeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring") //implementation auto
public interface EmployeeMapper {
    //ModelMapper library to perform the mapping between the DTO request (EmployeeRequestDTO)
    //and the domain object (Employee) and between the domain object (Employee) and the DTO response (EmployeeResponseDTO).
    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);
    Employee fromRequestToEntity(EmployeeRequestDTO employeeRequestDTO);
    EmployeeResponseDTO fromEntityToResponse(Employee employee);
    List<EmployeeResponseDTO> fromEntityListToResponse(List<Employee> employees);
}
