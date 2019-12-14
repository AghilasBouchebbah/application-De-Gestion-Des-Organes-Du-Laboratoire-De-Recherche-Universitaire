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
public class ChercheurTable {
     private SimpleStringProperty  nom, grade, diplome,specialite,tel,email,type,prenom ;
    private SimpleIntegerProperty id;
   
    public ChercheurTable(int id, String Nom, String pre, String grd, String dip,String sps,String tel,String email,String type) {
        this.id = new SimpleIntegerProperty(id);
       
        this.nom = new SimpleStringProperty(Nom+" "+pre);
        this.grade = new SimpleStringProperty(grd);
        this.diplome = new SimpleStringProperty(dip);
        this.specialite = new SimpleStringProperty(sps);
        
         this.type = new SimpleStringProperty(type);
        this.tel = new SimpleStringProperty(tel);
        this.email = new SimpleStringProperty(email);
  
    }
   public  ChercheurTable( Integer id, String nom,String  prenom,String grade,String specialite){
        this.id = new SimpleIntegerProperty(id);
           this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.grade = new SimpleStringProperty(grade);
     
        this.specialite = new SimpleStringProperty(specialite);
   
   
   
   }
   public  ChercheurTable( Integer id, String nom,String prenom,String grade,String diplom,String specialite,String type){
        this.id = new SimpleIntegerProperty(id);
          this.nom = new SimpleStringProperty(nom+" "+prenom);
        this.prenom = new SimpleStringProperty(prenom);
        this.grade = new SimpleStringProperty(grade);
     this.type=new SimpleStringProperty(type);
        this.specialite = new SimpleStringProperty(specialite);
        this.diplome = new SimpleStringProperty(diplom);
   //     this.type = new SimpleStringProperty(type);
   
    
    
    
    
   }
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nomm) {
        nom.set(nomm);
    }
    public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(String nomm) {
        prenom.set(nomm);
    }

   
    public String getGrade() {
        return grade.get();
    }

    public void setGrade(String grd) {
        grade.set(grd);
    }

    public String getDiplome() {
        return diplome.get();
    }

    public void setDiplome(String diplm) {
        this.diplome.set(diplm);
    }

    public String getSpecialite() {
        return specialite.get();
    }

    public void setSpecialite(String sps) {
        this.specialite.set(sps);
    }
    public String getTel() {
        return tel.get();
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String mail) {
        this.email.set(mail);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String tp) {
        this.type.set(tp);
    }
}
