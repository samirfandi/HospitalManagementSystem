package hospital.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hospital.demo.data.entity.Patient;
import hospital.demo.data.repository.PatientRepository;
import jakarta.validation.Valid;

@Controller 
public class PatientController {

    @Autowired 
    private PatientRepository patientRepository;


    @GetMapping ("/")
    public String toHome(){
        return "redirect:/user/index";
    }



    @GetMapping ("/user/index")
    public String index(Model model,@RequestParam (name = "page", defaultValue = "0") int page,
                                    @RequestParam (name = "size", defaultValue = "5") int size,
                                    @RequestParam (name = "keyword", defaultValue = "") String keyword){

        Page<Patient> pagePatients = patientRepository.findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(keyword,keyword,PageRequest.of(page, size));
        model.addAttribute("listePatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("currentKeyword", keyword);
        return "patients";
    }

    
    //@PreAuthorize("hasRole('ADMIN')") 
    @GetMapping ("/admin/deletePatient")
    public String deletePatient(
                            @RequestParam (name = "id") Long id,
                            @RequestParam (name = "page") int page,
                            @RequestParam (name = "keyword") String keyword){

        patientRepository.deleteById(id);
        // + "&page="+page
        return "redirect:/user/index?keyword="+ keyword+ "&page="+page;

    }

    @GetMapping ("/admin/addPatientForm")
    public String addPatientForm(Model model){ 
        model.addAttribute("patient", new Patient());
        return "addPatientForm";
    }


    @GetMapping ("/user/updatePatient")
    public String updatePatient(@RequestParam (name = "id") Long id , Model model){
        
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient", patient);
        return "updatePatientForm";

    }

    @PostMapping ("/user/validateUpdate")
    public String validateUpdate(@Valid @ModelAttribute("patient") Patient patient,
        BindingResult result) {

        if (result.hasErrors()) {
            return "updatePatientForm";
        }

        patientRepository.save(patient);
        return "redirect:/user/index";
    }

    @GetMapping ("/layout")
    public String layout(){
        return "layout";
    }




    /**
    @PostMapping ("/addPatient")
    public String addPatient(@RequestParam (name = "nom") String nom ,
                             @RequestParam (name = "prenom") String prenom,
                             @RequestParam (name = "ddn") String ddn,
                             @RequestParam (name = "malade", defaultValue = "false") Boolean malade,
                             @RequestParam (name = "score", defaultValue = "0") int score ) {

        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setDateNaissance(java.sql.Date.valueOf(ddn));
        patient.setMalade(malade);
        patient.setScore(score);
        patientRepository.save(patient);

        return "redirect:/index";
    }
    */

    @PostMapping ("/admin/addPatient")
    public String addPatient( @Valid @ModelAttribute("patient") Patient patient,
        BindingResult result) {

    if (result.hasErrors()) {
        return "addPatientForm";
    }
    patientRepository.save(patient);

        return "redirect:/user/index";
    }


    @GetMapping ("/user/about")
    public String about(){
        return "about";
    }






}

