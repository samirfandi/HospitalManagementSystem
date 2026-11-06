package hospital.demo.data.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import hospital.demo.data.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

    public Page<Patient> findByNomContainsIgnoreCase(String keyword, Pageable page);
    public Page<Patient> findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(String keyword, String prenom, Pageable page);
    public Optional<Patient> findById(Long id);



    

}
