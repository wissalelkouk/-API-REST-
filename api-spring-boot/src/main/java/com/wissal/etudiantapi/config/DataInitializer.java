package com.wissal.etudiantapi.config;

import com.wissal.etudiantapi.entity.Etudiant;
import com.wissal.etudiantapi.entity.Departement;
import com.wissal.etudiantapi.repository.EtudiantRepository;
import com.wissal.etudiantapi.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @Autowired
    private DepartementRepository departementRepository;

    @Override
    public void run(String... args) throws Exception {
        if (etudiantRepository.count() == 0) {
            // Create departments first
            Departement infoDept = new Departement();
            infoDept.setNom("Informatique");
            infoDept = departementRepository.save(infoDept);
            
            Departement mathDept = new Departement();
            mathDept.setNom("Mathématiques");
            mathDept = departementRepository.save(mathDept);
            
            Departement phyDept = new Departement();
            phyDept.setNom("Physique");
            phyDept = departementRepository.save(phyDept);
            
            // Add sample students with complete data
            etudiantRepository.save(new Etudiant(null, "CIN123456", "Mohammed Ali", LocalDate.of(2000, 5, 15), 
                "mohammed.ali@email.com", 2020, infoDept));
            etudiantRepository.save(new Etudiant(null, "CIN789012", "Fatima Zahra", LocalDate.of(2001, 8, 22), 
                "fatima.zahra@email.com", 2021, infoDept));
            etudiantRepository.save(new Etudiant(null, "CIN345678", "Youssef Ben", LocalDate.of(1999, 12, 3), 
                "youssef.ben@email.com", 2019, mathDept));
            etudiantRepository.save(new Etudiant(null, "CIN901234", "Amina Alaoui", LocalDate.of(2002, 3, 10), 
                "amina.alaoui@email.com", 2022, mathDept));
            etudiantRepository.save(new Etudiant(null, "CIN567890", "Omar Idrissi", LocalDate.of(2000, 7, 28), 
                "omar.idrissi@email.com", 2020, phyDept));
            
            System.out.println("Sample data initialized successfully!");
        }
    }
}
