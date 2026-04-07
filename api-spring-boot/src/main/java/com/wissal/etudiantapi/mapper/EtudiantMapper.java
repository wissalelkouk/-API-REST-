package com.wissal.etudiantapi.mapper;

import com.wissal.etudiantapi.dto.EtudiantDTO;
import com.wissal.etudiantapi.entity.Etudiant;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.time.LocalDate;

@Component
public class EtudiantMapper {
    
    public EtudiantDTO toDTO(Etudiant etudiant) {
        if (etudiant == null) {
            return null;
        }
        
        return EtudiantDTO.builder()
                .id(etudiant.getId())
                .cin(etudiant.getCin())
                .nom(etudiant.getNom())
                .dateNaissance(etudiant.getDateNaissance())
                .email(etudiant.getEmail())
                .anneePremiereInscription(etudiant.getAnneePremiereInscription())
                .departementId(etudiant.getDepartement() != null ? etudiant.getDepartement().getId() : null)
                .departementNom(etudiant.getDepartement() != null ? etudiant.getDepartement().getNom() : null)
                .age(calculateAge(etudiant.getDateNaissance()))
                .build();
    }
    
    public Etudiant toEntity(EtudiantDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Etudiant etudiant = new Etudiant();
        etudiant.setId(dto.getId());
        etudiant.setCin(dto.getCin());
        etudiant.setNom(dto.getNom());
        etudiant.setDateNaissance(dto.getDateNaissance());
        etudiant.setEmail(dto.getEmail());
        etudiant.setAnneePremiereInscription(dto.getAnneePremiereInscription());
        
        // Note: Le département doit être géré séparément pour éviter les problèmes de lazy loading
        return etudiant;
    }
    
    private int calculateAge(LocalDate dateNaissance) {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }
}
