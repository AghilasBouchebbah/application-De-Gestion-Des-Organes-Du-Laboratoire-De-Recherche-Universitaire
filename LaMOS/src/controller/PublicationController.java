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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Blob;
import db.Connexion;
import fonctions.navigation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.JOptionPane;
import model.Doctorant;
import model.publicationModel;
import model.publicationTable;
import sun.awt.DesktopBrowse;




public class PublicationController implements Initializable {

    @FXML
    private ImageView img_publication;
    @FXML
    private Label titre_publication;
    @FXML
    private Label auteur_publication;
    @FXML
    private Label date_publication;
    @FXML
    private TableColumn<publicationTable, String> Titre;
    @FXML
    private TableColumn<publicationTable, String> Auteur;
  
    @FXML
    private JFXButton btn_ajout_pub;
    @FXML
    private JFXButton btn_supp_pub;
    @FXML
    private TableView<publicationTable> table_pub;

    
    @FXML
    private AnchorPane publication;
    
    
    Connexion con=new Connexion();
    publicationModel model=new publicationModel();
     private ObservableList<publicationTable> data;
   
     private String titre,auteur,type,date,image;
     private Integer idpub;
     
    FileChooser chooser;
    navigation nav=new navigation();
    private File file;
    
 private FileInputStream fis;
 
 private Desktop desktop=Desktop.getDesktop();
     
     
    @FXML
    private TableColumn<publicationTable, String> Nombre_page;
    @FXML
    private TableColumn<publicationTable, String> Date_pub;
    @FXML
    private TableColumn<?, ?> photo_pub;
    @FXML
    private JFXTextField nbrPage;
    @FXML
    private JFXButton loadImg;
    @FXML
    private JFXTextField titre_pub;
    @FXML
    private JFXTextField auteur_pub; 
    @FXML
    private JFXDatePicker date_pub;
     
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private JFXTextField imageUrl;
    @FXML
    private TextField txt_rech_p;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
            loadtable();
            action_rech_txt();
      
    }    

    
    public void  loadtable() {
    
        try {
            con.db();
            
            data=FXCollections.observableArrayList();
            con.res=con.stat.executeQuery("select idpub,titre,auteur,type,date from publication");
       
        while(con.res.next()){
            String titre=con.res.getString(2);
           String auteur=con.res.getString(3);
           String type=con.res.getString(4);
           String date=con.res.getString(5);
           
           String id=con.res.getString(1);
           
           
            data.add(new publicationTable(id,titre,auteur,type,date));
          
        }
            Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            Auteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
            Nombre_page.setCellValueFactory(new PropertyValueFactory<>("type") );
         
            Date_pub.setCellValueFactory(new PropertyValueFactory<>("date") );
            photo_pub.setCellValueFactory(new PropertyValueFactory<>("photo") );
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            
         
            table_pub.setItems(data);
            
           
      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    
    }
    
   

    @FXML
    private void rowClick(MouseEvent event) {
        
        titre = table_pub.getSelectionModel().getSelectedItem().getTitre();
            auteur = table_pub.getSelectionModel().getSelectedItem().getAuteur();
        type = table_pub.getSelectionModel().getSelectedItem().getType();
        date = table_pub.getSelectionModel().getSelectedItem().getDate();
        idpub=Integer.parseInt(table_pub.getSelectionModel().getSelectedItem().getID());
        
     //   image = "C:\\Users\\BOUCHEBAH\\Documents\\NetBeansProjects\\LaMOS\\src\\img\\"+table_pub.getSelectionModel().getSelectedItem().getPhoto();
        
        con.db();
        try {
            
            PreparedStatement pst;
            pst=con.con.prepareStatement("select image from publication where idpub=?");
            pst.setInt(1, idpub);
            con.res=pst.executeQuery();
            if(con.res.next()){
                
                InputStream is=con.res.getBinaryStream(1);
            
                   
                    
                    
            
            Image imagee=new Image(is);
            img_publication.setImage(imagee);
        }} catch (SQLException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
           titre_publication.setText(titre);
           auteur_publication.setText(auteur);
           date_publication.setText(date);
           
        
              titre_pub.setText(titre);
         auteur_pub.setText(auteur);
         nbrPage.setText(type);
         date_pub.setValue(LocalDate.parse(date));
        
           
    
    }
    
   
    
    @FXML
    private void loadClick(ActionEvent event) {
   
    chooser=new FileChooser();
            chooser.getExtensionFilters().addAll(//new FileChooser.ExtensionFilter("All files",""),
                    new FileChooser.ExtensionFilter("images Files","*.png","*.jpg","*.gif")  );
            Stage stage;
        stage=(Stage) publication.getScene().getWindow();
        file=chooser.showOpenDialog(stage);
        if(file!=null){try {
            desktop.open(file);
            imageUrl.setText(file.toURI().toString());
        } catch (IOException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
        
        
           
        
    }
    @FXML
    private void ajoutClick(ActionEvent event) throws FileNotFoundException {
        
        
        
        String titre=titre_pub.getText();
        String auteur=auteur_pub.getText();
        String type=nbrPage.getText();
           
      
       if(titre.equals("")||auteur.equals("")||
                type.equals("")) nav.showAlert(Alert.AlertType.WARNING, "Avertissement", null, "veuillez remplire tout les champs !!");
       else{
        con.db();
          String date=date_pub.getValue().toString();
   
       fis=new FileInputStream(file); 
        
        
        
        
            PreparedStatement st;
        try {
            st = con.con.prepareStatement(model.getInsert);
              st.setString(1, titre);
              st.setString(2, auteur);
              st.setString(3, type);
              st.setString(4, date);
              
           st.setBinaryStream(5,(InputStream)fis,(int)file.length());
           st.executeUpdate();
             
           nav.showAlert(Alert.AlertType.INFORMATION,"succes", null, "Publication ajouter avec succes");
         
            System.out.println(fis); 
            Image img=new Image(file.toURI().toString());
            img_publication.setImage(img);
            
            titre_publication.setText(titre);
            auteur_publication.setText(auteur);
            date_publication.setText(date);
            
            titre_pub.setText("");
            auteur_pub.setText("");
            nbrPage.setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
            loadtable();}
}

    @FXML
    private void suprimerClick(ActionEvent event) {
        con.db();
        
        if(table_pub.getSelectionModel().isEmpty()){ nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"veuillez selectionner une ligne dans la table");
           }else{
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer les données de publication");
        alert.setHeaderText("");
        alert.setContentText("Vous êtes sûr de supprimer ces données ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            con.stat.executeUpdate(model.getDelete(idpub));
            nav.showAlert(Alert.AlertType.INFORMATION, "succes", null,"Publication supprimer avec succes");
           loadtable();
           titre_publication.setText("Titre");
           auteur_publication.setText("Auteur");
           date_publication.setText("date");
         String imag = "C:\\Users\\BOUCHEBAH\\Documents\\NetBeansProjects\\LaMOS\\src\\img\\icon_webstore.jpg";
            try {
                FileInputStream fst=new FileInputStream(imag);
                
                Image img=new Image(fst);
            
                   img_publication.setImage(img);
                   
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
        } catch (SQLException ex) {
            nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"Les données n'ont pas pu etre supprimer");
            
        }}
    }

    @FXML
    private void modifierClick(ActionEvent event) throws FileNotFoundException {
         con.db();
          PreparedStatement st;
        if(table_pub.getSelectionModel().isEmpty()){ nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"veuillez selectionner une ligne dans la table");
           }else{
            
              try {
             
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("modifier les données de publication");
        alert.setHeaderText("");
        alert.setContentText("Vous êtes sûr de modifier ces données ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
     
            String titr=titre_pub.getText();
            String aut=auteur_pub.getText();
            String type=nbrPage.getText();
            String dt=date_pub.getValue().toString();
            
            String im=imageUrl.getText();
            if(im.isEmpty()){
              
                 st = con.con.prepareStatement(model.getUpdate1);
              st.setString(1, titr);
              st.setString(2, aut);
              st.setString(3, type);
              st.setString(4, dt);
              st.setInt(5, idpub);
                st.executeUpdate();  
                 nav.showAlert(Alert.AlertType.INFORMATION,"succes", null, "Publication modifier avec succes");
            
            titre_pub.setText("");
            auteur_pub.setText("");
            nbrPage.setText("");
            //date_pub.setValue(null);
            
            
                loadtable();
            }
            
            
          
           else{
                fis=new FileInputStream(file); 
        
        
        
            
            st = con.con.prepareStatement(model.getUpdate);
              st.setString(1, titr);
              st.setString(2, aut);
              st.setString(3, type);
              st.setString(4, dt);
              
           st.setBinaryStream(5,(InputStream)fis,(int)file.length());
           st.setInt(6,idpub);
           st.executeUpdate();
             
           nav.showAlert(Alert.AlertType.INFORMATION,"succes", null, "Publication modifier avec succes");
         
            
            titre_pub.setText("");
            auteur_pub.setText("");
            nbrPage.setText("");
            
            imageUrl.setText("");
             Image img=new Image(file.toURI().toString());
            img_publication.setImage(img);
            
                loadtable();}
            
        }} catch (SQLException ex) {
            nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"Les données n'ont pas pu etre modifier");
        }
           
           
        }
             }
    
    
    private void action_rech_txt() {
        con.db();
        PreparedStatement pst;
        txt_rech_p.setOnKeyReleased(e1 -> {
            try {
            if (txt_rech_p.getText().equals("")) {
                loadtable();
            } else {
                data.clear();
                String rech = txt_rech_p.getText().toLowerCase();

                
                    con.res= con.stat.executeQuery(model.getrechrche(rech));
                    while (con.res.next()) {
                        String titre=con.res.getString(2);
           String auteur=con.res.getString(3);
           String type=con.res.getString(4);
           String date=con.res.getString(5);
           
           String id=con.res.getString(1);
           
           
            data.add(new publicationTable(id,titre,auteur,type,date));
                    }
                   
                 Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            Auteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
            Nombre_page.setCellValueFactory(new PropertyValueFactory<>("type") );
         
            Date_pub.setCellValueFactory(new PropertyValueFactory<>("date") );
            photo_pub.setCellValueFactory(new PropertyValueFactory<>("photo") );
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            
         
            table_pub.setItems(data);
            
           
            }
                     } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        });
        
    }
           
        
        
    }
    

