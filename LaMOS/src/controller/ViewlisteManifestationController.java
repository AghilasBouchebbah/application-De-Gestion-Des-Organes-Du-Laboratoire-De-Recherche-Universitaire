package controller;
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
import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import com.jfoenix.controls.JFXButton;
import model.listeManifestation;
import model.ManifestationModel;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import db.Connexion;
import fonctions.navigation;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
public class ViewlisteManifestationController implements Initializable {

    @FXML
    public JFXTextField idmanifest;
    @FXML
    private JFXTextField Intitulé;
    @FXML
    private JFXTextField lieu_org;
    @FXML
    private JFXTimePicker heure_org;
    @FXML
    private ComboBox type_manifest;
    @FXML
    private JFXTextArea resume;
    @FXML
    private JFXDatePicker date_org;
    ObservableList<String> liste = FXCollections.observableArrayList("Conférence", "Séminaire hebdomadaire");
    @FXML
    private TableView<listeManifestation> listeManifestation;
    @FXML
    private TableColumn<listeManifestation, String> colidManifest;
    @FXML
    private TableColumn<listeManifestation, String> colintitule;
    @FXML
    private TableColumn<listeManifestation, String> colType_manif;

    @FXML
    private TableColumn<listeManifestation, String> collieu_org;
    @FXML
    private TableColumn<listeManifestation, String> colheure_org;
    @FXML
    private TableColumn<listeManifestation, String> coldate_org;
    @FXML
    private TableColumn<listeManifestation, String> colresume;
    private ObservableList<listeManifestation> data;
    private Integer idmanif;
    ManifestationModel model = new ManifestationModel();
  //  Connection connection = DBConnection.Connect();
  //  DBConnection db = new DBConnection();
   
    @FXML
    private JFXTextField SearchBox;
    @FXML
    private ImageView imageview;
    private FileChooser fileChooser;
    private File file;
    private Stage stage;
    private Image image;
    private FileInputStream fis;
    String id = " ";
    @FXML
    private JFXTextField imgurl;
    Connexion con = new Connexion();
    @FXML
    private AnchorPane anchorPane;
    
    navigation nav=new navigation();
    @FXML
    private StackPane caché1;
    @FXML
    private AnchorPane loadPane1;
    @FXML
    private JFXButton btn_exit;
    @FXML
    private AnchorPane blur;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SearchUser();
        type_manifest.setItems(liste);
        onMouseclickedtabletotextfield();
        btn_exit.setVisible(false);
         
//*******load Tableview*********
       
        try {
          //  Connection conn = db.Connect();
          con.db();
            data = FXCollections.observableArrayList();
            con.res = con.stat.executeQuery(model.getrefrech());
            while (con.res.next()) {
                data.add(new listeManifestation(con.res.getInt(1), con.res.getString(2), con.res.getString(3), con.res.getString(4), con.res.getString(5), con.res.getString(6), con.res.getString(7)));
            }

            colidManifest.setCellValueFactory(new PropertyValueFactory<>("idManifestation"));
            colType_manif.setCellValueFactory(new PropertyValueFactory<>("typemanif"));
            colintitule.setCellValueFactory(new PropertyValueFactory<>("intitulémanif"));
            collieu_org.setCellValueFactory(new PropertyValueFactory<>("lieumanif"));
            colheure_org.setCellValueFactory(new PropertyValueFactory<>("heuremanif"));
            coldate_org.setCellValueFactory(new PropertyValueFactory<>("datemanif"));
            colresume.setCellValueFactory(new PropertyValueFactory<>("resumemanif"));
            listeManifestation.setItems(null);
            listeManifestation.setItems(data);
        } catch (SQLException ex) {
            System.err.println("Error" + ex.getMessage());

        }
                
        
    }
    //*************clear Textfield************

    public void textfieldClear() {
        idmanifest.setText(" ");
        Intitulé.setText(" ");
        lieu_org.setText(" ");
        ((TextField) heure_org.getEditor()).setText(" ");
        ((TextField) date_org.getEditor()).setText(" ");
        resume.setText(" ");
        Intitulé.requestFocus();
        setTypeManif();
        imageview.setImage(null);
        imgurl.setText(" ");
    }

    private void setTypeManif() {
        type_manifest.setValue("Type_Manifestation");
        type_manifest.setItems(liste);
    }
//****Alert****

   

///***************refrechhtableview*************
    /*
    

    */public void onMouseclickedtabletotextfield() {
        listeManifestation.setOnMouseClicked((MouseEvent event) -> {
            listeManifestation S = listeManifestation.getItems().get(listeManifestation.getSelectionModel().getSelectedIndex());
            idmanifest.setText(S.getIdManifestation() + " ");
            type_manifest.setValue(S.getTypemanif());
            Intitulé.setText(S.getIntitulémanif());
            lieu_org.setText(S.getLieumanif());
            heure_org.setValue(LocalTime.parse(S.getHeuremanif()));
            date_org.setValue(LocalDate.parse(S.getDatemanif()));
            resume.setText(S.getResumemanif());
            idmanif = S.getIdManifestation();
            Showimagemanifestation(idmanif);
            

        });
    }/*
//*****************ModifierManifestation*************

    

 */   public void openlistepart() throws IOException {

        Stage stg = new Stage();
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/view/ListePartenaireManif.fxml"));
        AnchorPane pane = Loader.load();
        Scene s = new Scene(pane, 530, 429);
        stg.setScene(s);
        stg.initStyle(StageStyle.TRANSPARENT);
        stg.initStyle(StageStyle.UNDECORATED);
        ListepartenaireManifController ajoutid = Loader.getController();
        try {
            id = listeManifestation.getSelectionModel().getSelectedItem().getIdManifestation() + " ";
            ajoutid.setData(id);
            stg.show();
        } catch (Exception e) {
         nav.   showAlert(Alert.AlertType.WARNING, "Warnnig", null, "Veuillez selectionner une manifestation scientifique !");

        }

    }
//***********rechercheManifestation*************

    
//*************SelectionnerImage*************

  
///***AffichageImage
    
    

    public void Showimagemanifestation(int idManif) {

        try {
         PreparedStatement st;
        st = con.con.prepareStatement(model.getshowmanifest());
            st.setInt(1, idManif);
            con.res = st.executeQuery();
            if (con.res.next()) {

            InputStream is = con.res.getBinaryStream(1);

                Image imagee = new Image(is);
                imageview.setImage(imagee);
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewlisteManifestationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//***************ConsulterParticipant********

    private void handparticipant(ActionEvent event) throws IOException {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/listeParticipants.fxml"));
            Scene s = new Scene(root, 597, 404);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(s);
            primaryStage.show();
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }

    }

    @FXML
    private void browser(ActionEvent event) {
         fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
              
        );
        stage = (Stage) anchorPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            System.out.println("" + file.getAbsolutePath());
            image = new Image(file.getAbsoluteFile().toURI().toString());
            imgurl.setText(file.toURI().toString());
            imageview.setImage(image);
        }

            
        
    }

    @FXML
    private void handReferch(ActionEvent event) {
         data.clear();
        try {

            //con.st = connection.prepareStatement(model.getrefrech());
            con.res = con.stat.executeQuery(model.getrefrech());

            while (con.res.next()) {
                data.add(new listeManifestation(con.res.getInt("idManifestation"), con.res.getString("typemanif"), con.res.getString("intitulémanif"), con.res.getString("lieumanif"), con.res.getString("heuremanif"), con.res.getString("datemanif"),con.res.getString("resumemanif")));

            }
            listeManifestation.setItems(data);
        } catch (Exception e) {
            System.err.println(e);
        }

    }


    @FXML
    private void SearchUser() {
        
        con.db();
        
         SearchBox.setOnKeyReleased(e -> {

            if (SearchBox.getText().equals(" ")) {
                data.clear();
                try {
                      
                    //st = con.con.prepareStatement(model.getrefrech());
                    con.res = con.stat.executeQuery(model.getrefrech());
                    while (con.res.next()) {
                         data.add(new listeManifestation(con.res.getInt("idManifestation"), con.res.getString("typemanif"), con.res.getString("intitulémanif"), con.res.getString("lieumanif"), con.res.getString("heuremanif"), con.res.getString("datemanif"),con.res.getString("resumemanif")));
 
                    }
                    listeManifestation.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ViewlisteManifestationController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                data.clear();
                try {
                    //db.st = connection.prepareStatement(model.getsearch(SearchBox.getText()));
                    con.res =con.stat.executeQuery(model.getsearch(SearchBox.getText()));
                    while (con.res.next()) {
                          data.add(new listeManifestation(con.res.getInt("idManifestation"), con.res.getString("typemanif"), con.res.getString("intitulémanif"), con.res.getString("lieumanif"), con.res.getString("heuremanif"), con.res.getString("datemanif"),con.res.getString("resumemanif")));

                    }
                    listeManifestation.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ViewlisteManifestationController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }


    @FXML
    private void handajouter(ActionEvent event) {
          if ( Intitulé.getText().equals("") || lieu_org.getText().equals("") || date_org.getValue().equals(" ") || heure_org.getValue().equals("") || resume.getText().equals("") || imgurl.getText().equals("")) {
            nav.showAlert(Alert.AlertType.WARNING, "Avertissement", null, "veuillez remplire tout les champs !!");
        } else {
            try {  
                con.db();
                PreparedStatement st;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateText = sdf.format(Date.valueOf(date_org.getValue()));
                SimpleDateFormat stm = new SimpleDateFormat("HH:mm:ss");
                String timeText = stm.format(Time.valueOf(heure_org.getValue()));
                st = con.con.prepareStatement(model.getinsertmanif());
                st.setString(1, type_manifest.getSelectionModel().getSelectedItem().toString());
                st.setString(2, Intitulé.getText());
                st.setString(3, lieu_org.getText());
                st.setString(4, timeText);
                st.setString(5, dateText);
                st.setString(6, resume.getText());
                fis = new FileInputStream(file);
                st.setBinaryStream(7, (InputStream) fis, (int) file.length());
                st.executeUpdate();
            nav.    showAlert(Alert.AlertType.INFORMATION, "succes", null, "la Manifestation est ajouté avec succes");
                System.out.println(fis);
                Image img = new Image(file.toURI().toString());
                imageview.setImage(img);
                textfieldClear();
            } catch (SQLException ex) {
                Logger.getLogger(ViewlisteManifestationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {

             nav.   showAlert(Alert.AlertType.ERROR, "Error", null, "veuillez remplire tous les champs !");
            }
        }
        textfieldClear();

    }

    @FXML
    private void handmodifier(ActionEvent event) throws FileNotFoundException {
         if (listeManifestation.getSelectionModel().isEmpty()) {
               nav. showAlert(Alert.AlertType.ERROR, "Erreur", null, "veuillez selectionner une ligne dans la table !");
        } else {

            try {
                PreparedStatement st;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modification");
                alert.setHeaderText("");
                alert.setContentText("Vous êtes vraiment sûr de modifier ces données ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    SimpleDateFormat stm = new SimpleDateFormat("HH:mm:ss");
                    String timeText = stm.format(Time.valueOf(heure_org.getValue()));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateText = sdf.format(Date.valueOf(date_org.getValue()));
                    String img = imgurl.getText();
                    if (img.isEmpty()) {
                        st = con.con.prepareStatement(model.getupdatemanif());
                        st.setString(1, idmanifest.getText());
                        st.setString(2, type_manifest.getSelectionModel().getSelectedItem().toString());
                        st.setString(3, Intitulé.getText());
                        st.setString(4, lieu_org.getText());
                        st.setString(5, timeText);
                        st.setString(6, dateText);
                        st.setString(7, resume.getText());
                        st.setString(8, idmanifest.getText());
                        st.executeUpdate();
                       nav. showAlert(Alert.AlertType.INFORMATION, "succes", null, "La Manifestation Scientifique est modifieé avec succés");
                        textfieldClear();
                    } else {
                        fis = new FileInputStream(file);
                        st = con.con.prepareStatement(model.getupdatemanifimg());
                        st.setString(1, idmanifest.getText());
                        st.setString(2, type_manifest.getSelectionModel().getSelectedItem().toString());
                        st.setString(3, Intitulé.getText());
                        st.setString(4, lieu_org.getText());
                        st.setString(5, timeText);
                        st.setString(6, dateText);
                        st.setString(7, resume.getText());
                        st.setBinaryStream(8, (InputStream) fis, (int) file.length());
                        st.setString(9, idmanifest.getText());
                        st.executeUpdate();
                      nav.  showAlert(Alert.AlertType.INFORMATION, "succes", null, "La Manifestation Scientifique est modifieé avec succés");
                        textfieldClear();

                    }
                }

            } catch (SQLException ex) {
               nav. showAlert(Alert.AlertType.ERROR, "erreur", null, "Les données n'ont pas pu être modifiée");
            }
        }
    }

    @FXML
    private void handsupprimer(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("");
        alert.setContentText("Voulez vous vraiment supprimer la manifestation selectionnée ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            listeManifestation user = (listeManifestation) listeManifestation.getSelectionModel().getSelectedItem();
            model.deletemanifestation(user.getIdManifestation());
           nav. showAlert(Alert.AlertType.INFORMATION, "succés", null, " la Manifestation '" + user.getIntitulémanif() + "' est supprimée avec succés!");
            textfieldClear();
        }
    }

    @FXML
    private void handajoutpartenaire(ActionEvent event) throws IOException {
           
          //openlistepart();
          btn_exit.setVisible(true);
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/view/ListePartenaireManif.fxml"));
        AnchorPane pane = Loader.load();
       
        ListepartenaireManifController ajoutid = Loader.getController();
        try {
            id = listeManifestation.getSelectionModel().getSelectedItem().getIdManifestation() + " ";
            ajoutid.setData(id);
              blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(caché1).play();
          loadPane1.getChildren().setAll(pane);
       
        } catch (Exception e) {
         nav.   showAlert(Alert.AlertType.WARNING, "Warnnig", null, "Veuillez selectionner une manifestation scientifique !");

        }
        
        
    }
    

    @FXML
    private void handvoirparticipe(ActionEvent event) throws IOException {
      
      FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/view/listeParticipants.fxml"));
        AnchorPane pane = Loader.load();
       
         btn_exit.setVisible(true);
        try {
           
              blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(caché1).play();
          loadPane1.getChildren().setAll(pane);
       
        } catch (Exception e) {
         nav.   showAlert(Alert.AlertType.WARNING, "Warnnig", null, "Veuillez selectionner une manifestation scientifique !");

        }
        
    }

    @FXML
    private void exitClick(ActionEvent event) {
        
        blur.setEffect(null);
        new FadeOutLeftTransition(caché1).play();
        btn_exit.setVisible(false);
        
    }

    @FXML
    private void onDefault(MouseEvent event) {
        btn_exit.setStyle("-fx-background-color:  white;");
    }

    @FXML
    private void onHover(MouseEvent event) {
        btn_exit.setStyle("-fx-background-color: red;");
    }

   
}
