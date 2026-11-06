package hospital.demo.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hospital.demo.security.entity.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, String>{

    


}
