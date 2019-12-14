package model;
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

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Doctorant {

    private SimpleStringProperty  Nom, pre, intitule, dom, respo, date, sexe, datesou;
    private SimpleIntegerProperty id,age;
   
    public Doctorant(int id, String nom, String pre,   String intitule,  String respo,String dom, String datesou) {
        this.id = new SimpleIntegerProperty(id);
          this.Nom = new SimpleStringProperty(nom+"  "+pre);
        
        
        this.intitule = new SimpleStringProperty(intitule);
        this.dom = new SimpleStringProperty(dom);
        this.respo = new SimpleStringProperty(respo);
      
        this.datesou = new SimpleStringProperty(datesou);}

    

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNom() {
        return Nom.get();
    }

    public void setNom(String nom) {
        Nom.set(nom);
    }

    public String getPre() {
        return pre.get();
    }

    public void setPre(String pre) {
        this.pre.set(pre);
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getSexe() {
        return sexe.get();
    }

    public void setSexe(String sex) {
        sexe.set(sex);
    }

    public String getDom() {
        return dom.get();
    }

    public void setDom(String dom) {
        this.dom.set(dom);
    }

    public String getIntitule() {
        return intitule.get();
    }

    public void setIntitule(String intitule) {
        this.intitule.set(intitule);
    }

    public String getRespo() {
        return respo.get();
    }

    public Doctorant() {
    }

    public void setRespo(String respo) {
        this.respo.set(respo);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getDatesou() {
        return datesou.get();
    }

    public void setDatesou(String datesou) {
        this.datesou.set(datesou);
    }

}
