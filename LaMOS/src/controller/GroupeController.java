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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import model.GroupeTable;
import model.GroupeModel;
import fonctions.navigation;
import db.Connexion;
import fonctions.GlissementSouris;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * 
 */
public class GroupeController implements Initializable {

    private AnchorPane blur;
    @FXML
    private JFXTextField txt_acro;
    @FXML
    private JFXTextField txt_intit;
    
    @FXML
    private TableColumn<GroupeTable,Integer> columnID;
    
    @FXML
    private Label lb_res;
    @FXML
    private TableColumn<GroupeTable, String> colNomG;
    @FXML
    private TableColumn<GroupeTable, String> colAcroG;
  
  

    Connexion con=new Connexion();
     private ObservableList<GroupeTable> data;
     navigation nav=new navigation();
     GroupeModel model=new GroupeModel();
    String nomgroup,acroGrp;
    Integer idgroupe,nbrCh,idequipe,idcherch;
    @FXML
    private Label lb_id_equip;
    @FXML
    private TableColumn<GroupeTable, Integer> colNbrCher;
    @FXML
    private TableView<GroupeTable> tabGroup;
    @FXML
    private StackPane caché1;
    @FXML
    private AnchorPane loadpane1;
    @FXML
    private JFXButton btn_exit1;
    @FXML
    private AnchorPane loadpane2;
    @FXML
    private JFXButton btn_exit2;
    @FXML
    private JFXButton boutonAjoutRespo;
    @FXML
    private JFXButton boutonModifiRes;
    
    @FXML
    private Group groupe1;
    @FXML
    private Group groupe2;
    @FXML
    private AnchorPane blur1;
    @FXML
    private JFXButton boutonSuppRespo1;
    @FXML
    private Label lb_email;
    @FXML
    private Label lb_tel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         loadtable();
    
    }    

    public void setData(Integer idEq) {
        idequipe=idEq;
    }
     public void setData2(Integer idCher) {
        idcherch=idCher;
    }

   
     public void  loadtable() {
    
        try {
            con.db();
            
            data=FXCollections.observableArrayList();
            
            con.res=con.stat.executeQuery("select idGroupe,intitgroup,acrogroup,nbrcher from  groupe where idEquipe='"+idequipe+"'  ");
            
       
           
        while(con.res.next()){
            String intitule=con.res.getString(2);
           String acro=con.res.getString(3);
           Integer nbr=con.res.getInt(4);
           int id=con.res.getInt(1);
           
           
            data.add(new GroupeTable(id,intitule,acro,nbr));
          
        }
            colNomG.setCellValueFactory(new PropertyValueFactory<>("intitule"));
            colAcroG.setCellValueFactory(new PropertyValueFactory<>("acro"));
            colNbrCher.setCellValueFactory(new PropertyValueFactory<>("nbrcher")); 
            
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            
         
            tabGroup.setItems(data);
          
            
         }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    
    }
    
    
    @FXML
    private void handajouter(ActionEvent event) {
            
         
        String intitule=txt_intit.getText();
        String acrro=txt_acro.getText();
       
        
           
      
       if(intitule.equals("")||acrro.equals("")
               ) nav.showAlert(Alert.AlertType.WARNING, "Avertissement", null, "veuillez remplire tout les champs !!");
       else{
        con.db();
         
        
            PreparedStatement st;
        try {
            st = con.con.prepareStatement(model.getInsert);
              st.setString(1, intitule);
              st.setString(2, acrro);
               st.setInt(3, idequipe);
             
              
              
              
           st.executeUpdate();
             
           nav.showAlert(Alert.AlertType.INFORMATION,"succes", null, "Groupe ajouter avec succes");
         
            
        txt_intit.setText("");
        txt_acro.setText("");
        
           
        } catch (SQLException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
            loadtable();}
    }

    
    
    
    @FXML
    private void handmodifier(ActionEvent event) {
        
          con.db();
          PreparedStatement st;
        if(tabGroup.getSelectionModel().isEmpty()){ nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"veuillez selectionner une ligne dans la table");
           }else{
            
              try {
             
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("modifier les données de du groupes");
        alert.setHeaderText("");
        alert.setContentText("Vous êtes sûr de modifier ces données ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
     
            String intit=txt_intit.getText();
            String acrro=txt_acro.getText();
          
           
            
               
                
                 st = con.con.prepareStatement(model.getUpdate);
              st.setString(1, intit);
              st.setString(2, acrro);
              st.setInt(3,idgroupe);
              st.setInt(4,idequipe);
              
            
              
               st.executeUpdate();
                
                 nav.showAlert(Alert.AlertType.INFORMATION,"succes", null, "données du goupe modifiées avec succes");
            
           
            
                loadtable();
            }
            
            
          
          
            
        } catch (SQLException ex) {
            nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"Les données n'ont pas pu etre modifier");
        }
           
        
    }}
    
    
    
    

    @FXML
    private void handsupprimer(ActionEvent event) {
       
         con.db();
        
        if(tabGroup.getSelectionModel().isEmpty()){ nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"veuillez selectionner une ligne dans la table");
           }else{
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression d'un groupe");
        alert.setHeaderText("");
        alert.setContentText("Vous êtes sûr de supprimer ce groupe ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            con.stat.executeUpdate(model.getDelete(idgroupe));
            nav.showAlert(Alert.AlertType.INFORMATION, "succes", null,"Groupe supprimé avec succes");
           loadtable();
           
                    String type="chercheur simple";
            con.stat.executeUpdate("update chercheur set typecher='"+ type+"' where idChercheur='"+idcherch+"'");
                   lb_res.setText("");
                   }
           
        } catch (SQLException ex) {
            nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"Les données n'ont pas pu etre supprimer");
            
        }}
    }

    @FXML
    private void rowClick(MouseEvent event) throws SQLException {
        
         nomgroup = tabGroup.getSelectionModel().getSelectedItem().getIntitule();
            acroGrp  = tabGroup.getSelectionModel().getSelectedItem().getAcro();
        nbrCh = tabGroup.getSelectionModel().getSelectedItem().getNbrcher();
        
        idgroupe=tabGroup.getSelectionModel().getSelectedItem().getId();
        
           String nom,prenom;
           
            txt_intit.setText(nomgroup);
        txt_acro.setText(acroGrp);
         
         
         con.db();
      //  con.res=con.stat.executeQuery("select * from groupe where idGroupe='"+idgroupe+"' ");
         
   
     //   if(con.res.next()){ 
        
        String repo="chef groupe";
        
        con.res=con.stat.executeQuery("select idChercheur from chercheur where typecher='"+repo+"' and idEq='"+idequipe+"' and idGr='"+idgroupe+"'");
        if(con.res.next()){
            idcherch=con.res.getInt(1);
          try {
              
              System.out.println(idcherch);
            con.res=con.stat.executeQuery("select nomcher,prencher,email,tel from chercheur where idChercheur='"+idcherch+"'");
            
            
            if(con.res.next()){
            nom=con.res.getString(1);prenom=con.res.getString(2);
            lb_res.setText(nom+" "+prenom);
            lb_email.setText(con.res.getString(3));
            lb_tel.setText(con.res.getString(4));
            boutonModifiRes.setVisible(true);
            boutonSuppRespo1.setVisible(true);
            boutonAjoutRespo.setVisible(false);
            
            
            
               
            
   }} catch (SQLException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);}}
   
        
       
        
       
            
            else{
            nav.showAlert(Alert.AlertType.INFORMATION,"information", null, "Cette équipe ne possede pas un responsable!, veuillez affecter un responsable");
            lb_res.setText("");
            lb_email.setText("");
            lb_tel.setText("");
            boutonModifiRes.setVisible(false);
            boutonSuppRespo1.setVisible(false);
            boutonAjoutRespo.setVisible(true);
        
            
            }}
           
        
    

    @FXML
    private void handVider(ActionEvent event) {
        txt_acro.clear();
        txt_intit.clear();
    }

    @FXML
    private void btn_responsable(ActionEvent event) throws IOException {
        if (tabGroup.getSelectionModel().isEmpty()) {
             Alert alert=new Alert(Alert.AlertType.WARNING);
             alert.setHeaderText("");
             alert.setContentText("Veuillez Selectionné Un groupe");
             alert.showAndWait();
        } else {
           
            
      
            
          groupe1.setVisible(false);
          groupe2.setVisible(true);
          
        FXMLLoader Loader1 = new FXMLLoader();
        Loader1.setLocation(getClass().getResource("/view/ChefGroup.fxml"));
        AnchorPane pane = Loader1.load();
                  ChefGroupController ajoutid = Loader1.getController();
       
           ajoutid.setData(idgroupe,idequipe);
           ajoutid.loadtable();
          
            
              blur1.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(caché1).play();
          loadpane2.getChildren().setAll(pane);
       
        
        
             }}

    @FXML
    private void btn_ajout_cherch(ActionEvent event) throws IOException {
         
             /*
             Stage stg = new Stage();
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/view/Chercheur.fxml"));
        AnchorPane pane = Loader.load();
        Scene s = new Scene(pane, 781, 457);
        stg.setScene(s);
        stg.initStyle(StageStyle.TRANSPARENT);
        stg.initStyle(StageStyle.UNDECORATED);
        GlissementSouris md = new GlissementSouris();
                    md.setDragged(pane, stg);
            
        ChercheurController ajoutid = Loader.getController();
        try {
            
            ajoutid.setData(idgroupe,idequipe);
            ajoutid.loadtable();
            stg.show();
        } catch (Exception e) {
            System.out.println(e);
        }
             */    
             
             if (tabGroup.getSelectionModel().isEmpty()) {
             Alert alert=new Alert(Alert.AlertType.WARNING);
             alert.setHeaderText("");
             alert.setContentText("Veuillez Selectionné Un groupe");
             alert.showAndWait();
        } else
         
         {
             
          groupe2.setVisible(false);
          groupe1.setVisible(true);
          btn_exit1.setVisible(true);
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/view/Chercheur.fxml"));
        AnchorPane pane = Loader.load();
                  ChercheurController ajoutid = Loader.getController();
       
          
            try{
                 ajoutid.setData(idgroupe,idequipe);
           ajoutid.loadtable();
          
              blur1.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(caché1).play();
          loadpane1.getChildren().setAll(pane);
            } catch (Exception e) {
            System.out.println(e);
        }
        }}
           
            
    

    @FXML
    private void exitClick1(ActionEvent event) {
          groupe2.setVisible(true);
         blur1.setEffect(null);
        new FadeOutLeftTransition(caché1).play();
        loadtable();
    }

    @FXML
    private void exitClick2(ActionEvent event) {
         groupe1.setVisible(true);
         blur1.setEffect(null);
        new FadeOutLeftTransition(caché1).play();
         loadtable();
    }

    @FXML
    private void btn_modifi_respo(ActionEvent event) {
    }

    @FXML
    private void btn_supp_respo(ActionEvent event) {
         con.db();
        
        if(tabGroup.getSelectionModel().isEmpty()){ nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"veuillez selectionner une ligne dans la table");
           }else{
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprission cheg de groupe");
        alert.setHeaderText("");
        alert.setContentText("Vous êtes sûr de supprimer comme chef de groupe ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            String type="chercheur simple";
            con.stat.executeUpdate("update chercheur set typecher='"+type+"' where idChercheur='"+idcherch+"' and idGr='"+idgroupe+"' and idEq='"+idequipe+"'");
            nav.showAlert(Alert.AlertType.INFORMATION, "succes", null,"responsable supprimer avec succes");
           loadtable();
           
                   lb_email.setText("");
                   lb_res.setText("");
                   lb_tel.setText("");
                   }
           
        } catch (SQLException ex) {
            nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"Les données n'ont pas pu etre supprimer");
            
        }}
    }
   /* public void cacheChefGroupe(){ groupe1.setVisible(true);
         blur.setEffect(null);
        new FadeOutLeftTransition(caché1).play();}*/

    @FXML
    private void onDefault(MouseEvent event) {
        btn_exit1.setStyle("-fx-background-color:  white;");
    }

    @FXML
    private void onHover(MouseEvent event) {
          btn_exit1.setStyle("-fx-background-color:  red;");
    }

    @FXML
    private void onDefault2(MouseEvent event) {
        btn_exit2.setStyle("-fx-background-color:  white;");
    }

    @FXML
    private void onHover2(MouseEvent event) {
          btn_exit2.setStyle("-fx-background-color:  red;");
    }
    
}
