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

import db.Connexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.PreparedStatement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import model.Personnelgetset;
import model.Model_requete;
import com.jfoenix.controls.JFXDatePicker;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class Personnelcntrl implements Initializable {

    ObservableList<String> list = FXCollections.observableArrayList("TCS", "Secrtariare", "Ingénieur", "autre");
    ObservableList<Personnelgetset> personnel = FXCollections.observableArrayList();
    Connexion con = new Connexion();
    Model_requete rqt = new Model_requete();
    
    @FXML
    private TableView<Personnelgetset> tabper;
    @FXML
    private JFXDatePicker dt_embo_pe;
    @FXML
    private TableColumn<Personnelgetset, String> col_id;
    @FXML
    private TableColumn<Personnelgetset, String> col_nom_pe;
    @FXML
    private TableColumn<Personnelgetset, String> col_pren_pe;
    @FXML
    private TableColumn<Personnelgetset, String> col_type_pe;
    private TableColumn<Personnelgetset, String> col_age_pe;
    @FXML
    private TableColumn<Personnelgetset, String> col_dateembo_pe;
    @FXML
    private JFXComboBox<String> cm_type_pe;
    @FXML
    private JFXTextField txt_nom_pe;
    @FXML
    private JFXTextField txt_pre_pe;
    private JFXTextField txt_age_pe;
    @FXML
    private JFXTextField txt_rech_pe;
    @FXML
    private JFXTextField txt_id_pe;
    @FXML
    private JFXButton btn_modi_pe;
    @FXML
    private JFXButton btn_ajou_pe;
    @FXML
    private JFXButton btn_ann_pe;
    @FXML
    private JFXButton btn_supp_pe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cm_type_pe.setItems(list);
        ref();
        col_id.setCellValueFactory(new PropertyValueFactory<>("idpersonnel"));
        col_type_pe.setCellValueFactory(new PropertyValueFactory<>("typeperso"));
        col_nom_pe.setCellValueFactory(new PropertyValueFactory<>("nomperso"));
        col_pren_pe.setCellValueFactory(new PropertyValueFactory<>("prenomperso"));
        
        
        col_dateembo_pe.setCellValueFactory(new PropertyValueFactory<>("dateemboperso"));
        tabper.setItems(null);
        tabper.setItems(personnel);
        select();

    }

    public void clear1() {
        txt_nom_pe.clear();
        txt_pre_pe.clear();
       
        cm_type_pe.setValue(null);
        dt_embo_pe.setValue(null);
        
    }

    public void ref() {
        personnel.clear();
con.db();
        try {
           con.res = con.stat.executeQuery("SELECT * FROM personnel");
            while (con.res.next()) {
                personnel.add(new Personnelgetset(con.res.getInt(1), con.res.getString(2), con.res.getString(3), con.res.getString(4), con.res.getString(5)));

            }
        } catch (Exception e) {
            System.out.println("doc" + e);
        }
        tabper.setItems(personnel);

    }

    public void select() {

        tabper.setOnMouseClicked((MouseEvent event) -> {
            Personnelgetset per = (Personnelgetset) tabper.getSelectionModel().getSelectedItem();
            cm_type_pe.setValue(per.getTypeperso());
            txt_nom_pe.setText(per.getNomperso());
            txt_pre_pe.setText(per.getPrenomperso());
            

            dt_embo_pe.setValue(LocalDate.parse(per.getDateemboperso()));
           

        });

    }

    @FXML
    public void recherchetxt() {
        con.db();
        txt_rech_pe.setOnKeyReleased(e1 -> {
            if (txt_rech_pe.getText().equals("")) {
                ref();
            } else {
                personnel.clear();
                String rech = txt_rech_pe.getText().toLowerCase();

                try {
                   con.res = con.stat.executeQuery("Select * from personnel where nomperso like'" + rech + "%'");
                    while (con.res.next()) {
                        personnel.add(new Personnelgetset(con.res.getInt("idpersonnel"), con.res.getString("typeperso"), con.res.getString("nomperso"), con.res.getString("prenomperso"), con.res.getString("dateemboperso")));
                    }
                } catch (Exception e) {
                    System.out.println("perso" + e);
                }
                tabper.setItems(personnel);
            }
        });
    }

    @FXML
    private void actionajou(ActionEvent event) {
        if (txt_nom_pe.getText().equals("") || txt_pre_pe.getText().equals("")  || dt_embo_pe.getValue().equals("")) {
            showAlert(Alert.AlertType.WARNING, "Avertissement", null, "veuillez remplire tout les champs !!");
        } else {
            con.db();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
            String date = dateFormat.format(Date.valueOf(dt_embo_pe.getValue()));

            PreparedStatement st;

            try {
                        
           
                
                st = con.con.prepareStatement(rqt.getinserper());
                st.setString(1, cm_type_pe.getValue());
                st.setString(2, txt_nom_pe.getText());
                st.setString(3, txt_pre_pe.getText());
                

               
                st.setString(4, date);
                st.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("");
                alert.setContentText(cm_type_pe.getValue() + " ajouté avec succée");
                alert.showAndWait();
            } catch (SQLException ex) {
                System.err.println("insert  " + ex);
            }

            ref();
        }
    }

    @FXML
    private void actionmodi(ActionEvent event) {
        if (tabper.getSelectionModel().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", null, "veuillez selectionner un Personnel !");
        } else {
            con.db();
            try {
                PreparedStatement st;
                 Personnelgetset p = (Personnelgetset) tabper.getSelectionModel().getSelectedItem();

                st = con.con.prepareStatement(rqt.getupdateper());
                st.setString(1, cm_type_pe.getValue());
                st.setString(2, txt_nom_pe.getText());
                st.setString(3, txt_pre_pe.getText());
                
                
             
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
                String date = dateFormat.format(Date.valueOf(dt_embo_pe.getValue()));
                st.setString(4, date);
                
                st.setInt(5,p.getIdpersonnel());
                st.executeUpdate();
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setHeaderText("");
                alert1.setContentText("Modification avec Succé");
                alert1.showAndWait();
                ref();
            } catch (SQLException ex) {
                Logger.getLogger(Personnelcntrl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @FXML
    private void actionsupprimer(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confiramtion de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de supprimer?");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {

            Personnelgetset p = (Personnelgetset) tabper.getSelectionModel().getSelectedItem();

            try {
con.db();
    PreparedStatement st;
      st = con.con.prepareStatement(rqt.getdeleteper(p.getIdpersonnel()));
                    

                st.executeUpdate();
                st.close();
                showAlert(Alert.AlertType.INFORMATION, "succés", null, " le personnel '" + p.getNomperso() + "' est supprimée avec succés!");

                ref();
            } catch (SQLException e) {
                System.out.println(e);
            }

        } else {
            alert.close();
        }

    }

    @FXML
    private void actionannuler(ActionEvent event) {
        clear1();
    }
}
