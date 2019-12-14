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


import db.Connexion;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;


/**
 *
 * @author asus
 */
public class ListePartenaireManifModel {

Connexion con=new Connexion();

    public void insertidpart(String idpar, String idm) {
        try {
            con.db();
          con.stat.executeUpdate("insert into participe(idManif,idPart)values('" + idm + "','" + idpar + "')");

            //st.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "succes", null, "le partenaire est validé ");

        } catch (SQLException | HeadlessException e) {
              System.out.println(e.getMessage());
              showAlert(Alert.AlertType.WARNING, "Warnnig", null, "le partenaire est déja selectionné !");
        }

    }

    public void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void insertpartenaire(String typepart, String intitpart, String dompart, String dateajoutpart, String infopart, String tarifpart) {
        try {
            con.db();
            
           con.stat.executeUpdate("insert into partenaire(typepart,intitpart,dompart,dateajoutpart,infopart,tarifpart) values ('"
                    + typepart + "','" + intitpart + "','"
                    + dompart + "','" + dateajoutpart + "','" + infopart + "','" + tarifpart + "')");
           // st.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "succes", null, "le partenaire est ajouté avec succes");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
        public void insertSpartenaire(String typepart, String intitpart, String dompart, String dateajoutpart, String infopart) {
        try {
            con.db();
            con.stat.executeUpdate("insert into partenaire(typepart,intitpart,dompart,dateajoutpart,infopart) values ('"
                    + typepart + "','" + intitpart + "','"
                    + dompart + "','" + dateajoutpart + "','" + infopart + "')");
            //st.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "succes", null, "le partenaire est ajouté avec succes");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
   public String getsearchp(String Searchtype){
        return "SELECT * FROM partenaire where typepart LIKE '"+Searchtype+"%'";}
     public String getrefrechp(){
        return "SELECT * FROM partenaire";}
}

