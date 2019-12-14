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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.ConnexionEQ;
import model.ImprimenteTable;
import model.ModelImprimant;

/**
 * FXML Controller class
 *
 * @author TEAM 161
 */
public class ImprimantessController implements Initializable {

    @FXML
    private JFXTextField txtMarqimprim;
    @FXML
    private JFXTextField txtPRIXimprim;
    @FXML
    private JFXButton buttonAjimpri;
    @FXML
    private JFXButton buttMODimprim;
    @FXML
    private JFXButton buttsuppimprim;
    @FXML
    private TableView<ImprimenteTable> tablimprim;
    @FXML
    private TableColumn<ImprimenteTable, Integer> idimprimente;
    @FXML
    private TableColumn<ImprimenteTable, String> Marqueimprimente;
    @FXML
    private TableColumn<ImprimenteTable, Double> priximprim;
    @FXML
    private TableColumn<ImprimenteTable, Date> dateachatimpri;
    @FXML
    private TableColumn<ImprimenteTable, String> etatimprim;
    @FXML
    private JFXTextField txtrech;
    @FXML
    private JFXDatePicker txtdatAchatimprim;
   
    @FXML
    private JFXComboBox<String> cometatimprim;
   
     
    
       
       ObservableList<String> list2 = FXCollections.observableArrayList("nouvelle", "Ancienne","En Paine");

     private ObservableList<ImprimenteTable> data;
    
    ConnexionEQ con=new  ConnexionEQ();
    

    PreparedStatement pst;
   public Statement stat;
     public  ResultSet res;
    @FXML
    private Label labimprimclose;
    @FXML
    private JFXButton VIDER;
    ModelImprimant t=new ModelImprimant();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
               cometatimprim.setItems(list2);
                //gerer la base de donnees
        try {
            
             stat = con.obtenirconx().createStatement();
             res = stat.executeQuery(t.getSelection());
            data=FXCollections.observableArrayList();
            while(res.next()){
            Integer idEquipement=res.getInt(1);
            String marqequi=res.getString(2);
            Double prix=res.getDouble(3);
            String date =res.getString(4);
            String etat=res.getString(5);
                ImprimenteTable model=new ImprimenteTable(idEquipement, marqequi, prix, date, etat);
        
         data.add(model);
          
        }
            idimprimente.setCellValueFactory(new PropertyValueFactory<>("idEquipement"));
              Marqueimprimente.setCellValueFactory(new PropertyValueFactory<>("marqequi"));
             priximprim.setCellValueFactory(new PropertyValueFactory<>("prix"));
               dateachatimpri.setCellValueFactory(new PropertyValueFactory<>("date"));
                etatimprim.setCellValueFactory(new PropertyValueFactory<>("etatimpri"));
            
          tablimprim.setItems(data);
            
           
      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
     onkey();
        
    } 
    

    @FXML
    private void ajoutimprim(ActionEvent event) {
        
        if (txtMarqimprim.getText().equals("")||txtPRIXimprim.getText().equals("")
                || cometatimprim.getSelectionModel().getSelectedItem().toString().equals("")
                ||txtdatAchatimprim.getValue().equals("")) {
            
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText("veuillez remplire tous  les champs SVP!!");
              
            alert.show();
            
        } else {
        
             
        String marque=txtMarqimprim.getText();
        String prix=txtPRIXimprim.getText();
        String etat=cometatimprim.getSelectionModel().getSelectedItem().toString();
        SimpleDateFormat stm=new SimpleDateFormat("yyyy-MM-dd");
        String datee=stm.format(Date.valueOf(txtdatAchatimprim.getValue()));
        
        
        try {
            //ref();
            PreparedStatement pst;
            ;
            
              pst =  con.obtenirconx().prepareStatement(t.getajoutordi());
              pst.setString(1, marque);
              pst.setString(2, prix);
              pst.setString(3, datee);
              pst.setString(4, etat);
              pst.executeUpdate();
              pst.close();
              ref();
            

        } catch (SQLException e) {
            
        }
         }}
        
    

    @FXML
    private void modimprom(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confiramtion de modification");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de modifier?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            try {
         String marque=txtMarqimprim.getText();
         String prix=txtPRIXimprim.getText();
         String etat=cometatimprim.getSelectionModel().getSelectedItem().toString();
         SimpleDateFormat stm=new SimpleDateFormat("yyyy-MM-dd");
         String datee=stm.format(Date.valueOf(txtdatAchatimprim.getValue()));
                
                ImprimenteTable p = tablimprim.getSelectionModel().getSelectedItem();

                pst = con.obtenirconx().prepareStatement("UPDATE equipement SET "
                               + " marqequi = ?, prix = ?, date = ?, etatimpri=? where marqequi ='"+p.getMarqequi()+"'");
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
            
         } else {
            alert.close();
        }
        
    }

    @FXML
    private void supprimimprim(ActionEvent event) {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confiramtion de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes-vous sur de supprimer?");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {

            ImprimenteTable c = (ImprimenteTable)tablimprim.getSelectionModel().getSelectedItem();

            try {
                      ref();
                      
               
                pst = con.obtenirconx().prepareStatement(t.getsupp());
                pst.setString(1, c.getMarqequi());
                pst.executeUpdate();
                
                pst.close();
                
                ref();
              clear();
              
               
               
               
            } catch (SQLException e) {
                System.out.println(e);
            }

        } else {
            alert.close();
        }
    }
    
   
    
    public  void ref() throws SQLException {
       data.clear();
          try {
             stat = con.obtenirconx().createStatement();
             res = stat.executeQuery(t.getSelection());
            data=FXCollections.observableArrayList();
             while(res.next()){
                ImprimenteTable model=new ImprimenteTable(
                        res.getInt("idEquipement"),
                        res.getString("marqequi"),
                        res.getDouble("prix"),
                       res.getString( "date"),
                        res.getString("etatimpri"));
        
         data.add(model);
          tablimprim.setItems(data);
         
        
     }
            clear();     
        } catch (Exception e) {
            System.out.println("doc" + e);    
        }
    }
    


    @FXML
    private void selectionner(MouseEvent event) {
        ImprimenteTable c = (ImprimenteTable)  tablimprim.getSelectionModel().getSelectedItem();
        txtMarqimprim.setText(c.getMarqequi());
        txtPRIXimprim.setText(c.getPrix()+"");
        cometatimprim.setValue(c.getEtatimpri());
        txtdatAchatimprim.setValue(LocalDate.parse(c.getDate()));
          
    }
    
    
    
    
      public void clear(){
        txtMarqimprim.clear();
        txtPRIXimprim.clear();
        txtdatAchatimprim.setValue(null);
        cometatimprim.setValue("");
        
     }

    @FXML
    public void onkey(){
      txtrech.setOnKeyReleased(e1 ->{
        if (txtrech.getSelectedText()=="") {
          
             //   ref();
           
            
        }else{
       
            data.clear();
        String rech = txtrech.getText().toLowerCase();
        
        try {
            res = con.obtenirconx().createStatement().executeQuery(t.getrech(rech));
            while (res.next()) {
                ImprimenteTable model=new ImprimenteTable(
                        res.getInt("idEquipement"),
                        res.getString("marqequi"),
                        res.getDouble("prix"),
                        res.getString( "date"),
                        res.getString("etatimpri"));
        
         data.add(model);
         tablimprim.setItems(data);
            }
        } catch (Exception e) {
            System.out.println("doc" + e);
        }
        
         
        }
              });
    }


    @FXML
    private void actionrech(ActionEvent event) {
         txtrech.setOnKeyReleased(e1 ->{
        if (txtrech.getSelectedText()=="") {
          
             //   ref();
           
            
        }else{
       
            data.clear();
        String rech = txtrech.getText().toLowerCase();
        String sql = "SELECT * FROM equipement where  marqequi like '" +rech+ "%'";
        try {
            res = con.obtenirconx().createStatement().executeQuery(sql);
            while (res.next()) {
                ImprimenteTable model=new ImprimenteTable(
                        res.getInt("idEquipement"),
                        res.getString("marqequi"),
                        res.getDouble("prix"),
                        res.getString( "date"),
                        res.getString("etatimpri"));
        
         data.add(model);
         tablimprim.setItems(data);
            }
        } catch (Exception e) {
            System.out.println("doc" + e);
        }
        
         
        }
              });
        
    }

    @FXML
    private void setDfClose(MouseEvent event) {
         labimprimclose.setStyle("-fx-background-color: #f4eeee;");
    }

    @FXML
    private void SetCloseInterf(MouseEvent event) {
         labimprimclose.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void closeInterf(MouseEvent event) {
         Stage stage = (Stage) labimprimclose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void VIDER(ActionEvent event) {
          txtMarqimprim.clear();
        txtPRIXimprim.clear();
        txtdatAchatimprim.setValue(null);
        cometatimprim.setValue("");
    }

   
    
}
