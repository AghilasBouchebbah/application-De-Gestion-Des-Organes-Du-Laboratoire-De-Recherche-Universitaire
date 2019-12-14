/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.sql.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
public class listeManifestation {

    private final SimpleIntegerProperty idManifestation;
    private final SimpleStringProperty typemanif;

    private final SimpleStringProperty intitulémanif;
    private final SimpleStringProperty lieumanif;
    private final SimpleStringProperty heuremanif;
    private final SimpleStringProperty datemanif;
    private final SimpleStringProperty resumemanif;

    //Default constructor
    public listeManifestation(int idManifestation, String type_manif, String Intitulé, String lieu_org, String heure_org, String date_org, String Resume) {
        this.idManifestation = new SimpleIntegerProperty(idManifestation);
        this.typemanif = new SimpleStringProperty(type_manif);

        this.intitulémanif = new SimpleStringProperty(Intitulé);
        this.lieumanif = new SimpleStringProperty(lieu_org);
        this.heuremanif = new SimpleStringProperty(heure_org);
        this.datemanif = new SimpleStringProperty(date_org);
        this.resumemanif = new SimpleStringProperty(Resume);

    }

    public int getIdManifestation() {
        return idManifestation.get();
    }

    public String getTypemanif() {
        return typemanif.get();
    }

    public String getIntitulémanif() {
        return intitulémanif.get();
    }

    public String getLieumanif() {
        return lieumanif.get();
    }

    public String getHeuremanif() {
        return heuremanif.get();
    }

    public String getDatemanif() {
        return datemanif.get();
    }

    public String getResumemanif() {
        return resumemanif.get();
    }

    //Setters
    public void setTypemanif(String value) {
        typemanif.set(value);
    }

    public void setIntitulémanif(String value) {
        intitulémanif.set(value);
    }

    public void setLieumanif(String value) {
        lieumanif.set(value);
    }

    public void setHeuremanif(String value) {
        heuremanif.set(value);
    }

    public void setDatemanif(String value) {
        datemanif.set(value);
    }

    public void setResumemanif(String value) {
        resumemanif.set(value);
    }

    public void setIdManifestation(int value) {
        idManifestation.set(value);
    }

    //Property values
    public IntegerProperty idManifestationProperty() {
        return idManifestation;
    }

    public StringProperty typemanifProperty() {
        return typemanif;
    }

    public StringProperty intitulémanifProperty() {
        return intitulémanif;
    }

    public StringProperty lieumanifProperty() {
        return lieumanif;
    }

    public StringProperty heuremanifProperty() {
        return heuremanif;
    }

    public StringProperty datemanifProperty() {
        return datemanif;
    }

    public StringProperty resumemanifProperty() {
        return resumemanif;
    }

}
