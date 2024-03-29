
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

import com.jfoenix.controls.JFXButton;
import fonctions.exit;
import fonctions.GlissementSouris;
import fonctions.navigation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.homeModel;
import model.loginModel;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class LoginController implements Initializable {
    
    navigation nav = new navigation();
    
 
    @FXML
    private Label exit;
    
    @FXML
    private Button login;
    
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
        
    @FXML
    private AnchorPane effectFade;
    @FXML
    private AnchorPane loginForm;
    @FXML
    private AnchorPane Header;
    @FXML
    private JFXButton cancel;
    
   
    
    @FXML
    public void cancelClicked(ActionEvent event){
        System.exit(0);
    }
    
    @FXML
    private void handleExitClicked(){
        exit ex = new exit();
        ex.exitClicked();
    }
    
    @FXML
    private void setHover(javafx.scene.input.MouseEvent event){
        exit.setStyle("-fx-background-color: red;");
    }
            
    @FXML
    private void setDefault(javafx.scene.input.MouseEvent event){
        exit.setStyle("-fx-background-color:  #4183D7;");
    }
            
    @FXML
    private void Login(ActionEvent event) throws IOException{
       TrayNotification tray = new TrayNotification();
        String username_text = username.getText();
        String password_text = password.getText();
        if(username_text.equals("")||password_text.equals("")){
            nav.showAlert(AlertType.WARNING, "ereur", null, " veuillez remplire les champs!!");
            username.requestFocus();
        }
        else{
            loginModel log = new loginModel();
            log.setLogin(username_text, password_text);
            if(log.getLogin()==true){
             /*   Stage primaryStage = new Stage();													
		Parent root = FXMLLoader.load(getClass().getResource(nav.getHome())); 
		Scene scene = new Scene(root);											 
		primaryStage.setScene(scene);
		primaryStage.show();*/
    
                   tray.setNotificationType(NotificationType.CUSTOM);
                    tray.setTitle("Login Success");
                    tray.setMessage("Hello "+log.getNama()+". Welcome to my apps @dzf");
                    tray.setAnimationType(AnimationType.FADE);
                    tray.showAndDismiss(Duration.millis(1500));
                    tray.setRectangleFill(Color.valueOf("#4183D7"));
                    tray.setImage(new Image("/img/icons8_Male_User_100px_2.png"));
                  
                  Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    stage2.hide();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource(nav.getHome()));
                    try {
                        loader.load();
                    } catch (Exception e) {
                    }

                    HomeController hc = loader.getController();
                    hc.setValue(log.getId());
                    Parent p = loader.getRoot();
                    Stage stage = new Stage();
                    Scene pp = new Scene(p);
                    pp.setFill(javafx.scene.paint.Color.TRANSPARENT);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(pp);
                    stage.setTitle("Home");
                    stage.getIcons().add(nav.applicationIcon);
                    GlissementSouris md = new GlissementSouris();
                    md.setDragged(p, stage);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                        @Override
                        public void handle(WindowEvent event){
                            Platform.runLater(new Runnable(){
                                @Override
                                public void run(){
                                    homeModel modelStatus=new homeModel();
                                    modelStatus.setStatus(log.getId());
                                    System.exit(0);
                                }
                            });
                        }
                    });
                    stage.show();
                }
            
            else{
                nav.showAlert(AlertType.ERROR, "Error", null, "username et mot de passe existe pas !!");
                username.setText("");
                password.setText("");
                username.requestFocus();
            }
        }
    }

    @FXML
    private void loginHover(MouseEvent event) {
        login.setCursor(Cursor.HAND);
    }

    @FXML
    private void Login(MouseEvent event) {
    }

    @FXML
    private void cancelHover(MouseEvent event) {
        cancel.setCursor(Cursor.HAND);
    }
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
