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

import model.Partenaire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import db.Connexion;
import model.PartenaireModel;


public class AjoutpartenairePartController implements Initializable {

    FileChooser chooser;
    private File file;
    @FXML
    private AnchorPane pane;

    ObservableList<String> list = FXCollections.observableArrayList("Economique", "Social");

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    @FXML
    private JFXTextField txt_inti_p;
    @FXML
    private JFXTextField txt_dom_p;
    @FXML
    private JFXTextField txt_num_email_p;
    @FXML
    private JFXTextField txt_tarif_p;
    @FXML
    private JFXDatePicker dt_ajou_p;
    @FXML
    private JFXComboBox<String> cm_type_p;


   
    @FXML
    private Label lb_warning;
    private FileInputStream fis;

    
    @FXML
    private ImageView image;

    Connexion con = new Connexion();
    PartenaireModel model=new PartenaireModel();
    
    @FXML
    private JFXTextField txt_image_url;
    @FXML
    private Button bnt_logo_p;
    @FXML
    private Label lb_ajou_p;
    @FXML
    private JFXButton bnt_entr_p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cm_type_p.setItems(list);
        txt_tarif_p.setVisible(false);
        

    }

    String t[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String e[] = {"com", "dz", "fr"};
    int i = 0, k = 4;

    ////
    public void clear1() {

        txt_inti_p.clear();
        txt_dom_p.clear();
        cm_type_p.setValue("");
        txt_tarif_p.clear();
        dt_ajou_p.setValue(LocalDate.now());
        txt_num_email_p.clear();
        txt_image_url.clear();
        image.setImage(null);

    }

    

    public boolean verifier_mobile() {
        String txt = txt_num_email_p.getText();

        if ((txt.substring(0, 1).equals("0") && (txt.length() == 10 || txt.length() == 9))) {
            if (txt.length() == 10) {
                if (txt.substring(1, 2).equals("5") || txt.substring(1, 2).equals("6") || txt.substring(1, 2).equals("7")) {
                    while (txt.substring(3, k).equals(t[i])) {
                        i++;
                        k++;
                    }

                    return true;
                } else {
                    System.err.println("po de 5,6,7");
                    return false;
                }

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean verifier_fix() {
        String txt = txt_num_email_p.getText();
        if (txt.substring(0, 3).equals("034")) {
            if (txt.length() == 9) {
                while (txt.substring(3, k).equals(t[i])) {
                    i++;
                    k++;

                }
                System.err.println("1");
                return true;

            } else {
                System.err.println("2");
                return false;
            }
        } else {
            System.err.println("3");
            return false;
        }
    }

    public boolean verifier_email() {
        String txt = txt_num_email_p.getText();
        if ((txt.substring(0, 1).equals("0") && (txt.length() > 10 || txt.length() > 9))) {
            return false;
        } else if (txt.contains("@")) {
            if (txt.substring(0, 1).matches("[a-zA-Z]") == true) {
                int o = 1;
                int j = 0;
                while (txt.substring(o, o + 1).matches("[a-zA-Z]") == true || txt.substring(o, o + 1).equals(t[j])) {
                    o++;
                    j++;

                }
                if (txt.substring(o, o + 1).equals("@")) {
                    o++;
                    if (txt.substring(o, o + 1).matches("[a-zA-Z]") == true) {
                        o++;
                        while (txt.substring(o, o + 1).matches("[a-zA-Z]") == true) {
                            o++;
                        }
                        if (txt.substring(o, o + 1).equals(".")) {
                            o++;
                            int h = 0;
                            if (txt.substring(o).length() > 2) {
                                while (txt.substring(o).equals(e[h])) {
                                    h++;
                                    System.err.println("h" + e[h]);
                                }
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }

                } else {
                    return false;

                }

            } else {
                System.out.println("taille depasse ou po num po email ");
                txt_num_email_p.setStyle("-fx-text-content:e-mail ou numero de téléphone;-fx-text-color:red");
            }
            return false;
        } else {
            txt_num_email_p.setStyle("-fx-text:e-mail ou numero de téléphone;-fx-text-fill:red");
            return false;
        }
    }

    @FXML
    private void inputClicked(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        con.db();
        PreparedStatement pst;
        
        String url = txt_image_url.getText();

        if (txt_inti_p.getText().equals("") && txt_dom_p.getText().equals("") && txt_num_email_p.getText().equals("")) {
            lb_warning.setVisible(true);

        } else if (url.isEmpty()) {
            try {
                pst = con.con.prepareStatement(model.getinserpartsansimage());
                pst.setString(1, txt_inti_p.getText());
                pst.setString(2, txt_dom_p.getText());
                pst.setString(3, (String) cm_type_p.getValue());
                if (!verifier_mobile() && !verifier_fix() && !verifier_email()) {
                    txt_num_email_p.setStyle("-jfx-unfocus-color:red;");
                    txt_num_email_p.setText("");
                } else {
                    pst.setString(4, txt_num_email_p.getText());
                }
                pst.setString(5, txt_tarif_p.getText());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
                String date = dateFormat.format(Date.valueOf(dt_ajou_p.getValue()));
                pst.setString(6, date);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("");
                alert.setContentText("Partenaire ajouté avec succée");
                alert.showAndWait();

                clear1();
            } catch (Exception e) {
            }
        } else {

            try {

                pst = con.con.prepareStatement(model.getinserpart());
                pst.setString(1, txt_inti_p.getText());
                pst.setString(2, txt_dom_p.getText());
                pst.setString(3, (String) cm_type_p.getValue());
                if (!verifier_mobile() && !verifier_fix() && !verifier_email()) {
                    txt_num_email_p.setStyle("-jfx-unfocus-color:red;");
                    txt_num_email_p.setText("");
                } else {
                   pst.setString(4, txt_num_email_p.getText());
                }

                pst.setString(5, txt_tarif_p.getText());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
                String date = dateFormat.format(Date.valueOf(dt_ajou_p.getValue()));
                pst.setString(6, date);
                fis = new FileInputStream(file);
                pst.setBinaryStream(7, (InputStream) fis, file.length());

                int l = pst.executeUpdate();
                if (l == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("");
                    alert.setContentText("Partenaire ajouté avec succée");
                    alert.showAndWait();
                }
                clear1();

            } catch (SQLException ex) {
                System.err.println("erreur ajouter " + ex);
            }
        }

    }

    @FXML
    private void type(ActionEvent event) {
        if (cm_type_p.getValue().equals("Economique")) {
            txt_tarif_p.setVisible(true);

        } else {
            txt_tarif_p.setVisible(false);
            txt_tarif_p.setText("0");
        }
    }

    @FXML
    private void actionlogo(ActionEvent event) throws FileNotFoundException {
        chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(//new FileChooser.ExtensionFilter("All files",""),
                new FileChooser.ExtensionFilter("images Files", "*.png", "*.jpg", "*.gif")
        //,new FileChooser.ExtensionFilter("Text Fille", "txt")

        );
        Stage stage;
        stage = (Stage) pane.getScene().getWindow();
        file = chooser.showOpenDialog(stage);
        if(file.equals(null)){System.out.println("khkh");}else{
                


        fis = new FileInputStream(file);}
    }


   
   

}
