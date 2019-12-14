/**
 *****
 * *****
 
                         ****  @author BOUCHEBAH  AGHILAS  *****
                 
                 
       
                 **             *************                       |************|            *
                *  *            |           *                       |            | 
               *    *           |                                   |            |
              *      *          |    *********                      |************|            *
             **********         |    |       *      ********        |            |
            *          *        |            *       ******         |            |
           *            *       **************        ****          **************
***
***|
*/ 
package model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personnelgetset {

    StringProperty nomperso, prenomperso, typeperso, sexeperso, dateemboperso;
    IntegerProperty idpersonnel, ageperso;

    public Personnelgetset(int id, String type, String nom, String prenom, String dateembo) {
        this.idpersonnel = new SimpleIntegerProperty(id);
        this.typeperso = new SimpleStringProperty(type);
        this.nomperso = new SimpleStringProperty(nom);
        this.prenomperso = new SimpleStringProperty(prenom);
        this.dateemboperso = new SimpleStringProperty(dateembo);
    }

    public int getIdpersonnel() {
        return idpersonnel.get();
    }

    public void setIdpersonnel(int id) {
        this.idpersonnel.set(id);
    }

    public String getNomperso() {
        return nomperso.get();
    }

    public void setNomperso(String nom) {
        this.nomperso.set(nom);
    }

    public String getPrenomperso() {
        return prenomperso.get();
    }

    public void setPrenomperso(String prenom) {
        this.prenomperso.set(prenom);
    }

    public int getAgeperso() {
        return ageperso.get();
    }

    public void setAgeperso(int age) {
        this.ageperso.set(age);
    }

    public String getTypeperso() {
        return typeperso.get();
    }

    public void setTypeperso(String type) {
        this.typeperso.set(type);
    }

    public String getSexeperso() {
        return sexeperso.get();
    }

    public void setSexeperso(String sexe) {
        this.sexeperso.set(sexe);
    }

    public String getDateemboperso() {
        return dateemboperso.get();
    }

    public void setDateemboperso(String dateembo) {
        this.dateemboperso.set(dateembo);
    }

}
