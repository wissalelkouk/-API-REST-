package com.wissal.etudiantapi.service;

import com.wissal.etudiantapi.dto.DepartementDTO;
import com.wissal.etudiantapi.entity.Departement;
import com.wissal.etudiantapi.mapper.DepartementMapper;
import com.wissal.etudiantapi.repository.DepartementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartementService {
    
    private final DepartementRepository departementRepository;
    private final DepartementMapper departementMapper;
    
    public List<DepartementDTO> findAll() {
        return departementRepository.findAll().stream()
                .map(departementMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<DepartementDTO> findById(Long id) {
        return departementRepository.findById(id)
                .map(departementMapper::toDTO);
    }
    
    public DepartementDTO save(DepartementDTO dto) {
        Departement departement = departementMapper.toEntity(dto);
        Departement saved = departementRepository.save(departement);
        return departementMapper.toDTO(saved);
    }
    
    public Optional<DepartementDTO> update(Long id, DepartementDTO dto) {
        return departementRepository.findById(id)
                .map(existing -> {
                    existing.setNom(dto.getNom());
                    Departement updated = departementRepository.save(existing);
                    return departementMapper.toDTO(updated);
                });
    }
    
    public boolean deleteById(Long id) {
        if (departementRepository.existsById(id)) {
            departementRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
