/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vortexmakers.demo;

/**
 *
 * @author mrcto
 */

import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/etudiant")
@CrossOrigin(origins = "http://localhost:8080") // Autoriser les requêtes depuis notre frontend
public class EtudiantController {

    private GestionnaireEtudiant gestionnaire = new GestionnaireEtudiant();

    // Endpoint pour la connexion
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        try {
            Etudiant etudiant = gestionnaire.login(loginRequest.getLogin(), loginRequest.getPassword());
            if (etudiant != null) {
                return "Connexion réussie! Bienvenue " + etudiant.getPrenom();
            } else {
                return "Identifiants incorrects";
            }
        } catch (SQLException e) {
            return "Erreur lors de la connexion: " + e.getMessage();
        }
    }

    // Endpoint pour l'inscription
    @PostMapping("/register")
    public String register(@RequestBody Etudiant etudiant) {
        try {
            gestionnaire.createOrUpdateEtudiant(
                etudiant.getId(), 
                etudiant.getNom(), 
                etudiant.getPrenom(), 
                etudiant.getFiliere(), 
                etudiant.getDateNaiss(), 
                etudiant.getLogin(), 
                etudiant.getPassword()
            );
            return "Inscription réussie!";
        } catch (Exception e) {
            return "Erreur lors de l'inscription: " + e.getMessage();
        }
    }

    // Classe interne pour la requête de login
    public static class LoginRequest {
        private String login;
        private String password;

        // Getters et Setters
        public String getLogin() { 
            return login;  
        }
        public void setLogin(String login) {
            this.login = login; 
        }
        public String getPassword() { 
            return password; 
        }
        public void setPassword(String password) { 
            this.password = password; 
        }
    }
}
