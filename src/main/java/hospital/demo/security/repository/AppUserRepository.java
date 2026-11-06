package hospital.demo.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.demo.security.entity.AppUser;





public interface AppUserRepository extends JpaRepository<AppUser, String> {

    AppUser findByUsername(String username);


}
