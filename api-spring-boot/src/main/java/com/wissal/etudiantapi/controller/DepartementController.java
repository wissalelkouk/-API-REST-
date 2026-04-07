package com.wissal.etudiantapi.controller;

import com.wissal.etudiantapi.dto.DepartementDTO;
import com.wissal.etudiantapi.service.DepartementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/departements")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DepartementController {
    
    private final DepartementService departementService;
    
    @GetMapping
    public ResponseEntity<List<DepartementDTO>> getAllDepartements() {
        return ResponseEntity.ok(departementService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DepartementDTO> getDepartementById(@PathVariable Long id) {
        return departementService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<DepartementDTO> createDepartement(@Valid @RequestBody DepartementDTO departementDTO) {
        DepartementDTO created = departementService.save(departementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DepartementDTO> updateDepartement(@PathVariable Long id, 
                                                         @Valid @RequestBody DepartementDTO departementDTO) {
        return departementService.update(id, departementDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Long id) {
        if (departementService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
