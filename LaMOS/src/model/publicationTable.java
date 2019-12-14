
package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
public class publicationTable {
   

    private final StringProperty titre,id;
    private final StringProperty auteur,type,date;
    


    public publicationTable(String id,String titre,String auteur, String type,String date) {
        
        this.id = new SimpleStringProperty(id);
    
        this.titre = new SimpleStringProperty(titre);
        this.auteur = new SimpleStringProperty(auteur);
        this.type = new SimpleStringProperty(type);
        this.date = new SimpleStringProperty(date);
        
    }
    
    public String getID() {
        return id.get();
    }

    public void setId(String id) {
        this.id.setValue(id);
    }
    
    public String getAuteur() {
        return auteur.get();
    }

    public void setAuteur(String auteur) {
        this.auteur.setValue(auteur);
    }

    public String getTitre() {
        return titre.get();
    }

    public void setTitre(String titre) {
        this.titre.setValue(titre);
    }
public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.setValue(date);
    }
    public String getType() {
        return type.get();
    }

    public void setNbrdate(String nbr) {
        this.type.setValue(nbr);
    }
    
  
    
    
    
    
    
    
    

    
    
    
}
