package hospital.demo.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hospital.demo.security.entity.AppUser;
import lombok.AllArgsConstructor;



//@Service 
@AllArgsConstructor 
public class UserDetailsServiceImpl implements UserDetailsService{

    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = accountService.loadUserByUsername(username);

        if (user == null) throw new UsernameNotFoundException(String.format("Username %s not found ", username));    


        String[] roles = user.getRoles().stream().map(o -> o.getRole()).toArray(String[]::new);

        UserDetails userDetails = User.
                            withUsername(user.getUsername()).
                            password(user.getPassword()).
                            roles(roles).
                            build();
        return userDetails;
        
    }

}
