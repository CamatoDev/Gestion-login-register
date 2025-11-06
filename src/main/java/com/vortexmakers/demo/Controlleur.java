/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vortexmakers.demo;

/**
 *
 * @author CamatoDev
 */
public class Controlleur {

    GestionnaireEtudiant gestionnaireEtudiant = new GestionnaireEtudiant();
    
    public Controlleur(){
        
    }

    public void routeVersCreateOrUpdate(Etudiant etudiant) throws Exception{

        gestionnaireEtudiant.createOrUpdateEtudiant(etudiant.getId(), etudiant.getNom(),
        etudiant.getPrenom(), etudiant.getFiliere(), etudiant.getDateNaiss(),
        etudiant.getLogin(), etudiant.getPassword());
    }

};