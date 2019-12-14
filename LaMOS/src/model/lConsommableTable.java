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

import javafx.beans.property.*;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableIntegerProperty;


        

public class lConsommableTable {
    private  IntegerProperty idEquipement;
     private  StringProperty typeconso;
    private  StringProperty marqequi;
    private  DoubleProperty prix ;
   private  StringProperty date;
    

    public lConsommableTable(Integer idEquipement, String typeconso, String marquequip, Double prix, String date) {
        this.idEquipement = new SimpleIntegerProperty(idEquipement);
          this.typeconso=new  SimpleStringProperty(typeconso);
        this.marqequi = new  SimpleStringProperty(marquequip);
        this.prix = new  SimpleDoubleProperty(prix);
        this.date =new  SimpleStringProperty(date);
      
    }

    public Integer getIdEquipement() {
        return idEquipement.get();
    }

    public void setIdEquipement(Integer idequipement) {
         this.idEquipement .set(idequipement);
       
    }
    public String getTypeconso() {
        return typeconso.get();
    }

    public void setTypeconso(String typeconso) {
        this.typeconso .set(typeconso);
    }

    public String getMarqequi() {
        return marqequi.get();
    }

    public void setMarqequi(String marqequi) {
        this.marqequi .set(marqequi);
    }

    public Double getPrix() {
        return prix.get();
    }

    public void setPrix(Double prix) {
        this.prix .set(prix);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date .set(date);
    }

     public IntegerProperty idEquipementProperty(){
        return  idEquipement;
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
    public StringProperty typeconsoProperty(){
        return typeconso;
    }
   
    
    
    
}
