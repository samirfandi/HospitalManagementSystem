package hospital.demo.security.service;

import hospital.demo.security.entity.AppRole;
import hospital.demo.security.entity.AppUser;

public interface AccountService {

    public AppUser addNewUser(String username, String password, String email, String confirmPassword);

    public AppRole addNewRole(String role);

    public void addRoleToUser(String username, String role);

    public void removeRoleFromUser(String username, String role);

    public AppUser loadUserByUsername(String username);




}
