package com.wissal.etudiantapi.controller;

import com.wissal.etudiantapi.dto.EtudiantDTO;
import com.wissal.etudiantapi.service.EtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Étudiants", description = "API pour la gestion des étudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    @GetMapping
    @Operation(summary = "Lister tous les étudiants", 
               description = "Récupère la liste complète de tous les étudiants")
    @ApiResponse(responseCode = "200", description = "Liste des étudiants récupérée avec succès")
    public ResponseEntity<List<EtudiantDTO>> getAllEtudiants() {
        return ResponseEntity.ok(etudiantService.findAll());
    }

    @GetMapping(params = "annee")
    @Operation(summary = "Filtrer les étudiants par année", 
               description = "Récupère les étudiants par leur année de première inscription")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste filtrée récupérée avec succès"),
        @ApiResponse(responseCode = "400", description = "Paramètre invalide")
    })
    public ResponseEntity<List<EtudiantDTO>> getEtudiantsByAnnee(
            @Parameter(description = "Année de première inscription") 
            @RequestParam int annee) {
        return ResponseEntity.ok(etudiantService.findByAnneePremiereInscription(annee));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un étudiant par son ID", 
               description = "Retourne les détails d'un étudiant spécifique")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Étudiant trouvé"),
        @ApiResponse(responseCode = "404", description = "Étudiant non trouvé")
    })
    public ResponseEntity<EtudiantDTO> getEtudiantById(
            @Parameter(description = "ID de l'étudiant") 
            @PathVariable Long id) {
        return etudiantService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Créer un nouvel étudiant", 
               description = "Ajoute un nouvel étudiant à la base de données")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Étudiant créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<EtudiantDTO> createEtudiant(
            @Parameter(description = "Données de l'étudiant à créer") 
            @Valid @RequestBody EtudiantDTO etudiantDTO) {
        EtudiantDTO created = etudiantService.save(etudiantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un étudiant", 
               description = "Modifie les informations d'un étudiant existant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Étudiant mis à jour"),
        @ApiResponse(responseCode = "404", description = "Étudiant non trouvé"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<EtudiantDTO> updateEtudiant(
            @Parameter(description = "ID de l'étudiant à modifier") 
            @PathVariable Long id,
            @Parameter(description = "Nouvelles données de l'étudiant") 
            @Valid @RequestBody EtudiantDTO etudiantDTO) {
        return etudiantService.update(id, etudiantDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un étudiant", 
               description = "Retire un étudiant de la base de données")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Étudiant supprimé"),
        @ApiResponse(responseCode = "404", description = "Étudiant non trouvé")
    })
    public ResponseEntity<Void> deleteEtudiant(
            @Parameter(description = "ID de l'étudiant à supprimer") 
            @PathVariable Long id) {
        if (etudiantService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
