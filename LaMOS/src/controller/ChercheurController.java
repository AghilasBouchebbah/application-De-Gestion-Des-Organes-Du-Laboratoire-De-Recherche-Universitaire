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
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import db.Connexion;
import fonctions.navigation;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.ChercheurTable;
import model.ChercheurModel;
/**
 * FXML Controller class
 *
 * 
 */
public class ChercheurController implements Initializable {

    @FXML
    private JFXTextField txt_dip;
    @FXML
    private JFXTextField txt_pre;
    @FXML
    private Label lb_eq;
    @FXML
    private Label lb_grp;
    @FXML
    private JFXTextField txt_grad;
    @FXML
    private JFXTextField txt_spes;
    @FXML
    private JFXTextField txt_nom;
    @FXML
    private JFXComboBox<String> cmb_typeCher;

    /**
     * Initializes the controller class.
     */
    
     Connexion con=new Connexion();
     private ObservableList<ChercheurTable> data;
     ChercheurModel model=new ChercheurModel(); 
     navigation nav=new navigation();
    String nom,prenom,grade,diplome,specialite,type;
    Integer idchercheur,idEquipe,idGroupe;
      ObservableList<String> liste = FXCollections.observableArrayList("Doctorant", "Chercheur simple");
    
    @FXML
    private TableColumn<ChercheurTable, Integer> columnID;
    @FXML
    private TableColumn<ChercheurTable, String> colNom;
    private TableColumn<ChercheurTable,  String> colPrenom;
    @FXML
    private TableColumn<ChercheurTable,  String> colDiplome;
    @FXML
    private TableColumn<ChercheurTable,  String> colSpecialit;
    @FXML
    private TableView<ChercheurTable> tabChercheur;
    @FXML
    private TableColumn<ChercheurTable, String> colGrade;
    @FXML
    private JFXButton btn_exit;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_typeCher.setItems(liste);
        // TODO
    }    

     public void  loadtable() {
         
    
        try {
            con.db();
            
            data=FXCollections.observableArrayList();
            con.res=con.stat.executeQuery("select idChercheur,nomcher,prencher,gradecher,diplocher,speciacher,typecher from chercheur where idGr='"+idGroupe+"' and idEq='"+idEquipe+"'  ");
       
        while(con.res.next()){
            
        
            String nom=con.res.getString(2);
           String prenom=con.res.getString(3);
           String grade=con.res.getString(4);
          String type=con.res.getString(7);
           String diplome=con.res.getString(5);
           String specialite=con.res.getString(6);
           int id=con.res.getInt(1);
           
           
            data.add(new ChercheurTable(id,nom,prenom,grade,diplome,specialite,type));
          
        }
        
        
            colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
            colDiplome.setCellValueFactory(new PropertyValueFactory<>("diplome") );
            colSpecialit.setCellValueFactory(new PropertyValueFactory<>("specialite") );
           
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            
         
            tabChercheur.setItems(data);
            
           
      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    
    }
    
    @FXML
    private void handajouter(ActionEvent event) {
          String nom=txt_nom.getText();
        String prenom=txt_pre.getText();
        String grade=txt_grad.getText();
        String diplome=txt_dip.getText();
        String specialite=txt_spes.getText();
        String type=cmb_typeCher.getSelectionModel().getSelectedItem().toString();
       
           
      
       if(nom.equals("")||prenom.equals("")||
                grade.equals("")||diplome.equals("")||specialite.equals("")) nav.showAlert(Alert.AlertType.WARNING, "Avertissement", null, "veuillez remplire tout les champs !!");
       else{
        con.db();
         
        
            PreparedStatement st;
        try {
            st = con.con.prepareStatement(model.getInsert);
              st.setString(1, nom);
              st.setString(2, prenom);
              st.setString(3, type);
              st.setString(4, grade);
              st.setString(5, diplome);
              st.setString(6, specialite);
              st.setInt(7, idGroupe);
              st.setInt(8, idEquipe);
              
              
              
           st.executeUpdate();
             
           nav.showAlert(Alert.AlertType.INFORMATION,"succes", null, "chercheur ajouter avec succes");
           con.stat.executeUpdate("update groupe set nbrcher=nbrcher+1 where idGroupe='"+idGroupe+"' and  idEquipe='"+idEquipe+"'");
                     clear();
           
        } catch (SQLException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
            loadtable();}
    }

    @FXML
    private void handmodifier(ActionEvent event) {
          con.db();
          PreparedStatement st;
        if(tabChercheur.getSelectionModel().isEmpty()){ nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"veuillez selectionner une ligne dans la table");
           }else{
            
              try {
             
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("modifier les données d'un chercheur");
        alert.setHeaderText("");
        alert.setContentText("Vous êtes sûr de modifier ces données ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
                
            String nom=txt_nom.getText();
            String prenom=txt_pre.getText();
             String type=cmb_typeCher.getSelectionModel().getSelectedItem().toString();
             String grade=txt_grad.getText();
             String diplome=txt_dip.getText();
             String specialite=txt_spes.getText();
             
           
            
               
                
                 st = con.con.prepareStatement(model.getUpdate);
              st.setString(1, nom);
              st.setString(2, prenom);
              st.setString(3, type);
              st.setString(4, grade);
               st.setString(5, diplome);
                st.setString(6,specialite );
                st.setInt(7,idchercheur);
            
              
               st.executeUpdate();
                
               
                 nav.showAlert(Alert.AlertType.INFORMATION,"succes", null, "Publication modifier avec succes");
            clear();
           
            
                loadtable();
            }
            
            
          
          
            
        } catch (SQLException ex) {
            nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"Les données n'ont pas pu etre modifier");
        }
           
        
    }
    }

    @FXML
    private void handVider(ActionEvent event) {
        clear();
    }

    @FXML
    private void rowClick(MouseEvent event) throws SQLException {
        con.db();
        idchercheur=tabChercheur.getSelectionModel().getSelectedItem().getId();
       
            grade = tabChercheur.getSelectionModel().getSelectedItem().getGrade();
        diplome = tabChercheur.getSelectionModel().getSelectedItem().getDiplome();
        specialite=tabChercheur.getSelectionModel().getSelectedItem().getSpecialite();
        type=tabChercheur.getSelectionModel().getSelectedItem().getType();
        
        txt_grad.setText(grade);
        txt_dip.setText(diplome );
        txt_spes.setText(specialite);
        cmb_typeCher.setValue(type);
        con.res=con.stat.executeQuery("select nomcher,prencher from chercheur where idChercheur='"+idchercheur+"'");
        if(con.res.next()){
        String nomm =con.res.getString(1);
       String prenomm =con.res.getString(2);
        txt_nom.setText(nomm);
        txt_pre.setText(prenomm);}
        
    }

    @FXML
    private void handsupprimer(ActionEvent event) {
          con.db();
        
        if(tabChercheur.getSelectionModel().isEmpty()){ nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"veuillez selectionner une ligne dans la table");
           }else{
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer les données d'un chercheur");
        alert.setHeaderText("");
        alert.setContentText("Vous êtes sûr de supprimer ces données ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            con.stat.executeUpdate(model.getDelete(idchercheur));
            nav.showAlert(Alert.AlertType.INFORMATION, "succes", null,"chercheur supprimer avec succes");
           loadtable();
             con.stat.executeUpdate("update groupe set nbrcher=nbrcher-1 where idGroupe='"+idGroupe+"' and  idEquipe='"+idEquipe+"'");
                   
              clear();
                   }
           
        } catch (SQLException ex) {
            nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"Les données n'ont pas pu etre supprimer");
            
        }}
    }
    

public void clear(){
    txt_dip.clear();
    txt_grad.clear();txt_nom.clear();txt_pre.clear();txt_spes.clear();
}

    public void setData(Integer idgr,Integer ideq){
         idGroupe=idgr;
         idEquipe=ideq;
    }

    @FXML
    private void onDefault(MouseEvent event) {
        btn_exit.setStyle("-fx-background-color:  white;");
    }

    @FXML
    private void onHover(MouseEvent event) {
        btn_exit.setStyle("-fx-background-color:  red;");
    }

    /*@FXML
    private void exitClick(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/Groupe.fxml"));
        loader.load();
        GroupeController hm=loader.getController();
        hm.loadtable();
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.close();
    }*/

    @FXML
    private void exitClick(ActionEvent event) {
    }

}
