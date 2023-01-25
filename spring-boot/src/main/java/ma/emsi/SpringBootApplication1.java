package ma.emsi;

import ma.emsi.repository.RoleRepository;
import ma.emsi.services.EmployeeService;
import ma.emsi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
public class SpringBootApplication1 implements CommandLineRunner {

	@Autowired // field will be automatically initialized with the corresponding beans from the application context
	RoleRepository roleRepository;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) { SpringApplication.run(SpringBootApplication1.class, args);}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		System.out.println("app working");

		/*roleRepository.save(new Role(ERole.ROLE_ADMIN));
		roleRepository.save(new Role(ERole.ROLE_USER));
		roleRepository.save(new Role(ERole.ROLE_MODERATOR));*/
	}
}
