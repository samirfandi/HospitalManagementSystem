package hospital.demo.security.entity;


import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data @AllArgsConstructor @NoArgsConstructor @Builder 
@Table (name = "\"users\"") 
public class AppUser {

    @Id 
    private String userId;

    @Column (unique = true)
    private String username;

    private String password;

    @Column (unique = true)
    private String email;

    @ManyToAny (fetch = FetchType.EAGER)
    private List<AppRole> roles;



}
