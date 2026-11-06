package hospital.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import hospital.demo.data.entity.Patient;
import hospital.demo.data.repository.PatientRepository;
import hospital.demo.security.service.AccountService;

@SpringBootApplication
public class Application {

	@Autowired 
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean 
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean 
	public CommandLineRunner start1(){
		return args -> {

			Patient p1 = new Patient(null, "James", "MILLER", new Date(), false, 230);
			Patient p2 = new Patient(null, "Emma", "WILSON", new Date(), true, 344);
			Patient p3 = new Patient(null, "Lucas", "DUPONT", new Date(), false, 663);
			Patient p4 = new Patient(null, "Chloé", "MOREAU", new Date(), false, 230);
			Patient p5 = new Patient(null, "Sophie", "MARTIN", new Date(), true, 344);
			Patient p6 = new Patient(null, "Michael", "JOHNSON", new Date(), false, 663);
			
			patientRepository.save(p1);
			patientRepository.save(p2);
			patientRepository.save(p3);
			patientRepository.save(p4);
			patientRepository.save(p5);
			patientRepository.save(p6);
			

			// changed 
			
		};
	}


	//@Bean 
	public CommandLineRunner start2(AccountService accountService){

		return args -> {

			accountService.addNewUser("user11", "321", "user11@gmail.com", "321");
			accountService.addNewUser("admin11", "321", "admin11@gmail.com", "321");

			accountService.addNewRole("USER");
			accountService.addNewRole("ADMIN");

			accountService.addRoleToUser("user11", "USER");
			accountService.addRoleToUser("admin11", "ADMIN");
			accountService.addRoleToUser("admin11", "USER");

		
		};
		

	} 


	 
	
		

}
