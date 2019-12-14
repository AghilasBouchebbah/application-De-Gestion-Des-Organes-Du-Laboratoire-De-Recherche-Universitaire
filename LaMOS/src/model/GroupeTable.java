/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author BOUCHEBAH
 */
public class GroupeTable {
     private SimpleStringProperty  intitule,acro;
    private SimpleIntegerProperty id,nbrcher;
   
    public GroupeTable(int id, String intitule, String acro,Integer nbr) {
        this.id = new SimpleIntegerProperty(id);
        this.intitule = new SimpleStringProperty(intitule);
        this.acro = new SimpleStringProperty(acro);
        this.nbrcher=new SimpleIntegerProperty(nbr);
        
        
        
      
    }
   

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getIntitule() {
        return intitule.get();
    }

    public void setIntitule(String intitule) {
        this.intitule.set(intitule);
    }

    public String getAcro() {
        return acro.get();
    }

    public void setAcro(String acro) {
        this.acro.set(acro);
    }

    public Integer getNbrcher() {
        return nbrcher.get();
    }

    public void setNbrcher(Integer nbr) {
        this.nbrcher.set(nbr);
    }   
    
}
