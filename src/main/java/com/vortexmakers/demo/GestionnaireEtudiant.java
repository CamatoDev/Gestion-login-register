/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vortexmakers.demo;

import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author CamatoDev
 */
public class GestionnaireEtudiant {

    Etudiant etudiant = new Etudiant();

    public void createOrUpdateEtudiant (Long id, String nom, String prenom, String filiere, Date dateNaiss, String login, String password) throws Exception{

        etudiant.save(id, nom, prenom, filiere, dateNaiss, login, password) ;
    }

    public Etudiant login(String login, String password) throws SQLException{

        return etudiant.getByLoginAndPassword(login, password) ;
    }
};