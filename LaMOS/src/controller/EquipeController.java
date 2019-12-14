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

import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import model.EquipeTable;
import model.EquipeModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import db.Connexion;
import fonctions.GlissementSouris;
import fonctions.navigation;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import model.publicationTable;
import fonctions.navigation;
import java.awt.Desktop;
import static java.awt.SystemColor.desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EquipeController implements Initializable {

    private JFXTextField txt_adr;
    @FXML
    private JFXTextField txt_acro;
    @FXML
    private JFXTextField txt_intit;
    @FXML
    private TableView<EquipeTable> tabEquipe;
    @FXML
    private TableColumn<?, ?> columnID;
    @FXML
    private TableColumn<EquipeTable, String> colNomE;
    @FXML
    private TableColumn<EquipeTable, String> colAcro;
    @FXML
    private TableColumn<EquipeTable, String> colMail;

    Connexion con = new Connexion();
    EquipeModel model = new EquipeModel();
    private ObservableList<EquipeTable> data;
    navigation nav = new navigation();
    String nomequipe, acroequip, date;
    Integer idequipe;
    Boolean possedeEquipe;
    int idCherch;
    FileChooser chooser;

    private File file;

    private FileInputStream fis;

    @FXML
    private StackPane caché1;
    @FXML
    private AnchorPane loadPane1;
    @FXML
    private JFXButton btn_exit;
    @FXML
    private Label lb_eq;
    @FXML
    private Label lb_res;
    @FXML
    private Label lb_mail;
    @FXML
    private AnchorPane blur;
    @FXML
    private JFXButton boutonModifRespo;
    @FXML
    private JFXButton boutonSuppRespo;
    @FXML
    private JFXButton boutonAjoutRespo;
    @FXML
    private Group groupe1;
    @FXML
    private Group groupe2;
    @FXML
    private AnchorPane loadpane2;
    @FXML
    private JFXButton btn_exit2;
    @FXML
    private JFXDatePicker dateCrea;
    @FXML
    private ImageView img_respo;
    @FXML
    private Label lb_tel;
    @FXML
    private AnchorPane equipe;
    Desktop desktop = Desktop.getDesktop();
    String tel, email;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadtable();

        boutonModifRespo.setVisible(true);
        boutonSuppRespo.setVisible(true);
        boutonAjoutRespo.setVisible(true);

    }

    public void loadtable() {

        try {
            con.db();

            data = FXCollections.observableArrayList();
            con.res = con.stat.executeQuery("select * from equipe");

            while (con.res.next()) {
                String intitule = con.res.getString(2);
                String acro = con.res.getString(3);
                String datecrea = con.res.getString(4);
                int id = con.res.getInt(1);

                data.add(new EquipeTable(id, intitule, acro, datecrea));

            }
            colNomE.setCellValueFactory(new PropertyValueFactory<>("intitule"));
            colAcro.setCellValueFactory(new PropertyValueFactory<>("acro"));
            colMail.setCellValueFactory(new PropertyValueFactory<>("date"));

            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            tabEquipe.setItems(data);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    private void handajouter(ActionEvent event) {

        String intitule = txt_intit.getText();
        String acrro = txt_acro.getText();
        String datecreat = dateCrea.getValue().toString();

        if (intitule.equals("") || acrro.equals("")
                || dateCrea.equals("")) {
            nav.showAlert(Alert.AlertType.WARNING, "Avertissement", null, "veuillez remplire tout les champs !!");
        } else {
            con.db();

            PreparedStatement st;
            try {
                st = con.con.prepareStatement(model.getInsert);
                st.setString(1, intitule);
                st.setString(2, acrro);
                st.setString(3, datecreat);

                st.executeUpdate();

                nav.showAlert(Alert.AlertType.INFORMATION, "succes", null, "Equipe ajouter avec succes");

                txt_intit.clear();
                txt_acro.clear();
                dateCrea.setValue(null);

            } catch (SQLException ex) {
                Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
            }

            loadtable();
        }
    }

    @FXML
    private void handmodifier(ActionEvent event) {

        con.db();
        PreparedStatement st;
        if (tabEquipe.getSelectionModel().isEmpty()) {
            nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null, "veuillez selectionner une ligne dans la table");
        } else {

            try {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("modifier les données de l'équipe");
                alert.setHeaderText("");
                alert.setContentText("Vous êtes sûr de modifier ces données ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    String intit = txt_intit.getText();
                    String acrro = txt_acro.getText();
                    String datecrea = dateCrea.getValue().toString();

                    st = con.con.prepareStatement(model.getUpdate);
                    st.setString(1, intit);
                    st.setString(2, acrro);
                    st.setString(3, datecrea);
                    st.setInt(4, idequipe);

                    st.executeUpdate();

                    nav.showAlert(Alert.AlertType.INFORMATION, "succes", null, "Publication modifier avec succes");

                    loadtable();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
                nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null, "Les données n'ont pas pu etre modifiés");
            }

        }
    }

    @FXML
    private void handsupprimer(ActionEvent event) throws FileNotFoundException {

        con.db();

        if (tabEquipe.getSelectionModel().isEmpty()) {
            nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null, "veuillez selectionner une ligne dans la table");
        } else {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprission d'une equipe");
                alert.setHeaderText("");
                alert.setContentText("Vous êtes sûr de supprimer cette equipe ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    con.stat.executeUpdate(model.getDelete(idequipe));
                    nav.showAlert(Alert.AlertType.INFORMATION, "succes", null, "Equipe supprimée avec succes");
                    loadtable();
                    String type = "chercheur simple";
                    con.stat.executeUpdate("update chercheur set tel=NULL,typecher='" + type + "',email=NULL,photochef=NULL where idChercheur='" + idCherch + "'");
                    lb_mail.setText("");
                    lb_res.setText("");
                    lb_tel.setText("");
                    String imag = "C:\\Users\\Hpc\\Documents\\NetBeansProjects\\LaMOS\\src\\img\\icons8_Male_User_100px_2.png";

                    FileInputStream fst = new FileInputStream(imag);

                    Image img = new Image(fst);

                    img_respo.setImage(img);

                }

            } catch (SQLException ex) {
                nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null, "Lequipe n'a pas pu etre supprimée");

            }
        }
    }

    @FXML
    private void rowClick(MouseEvent event) throws SQLException, FileNotFoundException, IOException {
        nomequipe = tabEquipe.getSelectionModel().getSelectedItem().getIntitule();
        acroequip = tabEquipe.getSelectionModel().getSelectedItem().getAcro();
        date = tabEquipe.getSelectionModel().getSelectedItem().getDate();

        idequipe = tabEquipe.getSelectionModel().getSelectedItem().getId();

        txt_intit.setText(nomequipe);
        txt_acro.setText(acroequip);
        dateCrea.setValue(LocalDate.parse(date));
        lb_eq.setText(acroequip);

        String respo = "chef equipe";
        con.db();
        con.res = con.stat.executeQuery("select idChercheur from chercheur where typecher='" + respo + "' and idEq='" + idequipe + "'");
        if (con.res.next()) {
            idCherch = con.res.getInt(1);

            try {

                System.out.println(idCherch);

                con.res = con.stat.executeQuery("select nomcher,prencher,tel,email,photochef from chercheur where idChercheur='" + idCherch + "'");

                possedeEquipe = true;
                String nom, prenom;
                if (con.res.next()) {
                    nom = con.res.getString(1);
                    prenom = con.res.getString(2);
                    tel = con.res.getString(3);
                    email = con.res.getString(4);
                    lb_res.setText(nom + " " + prenom);
                    lb_tel.setText(tel);
                    lb_mail.setText(email);
                }
                boutonModifRespo.setVisible(true);
                boutonSuppRespo.setVisible(true);
                boutonAjoutRespo.setVisible(false);

                byte[] passimg = con.res.getBytes(5);
                if (passimg == null) {
                    String imag = "C:\\Users\\Hpc\\Documents\\NetBeansProjects\\LaMOS\\src\\img\\icons8_Male_User_100px_2.png";

                    FileInputStream fst = new FileInputStream(imag);

                    Image img = new Image(fst);

                    img_respo.setImage(img);
                } else {
                    InputStream is = con.res.getBinaryStream(5);

                    Image imagee = new Image(is);

                    img_respo.setImage(imagee);

                }

            } catch (Exception ex) {
                System.out.println("L'erreur est dans rowClik If  " + ex);
            }
        } else {
            nav.showAlert(Alert.AlertType.INFORMATION, "information", null, "Cette équipe ne possede pas un responsable!, veuillez affecter un responsable");

            boutonModifRespo.setVisible(false);
            boutonSuppRespo.setVisible(false);
            boutonAjoutRespo.setVisible(true);
            String imag = "C:\\Users\\Hpc\\Documents\\NetBeansProjects\\LaMOS\\src\\img\\icons8_Male_User_100px_2.png";

            FileInputStream fst = new FileInputStream(imag);

            Image img = new Image(fst);

            img_respo.setImage(img);

            possedeEquipe = false;

          
            lb_mail.setText("");
            lb_res.setText("");
            lb_tel.setText("");

        }

    }

    @FXML
    private void handVider(ActionEvent event) {
        txt_acro.clear();
        dateCrea.setValue(null);
        txt_intit.clear();
    }

    @FXML
    private void btn_ajout_grp(ActionEvent event) throws IOException {

        if (tabEquipe.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("");
            alert.setContentText("Veuillez Selectionner Une Equipe");
            alert.showAndWait();
        } else {
               
            groupe2.setVisible(false);
            groupe1.setVisible(true);
         btn_exit.setVisible(true);
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/view/Groupe.fxml"));
            AnchorPane pane = Loader.load();

            GroupeController ajoutid = Loader.getController();
            try {

                ajoutid.setData(idequipe);
                ajoutid.loadtable();
                blur.setEffect(new GaussianBlur(10));
                new FadeInRightTransition(caché1).play();
                loadPane1.getChildren().setAll(pane);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        

    }

    @FXML
    private void onDefault(MouseEvent event) {
        btn_exit.setStyle("-fx-background-color:  white;");
    }

    @FXML
    private void onHover(MouseEvent event) {
        btn_exit.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void exitClick(ActionEvent event) {
       groupe2.setVisible(true);
        blur.setEffect(null);
        new FadeOutLeftTransition(caché1).play();

    }

    @FXML
    private void btn_modif_respo(ActionEvent event) throws IOException {
        Stage stg = new Stage();
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/view/modifierChefEquip.fxml"));
        AnchorPane pane = Loader.load();
        Scene s = new Scene(pane);
        stg.setScene(s);
        stg.initStyle(StageStyle.TRANSPARENT);
        stg.initStyle(StageStyle.UNDECORATED);
        GlissementSouris md = new GlissementSouris();
        md.setDragged(pane, stg);

        ModifierChefEquipController ajoutid = Loader.getController();
        try {

            ajoutid.setData(tel, email, idCherch);

            stg.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void btn_supp_respo(ActionEvent event) throws FileNotFoundException {
        con.db();

        if (tabEquipe.getSelectionModel().isEmpty()) {
            nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null, "veuillez selectionner une ligne dans la table");
        } else {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprission des données d'une publication");
                alert.setHeaderText("");
                alert.setContentText("Vous êtes sûr de voulioir supprimer ces données ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String type = "chercheur simple";
                    con.stat.executeUpdate("update chercheur set tel=NULL,typecher='" + type + "',email=NULL,photochef=NULL where idChercheur='" + idCherch + "'");
                    nav.showAlert(Alert.AlertType.INFORMATION, "succes", null, "Chef d'équipe supprimer avec succes");

                    lb_mail.setText("");
                    lb_res.setText("");
                    lb_tel.setText("");
                    String imag = "C:\\Users\\Hpc\\Documents\\NetBeansProjects\\LaMOS\\src\\img\\icons8_Male_User_100px_2.png";

                    FileInputStream fst = new FileInputStream(imag);

                    Image img = new Image(fst);

                    img_respo.setImage(img);

                }
            } catch (SQLException ex) {
                nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null, "le chef d'équipe na pas pu etre supprimé");

            }
        }

    }

    @FXML
    private void btn_ajoutrespo(ActionEvent event) throws IOException {
        if (tabEquipe.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("");
            alert.setContentText("Veuillez Selectionner Une Equipe");
            alert.showAndWait();
        } else {
            groupe1.setVisible(false);
         groupe2.setVisible(true);
            FXMLLoader Loader1 = new FXMLLoader();
            Loader1.setLocation(getClass().getResource("/view/ChefEquipe.fxml"));
            AnchorPane pane = Loader1.load();

            ChefEquipeController ajoutid = Loader1.getController();
        try{
            ajoutid.setData(idequipe);
            ajoutid.loadtable();

            blur.setEffect(new GaussianBlur(10));
            new FadeInRightTransition(caché1).play();
            loadpane2.getChildren().setAll(pane);
             } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

    @FXML
    private void onDefault2(MouseEvent event) {
        btn_exit2.setStyle("-fx-background-color:  white;");
    }

    @FXML
    private void onHover2(MouseEvent event) {
        btn_exit2.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void exitClick2(ActionEvent event) {
       groupe1.setVisible(true);
        blur.setEffect(null);
        new FadeOutLeftTransition(caché1).play();

    }

    /*public void cacheChefEquip() {
        groupe1.setVisible(true);
        blur.setEffect(null);
        new FadeOutLeftTransition(caché1).play();
    }*/

    public void setData(Integer idCher) {
        idCherch = idCher;
    }

    @FXML
    private void imgOnclick(MouseEvent event) throws FileNotFoundException, SQLException {
        if (tabEquipe.getSelectionModel().isEmpty()) {
            nav.showAlert(Alert.AlertType.INFORMATION, "Information", null, "Veuillez selectionner une ligne dans la table");
        } else {
            if (possedeEquipe == false) {
                nav.showAlert(Alert.AlertType.INFORMATION, "Information", null, "Cette équipe ne possede pas de responsable!, veuillez affecter un responsable");
            } else {
                chooser = new FileChooser();
                chooser.getExtensionFilters().addAll(//new FileChooser.ExtensionFilter("All files",""),
                        new FileChooser.ExtensionFilter("images Files", "*.png", "*.jpg", "*.gif"));
                Stage stage;
                stage = (Stage) equipe.getScene().getWindow();
                file = chooser.showOpenDialog(stage);
                if (file != null) {
                    try {
                        desktop.open(file);

                        Image imge = new Image(file.toURI().toString());
                        img_respo.setImage(imge);
                        con.db();

                        fis = new FileInputStream(file);
                        PreparedStatement st;

                        String img = "update chercheur set photochef=? where idChercheur=? ";
                        String type = "chef equipe";
                        st = con.con.prepareStatement(img);
                        st.setInt(2, idCherch);

                        st.setBinaryStream(1, (InputStream) fis, (int) file.length());

                        st.executeUpdate();

                        nav.showAlert(Alert.AlertType.INFORMATION, "succes", null, "photo ajouter avec succes");

                    } catch (Exception ex) {
                        System.out.println("erreur dans update image " + ex);
                    }

                }
            }
        }
    }

}
