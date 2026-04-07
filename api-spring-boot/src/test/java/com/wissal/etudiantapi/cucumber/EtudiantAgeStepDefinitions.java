package com.wissal.etudiantapi.cucumber;

import com.wissal.etudiantapi.entity.Etudiant;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class EtudiantAgeStepDefinitions {

    private Etudiant etudiant;
    private int ageCalcule;

    @Given("un étudiant avec la date de naissance {string}")
    public void unEtudiantAvecLaDateDeNaissance(String dateNaissance) {
        LocalDate date = LocalDate.parse(dateNaissance);
        etudiant = new Etudiant();
        etudiant.setDateNaissance(date);
    }

    @When("on calcule son âge")
    public void onCalculeSonAge() {
        ageCalcule = etudiant.age();
    }

    @Then("l'âge retourné doit être {int}")
    public void lAgeRetourneDoitEtre(int ageAttendu) {
        assertEquals(ageAttendu, ageCalcule);
    }
}
