
package fonctions;
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
import controller.HomeController;
import java.awt.event.KeyEvent;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class navigation {

    
    private final String login="/view/Login.fxml";
    
    private final String user="/view/User.fxml";

  
     
     private final String gestionPublication="/view/Publication.fxml";
   

    public String getGestionPublication() {
        return gestionPublication;
    }
     
     

   
    

  
    public String getUser() {
        return user;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getApplicationIcon() {
        return applicationIcon;
    }

    public void setApplicationIcon(Image applicationIcon) {
        this.applicationIcon = applicationIcon;
    }
    private final String home="/view/Home.fxml";
    private final String statistique="/view/FXMLDocument.fxml";

    public String getStatistique() {
        return statistique;
    }

    public String getGestionEquipe() {
        return gestionEquipe;
    }
    
    private final String gestionEquipe="/view/GestionEquipes.fxml";
   
    private String username,nama,email;
    
    public Image applicationIcon = new Image(getClass().getResourceAsStream("/img/lamos_logo_dessus.png"));
    
    public String getHome(){
        return home;
    }

   
    public String getLogin(){
        return login;
    }
    
    
    private final String ajoutPartenaire ="/view/ajoutpartenaire.fxml";
   
    
    public String getAjoutPartenaire(){
        return ajoutPartenaire;
    }
    
    public void showAlert(AlertType type, String title, String header, String text){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
        
    
            
    public void animationFade(Node e){
        FadeTransition x = new FadeTransition(new Duration(1000), e);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.setInterpolator(Interpolator.LINEAR);
        x.play();
    }
    
}
