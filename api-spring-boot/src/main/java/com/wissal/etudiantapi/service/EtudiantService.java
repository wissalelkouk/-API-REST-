package com.wissal.etudiantapi.service;

import com.wissal.etudiantapi.dto.EtudiantDTO;
import com.wissal.etudiantapi.entity.Etudiant;
import com.wissal.etudiantapi.entity.Departement;
import com.wissal.etudiantapi.mapper.EtudiantMapper;
import com.wissal.etudiantapi.repository.EtudiantRepository;
import com.wissal.etudiantapi.repository.DepartementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EtudiantService {
    
    private final EtudiantRepository etudiantRepository;
    private final DepartementRepository departementRepository;
    private final EtudiantMapper etudiantMapper;
    
    // @Cacheable(value = "etudiants") // Temporairement désactivé
    public List<EtudiantDTO> findAll() {
        return etudiantRepository.findAll().stream()
                .map(etudiantMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    // @Cacheable(value = "etudiants_by_annee", key = "#annee")
    public List<EtudiantDTO> findByAnneePremiereInscription(int annee) {
        return etudiantRepository.findByAnneePremiereInscription(annee).stream()
                .map(etudiantMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    // @Cacheable(value = "etudiant", key = "#id")
    public Optional<EtudiantDTO> findById(Long id) {
        return etudiantRepository.findById(id)
                .map(etudiantMapper::toDTO);
    }
    
    // @CacheEvict(value = {"etudiants", "etudiants_by_annee"}, allEntries = true)
    public EtudiantDTO save(EtudiantDTO dto) {
        Etudiant etudiant = etudiantMapper.toEntity(dto);
        
        // Gérer la relation avec le département
        if (dto.getDepartementId() != null) {
            Departement departement = departementRepository.findById(dto.getDepartementId())
                    .orElseThrow(() -> new RuntimeException("Département non trouvé avec l'ID: " + dto.getDepartementId()));
            etudiant.setDepartement(departement);
        }
        
        Etudiant saved = etudiantRepository.save(etudiant);
        return etudiantMapper.toDTO(saved);
    }
    
    // @Caching(evict = {
    //     @CacheEvict(value = "etudiants", allEntries = true),
    //     @CacheEvict(value = "etudiants_by_annee", allEntries = true),
    //     @CacheEvict(value = "etudiant", key = "#id")
    // })
    public Optional<EtudiantDTO> update(Long id, EtudiantDTO dto) {
        return etudiantRepository.findById(id)
                .map(existing -> {
                    existing.setCin(dto.getCin());
                    existing.setNom(dto.getNom());
                    existing.setDateNaissance(dto.getDateNaissance());
                    existing.setEmail(dto.getEmail());
                    existing.setAnneePremiereInscription(dto.getAnneePremiereInscription());
                    
                    // Gérer la relation avec le département
                    if (dto.getDepartementId() != null) {
                        Departement departement = departementRepository.findById(dto.getDepartementId())
                                .orElseThrow(() -> new RuntimeException("Département non trouvé avec l'ID: " + dto.getDepartementId()));
                        existing.setDepartement(departement);
                    }
                    
                    Etudiant updated = etudiantRepository.save(existing);
                    return etudiantMapper.toDTO(updated);
                });
    }
    
    // @CacheEvict(value = {"etudiants", "etudiants_by_annee", "etudiant"}, allEntries = true)
    public boolean deleteById(Long id) {
        if (etudiantRepository.existsById(id)) {
            etudiantRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
