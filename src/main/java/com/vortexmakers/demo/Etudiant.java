/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vortexmakers.demo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CamatoDev
 */
public class Etudiant {
    
    DatabaseConnection JDBC = new DatabaseConnection(); // Initialisation
    
    private Long id;
    public Long getId(){
        return this.id;
    }

    private String nom;
    public String getNom(){
        return this.nom;
    }

    private String prenom;
    public String getPrenom(){
        return this.prenom;
    }

    private String filiere;
    public String getFiliere(){
        return this.filiere;
    }
    private Date dateNaiss;
    public Date getDateNaiss(){
        return this.dateNaiss;
    }

    private String login;
    public String getLogin(){
        return this.login;
    }

    private String password;
    public String getPassword(){
        return this.password;
    }

    public Etudiant () {}

    public Etudiant (Long id, String nom, String prenom, String filiere, Date dateNaiss, String login, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        this.dateNaiss = dateNaiss;
        this.login = login;
        this.password = password;
    }
    
    public void save (Long id, String nom, String prenom, String filiere, Date dateNaiss, String login, String password) throws Exception{
        try {
            String command = "";
            if (id == null)
            command = "INSERT INTO etudiant (nom, prenom, filiere, dateNaiss, login, password) VALUES (?, ?, ?, ?, ?, ?)";
        else
            command = "UPDATE etudiant SET nom = ?, prenom = ?, filiere = ?, dateNaiss = ?, login = ?, password = ? WHERE id = ?";

        PreparedStatement addstmt 
            = JDBC.getConnection().prepareStatement(command);

        addstmt.setObject(1,nom);
        addstmt.setObject(2, prenom);
        addstmt.setObject(3, filiere);
        addstmt.setDate(4, new java.sql.Date(dateNaiss.getTime()));
        addstmt.setObject(5, login);
        addstmt.setObject(6,password);

        if(id != null) {
        addstmt.setObject(7, id);
        addstmt.execute();
        }else
                addstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level. SEVERE, null, ex);
            throw new Exception (ex.getMessage());
        }
    }
    
        public Etudiant getByLoginAndPassword(String login, String password) throws SQLException{
            String cmd = "select * from etudiant where login = ? and password = ?";
            PreparedStatement stmt 
                = JDBC.getConnection().prepareStatement(cmd) ;

            stmt.setObject(1, login);
            stmt.setObject(2, password);
            ResultSet rs=stmt.executeQuery() ;
            while (rs.next()) {
                return new Etudiant(rs.getLong(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7));
            }
            return null;
        }
};
