package com.wissal.etudiantapi.config;

import com.wissal.etudiantapi.entity.Etudiant;
import com.wissal.etudiantapi.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public void run(String... args) throws Exception {
        if (etudiantRepository.count() == 0) {
            // Add sample students
            etudiantRepository.save(new Etudiant(null, "CIN123456", "Mohammed Ali", LocalDate.of(2000, 5, 15)));
            etudiantRepository.save(new Etudiant(null, "CIN789012", "Fatima Zahra", LocalDate.of(2001, 8, 22)));
            etudiantRepository.save(new Etudiant(null, "CIN345678", "Youssef Ben", LocalDate.of(1999, 12, 3)));
            etudiantRepository.save(new Etudiant(null, "CIN901234", "Amina Alaoui", LocalDate.of(2002, 3, 10)));
            etudiantRepository.save(new Etudiant(null, "CIN567890", "Omar Idrissi", LocalDate.of(2000, 7, 28)));
            
            System.out.println("Sample data initialized successfully!");
        }
    }
}
