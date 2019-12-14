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

import model.OrdinateurTable;
import model.ConnexionEQ;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.*;
import model.ImprimenteTable;
import model.ModelOrdinateur;





public class OrdinateursController implements Initializable {
  
   
    @FXML
    private JFXTextField txtMarqordin;
    @FXML
    private JFXTextField txtPRIXordina;
    @FXML
    private JFXButton buttonAjordina;
    @FXML
    private JFXButton buttMODordinat;
    @FXML
    private JFXButton buttsuppordinat;
    @FXML
    private TableView<OrdinateurTable> tablordinat;
     @FXML
    private TableColumn<OrdinateurTable, Integer> idEquipement;
     @FXML
    private TableColumn<OrdinateurTable, String> marqordi;
   
    @FXML
    private TableColumn<OrdinateurTable, Double> collprixordina;
    @FXML
    private TableColumn<OrdinateurTable, Date> colldateachatordina;
    @FXML
    private TableColumn<OrdinateurTable, String> colletatordinat;
    @FXML
    private JFXTextField txtrech;
    @FXML
    private JFXDatePicker txtdatAchatordina;
    @FXML
    private JFXComboBox<String> cometatordinat;
     private ObservableList<OrdinateurTable> data;
    
       ObservableList<String> list2 = FXCollections.observableArrayList("Nouveau", "Ancien","En Paine");
    OrdinateurTable model;
    
    ConnexionEQ con=new  ConnexionEQ();
   public Statement stat;
     public  ResultSet res;
     PreparedStatement pst;
    @FXML
    private Label labordinclose;
    
    @FXML
    private JFXButton VIDER;
    ModelOrdinateur   m=new ModelOrdinateur();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cometatordinat.setItems(list2);
        try {
            
            
            stat = con.obtenirconx().createStatement();
            res = stat.executeQuery(m.getselection());
            data=FXCollections.observableArrayList();
            while(res.next()){
                Integer idEquipement=res.getInt(1);
                String marqordi=res.getString(2);
                Double prix=res.getDouble(3);
                String date =res.getString(4);
                String etat=res.getString(5);
                OrdinateurTable model=new OrdinateurTable(idEquipement, marqordi, prix, date, etat);
                
                data.add(model);
            }
            idEquipement.setCellValueFactory(new PropertyValueFactory<>("idEquipement"));
            marqordi.setCellValueFactory(new PropertyValueFactory<>("marqordi"));
            collprixordina.setCellValueFactory(new PropertyValueFactory<>("prix"));
            colldateachatordina.setCellValueFactory(new PropertyValueFactory<>("date"));
            colletatordinat.setCellValueFactory(new PropertyValueFactory<>("etatordi"));
            
            
            tablordinat.setItems(data);
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OrdinateursController.class.getName()).log(Level.SEVERE, null, ex);
        }
                onkey();
      }
    

    @FXML
    private void ajoutordinat(ActionEvent event) {
        if (txtMarqordin.getText().equals("")||txtPRIXordina.getText().equals("")
               ||txtdatAchatordina.getValue().equals("")
                ||cometatordinat.getSelectionModel().getSelectedItem().toString().equals(" ")  ) {
             
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Avertissement");
             alert.setHeaderText(null);
             alert.setContentText("veuillez remplire tout les champs !!");
             alert.show();
            
        } else {
        
        String marque=txtMarqordin.getText();
        String prix=txtPRIXordina.getText();
        SimpleDateFormat stm=new SimpleDateFormat("yyyy-MM-dd");
        String datee=stm.format(Date.valueOf(txtdatAchatordina.getValue()));
        String etat=cometatordinat.getSelectionModel().getSelectedItem().toString();
        
        
              
        try {
            //ref();
         
            
            
            pst =  con.obtenirconx().prepareStatement(m.getajoutordi());
             pst.setString(1, marque);
              pst.setString(2, prix);
              pst.setString(3, datee);
              pst.setString(4, etat);
               pst.executeUpdate();
              pst.close();
              ref();
              

        } catch (Exception e) {
            System.err.println("erreur"+e.getMessage());
        }
        
        
    }}

    @FXML
    private void modordina(ActionEvent event) {
        
         String marque=txtMarqordin.getText();
        String prix=txtPRIXordina.getText();
        String etat=cometatordinat.getSelectionModel().getSelectedItem().toString();
        SimpleDateFormat stm=new SimpleDateFormat("yyyy-MM-dd");
        String datee=stm.format(Date.valueOf(txtdatAchatordina.getValue()));
        
          try {
                OrdinateurTable p = tablordinat.getSelectionModel().getSelectedItem();

                pst = con.obtenirconx().prepareStatement("UPDATE equipement SET "
                               + " marqordi = ?, prix = ?, date = ?, etatordi=? WHERE marqordi ='"+p.getMarqordi()+"'" );
                pst.setString(1, marque);
                pst.setString(2, prix);
                pst.setString(3, datee);
                pst.setString(4, etat);
                pst.executeUpdate();
                ref();
              clear();
              
            } catch (SQLException ex) {
                System.err.println("erreur modi" + ex);
            }
           }

    @FXML
    private void supprordinat(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confiramtion de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de supprimer?");
        
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {

            OrdinateurTable c = (OrdinateurTable)tablordinat.getSelectionModel().getSelectedItem();

            try {
                      ref();
                
                pst = con.obtenirconx().prepareStatement(m.getsupp());
                pst.setString(1, c.getIdEquipement()+"");
                pst.executeUpdate();
                
                pst.close();
                ref();
              clear();
          
               
               
            } catch (SQLException e) {
               System.err.println("erreur"+e.getMessage());
            }

        } else {
            alert.close();
        }
        
    }

    @FXML
    private void selectionner(MouseEvent event) {
        OrdinateurTable c = (OrdinateurTable)  tablordinat.getSelectionModel().getSelectedItem();
        txtMarqordin.setText(c.getMarqordi());
        txtPRIXordina.setText(c.getPrix()+"");
        cometatordinat.setValue(c.getEtatordi());
        txtdatAchatordina.setValue(LocalDate.parse(c.getDate()));
        
    }
     public void clear(){
        txtMarqordin.clear();
        txtPRIXordina.clear();
        txtdatAchatordina.setValue(null);
        cometatordinat.setValue("");
        
     }
     public void ref() {
         data.clear();
         try {
             stat = con.obtenirconx().createStatement();
             res = stat.executeQuery(m.getselection());
            data=FXCollections.observableArrayList();
     while(res.next()){
          model=new OrdinateurTable(
            res.getInt("idEquipement"),
            res.getString("marqordi"),
            res.getDouble("prix"),
           res.getString("date"),
            res.getString("etatordi"));
           data.add(model);
         tablordinat.setItems(data);
        }
     clear();
     } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
    @FXML
    private void setDfClose(MouseEvent event) {
         labordinclose.setStyle("-fx-background-color: #f4eeee;");
    }

    @FXML
    private void SetCloseInterf(MouseEvent event) {
         labordinclose.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void closeInterf(MouseEvent event) {
         Stage stage = (Stage) labordinclose.getScene().getWindow();
        stage.close();
    }

  
    
    
    
    
    public void onkey(){
      txtrech.setOnKeyReleased(e1 ->{
        if (txtrech.getSelectedText()=="") {
          
             //   ref();
           
            
        }else{
       
            data.clear();
        String rech = txtrech.getText().toLowerCase();
        
        try {
            res = con.obtenirconx().createStatement().executeQuery(m.getrech(rech));
            while (res.next()) {
                OrdinateurTable model=new OrdinateurTable(
                     res.getInt("idEquipement"),
            res.getString("marqordi"),
            res.getDouble("prix"),
           res.getString("date"),
            res.getString("etatordi"));
        
         data.add(model);
         tablordinat.setItems(data);
            }
        } catch (Exception e) {
            System.out.println("doc" + e);
        }
        
         
        }
              });
    }

    @FXML
    private void VIDER(ActionEvent event) {
        txtMarqordin.clear();
        txtPRIXordina.clear();
        txtdatAchatordina.setValue(null);
        cometatordinat.setValue("");
    }
            
            
           
      
        
          
  }

    
   
    
   
  
             

