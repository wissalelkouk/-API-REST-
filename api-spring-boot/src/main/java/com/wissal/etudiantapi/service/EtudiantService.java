package com.wissal.etudiantapi.service;

import com.wissal.etudiantapi.entity.Etudiant;
import com.wissal.etudiantapi.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public Etudiant updateEtudiant(Long id, Etudiant etudiant) {
        Etudiant existingEtudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id: " + id));
        
        existingEtudiant.setCin(etudiant.getCin());
        existingEtudiant.setNom(etudiant.getNom());
        existingEtudiant.setDateNaissance(etudiant.getDateNaissance());
        
        return etudiantRepository.save(existingEtudiant);
    }

    public void deleteEtudiant(Long id) {
        Etudiant existingEtudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id: " + id));
        
        etudiantRepository.delete(existingEtudiant);
    }

    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id: " + id));
    }
}
