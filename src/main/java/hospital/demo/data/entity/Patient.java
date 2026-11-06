package hospital.demo.data.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table (name = "\"patients\"") 
@NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter @ToString 
public class Patient {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY) 
    @Column (name = "\"Identifiant\"")
    private Long id;
    
    
    @Column (name = "\"Nom\"")
    @NotEmpty()
    private String nom;

    @Column (name = "\"Prenom\"")
    @NotEmpty 
    private String prenom;

    @Column (name = "Date de naissance")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;

    @Column (name = "\"Malade\"" )
    private Boolean malade;

    @Column (name = "\"Score\"")
    @Min(value = 86, message = "Le score doit être supérieur ou égal à 86")
    private int score ;

}
