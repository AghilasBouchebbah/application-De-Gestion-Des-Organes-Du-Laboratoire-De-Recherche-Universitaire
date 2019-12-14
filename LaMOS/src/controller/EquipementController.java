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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class EquipementController implements Initializable {

    
    
    @FXML
    
    void ouvrirfentOrdii(ActionEvent event) throws IOException  {
           FXMLLoader fxmload=new  FXMLLoader (getClass().getResource("/view/Ordinateurs.fxml"));
            Parent root1=(Parent)fxmload.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Ordinateurs.fxml");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();

    }
    
   
    @FXML
    void ouvrirfentimpri(ActionEvent event) throws IOException  {
        FXMLLoader fxmloader=new  FXMLLoader (getClass().getResource("/view/Imprimantess_1.fxml"));
            Parent root=(Parent)fxmloader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Imprimantess_1.fxml");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        

    }
    
     @FXML
    void ouvrirfentConso(ActionEvent event) throws IOException {
        
        FXMLLoader fxmloaderr=new  FXMLLoader (getClass().getResource("/view/Consomable.fxml"));
            Parent root2=(Parent)fxmloaderr.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(root2));
            stage.setTitle("Consomable");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    FXMLLoader fxmloaderrr=new  FXMLLoader (getClass().getResource("/view/equipement.fxml"));
       

    
    } 
  
    
    
    
}
