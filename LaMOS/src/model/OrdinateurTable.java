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

public class OrdinateurTable {
     private  IntegerProperty idEquipement;
    private  StringProperty marqordi;
    private  DoubleProperty prix ;
   private  StringProperty date;
   private  StringProperty etatordi;

    public OrdinateurTable(Integer idEquipement, String marqordi, Double prix, String date, String etatordi) {
       this.idEquipement = new SimpleIntegerProperty(idEquipement);
        this.marqordi = new  SimpleStringProperty(marqordi);
        this.prix = new  SimpleDoubleProperty(prix);
        this.date =new  SimpleStringProperty(date);
        this.etatordi= new  SimpleStringProperty(etatordi);
    }
    
     public Integer getIdEquipement() {
        return idEquipement.get();
    }

    public void setIdOrdinateur(Integer idEquipement) {
        this.idEquipement .set(idEquipement);
    }

    public String getMarqordi() {
        return marqordi.get();
    }

    public void setMarqordi(String marqordi) {
        this.marqordi.set( marqordi);
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

    public void setDate(String date ) {
        this.date.set(date );
    }

    public String getEtatordi () {
        return etatordi .get();
    }

    public void setEtatordi (String etatordi  ) {
        this.etatordi .set( etatordi );
    }

    
     public IntegerProperty idEquipementProperty(){
        return idEquipement;
    }
    
    public StringProperty marqordiProperty(){
        return marqordi;
    }
    public DoubleProperty prixProperty(){
        return prix;
    }
    public StringProperty dateProperty(){
        return date;
    }
    public StringProperty etatordiProperty(){
        return etatordi;
    }
    

  
    
    
    
}
