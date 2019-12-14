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
import animation.FadeInTransition;
import animation.FadeOutLeftTransition;
import com.jfoenix.controls.JFXButton;
import fonctions.exit;
import fonctions.GlissementSouris;
import fonctions.navigation;
import fonctions.Temps;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.homeModel;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;



public class HomeController implements Initializable {
    
    TrayNotification tray = new TrayNotification(); 
     navigation nav = new navigation(); 
    homeModel model = new homeModel();
    exit ex = new exit();
    

    @FXML
    private StackPane caché;
    @FXML
    private AnchorPane loadPane;
    @FXML
    private JFXButton exitUser;
    @FXML
    private AnchorPane principale;
    @FXML
    private AnchorPane header;
    
    @FXML
    private ImageView imageUser;
    @FXML
    private Label date;
    @FXML
    private Label heure;
    @FXML
    private Label exit;
    @FXML
    private Label minimize;
    @FXML
    private AnchorPane navig;
    @FXML
    private VBox admin;
    @FXML
    private JFXButton btn_home;
    private JFXButton btn_Gestion_Utilisateur;
    @FXML
    private TitledPane gp;
    @FXML
    private JFXButton btn_Equipe;
    @FXML
    private JFXButton btn_doctorants;
    @FXML
    private JFXButton btn_Employes;
    @FXML
    private TitledPane g_ev;
    @FXML
    private JFXButton btn_Manifistations;
    private JFXButton btn_Siminaires;
    @FXML
    private TitledPane autre;
    @FXML
    private JFXButton btn_Equipements;
    @FXML
    private JFXButton btn_Partenaires;
    @FXML
    private VBox enseignant;
    @FXML
    private VBox user;
   
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label levelUser;
    @FXML
    private Label idUser;
    @FXML
    private Label usernameUser;
    @FXML
    private Label emailUser;
    @FXML
    private JFXButton btn_publication;

        private void bindToTime() {
    Timeline timeline = new Timeline(
    new KeyFrame(Duration.seconds(0),
      new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent actionEvent) {
          Calendar time = Calendar.getInstance();
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
          heure.setText(simpleDateFormat.format(time.getTime()));
        }
      }
    ),
    new KeyFrame(Duration.seconds(1))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
          bindToTime();
        Temps time = new Temps();
        date.setText(time.tanggal());
        imageUser.setCursor(Cursor.HAND);
        try {
            homeMenu();
        } catch (IOException ex) { 
        }
       
    }

    

    @FXML
    private void tombolClose(ActionEvent event) {
          principale.setEffect(null);
        new FadeOutLeftTransition(caché).play();
    }

    @FXML
    private void imageHover(MouseEvent event) {
        imageUser.setCursor(Cursor.HAND);
    }

    @FXML
    private void userClicked(MouseEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource(nav.getUser()));
        principale.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(caché).play();
        AnchorPane pane = Loader.load();
        UserController userController = Loader.getController();
        userController.setValue(model.getId(), model.getUsername(), model.getPassword(), model.getNama(), model.getEmail());
        loadPane.getChildren().setAll(pane);
    }
    

    @FXML
    private void logout(ActionEvent event) throws IOException {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("etes vous sur de vouloir  ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
            tray.setNotificationType(NotificationType.CUSTOM);
            tray.setTitle("Déconnexion avec succes");
            tray.setMessage("Merci d'avoir utiliser cette application");
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(Duration.millis(1500));
            tray.setRectangleFill(Color.valueOf("#4183D7"));
            tray.setImage(new Image("/img/icons8_Male_User_100px_2.png"));
            
            Parent database_parent = FXMLLoader.load(getClass().getResource(nav.getLogin()));
            Scene database_scene = new Scene(database_parent);
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(database_scene);
            app_stage.setTitle("Login");
            GlissementSouris md = new GlissementSouris();
            md.setDragged(database_parent, app_stage);
            app_stage.getIcons().add(nav.applicationIcon);
            app_stage.show();
        } 
        
    }

    @FXML
    private void setDefault(MouseEvent event) {
        exit.setStyle("-fx-background-color:  #4183D7;");
    }

    @FXML
    private void setHover(MouseEvent event) {
        exit.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void handleExitClicked(MouseEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous vraiment quitter l'app ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
            System.exit(0);
        } 
    }

    @FXML
    private void setDefault2(MouseEvent event) {
        minimize.setStyle("-fx-background-color:  #4183D7;");
    }

    @FXML
    private void setHover2(MouseEvent event) {
        minimize.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void handleMinimizeClicked(MouseEvent event) {
        ex.minimizeClicked(event);
    }

    @FXML
    private void setBackgroundHome(MouseEvent event) {
         btn_home.setStyle("-fx-background-color: #D2D7D3");
        btn_Employes.setStyle("-fx-background-color: #ECF0F1");
        btn_Equipe.setStyle("-fx-background-color: #ECF0F1");
        btn_Equipements.setStyle("-fx-background-color: #ECF0F1");
        btn_Manifistations.setStyle("-fx-background-color: #ECF0F1");
        btn_Partenaires.setStyle("-fx-background-color: #ECF0F1");
        
    btn_doctorants.setStyle("-fx-background-color: #ECF0F1");
   
    btn_publication.setStyle("-fx-background-color:#ECF0F1");
    
    
                }

    @FXML
    private void homeClicked(ActionEvent event) throws IOException {
        homeMenu();
         }

  

  

    @FXML
    private void setBackgroundEquipe(MouseEvent event) {
         btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_Employes.setStyle("-fx-background-color: #ECF0F1");
        btn_Equipe.setStyle("-fx-background-color: #D2D7D3");
        btn_Equipements.setStyle("-fx-background-color: #ECF0F1");
        btn_Manifistations.setStyle("-fx-background-color: #ECF0F1");
        btn_Partenaires.setStyle("-fx-background-color: #ECF0F1");
      
    btn_doctorants.setStyle("-fx-background-color: #ECF0F1");
    
    btn_publication.setStyle("-fx-background-color:#ECF0F1");
    
    }

    @FXML
    private void EquipeClicked(ActionEvent event) throws IOException {
        gestionEquipesMenu();
    }

    @FXML
    private void setBackgroundDoctorants(MouseEvent event) {
         btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_Employes.setStyle("-fx-background-color: #ECF0F1");
        btn_Equipe.setStyle("-fx-background-color: #ECF0F1 ");
        btn_Equipements.setStyle("-fx-background-color: #ECF0F1");
        btn_Manifistations.setStyle("-fx-background-color: #ECF0F1");
        btn_Partenaires.setStyle("-fx-background-color: #ECF0F1");
       
    btn_doctorants.setStyle("-fx-background-color: #D2D7D3");
    
    btn_publication.setStyle("-fx-background-color:#ECF0F1");
    
    }


/////////////////////////////////////
    @FXML
    private void doctorantsClicked(ActionEvent event) {
         try {
             
        
        rootPane.getChildren().clear();
        rootPane.setOpacity(0);
        new FadeInTransition(rootPane).play();
       // FXMLLoader loader=new FXMLLoader();
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/view/doctorant.fxml"));
        rootPane.getChildren().setAll(pane);
        }catch (Exception e) {
            System.out.println(e);
        }
    
    }


    @FXML
    private void setBackgroundEmployes(MouseEvent event) {
        btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_Employes.setStyle("-fx-background-color:#D2D7D3 ");
        btn_Equipe.setStyle("-fx-background-color: #ECF0F1 ");
        btn_Equipements.setStyle("-fx-background-color: #ECF0F1");
        btn_Manifistations.setStyle("-fx-background-color: #ECF0F1");
        btn_Partenaires.setStyle("-fx-background-color: #ECF0F1");
        
    btn_doctorants.setStyle("-fx-background-color:#ECF0F1 ");
    
    btn_publication.setStyle("-fx-background-color:#ECF0F1");
    
         
    }

    /////////////////////////////////////////////////////
    @FXML
    private void EmployesClicked(ActionEvent event) {
         try {
             
        
        rootPane.getChildren().clear();
        rootPane.setOpacity(0);
        new FadeInTransition(rootPane).play();
       // FXMLLoader loader=new FXMLLoader();
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/view/Personnel.fxml"));
        rootPane.getChildren().setAll(pane);
        }catch (Exception e) {
            System.out.println(e);
        }
    
        
    }

    @FXML
    private void setBackgroundManifistations(MouseEvent event) {
           btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_Employes.setStyle("-fx-background-color:#ECF0F1 ");
        btn_Equipe.setStyle("-fx-background-color: #ECF0F1 ");
        btn_Equipements.setStyle("-fx-background-color: #ECF0F1");
        btn_Manifistations.setStyle("-fx-background-color: #D2D7D3");
        btn_Partenaires.setStyle("-fx-background-color: #ECF0F1");
        
    btn_doctorants.setStyle("-fx-background-color:#ECF0F1 ");

    btn_publication.setStyle("-fx-background-color:#ECF0F1");
    
    }

    
    /////////////////////////////////////////////////
    @FXML
    private void ManifistationsClicked(ActionEvent event) {
    
       try {
             
        
        rootPane.getChildren().clear();
        rootPane.setOpacity(0);
        new FadeInTransition(rootPane).play();
       // FXMLLoader loader=new FXMLLoader();
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/view/listeManif.fxml"));
        rootPane.getChildren().setAll(pane);
        }catch (Exception e) {
            System.out.println(e);
        }
    
    }

    
    
   

  
    

    @FXML
    private void setBackgroundEquipements(MouseEvent event) {
             btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_Employes.setStyle("-fx-background-color: #ECF0F1");
        btn_Equipe.setStyle("-fx-background-color: #ECF0F1");
        btn_Equipements.setStyle("-fx-background-color: #D2D7D3");
        btn_Manifistations.setStyle("-fx-background-color: #ECF0F1");
        btn_Partenaires.setStyle("-fx-background-color: #ECF0F1");
        
    btn_doctorants.setStyle("-fx-background-color: #ECF0F1");
   
    btn_publication.setStyle("-fx-background-color:#ECF0F1");
    
    }
    

    //////////////////////////////////////////
    @FXML
    private void EquipementsClicked(ActionEvent event) {
        
     try {
             
        
        rootPane.getChildren().clear();
        rootPane.setOpacity(0);
        new FadeInTransition(rootPane).play();
       // FXMLLoader loader=new FXMLLoader();
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/view/equipement.fxml"));
        rootPane.getChildren().setAll(pane);
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void setBackgroundPartenaires(MouseEvent event) {
         btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_Employes.setStyle("-fx-background-color: #ECF0F1");
        btn_Equipe.setStyle("-fx-background-color: #ECF0F1");
        btn_Equipements.setStyle("-fx-background-color:#ECF0F1 ");
        btn_Manifistations.setStyle("-fx-background-color: #ECF0F1");
        btn_Partenaires.setStyle("-fx-background-color:#D2D7D3 ");
        
    btn_doctorants.setStyle("-fx-background-color: #ECF0F1");

    btn_publication.setStyle("-fx-background-color:#ECF0F1");
    }

    @FXML
    private void PartenairesClicked(ActionEvent event) {
    
     try {
             
        
        rootPane.getChildren().clear();
        rootPane.setOpacity(0);
        new FadeInTransition(rootPane).play();
       // FXMLLoader loader=new FXMLLoader();
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/view/Partenaire.fxml"));
        rootPane.getChildren().setAll(pane);
        }catch (Exception e) {
            System.out.println(e);
        }
    
    }

 
    
  
 
    private void setMenu(){
        if(levelUser.getText().equals("Admin")){
            admin.setVisible(true);
            enseignant.setVisible(false);
            user.setVisible(false);
        }
        else if (levelUser.getText().equals("Operator")){
            admin.setVisible(false);
            enseignant.setVisible(true);
            user.setVisible(false);
        }
        else{
            admin.setVisible(false);
            enseignant.setVisible(false);
            user.setVisible(true);
        }
    }
    
    private void setValueModel(){
        model.setValue(idUser.getText());
        usernameUser.setText(model.getNama());
        emailUser.setText(model.getEmail());
       // namaUser.setText(model.getNama());
        levelUser.setText(model.getLevel());
        setMenu();
    }
    
    public void setValue(String id){
        idUser.setText(id);
        setValueModel();
    }
    
      public void homeMenu() throws IOException{
        try {
            
        
        rootPane.getChildren().clear();
        rootPane.setOpacity(0);
        new FadeInTransition(rootPane).play();
       // FXMLLoader loader=new FXMLLoader();
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/view/Statistique.fxml"));
        rootPane.getChildren().setAll(pane);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
      
      public void gestionEquipesMenu() throws IOException{
          try {
             
        
        rootPane.getChildren().clear();
        rootPane.setOpacity(0);
        new FadeInTransition(rootPane).play();
       // FXMLLoader loader=new FXMLLoader();
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/view/Equipe.fxml"));
        rootPane.getChildren().setAll(pane);
        }catch (Exception e) {
            System.out.println(e);
        }
    
    }
      
     
      public void gestionPublicationMenu() throws IOException{
        try {
            
        
        rootPane.getChildren().clear();
        rootPane.setOpacity(0);
        new FadeInTransition(rootPane).play();
       // FXMLLoader loader=new FXMLLoader();
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/view/Publication.fxml"));
        rootPane.getChildren().setAll(pane);
        }catch (Exception e) {
            System.out.println(e);
        }
    }


    @FXML
    private void setBackgroundPublication(MouseEvent event) {
    btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_Employes.setStyle("-fx-background-color: #ECF0F1");
        btn_Equipe.setStyle("-fx-background-color: #ECF0F1");
        btn_Equipements.setStyle("-fx-background-color:#ECF0F1 ");
        btn_Manifistations.setStyle("-fx-background-color: #ECF0F1");
        btn_Partenaires.setStyle("-fx-background-color: #ECF0F1");
        
    btn_doctorants.setStyle("-fx-background-color: #ECF0F1");
   
    btn_publication.setStyle("-fx-background-color:#D2D7D3");
    }

    
            
    
    @FXML
    private void PublicationClicked(ActionEvent event) throws IOException {
        gestionPublicationMenu();
        
    }
    
}
