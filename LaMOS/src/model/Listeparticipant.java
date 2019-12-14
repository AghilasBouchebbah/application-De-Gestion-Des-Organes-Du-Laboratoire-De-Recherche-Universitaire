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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Listeparticipant {
 





    private final SimpleIntegerProperty idManifestation;
    private final SimpleStringProperty typemanif;

    private final SimpleStringProperty intitulémanif;
    private final SimpleStringProperty lieumanif;
    private final SimpleStringProperty heuremanif;
    private final SimpleStringProperty datemanif;
    private final SimpleStringProperty resumemanif;
    private final SimpleIntegerProperty idpartenaire;
    private final SimpleStringProperty typepart;
    private final SimpleStringProperty intitpart;
    private final SimpleStringProperty dompart;
    private final SimpleStringProperty dateajoutpart;
    private final SimpleStringProperty infopart;
    private final SimpleDoubleProperty tarifpart;

    //Default constructor
    public Listeparticipant(int idManifestation, String type_manif, String Intit, String lieu_org, String heure_org, String date_org, String Resume,int idpart, String type_part, String Intitulé, String domaine, String date_creation, String infopart, double tarif) {
        this.idManifestation = new SimpleIntegerProperty(idManifestation);
        this.typemanif = new SimpleStringProperty(type_manif);

        this.intitulémanif = new SimpleStringProperty(Intit);
        this.lieumanif = new SimpleStringProperty(lieu_org);
        this.heuremanif = new SimpleStringProperty(heure_org);
        this.datemanif = new SimpleStringProperty(date_org);
        this.resumemanif = new SimpleStringProperty(Resume);
        
        this.idpartenaire = new SimpleIntegerProperty(idpart);
        this.typepart = new SimpleStringProperty(type_part);
        this.intitpart = new SimpleStringProperty(Intitulé);
        this.dompart = new SimpleStringProperty(domaine);
        this.dateajoutpart = new SimpleStringProperty(date_creation);
        this.infopart = new SimpleStringProperty(infopart);
        this.tarifpart = new SimpleDoubleProperty(tarif);

    }
    
     //getters
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
   public int getIdpartenaire() {
        return idpartenaire.get();
    }

    public String getTypepart() {
        return typepart.get();
    }

    public String getIntitpart() {
        return intitpart.get();
    }

    public String getDompart() {
        return dompart.get();
    }

    public String getDateajoutpart() {
        return dateajoutpart.get();
    }

    public String getInfopart() {
        return infopart.get();
    }

    public double getTarifpart() {
        return tarifpart.get();
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
     public void setIdpartenaire(int value) {
        idpartenaire.set(value);
    }

    public void setTypepart(String value) {
        typepart.set(value);
    }

    public void setIntitpart(String value) {
        intitpart.set(value);
    }

    public void setDompart(String value) {
        dompart.set(value);
    }

    public void setDateajoutpart(String value) {
        dateajoutpart.set(value);
    }

    public void setInfopart(String value) {
        infopart.set(value);
    }

    public void setTarifpart(double value) {
        tarifpart.set(value);
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
    
    public StringProperty typepartProperty() {
        return typepart;
    }

    public StringProperty intitpartProperty() {
        return intitpart;
    }

    public StringProperty dompartProperty() {
        return dompart;
    }

    public StringProperty dateajoutpartProperty() {
        return dateajoutpart;
    }

    public StringProperty infopartProperty() {
        return infopart;
    }

    public IntegerProperty idpartenaireProperty() {
        return idpartenaire;
    }

    public DoubleProperty tarifpartProperty() {
        return tarifpart;
    }


}

