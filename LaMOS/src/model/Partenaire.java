/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.awt.Image;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author PR'O'PC
 */
public class Partenaire {
   private StringProperty inti,dom,info,type,date;
   private DoubleProperty tarif;
  private byte[] logo;
  private IntegerProperty id;


    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id .set(id);
    }
   

    public Partenaire(byte[] Logo,String inti, String dom, String type, String info,Double tarif, String date) {
       
        this.inti = new SimpleStringProperty(inti);
        this.dom = new SimpleStringProperty(dom);
        this.info = new SimpleStringProperty(info);
        this.logo=(logo) ;
        this.type = new SimpleStringProperty(type);
        this.tarif = new SimpleDoubleProperty(tarif);
        this.date = new SimpleStringProperty(date);
       
       }

   public Partenaire(String inti, String type, String dom, String info,Double tarif, String date) {
       
        this.inti = new SimpleStringProperty(inti);
        this.dom = new SimpleStringProperty(dom);
        this.info = new SimpleStringProperty(info);
        
        this.type = new SimpleStringProperty(type);
        this.tarif = new SimpleDoubleProperty(tarif);
        this.date = new SimpleStringProperty(date);
       
       }

    public String getInti() {
        return inti.get();
    }

    public void setInti(String intit) {
        inti.set(intit);
    }

    public String getDom() {
        return dom.get();
        
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public void setDom(String domm) {
        dom.set(domm);
    }

    public String getInfo() {
        return info.get();
    }

    public void setInfo(String info) {
        this.info .set(info);
    }


    public String getType() {
        return type.get();
    }

    public void setType(String typpe) {
        type.set(typpe);
    }
    public double getTarif() {
        return tarif.get();
    }

    public void setTarif(Double tarif) {
        this.tarif.set(tarif);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

  
    
    
    
   
}
