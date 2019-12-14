
package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class listePartenaire {

    private final SimpleIntegerProperty idpartenaire;
    private final SimpleStringProperty typepart;
    private final SimpleStringProperty intitpart;
    private final SimpleStringProperty dompart;
    private final SimpleStringProperty dateajoutpart;
    private final SimpleStringProperty infopart;
    private final SimpleDoubleProperty tarifpart;
    //Default constructor

    public listePartenaire(int idpart, String type_part, String Intitulé, String domaine, String date_creation, String infopart, double tarif) {

        this.idpartenaire = new SimpleIntegerProperty(idpart);
        this.typepart = new SimpleStringProperty(type_part);
        this.intitpart = new SimpleStringProperty(Intitulé);
        this.dompart = new SimpleStringProperty(domaine);
        this.dateajoutpart = new SimpleStringProperty(date_creation);
        this.infopart = new SimpleStringProperty(infopart);
        this.tarifpart = new SimpleDoubleProperty(tarif);
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
