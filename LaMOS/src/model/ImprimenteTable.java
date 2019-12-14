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
package  model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class ImprimenteTable {
       private  IntegerProperty idEquipement;
    private  StringProperty marqequi;
    private  DoubleProperty prix ;
   private  StringProperty date;
   private  StringProperty etatimpri;

    public ImprimenteTable(Integer idEquipement, String marqequi, Double prix, String date, String etatimpri) {
        this.idEquipement = new SimpleIntegerProperty(idEquipement);
        this.marqequi = new  SimpleStringProperty(marqequi);
        this.prix = new  SimpleDoubleProperty(prix);
        this.date =new  SimpleStringProperty(date);
        this.etatimpri = new  SimpleStringProperty(etatimpri);
    }
     

     public Integer getIdEquipement() {
        return idEquipement.get();
    }

    public void setIdEquipement(Integer idEquipement) {
        this.idEquipement .set(idEquipement);
    }

    public String getMarqequi() {
        return marqequi.get();
    }

    public void setMarqequi(String marqequi) {
        this.marqequi.set( marqequi);
    }

    public Double getPrix() {
        return prix.get();
    }

    public void setPrix(Double prix) {
        this.prix.set(prix);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate (String date ) {
        this.date.set(date );
    }

    public String getEtatimpri () {
        return etatimpri .get();
    }

    public void setEtatimpri (String etatimpri ) {
        this.etatimpri .set( etatimpri );
    }

    
     public IntegerProperty idEquipementProperty(){
        return idEquipement;
    }
    
    public StringProperty marqequiProperty(){
        return marqequi;
    }
    public DoubleProperty prixProperty(){
        return prix;
    }
    public StringProperty dateProperty(){
        return date;
    }
    public StringProperty etatimpriProperty(){
        return etatimpri;
    }
   
    
    
}
