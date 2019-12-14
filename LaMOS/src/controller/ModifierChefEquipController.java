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

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.Connexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import fonctions.navigation;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class ModifierChefEquipController implements Initializable {

    @FXML
    private JFXTextField txt_tel;
    @FXML
    private JFXTextField txt_email;
String tel,email;
Integer idcher;
    @FXML
    private JFXButton btn_exit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_email.setText(email);
        txt_tel.setText(tel);
      
    }    
    
    public void setData(String tell, String emaill,Integer idcheer){tel=tell;email=emaill;idcher=idcheer;}
navigation nav=new navigation();
    Connexion con=new Connexion();
    @FXML
    private void onValid(ActionEvent event)  {
       
       email= txt_email.getText();
        tel=txt_tel.getText();
        if(email==null|| tel==null){nav.showAlert(Alert.AlertType.ERROR," erreur","","veuillez remplire les champs");}
        else{
            con.db();
           try {
               con.stat.executeUpdate("update chercheur set tel='"+tel+"' ,email='"+email+"' where idChercheur='"+idcher+"'");
          
           nav.showAlert(Alert.AlertType.INFORMATION, "succes", "", "coordonnées modifier avec succes");
           
               
           } catch (SQLException ex) {
               Logger.getLogger(ModifierChefEquipController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        
        
        }
            
        
        
    }


    @FXML
    private void onHover(MouseEvent event) {
        btn_exit.setStyle("-fx-background-color:  red;");
    }

    @FXML
    private void exitClick(ActionEvent event) throws IOException {
          FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/Groupe.fxml"));
        loader.load();
        GroupeController hm=loader.getController();
        hm.loadtable();
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.close();
    }

    @FXML
    private void onDefault(MouseEvent event) {
        btn_exit.setStyle("-fx-background-color:  white;");
    }
    
}
