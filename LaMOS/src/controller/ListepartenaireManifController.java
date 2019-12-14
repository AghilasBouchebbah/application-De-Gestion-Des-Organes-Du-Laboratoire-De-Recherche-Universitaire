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


import model.ListePartenaireManifModel;
import model.listePartenaire;
import com.jfoenix.controls.JFXTextField;
import db.Connexion;
import fonctions.GlissementSouris;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


public class ListepartenaireManifController implements Initializable {

    ObservableList<String> list = FXCollections.observableArrayList("Economique", "Sociale");
    @FXML
    private TableView<listePartenaire> listePartenaire;
    @FXML
    private TableColumn<listePartenaire, String> colidpartenaire;
    @FXML
    private TableColumn<listePartenaire, String> coltype_partenaire;
    @FXML
    private TableColumn<listePartenaire, String> colInitulé;
    @FXML
    private TableColumn<listePartenaire, String> colDomaine;
    @FXML
    private TableColumn<listePartenaire, String> coldate_creation;
    @FXML
    private TableColumn<listePartenaire, String> colcoord;
    @FXML
    private TableColumn<listePartenaire, String> colTarif;
    @FXML
    private ComboBox type_part;
    private ObservableList<listePartenaire> datap;
    ViewlisteManifestationController view = new ViewlisteManifestationController();
    ListePartenaireManifModel modell = new ListePartenaireManifModel();
    Connexion con= new Connexion();
    @FXML
    private JFXTextField txtidpart;
    @FXML
    private AnchorPane loadPane;
    @FXML
    private JFXTextField txtidm;
    @FXML
    private Label labelpart;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

            con.db();
        onMouseclicketabletotextfield();

        type_part.setItems(list);

      

        try {
            

            //Connection conn = db.Connect();
            datap = FXCollections.observableArrayList();
            con.res = con.stat.executeQuery(modell.getrefrechp());
            while (con.res.next()) {
                datap.add(new listePartenaire(con.res.getInt("idpartenaire"),con.res.getString("typepart"), con.res.getString("intitpart"), con.res.getString("dompart"), con.res.getString("dateajoutpart"),con.res.getString("infopart"), con.res.getInt("tarifpart")));
            }

            colidpartenaire.setCellValueFactory(new PropertyValueFactory<>("idpartenaire"));
            coltype_partenaire.setCellValueFactory(new PropertyValueFactory<>("typepart"));
            colInitulé.setCellValueFactory(new PropertyValueFactory<>("intitpart"));
            colDomaine.setCellValueFactory(new PropertyValueFactory<>("dompart"));
            coldate_creation.setCellValueFactory(new PropertyValueFactory<>("dateajoutpart"));
            colcoord.setCellValueFactory(new PropertyValueFactory<>("infopart"));
            colTarif.setCellValueFactory(new PropertyValueFactory<>("tarifpart"));
            listePartenaire.setItems(null);
            listePartenaire.setItems(datap);

        } catch (SQLException ex) {
            System.err.println("Error" + ex.getMessage());

        }
    }
///****Recherchepartenaire*********

    @FXML
    private void handRechercher(ActionEvent event) {

        datap.clear();

        try {
            con.db();
           // db.st = connection.prepareStatement(modell.getsearchp(type_part.getSelectionModel().getSelectedItem().toString()));
            con.res = con.stat.executeQuery(modell.getsearchp(type_part.getSelectionModel().getSelectedItem().toString()));

            while (con.res.next()) {
           datap.add(new listePartenaire(con.res.getInt("idpartenaire"),con.res.getString("typepart"), con.res.getString("intitpart"), con.res.getString("dompart"), con.res.getString("dateajoutpart"),con.res.getString("infopart"), con.res.getInt("tarifpart")));
            }
            
            listePartenaire.setItems(datap);
        } catch (SQLException ex) {
            Logger.getLogger(ListepartenaireManifController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void handvalider(ActionEvent event) {
        
        modell.insertidpart(txtidpart.getText(), txtidm.getText());

    }
//***********créer-nouveau-partenaire*************

    @FXML
    private void handNouveau(ActionEvent event) throws IOException {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/AjoutPartenaireManif.fxml"));
            Scene s = new Scene(root, 377, 405);
            primaryStage.setScene(s);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.initStyle(StageStyle.UNDECORATED);
             GlissementSouris md = new GlissementSouris();
                    md.setDragged(root, primaryStage);
            primaryStage.show();
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void setData(String idm) {
        this.txtidm.setText(idm);
    }

    public void onMouseclicketabletotextfield() {
        listePartenaire.setOnMouseClicked((MouseEvent event) -> {
            listePartenaire S = listePartenaire.getItems().get(listePartenaire.getSelectionModel().getSelectedIndex());
            txtidpart.setText(S.getIdpartenaire() + " ");

        });
    }
//**********refrechtableview******************

    @FXML
    public void HandRefrcher(ActionEvent event) {
        datap.clear();
        try {
            con.db();

         // db.st = connection.prepareStatement(modell.getrefrechp());
            con.res = con.stat.executeQuery(modell.getrefrechp());
            while (con.res.next()) {
              datap.add(new listePartenaire(con.res.getInt("idpartenaire"),con.res.getString("typepart"), con.res.getString("intitpart"), con.res.getString("dompart"), con.res.getString("dateajoutpart"),con.res.getString("infopart"), con.res.getInt("tarifpart")));
             
            }
            listePartenaire.setItems(datap);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
//**********closefenetrepartenaire*****************
    @FXML
    private void Closepart(MouseEvent event) {
        Stage stage = (Stage) labelpart.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void setClose(MouseEvent event) {
        labelpart.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void setDefaultclose(MouseEvent event) {
        labelpart.setStyle("-fx-background-color:  #f4eeee;");
    }

}
