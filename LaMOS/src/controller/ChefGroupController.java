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
import db.Connexion;
import fonctions.navigation;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import model.ChercheurModel;
import model.ChercheurTable;


/**
 * FXML Controller class
 *
 * 
 */
public class ChefGroupController implements Initializable {

    @FXML
    private TableColumn<ChercheurTable, String> colNom;
    @FXML
    private TableColumn<ChercheurTable, String> colPren;
    @FXML
    private TableColumn<ChercheurTable, String> colGrad;
    @FXML
    private TableColumn<ChercheurTable, String> colSps;
    @FXML
    private TableColumn<ChercheurTable, Integer> colId;
    @FXML
    private Label lb_idGroup;

    /**
     * Initializes the controller class.
     */
    Connexion con=new Connexion();
     private ObservableList<ChercheurTable> data;
     ChercheurModel model=new ChercheurModel(); 
     navigation nav=new navigation();
    String nom,prenom,grade,specialite;
    Integer idchercheur;
    Integer idEquipe;
    Integer idGroupe;
    @FXML
    private TableView<ChercheurTable> tabChefGroup;
    @FXML
    private JFXButton btn_valid;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXTextField txt_tel;
    
    String t[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String e[] = {"com", "dz", "fr"};
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadtable();
        
        // TODO
    }

     public void setData(Integer idgr,Integer ideq) {
         idGroupe=idgr;
         idEquipe=ideq;
       }    

     public void  loadtable() {
         
    
        try {
            con.db();
            
            data=FXCollections.observableArrayList();
            con.res=con.stat.executeQuery("select idChercheur,nomcher,prencher,gradecher,speciacher from chercheur where typecher in('chercheur simple','doctorant') and idGr='"+idGroupe+"' ");
       
        while(con.res.next()){
            
            
            String nom=con.res.getString(2);
           String prenom=con.res.getString(3);
           String grade=con.res.getString(4);
          
           String specialite=con.res.getString(5);
           Integer id=con.res.getInt(1);
           
           
            data.add(new ChercheurTable(id,nom,prenom,grade,specialite));
          
        }
            colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colPren.setCellValueFactory(new PropertyValueFactory<>("prenom") );
            colGrad.setCellValueFactory(new PropertyValueFactory<>("grade"));
            
            colSps.setCellValueFactory(new PropertyValueFactory<>("specialite") );
           
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            
         
            tabChefGroup.setItems(data);
            
           
      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
     }
     
    @FXML
    private void rowsClick(MouseEvent event) throws SQLException {
         con.db();
        idchercheur=tabChefGroup.getSelectionModel().getSelectedItem().getId();
       
        
        
    }

    @FXML
    private void handValider(ActionEvent event) throws IOException {
        con.db();
          PreparedStatement st;
        if(tabChefGroup.getSelectionModel().isEmpty()){ nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"veuillez selectionner une ligne dans la table");}
     
         else if(txt_email.getText().equals("")||txt_tel.getText().equals("")){
               
               
               nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"veuillez remplire les champs Telephone et email !");}
           
             else if (verifier_mobile().equals("false")) {
                System.out.println(verifier_mobile());
           txt_tel.setStyle("-fx-text-content:erreur;-jfx-unfocus-color:red;");
           
           }
             
           else if (verifier_email().equals("false")){
            txt_email.setStyle("-fx-text-content:erreur;-jfx-unfocus-color:red;");
           } 
        
        
        else{
            
            
            
            
            
              try {
             
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Responsable de groupe");
        alert.setHeaderText("");
        alert.setContentText("Etes vous sur de choisir ce chercheur au tant que chef de ce groupe ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
     
            
             
           
            String type="chef groupe";
               
                
                 st = con.con.prepareStatement(model.getUpdate2);
                 st.setString(1, type);
                   st.setString(2,txt_tel.getText());
                 st.setString(3, txt_email.getText());
                 
                 st.setInt(4,idchercheur);
                 st.setInt(5, idEquipe);
                 st.setInt(6,idGroupe );
              
              
               st.executeUpdate();
                
               
                 nav.showAlert(Alert.AlertType.INFORMATION,"succes", null, "chef groupe ajouter avec succes");
            
          
       btn_valid.setVisible(false);
           
         
        }
            
            
          
          
            
        } catch (SQLException ex) {
            nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"le chef de groupe n'a pa pu etre affecter");
        }
           
        
    
    }}
    
    
    
     public String verifier_mobile() {
        String txt = txt_tel.getText();
        String bool="false";
int k=3;int i=2;int exist=2;
        if (txt.substring(0, 1).equals("0") && txt.length() == 10) {
         
                if (txt.substring(1, 2).equals("5") || txt.substring(1, 2).equals("6") || txt.substring(1, 2).equals("7")) {
                   
                    System.out.println("substring 1,2 "+txt.substring(1, 2)+"taille txt"+txt.length());
                    
                    
                    while (k<=10 && i<=9) {
                                 System.out.println("je suis dans while k ="+k+"i"+i);
                                 
                      try{  for (int j = 0; j <10; j++) {
                            
                            System.out.println("je suis dans for  j ="+j+" sub "+txt.substring(i, k));
                                    
                          if (Integer.parseInt(txt.substring(i, k))==j) {
                           System.out.println("je suis dans if et  substring"+ i+","+k+"="+txt.substring(i, k));
                           
                       exist++;
                        
                           }}
                      } 
                     catch (Exception e) {
               System.out.println("problemme dans while et  "+e);
               bool= "false";
           }
                      
                      
                      
                           
                       i++;
                        k++;
                        
                    }
                    System.out.println("exist = "+exist);
                      if(exist==10) {bool= "true";}
                   
                   else {bool=  "false"; }
                        
                    

              
                } else {
                    System.err.println("error dans while substring");
                    nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"le numero introduit est incorecte");
                    bool= "false";
                }

            } else {
            nav.showAlert(Alert.AlertType.INFORMATION, "erreur", null,"le numero introduit est incorrecte");
                bool= "false";
            }
        
                  
                
        return bool;
    }

  
    public String verifier_email() {
        String txt = txt_email.getText();
       String bool="false";
            if (txt.substring(0, 1).matches("[a-zA-Z]") == true) {
                int o = 1;
                int j = 0;
                while (txt.substring(o, o + 1).matches("[a-zA-Z]") == true || txt.substring(o, o + 1).equals(t[j]) ||txt.substring(o, o+1).equals(".")) {
                    o++;
                    j++;

                }
                if (txt.substring(o, o + 1).equals("@")) {
                    o++;
                    if (txt.substring(o, o + 1).matches("[a-zA-Z]") == true) {
                        o++;
                        while (txt.substring(o, o + 1).matches("[a-zA-Z]") == true) {
                            o++;
                        }
                        if (txt.substring(o, o + 1).equals(".")) {
                            o++;
                            if (txt.substring(o).length() >1) {
                              boolean b=false;
                                for (int k = 0; k<3; k++) {
                                    
                                 if(txt.substring(o).equals(e[k])){ b=true;}else{k++;}}
                                
                                if(b==true)
                                bool= "true";else{bool="false";}
                                
                                
                            } else {
                                bool= "false";
                            }
                        } else {
                            bool= "false";
                        }
                    } else {
                        bool= "false";
                    }

                } else {
                    bool= "false";

                }

            } else {
                System.out.println("carrectere interdit  ");
              //  txt_email.setStyle("-fx-text-content:e-mail ;-fx-text-color:red");
           bool= "false";}return bool;}
    
              
    
    


    
    
    
}
