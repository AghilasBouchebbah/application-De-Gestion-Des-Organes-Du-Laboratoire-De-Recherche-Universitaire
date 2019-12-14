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

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author BOUCHEBAH
 */
public class EquipeTable {
  private SimpleStringProperty  intitule,acro,date;
    private SimpleIntegerProperty id;
   
    public EquipeTable(int id, String intitule, String acro,String date) {
        this.id = new SimpleIntegerProperty(id);
        this.intitule = new SimpleStringProperty(intitule);
        this.acro = new SimpleStringProperty(acro);
        this.date = new SimpleStringProperty(date);
        
      
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

    public String getDate() {
        return date.get();
    }

    public void setDate(String email) {
        this.date.set(email);
    }   
}
