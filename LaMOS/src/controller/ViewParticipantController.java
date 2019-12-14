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


import model.Listeparticipant;
import model.ParticipantModel;
import com.jfoenix.controls.JFXTextField;
import db.Connexion;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ViewParticipantController implements Initializable {

    @FXML
    private TableView<Listeparticipant> listeparticipant;
    @FXML
    private TableColumn<Listeparticipant, String> colidManifest;
    @FXML
    private TableColumn<Listeparticipant, String> colintitule;
    @FXML
    private TableColumn<Listeparticipant, String> colType_manif;
    @FXML
    private TableColumn<Listeparticipant, String> collieu_org;
    @FXML
    private TableColumn<Listeparticipant, String> colheure_org;
    @FXML
    private TableColumn<Listeparticipant, String> coldate_org;
    @FXML
    private TableColumn<Listeparticipant, String> colresume;
    @FXML
    private TableColumn<Listeparticipant, String> colidpartenaire;
    @FXML
    private TableColumn<Listeparticipant, String> coltype_partenaire;
    @FXML
    private TableColumn<Listeparticipant, String> colInitulé;
    @FXML
    private TableColumn<Listeparticipant, String> colDomaine;
    @FXML
    private TableColumn<Listeparticipant, String> coldate_creation;
    @FXML
    private TableColumn<Listeparticipant, String> colcoord;
    @FXML
    private TableColumn<Listeparticipant, String> colTarif;
    private ObservableList<Listeparticipant> dataprt;
    @FXML
    private JFXTextField Searchparticipant;
    @FXML
    private Label labelparticipe;
    Connexion con = new Connexion();
    
    ParticipantModel mdl = new ParticipantModel();
 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//*******loadtableview*******
        try {
                con.db();
           
            dataprt = FXCollections.observableArrayList();
            con.res = con.stat.executeQuery(mdl.getrefrechparticipant());
            while (con.res.next()) {
            
                dataprt.add(new Listeparticipant(con.res.getInt(1), con.res.getString(2),con.res.getString(3), con.res.getString(4), con.res.getString(5), con.res.getString(6), con.res.getString(7), con.res.getInt(9),con.res.getString(10), con.res.getString(11),con.res.getString(12), con.res.getString(13), con.res.getString(14), con.res.getInt(15)));

            }         
            colidManifest.setCellValueFactory(new PropertyValueFactory<>("idManifestation"));
            colType_manif.setCellValueFactory(new PropertyValueFactory<>("typemanif"));
            colintitule.setCellValueFactory(new PropertyValueFactory<>("intitulémanif"));
            collieu_org.setCellValueFactory(new PropertyValueFactory<>("lieumanif"));
            colheure_org.setCellValueFactory(new PropertyValueFactory<>("heuremanif"));
            coldate_org.setCellValueFactory(new PropertyValueFactory<>("datemanif"));
            colresume.setCellValueFactory(new PropertyValueFactory<>("resumemanif"));
            colidpartenaire.setCellValueFactory(new PropertyValueFactory<>("idpartenaire"));
            coltype_partenaire.setCellValueFactory(new PropertyValueFactory<>("typepart"));
            colInitulé.setCellValueFactory(new PropertyValueFactory<>("intitpart"));
            colDomaine.setCellValueFactory(new PropertyValueFactory<>("dompart"));
            coldate_creation.setCellValueFactory(new PropertyValueFactory<>("dateajoutpart"));
            colcoord.setCellValueFactory(new PropertyValueFactory<>("infopart"));
            colTarif.setCellValueFactory(new PropertyValueFactory<>("tarifpart"));
            listeparticipant.setItems(null);
            listeparticipant.setItems(dataprt);

        } catch (SQLException ex) {
            System.err.println("Error" + ex.getMessage());

        }

    }
//******rechercherparticipe************
    @FXML
    public void searchparticipe() {
        Searchparticipant.setOnKeyReleased(e -> {
            if (Searchparticipant.getText().equals("")) {
                dataprt.clear();
                try {

                    //con.stat = con.(mdl.getrefrechparticipant());
                    con.res = con.stat.executeQuery(mdl.getrefrechparticipant());

                    while (con.res.next()) {
                        dataprt.add(new Listeparticipant(con.res.getInt(1), con.res.getString(2), con.res.getString(3), con.res.getString(4), con.res.getString(5), con.res.getString(6),con.res.getString(7), con.res.getInt(9), con.res.getString(10), con.res.getString(11), con.res.getString(12), con.res.getString(13), con.res.getString(14), con.res.getInt(15)));

                    }
                    listeparticipant.setItems(dataprt);

                } catch (SQLException ex) {
                    Logger.getLogger(ViewParticipantController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                dataprt.clear();

                try {
                    con.db();
                   // db.st = connection.prepareStatement(mdl.getsearchparticipant(Searchparticipant.getText()));
                    con.res = con.stat.executeQuery(mdl.getsearchparticipant(Searchparticipant.getText()));

                    while (con.res.next()) {
                          dataprt.add(new Listeparticipant(con.res.getInt(1), con.res.getString(2), con.res.getString(3), con.res.getString(4), con.res.getString(5), con.res.getString(6),con.res.getString(7), con.res.getInt(9), con.res.getString(10), con.res.getString(11), con.res.getString(12), con.res.getString(13), con.res.getString(14), con.res.getInt(15)));

                    }
                    listeparticipant.setItems(dataprt);
                } catch (SQLException ex) {
                    Logger.getLogger(ViewParticipantController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }
//*****closefenetreparticipe
    @FXML
    private void Closeparticipe(MouseEvent event) {
        Stage stage = (Stage) labelparticipe.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Setclosep(MouseEvent event) {
        labelparticipe.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void setDefaultcloseprt(MouseEvent event) {
        labelparticipe.setStyle("-fx-background-color: #f4eeee;");
    }
}
