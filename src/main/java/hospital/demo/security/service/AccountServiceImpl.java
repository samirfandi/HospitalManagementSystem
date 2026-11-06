package hospital.demo.security.service;

import java.util.UUID;

import javax.management.relation.Role;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.demo.security.entity.AppRole;
import hospital.demo.security.entity.AppUser;
import hospital.demo.security.repository.AppRoleRepository;
import hospital.demo.security.repository.AppUserRepository;
import lombok.AllArgsConstructor;



//@Service 
//@Transactional 
@AllArgsConstructor 
public class AccountServiceImpl implements AccountService{


    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
       
        AppUser userExist = appUserRepository.findByUsername(username);

        if (userExist != null) throw new RuntimeException("This user already exist");
        if (! password.equals(confirmPassword)) throw new RuntimeException("Passwords not match");

        AppUser user = AppUser.builder()
                              .userId(UUID.randomUUID().toString())
                              .username(username)
                              .email(email)
                              .password(passwordEncoder.encode(confirmPassword))
                              .build();

        appUserRepository.save(user);

        return user;
    }

    @Override
    public void addRoleToUser(String username, String roleId) {
        
        AppUser user = appUserRepository.findByUsername(username);
        AppRole role = appRoleRepository.findById(roleId).get();

        user.getRoles().add(role);

        //c'est fait automatiquement par transactional 
        //appUserRepository.save(user);

    }

    @Override
    public void removeRoleFromUser(String username, String roleId) {
        
        AppUser user = appUserRepository.findByUsername(username);
        AppRole role = appRoleRepository.findById(roleId).get();

        user.getRoles().remove(role);

        //c'est fait automatiquement par transactional 
        //appUserRepository.save(user);
        
    }

    @Override
    public AppRole addNewRole(String role) {

        AppRole roleExist = appRoleRepository.findById(role).orElse(null);
        
        if (roleExist != null) throw new RuntimeException("Role already exist");

        AppRole newRole = AppRole.builder()
                              .role(role)
                              .build();

        appRoleRepository.save(newRole);

        return newRole;
    }

    @Override
    public AppUser loadUserByUsername(String username) {
       
        return appUserRepository.findByUsername(username);
    }

}
