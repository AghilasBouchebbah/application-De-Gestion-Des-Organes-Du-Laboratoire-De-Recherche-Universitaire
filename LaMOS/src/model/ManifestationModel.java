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


import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import db.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;


public class ManifestationModel {
      
  //  Connection connection = DBConnection.Connect();
    Connexion con=new Connexion();
     @FXML
    private JFXTextField Intitulé;
    @FXML
    private JFXTextField lieu_org;
    @FXML
    private JFXTextField SearchBox;
    @FXML
    private JFXTextArea resume;
    @FXML
    private JFXDatePicker date_org;
      @FXML
      
    private TableView<listeManifestation> listeManifestation;
    @FXML
       
   
    private ObservableList<listeManifestation> data;
    
    private String query;
      @FXML
    public void textfieldClear() {

        Intitulé.setText(" ");
        lieu_org.setText(" ");
   
        ((TextField) date_org.getEditor()).setText(" ");
        resume.setText(" ");
        Intitulé.requestFocus();

    }
    public void updatemanifestation(String idmanifest,String type_manif,String Intitulé, String lieu_org,String heure_org, String date_org, String resume,String img){
    
        try {con.db();
        
             
       
            /* con.stat.executeQuery("update manifestation set idManifestation='"+idmanifest+"',typemanif='"+type_manif
                    +"', intitulémanif='"+Intitulé+"', lieumanif='"+lieu_org
                    +"', heuremanif='"+heure_org+"', datemanif='"+date_org+"', resumemanif='"+resume+"',imagemanif='"+img+"'where idManifestation='"+idmanifest+"'");*/
           int i=con.stat.executeUpdate("update manifestation set idManifestation='"+idmanifest+"',typemanif='"+type_manif
                    +"', intitulémanif='"+Intitulé+"', lieumanif='"+lieu_org
                    +"', heuremanif='"+heure_org+"', datemanif='"+date_org+"', resumemanif='"+resume+"',imagemanif='"+img+"'where idManifestation='"+idmanifest+"'");
   ;
           System.out.println(i);
          if (i == 1) {
                JOptionPane.showMessageDialog(null, "la Manifestation scientifique est modifiée avec succés");

      }
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
    }
public void deletemanifestation(int idManifest){
    try{
      
        con.db();              con.stat.executeUpdate("delete from manifestation where idManifestation='"+idManifest+"'");
               
              
    }
    catch(Exception e){
        System.out.println(e);
    }
}
    public void insertmanifestation(String type_manifest,String Intitulé, String lieu_org, String heure_org, String date_org, String resume,String img){
        try {
              con.stat.executeUpdate("insert into manifestation(typemanif,intitulémanif,lieumanif,heuremanif,datemanif,resumemanif,imagemanif) values ('"
                    +type_manifest+"','"+Intitulé+"','"
                    +lieu_org+"','"+heure_org+"','"+date_org+"','"+resume+"','"+img+"')");
            //st.executeUpdate();
        } catch (Exception e) {
           
            System.out.println(e);
        }}

          public String getrefrech(){
        return "SELECT * FROM manifestation";}
          
             public String getinsertmanif(){
        return "insert into manifestation(typemanif,intitulémanif,lieumanif,heuremanif,datemanif,resumemanif,imagemanif)VALUES(?,?,?,?,?,?,?)" ;}
               
                 public String getupdatemanif(){
        return "update manifestation set idManifestation=?,typemanif=?,intitulémanif=?,lieumanif=?,heuremanif=?,datemanif=?,resumemanif=? where idManifestation=?";}
              public String getupdatemanifimg(){
        return "update manifestation set idManifestation=?,typemanif=?,intitulémanif=?,lieumanif=?,heuremanif=?,datemanif=?,resumemanif=?,imagemanif=? where idManifestation=?";}
             
             public String getshowmanifest(){
        return "SELECT imagemanif FROM manifestation where idManifestation=?";}


    public String getsearch(String Searchbox){
        return "SELECT * FROM manifestation where typemanif LIKE '%"+Searchbox+"%'UNION SELECT * FROM manifestation where intitulémanif LIKE '%"+Searchbox+"%'";}

}