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
import model.PartenaireModel;
import db.Connexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Partenaire;

public class ModifierPartenaire implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txt_dom_p;
    @FXML
    private JFXTextField txt_num_email_p;
    @FXML
    private JFXDatePicker dt_ajou_p;
    @FXML
    private JFXComboBox<String> cm_type_p;
    @FXML
    private JFXTextField txt_tarif_p;
    @FXML
    private Button bnt_entr_p;
    @FXML
    private JFXTextField txt_image_modi;
    @FXML
    private JFXButton bnt_image_modi;
    @FXML
    private JFXTextField txt_inti_p;
    @FXML
    private ImageView image_modi;
    @FXML
    private Label lb_modi_p;
    ObservableList<String> list = FXCollections.observableArrayList("Economique", "Social");
    
        Connexion con=new Connexion();
        PartenaireModel model=new PartenaireModel();
        Partenairecntrl ctrl=new Partenairecntrl();
        String intitule;
        private File file;
    FileChooser chooser;
    FileInputStream fis;
        

    @FXML
    private void type(ActionEvent event) {
         if (cm_type_p.getValue() == "Economique") {
            txt_tarif_p.setVisible(true);
        }
    }
    
    
    
    public void setData(String intit,String dom,String type, String tarif,String date,String num){
    txt_inti_p.setText(intit);
        txt_dom_p.setText(dom);
        cm_type_p.setValue(type);
        txt_tarif_p.setText(tarif);
        dt_ajou_p.setValue(LocalDate.parse(date));
        txt_num_email_p.setText(num);
        intitule=txt_inti_p.getText();
;    
    
    }

    @FXML
    private void inputClicked(ActionEvent event) {
     
        con.db();
        PreparedStatement pst;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confiramtion de modification");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de modifier?");
        Optional<ButtonType> action = alert.showAndWait();
        
        String url = txt_image_modi.getText();
        if (action.get() == ButtonType.OK) {
            if (!ctrl.verifier_mobile(txt_num_email_p.getText())  && !ctrl.verifier_fix(txt_num_email_p.getText()) && !ctrl.verifier_email(txt_num_email_p.getText()) ) {
                txt_num_email_p.setStyle("-jfx-unfocus-color:red;");
            } else {
                 if (url.isEmpty()) {
                    System.err.println("im here");
                   
                     try {
                        pst = con.con.prepareStatement(model.getupdatesansimage(intitule));
                        pst.setString(1, txt_inti_p.getText());
                        pst.setString(2, txt_dom_p.getText());
                        pst.setString(3, (String) cm_type_p.getValue());
                        pst.setString(4, txt_num_email_p.getText());
                        pst.setString(5, txt_tarif_p.getText());
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
                        String date = dateFormat.format(Date.valueOf(dt_ajou_p.getValue()));
                        pst.setString(6, date);
                        pst.executeUpdate();
                         Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                         alert1.setHeaderText("");
                         alert1.setContentText("Modification avec Succé");
                         alert1.showAndWait();
                        
                       
                     } catch (SQLException ex) {
                         Logger.getLogger(Partenairecntrl.class.getName()).log(Level.SEVERE, null, ex);
                     }
                    
                } else {
                    try {
                        pst = con.con.prepareStatement(model.getupdateavecimage(intitule));
                        pst.setString(1, txt_inti_p.getText());
                        pst.setString(2, txt_dom_p.getText());
                        pst.setString(3, (String) cm_type_p.getValue());
                        pst.setString(4, txt_num_email_p.getText());
                        pst.setString(5, txt_tarif_p.getText());
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
                        String date = dateFormat.format(Date.valueOf(dt_ajou_p.getValue()));
                        pst.setString(6, date);
                        fis = new FileInputStream(file);
                        pst.setBinaryStream(7, (InputStream) fis, file.length());
                        pst.executeUpdate();
                         Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                         alert1.setHeaderText("");
                         alert1.setContentText("Modification avec Succé");
                         alert1.showAndWait();
                        
                        
                        
                        
                    } catch (SQLException ex) {
                        System.err.println("erreur modi" + ex);
                    } catch (FileNotFoundException ex) {
                         Logger.getLogger(Partenairecntrl.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
            }

        } else {
            alert.close();
        }
    
    }

    @FXML
    private void bnt_image(ActionEvent event) {
          chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(//new FileChooser.ExtensionFilter("All files",""),
                new FileChooser.ExtensionFilter("images Files", "*.png", "*.jpg", "*.gif")
        //,new FileChooser.ExtensionFilter("Text Fille", "txt")
        );
        Stage stage;
        stage = (Stage) pane.getScene().getWindow();
        file = chooser.showOpenDialog(stage);
        txt_image_modi.setText(file.toURI().toString());
        Image img = new Image(file.toURI().toString());
        image_modi.setImage(img);
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Partenairecntrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
                
            
                 cm_type_p.setItems(list);
    }
    
}
